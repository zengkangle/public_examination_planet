package com.gcu.public_examination_planet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcu.public_examination_planet.domain.Course;
import com.gcu.public_examination_planet.dto.CourseForShow;

/**
 * @author HealMe
 * @description 针对表【course(课程)】的数据库操作Service
 * @createDate 2024-02-06 03:36:12
 */
public interface CourseService extends IService<Course> {

    IPage<CourseForShow> getCourseListByPage(Integer currentPage, Integer pageSize, String courseType);

    IPage<CourseForShow> getMyCourseListByPage(Integer currentPage, Integer pageSize, String courseType, Integer userId);
}
