package com.gcu.public_examination_planet.controller;

import com.gcu.public_examination_planet.common.Result;
import com.gcu.public_examination_planet.service.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/4 1:17
 **/
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    TeacherService teacherService;

    /**
     * 分页获取教师列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/getTeacherList")
    public Result getTeacherList(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize) {
        return Result.success(teacherService.getTeacherListByPage(currentPage,pageSize));
    }

    /**
     * 根据教师id获取教师信息
     * @param teacherId
     * @return
     */
    @GetMapping("/getTeacherById")
    public Result getTeacherById(@RequestParam("teacherId") Integer teacherId) {
        return Result.success(teacherService.getById(teacherId));
    }
}
