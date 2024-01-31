package com.gcu.public_examination_planet.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 微博评论
 * @TableName weibo_comment
 */
@TableName(value ="weibo_comment")
@Data
public class WeiboComment implements Serializable {
    /**
     * 微博评论id
     */
    @TableId(type = IdType.AUTO)
    private Integer weiboCommentId;

    /**
     * 微博评论的发布者id
     */
    private Integer userId;

    /**
     * 微博评论发布时间
     */
    private Date weiboCommentTime;

    /**
     * 对应的微博id
     */
    private Integer weiboId;

    /**
     * 微博评论回复数量
     */
    private Integer weiboCommentReplyAmount;

    /**
     * 微博评论内容
     */
    private String weiboCommentContent;

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
        WeiboComment other = (WeiboComment) that;
        return (this.getWeiboCommentId() == null ? other.getWeiboCommentId() == null : this.getWeiboCommentId().equals(other.getWeiboCommentId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getWeiboCommentTime() == null ? other.getWeiboCommentTime() == null : this.getWeiboCommentTime().equals(other.getWeiboCommentTime()))
            && (this.getWeiboId() == null ? other.getWeiboId() == null : this.getWeiboId().equals(other.getWeiboId()))
            && (this.getWeiboCommentReplyAmount() == null ? other.getWeiboCommentReplyAmount() == null : this.getWeiboCommentReplyAmount().equals(other.getWeiboCommentReplyAmount()))
            && (this.getWeiboCommentContent() == null ? other.getWeiboCommentContent() == null : this.getWeiboCommentContent().equals(other.getWeiboCommentContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWeiboCommentId() == null) ? 0 : getWeiboCommentId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getWeiboCommentTime() == null) ? 0 : getWeiboCommentTime().hashCode());
        result = prime * result + ((getWeiboId() == null) ? 0 : getWeiboId().hashCode());
        result = prime * result + ((getWeiboCommentReplyAmount() == null) ? 0 : getWeiboCommentReplyAmount().hashCode());
        result = prime * result + ((getWeiboCommentContent() == null) ? 0 : getWeiboCommentContent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", weiboCommentId=").append(weiboCommentId);
        sb.append(", userId=").append(userId);
        sb.append(", weiboCommentTime=").append(weiboCommentTime);
        sb.append(", weiboId=").append(weiboId);
        sb.append(", weiboCommentReplyAmount=").append(weiboCommentReplyAmount);
        sb.append(", weiboCommentContent=").append(weiboCommentContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}