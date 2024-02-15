package com.gcu.public_examination_planet.controller;

import cn.hutool.core.util.NumberUtil;
import com.gcu.public_examination_planet.common.Result;
import com.gcu.public_examination_planet.domain.Comment;
import com.gcu.public_examination_planet.domain.Course;
import com.gcu.public_examination_planet.domain.Teacher;
import com.gcu.public_examination_planet.service.CommentService;
import com.gcu.public_examination_planet.service.CourseService;
import com.gcu.public_examination_planet.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/12 18:54
 **/
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    CommentService commentService;

    @Resource
    CourseService courseService;

    @Resource
    TeacherService teacherService;

    /**
     * 保存评价信息
     * @param comment
     * @return
     */
    @PostMapping("/saveComment")
    public Result saveComment(@RequestBody Comment comment) {
        commentService.save(comment);
        if ("course".equals(comment.getCommentType())){
            Course course = courseService.getById(comment.getCourseId());
            int newCourseRateCount = course.getCourseRateCount()+1;
            Double CourseRateAll = course.getCourseRate() * course.getCourseRateCount();
            Double div = NumberUtil.div(CourseRateAll + comment.getCommentRate(),(newCourseRateCount * 1.0),1);
            course.setCourseRateCount(newCourseRateCount);
            course.setCourseRate(div);
            courseService.updateById(course);
        } else {
            Teacher teacher = teacherService.getById(comment.getTeacherId());
            int newTeacherRateCount = teacher.getTeacherRateCount()+1;
            Double teacherRateAll = teacher.getTeacherRate() * teacher.getTeacherRateCount();
            Double div = NumberUtil.div(teacherRateAll + comment.getCommentRate(),(newTeacherRateCount * 1.0), 1);
            teacher.setTeacherRateCount(newTeacherRateCount);
            teacher.setTeacherRate(div);
            teacherService.updateById(teacher);
        }
        return Result.success("");
    }

    /**
     * 获取评论列表
     * @param currentPage
     * @param pageSize
     * @param commentType
     * @param courseId
     * @param teacherId
     * @return
     */
    @GetMapping("/getRateByPage")
    public Result getRateByPage(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize,@RequestParam("commentType") String commentType, @RequestParam("courseId") Integer courseId , @RequestParam("teacherId") Integer teacherId) {
        return Result.success(commentService.getRateByPage(currentPage,pageSize,commentType,courseId,teacherId));
    }
}
