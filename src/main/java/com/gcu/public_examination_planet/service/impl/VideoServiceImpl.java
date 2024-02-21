package com.gcu.public_examination_planet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.public_examination_planet.domain.Course;
import com.gcu.public_examination_planet.domain.Video;
import com.gcu.public_examination_planet.dto.VideoForShow;
import com.gcu.public_examination_planet.service.CourseService;
import com.gcu.public_examination_planet.service.VideoService;
import com.gcu.public_examination_planet.mapper.VideoMapper;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.util.EntityUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* @author HealMe
* @description 针对表【video(视频)】的数据库操作Service实现
* @createDate 2024-02-07 03:23:57
*/
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
    implements VideoService{
    @Resource
    CourseService courseService;
    public IPage<VideoForShow> getCheckVideoList(Integer currentPage, Integer pageSize, Integer teacherId){
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        if (teacherId != null){
            List<Course> courseList = courseService.list(new QueryWrapper<Course>().eq("teacher_id", teacherId));
            Set<Integer> courseIds = EntityUtils.toSet(courseList, Course::getCourseId);
            wrapper.in("course_id",courseIds);
        }
        wrapper.orderByDesc("create_time");
        Page<Video> videoPage = this.page(new Page<>(currentPage, pageSize), wrapper);
        IPage<VideoForShow> videoForShowIPage = EntityUtils.toPage(videoPage, VideoForShow::new);
        Set<Integer> courseIds = EntityUtils.toSet(videoForShowIPage.getRecords(), VideoForShow::getCourseId);
        if (courseIds != null && courseIds.size() > 0){
            List<Course> courseList = courseService.list(new QueryWrapper<Course>().in("course_id", courseIds));
            Map<Integer, Course> courseMap = EntityUtils.toMap(courseList, Course::getCourseId, e -> e);
            for (VideoForShow videoForShow : videoForShowIPage.getRecords()) {
                videoForShow.setCourseTitle(courseMap.get(videoForShow.getCourseId()).getCourseTitle());
            }
        }
        return videoForShowIPage;
    }
}




