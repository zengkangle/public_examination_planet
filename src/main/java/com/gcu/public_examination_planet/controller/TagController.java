package com.gcu.public_examination_planet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gcu.public_examination_planet.common.Result;
import com.gcu.public_examination_planet.domain.Tag;
import com.gcu.public_examination_planet.service.TagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/4 4:42
 **/
@RestController
@RequestMapping("/tag")
public class TagController {
    @Resource
    TagService tagService;

    @GetMapping("/getTagsById")
    public Result getTagsById(@RequestParam("teacherId") Integer teacherId) {
        return Result.success(tagService.list(new QueryWrapper<Tag>().eq("teacher_id",teacherId)));
    }
}
