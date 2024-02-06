package com.gcu.public_examination_planet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gcu.public_examination_planet.common.Result;
import com.gcu.public_examination_planet.domain.Course;
import com.gcu.public_examination_planet.domain.Video;
import com.gcu.public_examination_planet.service.CourseService;
import com.gcu.public_examination_planet.service.VideoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/7 3:28
 **/
@RestController
@RequestMapping("/video")
public class VideoController {
    @Resource
    VideoService videoService;

    @Resource
    CourseService courseService;

    /**
     * 保存课程视频
     * @param video
     * @return
     */
    @PostMapping("/saveCourseVideo")
    public Result saveCourseVideo(@RequestBody Video video) {
        videoService.save(video);
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",video.getCourseId());
        Course course = courseService.getOne(wrapper);
        if (video.getCoursePage()>course.getCoursePageAmount()){
            course.setCoursePageAmount(course.getCoursePageAmount()+1);
            courseService.updateById(course);
        }
        return Result.success("success");
    }
}
