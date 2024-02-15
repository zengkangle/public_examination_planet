package com.gcu.public_examination_planet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.public_examination_planet.domain.Live;
import com.gcu.public_examination_planet.domain.Tag;
import com.gcu.public_examination_planet.domain.Teacher;
import com.gcu.public_examination_planet.domain.User;
import com.gcu.public_examination_planet.dto.LiveForShow;
import com.gcu.public_examination_planet.dto.LiveMsg;
import com.gcu.public_examination_planet.service.LiveService;
import com.gcu.public_examination_planet.mapper.LiveMapper;
import com.gcu.public_examination_planet.service.TagService;
import com.gcu.public_examination_planet.service.TeacherService;
import com.gcu.public_examination_planet.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import xin.altitude.cms.common.util.EntityUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* @author HealMe
* @description 针对表【live(直播)】的数据库操作Service实现
* @createDate 2024-02-03 21:31:55
*/
@Service
public class LiveServiceImpl extends ServiceImpl<LiveMapper, Live>
    implements LiveService{

    @Value("${live-room.open-live.server-address}")
    private String serverAddress;

    @Resource
    TeacherService teacherService;

    @Resource
    TagService tagService;

    @Resource
    UserService userService;

    public LiveMsg myLive(Integer teacherId){
        LiveMsg liveMsg = new LiveMsg();
        Teacher teacher = teacherService.getById(teacherId);
        Live live = getOne(new QueryWrapper<Live>().eq("teacher_id", teacherId));
        BeanUtil.copyProperties(live,liveMsg);
        BeanUtil.copyProperties(teacher,liveMsg);
        liveMsg.setServerAddress(serverAddress);
        List<Tag> tags = tagService.list(new QueryWrapper<Tag>().eq("tag_type", "live").eq("live_id", live.getLiveId()));
        List<String> tagContentList = new ArrayList<String>();
        if (tags != null && tags.size() > 0){
            Set<String> tagset = EntityUtils.toSet(tags, Tag::getTagContent);
            tagContentList.addAll(tagset);
        }
        liveMsg.setTags(tagContentList);
        return liveMsg;
    }

    public void openLive(LiveMsg liveMsg){
        Live live = new Live();
        BeanUtil.copyProperties(liveMsg,live);
        live.setLiveStatus("开播中");
        updateById(live);
        if (liveMsg.getTags() != null && liveMsg.getTags().size() > 0){
            tagService.remove(new QueryWrapper<Tag>().eq("tag_type","live").eq("live_id",liveMsg.getLiveId()));
            for (String tagContent : liveMsg.getTags()) {
                Tag tag = new Tag();
                tag.setTagType("live");
                tag.setTagContent(tagContent);
                tag.setLiveId(liveMsg.getLiveId());
                tagService.save(tag);
            }
        }
    }
    public void closeLive(Integer liveId){
        Live live = new Live();
        live.setLiveId(liveId);
        live.setLiveStatus("未开播");
        updateById(live);
    }

    public IPage<LiveForShow> getUserList(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize){
        Page<Live> livePage = page(new Page<>(currentPage, pageSize), new QueryWrapper<Live>().eq("live_status", "开播中"));
        IPage<LiveForShow> liveForShowIPage = EntityUtils.toPage(livePage, LiveForShow::new);
        Set<Integer> teacherIds = EntityUtils.toSet(liveForShowIPage.getRecords(), LiveForShow::getTeacherId);
        if (teacherIds != null && teacherIds.size() > 0){
            List<User> userList = userService.list(new QueryWrapper<User>().in("teacher_id", teacherIds));
            Map<Integer, User> userMap = EntityUtils.toMap(userList, User::getTeacherId, e -> e);
            Set<Integer> liveIds = EntityUtils.toSet(liveForShowIPage.getRecords(), LiveForShow::getLiveId);
            List<Tag> tagList = tagService.list(new QueryWrapper<Tag>().in("live_id", liveIds));
            if (tagList != null && tagList.size() > 0){
                Map<Integer, List<Tag>> tagMap = EntityUtils.groupBy(tagList, Tag::getLiveId);
                for (LiveForShow liveForShow : liveForShowIPage.getRecords()) {
                    liveForShow.setUserName(userMap.get(liveForShow.getTeacherId()).getUserName());
                    liveForShow.setUserAvatarUrl(userMap.get(liveForShow.getTeacherId()).getUserAvatarUrl());
                    List<Tag> tags = tagMap.get(liveForShow.getLiveId());
                    if (tags != null && tags.size() > 0){
                        List<String> tagContentList = new ArrayList<>();
                        for (Tag tag : tags) {
                            tagContentList.add(tag.getTagContent());
                        }
                        liveForShow.setTags(tagContentList);
                    }
                }
            }
        }
        return liveForShowIPage;
    }
}




