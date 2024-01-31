package com.gcu.public_examination_planet.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.public_examination_planet.domain.Chunk;
import com.gcu.public_examination_planet.domain.Files;
import com.gcu.public_examination_planet.exception.ServiceException;
import com.gcu.public_examination_planet.mapper.FilesMapper;
import com.gcu.public_examination_planet.service.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
* @author HealMe
* @description 针对表【files】的数据库操作Service实现
* @createDate 2024-01-20 01:54:08
*/
@Service
public class FilesServiceImpl extends ServiceImpl<FilesMapper, Files>
    implements FilesService{
    @Value("${server.port}")
    private String serverPort;
    @Value("${files.imageUpload.path}")
    private String imageRootPath;
    @Value("${files.videoUpload.path}")
    private String videoRootPath;
    @Resource
    private FilesMapper filesMapper;

    /**
     * 上传接口
     * @param file  前端传过来的文件
     * @throws IOException
     */
    public String imageUpload(MultipartFile file) throws IOException {
        File parentPath = new File(imageRootPath);
        if (!parentPath.exists())//判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
            parentPath.mkdirs();
        String uuid = IdUtil.fastSimpleUUID();//定义文件的唯一标识
        String originalFilename = file.getOriginalFilename();
        String extName = FileUtil.extName(originalFilename);//获取文件类型
        String uploadFileName = uuid+"."+extName;
        File uploadFile = new File(parentPath + "\\" + uploadFileName);//文件的上传路径
        file.transferTo(uploadFile);//上传文件到磁盘
        String md5 = SecureUtil.md5(uploadFile);//获取文件的md5
        Files dbFile = getFileByMd5(md5);//从数据库查询是否存在相同的文件
        String fileUrl;
        if (dbFile != null){
            uploadFile.delete();
            System.out.println("发现相同文件，删除成功");
            fileUrl=dbFile.getFileUrl();
            System.out.println(fileUrl);
            return fileUrl;
        } else{
            fileUrl ="http://localhost:"+serverPort+"/files/imageDownload/"+uploadFileName;
            Files files = new Files();
            files.setFileUrl(fileUrl);
            files.setMd5(md5);
            filesMapper.insert(files);
            System.out.println(fileUrl);
            return fileUrl;
        }
    }
    /**
     * 下载接口
     * @param uploadFileName
     * @param response
     */
    public void imageDownload(String uploadFileName, HttpServletResponse response) throws Exception{
        //根据文件的唯一标识码获取文件
        File uploadPath = new File(imageRootPath + "\\" + uploadFileName);
        //设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(uploadFileName,"UTF-8"));
        response.setContentType("application/octet-stream");
        //读取文件的字节流
        os.write(FileUtil.readBytes(uploadPath));
        os.flush();
        os.close();
    }

    private Files getFileByMd5(String md5){
        QueryWrapper<Files> filesQueryWrapper = new QueryWrapper<>();
        filesQueryWrapper.eq("md5",md5);
        Files one = filesMapper.selectOne(filesQueryWrapper);
        return one;
    }


    /**
     * 分片上传
     * 处理文件上传POST请求
     * 将上传的文件存放到服务器内
     *
     * @param chunk    文件块
     * @param response 响应
     * @return 上传响应状态
     * * 每一个上传块都会包含如下分块信息：
     * * chunkNumber: 当前块的次序，第一个块是 1，注意不是从 0 开始的。
     * * totalChunks: 文件被分成块的总数。
     * * chunkSize: 分块大小，根据 totalSize 和这个值你就可以计算出总共的块数。注意最后一块的大小可能会比这个要大。
     * * currentChunkSize: 当前块的大小，实际大小。
     * * totalSize: 文件总大小。
     * * identifier: 这个就是每个文件的唯一标示。
     * * filename: 文件名。
     * * relativePath: 文件夹上传的时候文件的相对路径属性。
     * * 一个分块可以被上传多次，当然这肯定不是标准行为，但是在实际上传过程中是可能发生这种事情的，这种重传也是本库的特性之一。
     * *
     * * 根据响应码认为成功或失败的：
     * * 200 文件上传完成
     * * 201 文加快上传成功
     * * 500 第一块上传失败，取消整个文件上传
     * * 507 服务器出错自动重试该文件块上传
     */

    public String videoUpload(Chunk chunk, HttpServletResponse response) {
        // 设置

        File uploadFile = new File(videoRootPath, chunk.getFilename());
        if (!uploadFile.getParentFile().exists()) {
            //如果不存在，就创建一个这个路径的文件夹。
            uploadFile.getParentFile().mkdirs();
        }
        //第一个块,则新建文件
        if (chunk.getChunkNumber() == 1 && !uploadFile.exists()) {
            try {
                uploadFile.createNewFile();
            }
            catch (IOException e) {
                throw  new ServiceException("500","上传失败！");
            }
        }

        //进行写文件操作
        try (
                //将块文件写入文件中
                InputStream fos = chunk.getFile().getInputStream();
                RandomAccessFile raf = new RandomAccessFile(uploadFile, "rw")
        ) {
            int len = -1;
            byte[] buffer = new byte[1024];
            raf.seek((chunk.getChunkNumber() - 1) * chunk.getChunkSize());
            while ((len = fos.read(buffer)) != -1) {
                raf.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (chunk.getChunkNumber() == 1) {
                uploadFile.delete();
            }
            throw new ServiceException("507","服务器出错自动重试该文件块上传");
        }
        if (chunk.getChunkNumber().equals(chunk.getTotalChunks())) {
            //  向数据库中保存上传信息
            String videoNameText = chunk.getFilename();
            // 获取文件后缀
            String extName = videoNameText.substring(videoNameText.lastIndexOf(".") + 1).toLowerCase();
            String uuid = IdUtil.fastSimpleUUID();//定义文件的唯一标识
            String newVideoName = uuid + "." + extName;
            // 把已保存的原文件名文件改成新文件名
            try {
                java.nio.file.Files.move(Paths.get(videoRootPath + "/" + videoNameText), Paths.get(videoRootPath + "/" + newVideoName));
            }
            catch (IOException e) {
                return e.toString();
            }

            File newUploadFile = new File(videoRootPath + "/" + newVideoName);
            String md5 = SecureUtil.md5(newUploadFile);//获取文件的md5
            Files dbFile = getFileByMd5(md5);//从数据库查询是否存在相同的文件
            String videoUrl;
            if (dbFile != null){
                newUploadFile.delete();
                System.out.println("发现相同文件，删除成功");
                videoUrl=dbFile.getFileUrl();
                System.out.println(videoUrl);
                return videoUrl;
            } else{
                videoUrl ="http://localhost:8009/files/playVideo/"+newVideoName;
                Files files = new Files();
                files.setFileUrl(videoUrl);
                files.setMd5(md5);
                filesMapper.insert(files);
                System.out.println(videoUrl);
                return videoUrl;
            }

        } else {
            return "ok";
        }
    }

    //查询视频流的接口
    public void playVideo(HttpServletRequest request, HttpServletResponse response,String videoName) throws Exception {
        String videoUploadPath = videoRootPath + "\\" + videoName;
        Path path = Paths.get(videoUploadPath);

        if (java.nio.file.Files.exists(path)) {
            String mimeType = java.nio.file.Files.probeContentType(path);
            if (StringUtils.hasText(mimeType)) {
                response.setContentType(mimeType);
            }

            // 设置支持部分请求（范围请求）的 'Accept-Ranges' 响应头
            response.setHeader("Accept-Ranges", "bytes");

            // 从请求头中获取请求的视频片段的范围（如果提供）
            long startByte = 0;
            long endByte = java.nio.file.Files.size(path) - 1;
            String rangeHeader = request.getHeader("Range");
            if (rangeHeader != null && rangeHeader.startsWith("bytes=")) {
                String[] range = rangeHeader.substring(6).split("-");
                startByte = Long.parseLong(range[0]);
                if (range.length == 2) {
                    endByte = Long.parseLong(range[1]);
                }
            }

            // 设置 'Content-Length' 响应头，指示正在发送的视频片段的大小
            long contentLength = endByte - startByte + 1;
            response.setHeader("Content-Length", String.valueOf(contentLength));

            // 设置 'Content-Range' 响应头，指示正在发送的视频片段的范围
            response.setHeader("Content-Range", "bytes " + startByte + "-" + endByte + "/" + java.nio.file.Files.size(path));

            // 设置响应状态为 '206 Partial Content'
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

            // 使用 'RangeFileChannel' 进行视频片段的传输，以高效地只读取文件的请求部分
            ServletOutputStream outputStream = response.getOutputStream();
            try (
                    RandomAccessFile file = new RandomAccessFile(path.toFile(), "r");
                    FileChannel fileChannel = file.getChannel()
            ) {
                fileChannel.transferTo(startByte, contentLength, Channels.newChannel(outputStream));
            } finally {
                outputStream.close();
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        }

    }

}




