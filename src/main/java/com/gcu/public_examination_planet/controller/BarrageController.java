package com.gcu.public_examination_planet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gcu.public_examination_planet.common.Result;
import com.gcu.public_examination_planet.domain.Barrage;
import com.gcu.public_examination_planet.service.BarrageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/11 14:10
 **/
@RestController
@RequestMapping("/barrage")
public class BarrageController {
    @Resource
    BarrageService barrageService;

    /**
     * 保存弹幕信息
     * @param barrage
     * @return
     */
    @PostMapping("/saveBarrage")
    public Result saveBarrage(@RequestBody Barrage barrage) {
        return Result.success(barrageService.save(barrage));
    }

    /**
     * 获取视频弹幕列表
     * @param videoId
     * @return
     */
    @GetMapping("/getBarrageList")
    public Result getBarrageList(@RequestParam("videoId") Integer videoId) {
        return Result.success(barrageService.list(new QueryWrapper<Barrage>().eq("video_id",videoId).orderByAsc("time").orderByDesc("createtime")));
    }
}
