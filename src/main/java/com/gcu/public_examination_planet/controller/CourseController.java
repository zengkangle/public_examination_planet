package com.gcu.public_examination_planet.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gcu.public_examination_planet.common.Result;
import com.gcu.public_examination_planet.domain.Course;
import com.gcu.public_examination_planet.domain.Tag;
import com.gcu.public_examination_planet.dto.CourseForShow;
import com.gcu.public_examination_planet.service.CourseService;
import com.gcu.public_examination_planet.service.TagService;
import com.gcu.public_examination_planet.vo.CreateCourse;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 分页获取购买课程列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/getCourseList")
    public Result getCourseList(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize,String courseType,Integer teacherId) {
        return Result.success(courseService.getCourseListByPage(currentPage,pageSize,courseType,teacherId));
    }

    /**
     * 分页获取我的课程列表
     * @param currentPage
     * @param pageSize
     * @param courseType
     * @param userId
     * @return
     */
    @GetMapping("/getMyCourseList")
    public Result getMyCourseList(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize,String courseType, Integer userId) {
        return Result.success(courseService.getMyCourseListByPage(currentPage,pageSize,courseType,userId));
    }
    /**
     * 编辑课程信息
     * @param courseForShow
     * @return
     */
    @PostMapping("/updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseForShow courseForShow) {
        Course course = new Course();
        BeanUtil.copyProperties(courseForShow,course);
        courseService.updateById(course);
        if (courseForShow.getTags() != null){
            QueryWrapper<Tag> wrapper = new QueryWrapper<>();
            wrapper.eq("course_id",courseForShow.getCourseId());
            tagService.remove(wrapper);
            for (String tagContent : courseForShow.getTags()) {
                Tag tag = new Tag();
                tag.setTagContent(tagContent);
                tag.setCourseId(courseForShow.getCourseId());
                tag.setTagType("course");
                tagService.save(tag);
            }
        }
        return Result.success("success");
    }

    /**
     * 修改课程状态
     * @param course
     * @return
     */
    @PostMapping("/updateCourseStatus")
    public Result updateCourseStatus(@RequestBody Course course) {
        courseService.updateById(course);
        return Result.success("success");
    }
}
