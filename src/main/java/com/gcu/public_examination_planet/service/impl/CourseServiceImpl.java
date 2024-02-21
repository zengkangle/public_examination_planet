package com.gcu.public_examination_planet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.public_examination_planet.domain.*;
import com.gcu.public_examination_planet.dto.CourseForShow;
import com.gcu.public_examination_planet.mapper.CourseMapper;
import com.gcu.public_examination_planet.service.*;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.util.EntityUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author HealMe
 * @description 针对表【course(课程)】的数据库操作Service实现
 * @createDate 2024-02-06 03:36:12
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
        implements CourseService{

    @Resource
    TagService tagService;

    @Resource
    TeacherService teacherService;

    @Resource
    UserService userService;

    @Resource
    OrdersService ordersService;

    public IPage<CourseForShow> getCourseListByPage(Integer currentPage, Integer pageSize,String courseType,Integer teacherId){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        if (courseType == null || "".equals(courseType)){
            wrapper.eq("teacher_id",teacherId).orderByDesc("create_time");
        }else {
            wrapper.eq("course_type",courseType).eq("course_status","上架").orderByDesc("create_time");
        }
        Page<Course> coursePage = this.page(new Page<>(currentPage, pageSize), wrapper);
        IPage<CourseForShow> courseForShowIPage = EntityUtils.toPage(coursePage, CourseForShow::new);
        Set<Integer> courseIds = EntityUtils.toSet(courseForShowIPage.getRecords(), CourseForShow::getCourseId);
        Set<Integer> teacherIds = EntityUtils.toSet(courseForShowIPage.getRecords(), CourseForShow::getTeacherId);
        if (courseIds.size()>0){
            List<Tag> tagList = tagService.list(new QueryWrapper<Tag>().in("course_id", courseIds));
            Map<Integer, List<Tag>> tagMap = EntityUtils.groupBy(tagList, Tag::getCourseId);
            List<Teacher> teacherList = teacherService.list(new QueryWrapper<Teacher>().in("teacher_id", teacherIds));
            Map<Integer, Teacher> teacherMap = EntityUtils.toMap(teacherList, Teacher::getTeacherId, e -> e);
            List<User> userList = userService.list(new QueryWrapper<User>().in("teacher_id", teacherIds));
            Map<Integer, User> userMap = EntityUtils.toMap(userList, User::getTeacherId, e -> e);
            for (CourseForShow courseForShow : courseForShowIPage.getRecords()) {
                List<String> tagContentList = new ArrayList<String>();
                if (tagMap.get(courseForShow.getCourseId())!=null && tagMap.get(courseForShow.getCourseId()).size()>0){
                    for (Tag tag : tagMap.get(courseForShow.getCourseId())) {
                        tagContentList.add(tag.getTagContent());
                    }
                }
                courseForShow.setTags(tagContentList);
                courseForShow.setTeacherDescribe(teacherMap.get(courseForShow.getTeacherId()).getTeacherDescribe());
                courseForShow.setTeacherRateCount(teacherMap.get(courseForShow.getTeacherId()).getTeacherRateCount());
                courseForShow.setTeacherRate(teacherMap.get(courseForShow.getTeacherId()).getTeacherRate());
                courseForShow.setUserName(userMap.get(courseForShow.getTeacherId()).getUserName());
                courseForShow.setUserAvatarUrl(userMap.get(courseForShow.getTeacherId()).getUserAvatarUrl());
            }
        }
        return courseForShowIPage;
    }

    public IPage<CourseForShow> getMyCourseListByPage(Integer currentPage, Integer pageSize, String courseType, Integer userId){
        QueryWrapper<Orders> ordersQueryWrapper = new QueryWrapper<>();
        ordersQueryWrapper.eq("user_id",userId).eq("order_type","course").eq("order_status","已支付").orderByDesc("pay_time");
        List<Orders> ordersList = ordersService.list(ordersQueryWrapper);
        if (ordersList != null && ordersList.size() > 0){
            Set<Integer> courseIds = EntityUtils.toSet(ordersList, Orders::getCourseId);
            Page<Course> coursePage = page(new Page<>(currentPage, pageSize), new QueryWrapper<Course>().in("course_id", courseIds).eq("course_type",courseType));
            IPage<CourseForShow> courseForShowIPage = EntityUtils.toPage(coursePage, CourseForShow::new);
            Set<Integer> teacherIds = EntityUtils.toSet(courseForShowIPage.getRecords(), CourseForShow::getTeacherId);
            if (teacherIds != null && teacherIds.size() > 0){
                List<User> userList = userService.list(new QueryWrapper<User>().in("teacher_id", teacherIds));
                Map<Integer, User> userMap = EntityUtils.toMap(userList, User::getTeacherId, e -> e);
                for (CourseForShow courseForShow : courseForShowIPage.getRecords()) {
                    courseForShow.setUserName(userMap.get(courseForShow.getTeacherId()).getUserName());
                    courseForShow.setUserAvatarUrl(userMap.get(courseForShow.getTeacherId()).getUserAvatarUrl());
                }
            }
            return courseForShowIPage;
        } else {
            return new Page<CourseForShow>();
        }

    }

}




