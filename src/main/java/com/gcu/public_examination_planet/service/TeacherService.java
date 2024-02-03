package com.gcu.public_examination_planet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gcu.public_examination_planet.domain.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcu.public_examination_planet.dto.TeacherForShow;

/**
* @author HealMe
* @description 针对表【teacher(教师)】的数据库操作Service
* @createDate 2024-02-03 21:13:35
*/
public interface TeacherService extends IService<Teacher> {
    IPage<TeacherForShow> getTeacherListByPage(Integer currentPage, Integer pageSize);
}
