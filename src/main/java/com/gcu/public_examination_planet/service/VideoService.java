package com.gcu.public_examination_planet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gcu.public_examination_planet.domain.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcu.public_examination_planet.dto.VideoForShow;

/**
* @author HealMe
* @description 针对表【video(视频)】的数据库操作Service
* @createDate 2024-02-07 03:23:57
*/
public interface VideoService extends IService<Video> {
    IPage<VideoForShow> getCheckVideoList(Integer currentPage,Integer pageSize, Integer teacherId);
}
