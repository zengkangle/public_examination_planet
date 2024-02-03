package com.gcu.public_examination_planet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.public_examination_planet.domain.Live;
import com.gcu.public_examination_planet.service.LiveService;
import com.gcu.public_examination_planet.mapper.LiveMapper;
import org.springframework.stereotype.Service;

/**
* @author HealMe
* @description 针对表【live(直播)】的数据库操作Service实现
* @createDate 2024-02-03 21:31:55
*/
@Service
public class LiveServiceImpl extends ServiceImpl<LiveMapper, Live>
    implements LiveService{

}




