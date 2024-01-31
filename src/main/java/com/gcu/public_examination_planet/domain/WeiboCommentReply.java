package com.gcu.public_examination_planet.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 微博评论回复
 * @TableName weibo_comment_reply
 */
@TableName(value ="weibo_comment_reply")
@Data
public class WeiboCommentReply implements Serializable {
    /**
     * 微博评论回复id
     */
    @TableId(type = IdType.AUTO)
    private Integer weiboCommentReplyId;

    /**
     * 回复发布者id
     */
    private Integer userId;

    /**
     * 对应的微博评论id
     */
    private Integer weiboCommentId;

    /**
     * 回复对象id
     */
    private Integer weiboCommentReplyTargetId;

    /**
     * 微博评论回复时间
     */
    private Date weiboCommentReplyTime;

    /**
     * 微博回复的内容
     */
    private String weiboCommentReplyContent;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        WeiboCommentReply other = (WeiboCommentReply) that;
        return (this.getWeiboCommentReplyId() == null ? other.getWeiboCommentReplyId() == null : this.getWeiboCommentReplyId().equals(other.getWeiboCommentReplyId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getWeiboCommentId() == null ? other.getWeiboCommentId() == null : this.getWeiboCommentId().equals(other.getWeiboCommentId()))
            && (this.getWeiboCommentReplyTargetId() == null ? other.getWeiboCommentReplyTargetId() == null : this.getWeiboCommentReplyTargetId().equals(other.getWeiboCommentReplyTargetId()))
            && (this.getWeiboCommentReplyTime() == null ? other.getWeiboCommentReplyTime() == null : this.getWeiboCommentReplyTime().equals(other.getWeiboCommentReplyTime()))
            && (this.getWeiboCommentReplyContent() == null ? other.getWeiboCommentReplyContent() == null : this.getWeiboCommentReplyContent().equals(other.getWeiboCommentReplyContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWeiboCommentReplyId() == null) ? 0 : getWeiboCommentReplyId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getWeiboCommentId() == null) ? 0 : getWeiboCommentId().hashCode());
        result = prime * result + ((getWeiboCommentReplyTargetId() == null) ? 0 : getWeiboCommentReplyTargetId().hashCode());
        result = prime * result + ((getWeiboCommentReplyTime() == null) ? 0 : getWeiboCommentReplyTime().hashCode());
        result = prime * result + ((getWeiboCommentReplyContent() == null) ? 0 : getWeiboCommentReplyContent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", weiboCommentReplyId=").append(weiboCommentReplyId);
        sb.append(", userId=").append(userId);
        sb.append(", weiboCommentId=").append(weiboCommentId);
        sb.append(", weiboCommentReplyTargetId=").append(weiboCommentReplyTargetId);
        sb.append(", weiboCommentReplyTime=").append(weiboCommentReplyTime);
        sb.append(", weiboCommentReplyContent=").append(weiboCommentReplyContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}