package com.gcu.public_examination_planet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gcu.public_examination_planet.domain.Live;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcu.public_examination_planet.dto.LiveForShow;
import com.gcu.public_examination_planet.dto.LiveMsg;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @author HealMe
* @description 针对表【live(直播)】的数据库操作Service
* @createDate 2024-02-03 21:31:55
*/
public interface LiveService extends IService<Live> {
    LiveMsg myLive(Integer teacherId);
    void openLive(LiveMsg liveMsg);
    void closeLive(Integer liveId);
    IPage<LiveForShow> getUserList(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize);
}
