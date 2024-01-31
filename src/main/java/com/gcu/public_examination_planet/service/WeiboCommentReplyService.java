package com.gcu.public_examination_planet.service;

import com.gcu.public_examination_planet.domain.WeiboCommentReply;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author HealMe
 * @description 针对表【weibo_comment_reply(微博评论回复)】的数据库操作Service
 * @createDate 2024-01-29 23:01:25
 */
public interface WeiboCommentReplyService extends IService<WeiboCommentReply> {

    void saveReply(WeiboCommentReply weiboCommentReply);

    Integer getRecentReplyUserNameByCommentId(Integer weiboCommentId);

    List<WeiboCommentReply> getReplyList(Integer weiboCommentId);
}
