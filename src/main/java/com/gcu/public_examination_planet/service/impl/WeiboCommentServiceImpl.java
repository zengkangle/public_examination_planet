package com.gcu.public_examination_planet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.public_examination_planet.domain.WeiboComment;
import com.gcu.public_examination_planet.mapper.WeiboCommentMapper;
import com.gcu.public_examination_planet.service.WeiboCommentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HealMe
 * @description 针对表【weibo_comment(微博评论)】的数据库操作Service实现
 * @createDate 2024-01-28 17:01:31
 */
@Service
public class WeiboCommentServiceImpl extends ServiceImpl<WeiboCommentMapper, WeiboComment>
        implements WeiboCommentService{

    public void saveComment(WeiboComment weiboComment){
        save(weiboComment);
    }

    public List<WeiboComment> getCommentListByWeiboId(Integer weiboId){
        QueryWrapper<WeiboComment> wrapper = new QueryWrapper<>();
        wrapper.eq("weibo_id",weiboId).orderByDesc("weibo_comment_time");
        return list(wrapper);
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




