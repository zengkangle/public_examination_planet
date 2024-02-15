package com.gcu.public_examination_planet.controller;

import com.gcu.public_examination_planet.common.Result;
import com.gcu.public_examination_planet.domain.Live;
import com.gcu.public_examination_planet.domain.Teacher;
import com.gcu.public_examination_planet.dto.LiveMsg;
import com.gcu.public_examination_planet.service.LiveService;
import com.gcu.public_examination_planet.service.TeacherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/14 16:57
 **/
@RestController
@RequestMapping("/live")
public class LiveController {
    @Resource
    LiveService liveService;

    @Resource
    TeacherService teacherService;

    @Value("${live-room.open-live.flv-url}")
    String flvUrl;

    /**
     * 获取教师个人直播信息
     * @param teacherId
     * @return
     */
    @GetMapping("/getMyLive")
    public Result getMyLive(@RequestParam("teacherId") Integer teacherId) {
        return Result.success(liveService.myLive(teacherId));
    }

    /**
     * 开播
     * @param LiveMsg
     * @return
     */
    @PostMapping("/openLive")
    public Result openLive(@RequestBody LiveMsg LiveMsg) {
        liveService.openLive(LiveMsg);
        return Result.success("success");
    }

    /**
     * 下播
     * @param liveId
     * @return
     */
    @GetMapping("/closeLive")
    public Result closeLive(@RequestParam("liveId") Integer liveId) {
        liveService.closeLive(liveId);
        return Result.success("success");
    }

    /**
     * 分页获取直播列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/getLiveList")
    public Result getLiveList(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize) {
        return Result.success(liveService.getUserList(currentPage,pageSize));
    }

    /**
     * 获取直播地址
     * @param liveId
     * @return
     */
    @GetMapping("/getLiveResource")
    public Result getLiveResource(@RequestParam("liveId") Integer liveId) {
        Live live = liveService.getById(liveId);
        Teacher teacher = teacherService.getById(live.getTeacherId());
        String liveResource = flvUrl + teacher.getLiveCode() + ".flv";
        return Result.success(liveResource);
    }
}
