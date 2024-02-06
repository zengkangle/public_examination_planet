package com.gcu.public_examination_planet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.public_examination_planet.domain.Video;
import com.gcu.public_examination_planet.service.VideoService;
import com.gcu.public_examination_planet.mapper.VideoMapper;
import org.springframework.stereotype.Service;

/**
* @author HealMe
* @description 针对表【video(视频)】的数据库操作Service实现
* @createDate 2024-02-07 03:23:57
*/
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
    implements VideoService{

}




