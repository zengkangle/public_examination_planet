package com.gcu.public_examination_planet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.public_examination_planet.domain.User;
import com.gcu.public_examination_planet.domain.WeiboCommentReply;
import com.gcu.public_examination_planet.dto.WeiboCommentReplyForShow;
import com.gcu.public_examination_planet.service.UserService;
import com.gcu.public_examination_planet.service.WeiboCommentReplyService;
import com.gcu.public_examination_planet.mapper.WeiboCommentReplyMapper;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.util.EntityUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author HealMe
 * @description 针对表【weibo_comment_reply(微博评论回复)】的数据库操作Service实现
 * @createDate 2024-01-29 23:01:25
 */
@Service
public class WeiboCommentReplyServiceImpl extends ServiceImpl<WeiboCommentReplyMapper, WeiboCommentReply>
        implements WeiboCommentReplyService{

    @Resource
    UserService userService;

    public void saveReply(WeiboCommentReply weiboCommentReply){
        save(weiboCommentReply);
    }

    public Integer getRecentReplyUserNameByCommentId(Integer weiboCommentId){
        QueryWrapper<WeiboCommentReply> wrapper = new QueryWrapper<>();
        wrapper.eq("weibo_comment_id",weiboCommentId).orderByDesc("weibo_comment_reply_time").last("limit 1");
        WeiboCommentReply weiboCommentReply = getOne(wrapper);
        return weiboCommentReply.getUserId();
    }

    public List<WeiboCommentReplyForShow> getReplyList(Integer weiboCommentId){
        List<WeiboCommentReplyForShow> weiboCommentReplyForShows = new ArrayList<>();
        List<WeiboCommentReply> replyList = list(new QueryWrapper<WeiboCommentReply>().eq("weibo_comment_id", weiboCommentId).orderByDesc("weibo_comment_reply_time"));
        Set<Integer> userIds = EntityUtils.toSet(replyList, WeiboCommentReply::getUserId);
        if (userIds != null && userIds.size() > 0){
            List<User> userList = userService.list(new QueryWrapper<User>().in("user_id", userIds));
            Map<Integer, User> userMap = EntityUtils.toMap(userList, User::getUserId, e -> e);
            Set<Integer> targetIds = EntityUtils.toSet(replyList, WeiboCommentReply::getWeiboCommentReplyTargetId);
            Map<Integer, User> targetMap = new HashMap<>();
            if (targetIds != null && targetIds.size() > 0){
                List<User> targetList = userService.list(new QueryWrapper<User>().in("user_id", targetIds));
                targetMap = EntityUtils.toMap(targetList, User::getUserId, e -> e);
            }
            for (WeiboCommentReply weiboCommentReply : replyList) {
                WeiboCommentReplyForShow weiboCommentReplyForShow = new WeiboCommentReplyForShow();
                BeanUtil.copyProperties(weiboCommentReply,weiboCommentReplyForShow);
                weiboCommentReplyForShow.setUserName(userMap.get(weiboCommentReplyForShow.getUserId()).getUserName());
                weiboCommentReplyForShow.setUserLevel(userMap.get(weiboCommentReplyForShow.getUserId()).getUserLevel());
                if (weiboCommentReplyForShow.getWeiboCommentReplyTargetId() != null){
                    weiboCommentReplyForShow.setTargetUserName(targetMap.get(weiboCommentReplyForShow.getWeiboCommentReplyTargetId()).getUserName());
                    weiboCommentReplyForShow.setTargetUserLevel(targetMap.get(weiboCommentReplyForShow.getWeiboCommentReplyTargetId()).getUserLevel());
                }
                weiboCommentReplyForShows.add(weiboCommentReplyForShow);
            }
        }
        return weiboCommentReplyForShows;
    }
}




