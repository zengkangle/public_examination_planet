package com.gcu.public_examination_planet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.public_examination_planet.domain.Course;
import com.gcu.public_examination_planet.domain.Tag;
import com.gcu.public_examination_planet.dto.CourseForShow;
import com.gcu.public_examination_planet.mapper.CourseMapper;
import com.gcu.public_examination_planet.service.CourseService;
import com.gcu.public_examination_planet.service.TagService;
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

    public IPage<CourseForShow> getCourseListByPage(Integer currentPage, Integer pageSize){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        Page<Course> coursePage = this.page(new Page<>(currentPage, pageSize), wrapper);
        IPage<CourseForShow> courseForShowIPage = EntityUtils.toPage(coursePage, CourseForShow::new);
        Set<Integer> courseIds = EntityUtils.toSet(courseForShowIPage.getRecords(), CourseForShow::getCourseId);
        if (courseIds.size()>0){
            List<Tag> tagList = tagService.list(new QueryWrapper<Tag>().in("course_id", courseIds));
            Map<Integer, List<Tag>> tagMap = EntityUtils.groupBy(tagList, Tag::getCourseId);
            for (CourseForShow courseForShow : courseForShowIPage.getRecords()) {
                List<String> tagContentList = new ArrayList<String>();
                if (tagMap.get(courseForShow.getCourseId())!=null && tagMap.get(courseForShow.getCourseId()).size()>0){
                    for (Tag tag : tagMap.get(courseForShow.getCourseId())) {
                        tagContentList.add(tag.getTagContent());
                    }
                }
                courseForShow.setTags(tagContentList);
            }
        }
        return courseForShowIPage;
    }
}




