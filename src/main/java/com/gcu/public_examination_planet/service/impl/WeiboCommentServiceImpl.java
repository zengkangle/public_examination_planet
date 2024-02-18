package com.gcu.public_examination_planet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.public_examination_planet.domain.User;
import com.gcu.public_examination_planet.domain.WeiboComment;
import com.gcu.public_examination_planet.dto.WeiboCommentForShow;
import com.gcu.public_examination_planet.mapper.WeiboCommentMapper;
import com.gcu.public_examination_planet.service.UserService;
import com.gcu.public_examination_planet.service.WeiboCommentService;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.util.EntityUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author HealMe
 * @description 针对表【weibo_comment(微博评论)】的数据库操作Service实现
 * @createDate 2024-01-28 17:01:31
 */
@Service
public class WeiboCommentServiceImpl extends ServiceImpl<WeiboCommentMapper, WeiboComment>
        implements WeiboCommentService{

    @Resource
    UserService userService;

    public void saveComment(WeiboComment weiboComment){
        save(weiboComment);
    }

    public List<WeiboCommentForShow> getCommentListByWeiboId(Integer weiboId){
        List<WeiboCommentForShow> weiboCommentForShows = new ArrayList<>();
        List<WeiboComment> weiboCommentList = list(new QueryWrapper<WeiboComment>().eq("weibo_id", weiboId).orderByDesc("weibo_comment_time"));
        Set<Integer> userIds = EntityUtils.toSet(weiboCommentList, WeiboComment::getUserId);
        if (userIds != null && userIds.size() > 0){
            List<User> userList = userService.list(new QueryWrapper<User>().in("user_id", userIds));
            Map<Integer, User> userMap = EntityUtils.toMap(userList, User::getUserId, e -> e);
            for (WeiboComment weiboComment : weiboCommentList) {
                WeiboCommentForShow weiboCommentForShow = new WeiboCommentForShow();
                BeanUtil.copyProperties(weiboComment,weiboCommentForShow);
                weiboCommentForShow.setUserName(userMap.get(weiboCommentForShow.getUserId()).getUserName());
                weiboCommentForShow.setUserAvatarUrl(userMap.get(weiboCommentForShow.getUserId()).getUserAvatarUrl());
                weiboCommentForShow.setUserLevel(userMap.get(weiboCommentForShow.getUserId()).getUserLevel());
                weiboCommentForShows.add(weiboCommentForShow);
            }
        }
        return weiboCommentForShows;
    }

    public void increaseById(Integer weiboCommentId){
        WeiboComment comment = getById(weiboCommentId);
        comment.setWeiboCommentReplyAmount(comment.getWeiboCommentReplyAmount()+1);
        updateById(comment);
    }

    public Integer getWeiboIdByCommentId(Integer weiboCommentId){
        WeiboComment comment = getById(weiboCommentId);
        return comment.getWeiboId();
    }
}




