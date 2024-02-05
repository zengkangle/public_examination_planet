package com.gcu.public_examination_planet.controller;

import cn.hutool.core.bean.BeanUtil;
import com.gcu.public_examination_planet.common.Result;
import com.gcu.public_examination_planet.domain.Course;
import com.gcu.public_examination_planet.domain.Tag;
import com.gcu.public_examination_planet.service.CourseService;
import com.gcu.public_examination_planet.service.TagService;
import com.gcu.public_examination_planet.vo.CreateCourse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/6 3:39
 **/
@RestController
@RequestMapping("/course")
public class CourseController {
    @Resource
    CourseService courseService;

    @Resource
    TagService tagService;

    /**
     * 创建课程
     * @param createCourse
     * @return
     */
    @PostMapping("/createCourse")
    public Result createCourse(@RequestBody CreateCourse createCourse) {
        Course course = new Course();
        BeanUtil.copyProperties(createCourse,course);
        courseService.save(course);
        if (createCourse.getTags() != null && createCourse.getTags().size()>0){
            for (String tagContent : createCourse.getTags()) {
                Tag tag = new Tag();
                tag.setTagContent(tagContent);
                tag.setTagType("course");
                tag.setCourseId(course.getCourseId());
                tagService.save(tag);
            }
        }
        return Result.success("success");
    }
}
