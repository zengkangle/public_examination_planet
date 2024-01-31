package com.gcu.public_examination_planet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.public_examination_planet.domain.WeiboCommentReply;
import com.gcu.public_examination_planet.service.WeiboCommentReplyService;
import com.gcu.public_examination_planet.mapper.WeiboCommentReplyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HealMe
 * @description 针对表【weibo_comment_reply(微博评论回复)】的数据库操作Service实现
 * @createDate 2024-01-29 23:01:25
 */
@Service
public class WeiboCommentReplyServiceImpl extends ServiceImpl<WeiboCommentReplyMapper, WeiboCommentReply>
        implements WeiboCommentReplyService{

    public void saveReply(WeiboCommentReply weiboCommentReply){
        save(weiboCommentReply);
    }

    public Integer getRecentReplyUserNameByCommentId(Integer weiboCommentId){
        QueryWrapper<WeiboCommentReply> wrapper = new QueryWrapper<>();
        wrapper.eq("weibo_comment_id",weiboCommentId).orderByDesc("weibo_comment_reply_time").last("limit 1");
        WeiboCommentReply weiboCommentReply = getOne(wrapper);
        return weiboCommentReply.getUserId();
    }

    public List<WeiboCommentReply> getReplyList(Integer weiboCommentId){
        QueryWrapper<WeiboCommentReply> wrapper = new QueryWrapper<>();
        wrapper.eq("weibo_comment_id",weiboCommentId).orderByDesc("weibo_comment_reply_time");
        List<WeiboCommentReply> replyList = list(wrapper);
        return replyList;
    }
}




