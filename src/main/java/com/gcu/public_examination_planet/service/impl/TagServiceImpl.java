package com.gcu.public_examination_planet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.public_examination_planet.domain.Tag;
import com.gcu.public_examination_planet.service.TagService;
import com.gcu.public_examination_planet.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author HealMe
* @description 针对表【tag(标签)】的数据库操作Service实现
* @createDate 2024-02-04 02:08:18
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




