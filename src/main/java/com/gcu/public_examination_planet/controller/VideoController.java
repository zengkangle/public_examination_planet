package com.gcu.public_examination_planet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gcu.public_examination_planet.common.Result;
import com.gcu.public_examination_planet.domain.Course;
import com.gcu.public_examination_planet.domain.Video;
import com.gcu.public_examination_planet.service.CourseService;
import com.gcu.public_examination_planet.service.VideoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

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
        video.setCreateTime(new Date());
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",video.getCourseId());
        Course course = courseService.getOne(wrapper);
        if (video.getCoursePage()<=course.getCoursePageAmount()){
            video.setVideoStatus("审核中");
            videoService.update(video,new QueryWrapper<Video>().eq("course_id",video.getCourseId()).eq("course_page",video.getCoursePage()));
        }else {
            course.setCoursePageAmount(course.getCoursePageAmount()+1);
            videoService.save(video);
            courseService.updateById(course);
        }
        return Result.success("success");
    }

    /**
     * 分页获取审核视频列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/getCheckVideoList")
    public Result getCheckVideoList(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize) {
        return Result.success(videoService.getCheckVideoList(currentPage,pageSize));
    }

    /**
     * 审核视频
     * @param video
     * @return
     */
    @PostMapping("/updateVideoStatus")
    public Result updateVideoStatus(@RequestBody Video video) {
        return Result.success(videoService.updateById(video));
    }

    /**
     * 根据课程id获取视频列表
     * @param courseId
     * @return
     */
    @GetMapping("/getCourseVideoList")
    public Result getCourseVideoList(@RequestParam("courseId") Integer courseId) {
        return Result.success(videoService.list(new QueryWrapper<Video>().eq("course_id",courseId).eq("video_status","通过").orderByAsc("course_page")));
    }

    /**
     * 获取课程的第一个视频
     * @param courseId
     * @return
     */
    @GetMapping("/getFirstVideo")
    public Result getFirstVideo(@RequestParam("courseId") Integer courseId) {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId).eq("video_status","通过").orderByAsc("course_page").last("limit 1");
        return Result.success(videoService.getOne(wrapper));
    }
}
