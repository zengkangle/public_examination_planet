package com.gcu.public_examination_planet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.public_examination_planet.domain.Tag;
import com.gcu.public_examination_planet.domain.Teacher;
import com.gcu.public_examination_planet.domain.User;
import com.gcu.public_examination_planet.dto.TeacherForShow;
import com.gcu.public_examination_planet.mapper.TeacherMapper;
import com.gcu.public_examination_planet.service.TagService;
import com.gcu.public_examination_planet.service.TeacherService;
import com.gcu.public_examination_planet.service.UserService;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.util.EntityUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* @author HealMe
* @description 针对表【teacher(教师)】的数据库操作Service实现
* @createDate 2024-02-03 21:13:35
*/
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService{

    @Resource
    TagService tagService;

    @Resource
    UserService userService;
    public IPage<TeacherForShow> getTeacherListByPage(Integer currentPage, Integer pageSize){
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        Page<Teacher> teacherPage = this.page(new Page<>(currentPage, pageSize), wrapper);
        IPage<TeacherForShow> teacherForShowIPage = EntityUtils.toPage(teacherPage, TeacherForShow::new);
        Set<Integer> teacherIds = EntityUtils.toSet(teacherForShowIPage.getRecords(), TeacherForShow::getTeacherId);
        if (teacherIds.size()>0){
            List<Tag> tagList = tagService.list(new QueryWrapper<Tag>().in("teacher_id", teacherIds));
            Map<Integer, List<Tag>> tagMap = EntityUtils.groupBy(tagList, Tag::getTeacherId);
            List<User> userList = userService.list(new QueryWrapper<User>().in("teacher_id", teacherIds));
            Map<Integer, User> userMap = EntityUtils.toMap(userList, User::getTeacherId, e -> e);
            for (TeacherForShow teacherForShow : teacherForShowIPage.getRecords()) {
                List<Tag> teacherTagList = tagMap.get(teacherForShow.getTeacherId());
                ArrayList<String> tags = new ArrayList<>();
                if (teacherTagList != null && teacherTagList.size()>0){
                    for (Tag tag : tagMap.get(teacherForShow.getTeacherId())) {
                        tags.add(tag.getTagContent());
                    }
                }
                teacherForShow.setTags(tags);
                User user = userMap.get(teacherForShow.getTeacherId());
                teacherForShow.setUserId(user.getUserId());
                teacherForShow.setUserName(user.getUserName());
                teacherForShow.setUserGender(user.getUserGender());
            }
        }
        return teacherForShowIPage;
    }

    public IPage<TeacherForShow> getSimpleTeacherListByPage(Integer currentPage, Integer pageSize){
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("is_show",1);
        Page<Teacher> teacherPage = this.page(new Page<>(currentPage, pageSize), wrapper);
        IPage<TeacherForShow> teacherForShowIPage = EntityUtils.toPage(teacherPage, TeacherForShow::new);
        Set<Integer> teacherIds = EntityUtils.toSet(teacherForShowIPage.getRecords(), TeacherForShow::getTeacherId);
        if (teacherIds.size()>0){
            List<User> userList = userService.list(new QueryWrapper<User>().in("teacher_id", teacherIds));
            Map<Integer, User> userMap = EntityUtils.toMap(userList, User::getTeacherId, e -> e);
            for (TeacherForShow teacherForShow : teacherForShowIPage.getRecords()) {
                User user = userMap.get(teacherForShow.getTeacherId());
                teacherForShow.setUserId(user.getUserId());
                teacherForShow.setUserName(user.getUserName());
                teacherForShow.setUserGender(user.getUserGender());
            }
        }
        return teacherForShowIPage;
    }
}




