package com.gcu.public_examination_planet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gcu.public_examination_planet.domain.Chunk;
import com.gcu.public_examination_planet.domain.Files;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @author HealMe
* @description 针对表【files】的数据库操作Service
* @createDate 2024-01-20 01:54:08
*/
public interface FilesService extends IService<Files> {
    String imageUpload(MultipartFile file) throws IOException;

    void imageDownload(@PathVariable String uploadFileName, HttpServletResponse response) throws Exception;

    String videoUpload(@ModelAttribute Chunk chunk, HttpServletResponse response);
}
