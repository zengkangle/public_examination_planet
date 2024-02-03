package com.gcu.public_examination_planet.controller;

import com.gcu.public_examination_planet.common.Result;
import com.gcu.public_examination_planet.domain.Chunk;
import com.gcu.public_examination_planet.service.FilesService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author HealMe
 * @Description
 * @date 2024/1/20 上午 2:04
 **/
@RestController
@RequestMapping("/files")
public class FilesController {
    @Resource
    FilesService filesService;

    /**
     * 图片上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/imageUpload")
    public Result imageUpload(MultipartFile file) throws IOException {
        return Result.success(filesService.imageUpload(file));
    }

    /**
     * 图片下载
     * @param uploadFileName
     * @param response
     * @throws Exception
     */
    @GetMapping("/imageDownload/{uploadFileName}")
    public void imageDownload(@PathVariable String uploadFileName, HttpServletResponse response) throws Exception{
        filesService.imageDownload(uploadFileName,response);
    }

    /**
     * 视频上传（大文件分片上传）
     * @param chunk
     * @param response
     * @return
     */
    @PostMapping("/videoUpload")
    public Result videoUpload(@ModelAttribute Chunk chunk, HttpServletResponse response) {
        return Result.success(filesService.videoUpload(chunk,response));
    }

    /**
     * 视频播放
     * @param request
     * @param response
     * @param videoName
     * @throws Exception
     */
    @GetMapping("/playVideo/{videoName}")
    public void playVideo(HttpServletRequest request, HttpServletResponse response, @PathVariable String videoName) throws Exception {
        filesService.playVideo(request,response,videoName);
    }
}
