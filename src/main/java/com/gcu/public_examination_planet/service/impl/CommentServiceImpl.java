package com.gcu.public_examination_planet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.public_examination_planet.domain.Comment;
import com.gcu.public_examination_planet.domain.User;
import com.gcu.public_examination_planet.dto.CommentForShow;
import com.gcu.public_examination_planet.mapper.CommentMapper;
import com.gcu.public_examination_planet.service.CommentService;
import com.gcu.public_examination_planet.service.UserService;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.util.EntityUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* @author HealMe
* @description 针对表【comment(评价)】的数据库操作Service实现
* @createDate 2024-02-12 18:54:00
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    @Resource
    UserService userService;

    public IPage<CommentForShow> getRateByPage(Integer currentPage,Integer pageSize,String commentType,Integer courseId,Integer teacherId){
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        if ("course".equals(commentType)){
            wrapper.eq("course_id",courseId);
        }else {
            wrapper.eq("teacher_id",teacherId);
        }
        Page<Comment> commentPage = page(new Page<>(currentPage, pageSize), wrapper);
        IPage<CommentForShow> commentForShowIPage = EntityUtils.toPage(commentPage, CommentForShow::new);
        Set<Integer> UserIds = EntityUtils.toSet(commentForShowIPage.getRecords(), CommentForShow::getUserId);
        if (UserIds != null && UserIds.size() > 0){
            List<User> userList = userService.list(new QueryWrapper<User>().in("user_id", UserIds));
            Map<Integer, User> userMap = EntityUtils.toMap(userList, User::getUserId, e -> e);
            for (CommentForShow commentForShow : commentForShowIPage.getRecords()) {
                commentForShow.setUserName(userMap.get(commentForShow.getUserId()).getUserName());
                commentForShow.setUserAvatarUrl(userMap.get(commentForShow.getUserId()).getUserAvatarUrl());
            }
        }
        return commentForShowIPage;
    }
}




