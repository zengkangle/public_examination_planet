package com.gcu.public_examination_planet.service;

import com.gcu.public_examination_planet.domain.WeiboComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcu.public_examination_planet.dto.WeiboCommentForShow;

import java.util.List;

/**
 * @author HealMe
 * @description 针对表【weibo_comment(微博评论)】的数据库操作Service
 * @createDate 2024-01-28 17:01:31
 */
public interface WeiboCommentService extends IService<WeiboComment> {
    void saveComment(WeiboComment weiboComment);

    List<WeiboCommentForShow> getCommentListByWeiboId(Integer weiboId);

    void increaseById(Integer weiboCommentId);

    Integer getWeiboIdByCommentId(Integer weiboCommentId);
}
