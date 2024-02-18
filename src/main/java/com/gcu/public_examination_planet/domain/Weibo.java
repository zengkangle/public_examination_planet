package com.gcu.public_examination_planet.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 微博
 * @TableName weibo
 */
@TableName(value ="weibo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weibo implements Serializable {
    /**
     * 微博id
     */
    @TableId(type = IdType.AUTO)
    private Integer weiboId;

    /**
     * 微博内容
     */
    private String weiboContent;

    /**
     * 微博的发布者id
     */
    private Integer userId;

    /**
     * 微博发布时间
     */
    private Date weiboPostTime;

    /**
     * 微博评论数量
     */
    private Integer weiboCommentAmount;

    /**
     * 微博是否有图片
     */
    private Integer weiboHaveImg;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Weibo(Weibo weibo) {
        this.weiboId = weibo.getWeiboId();
        this.weiboContent = weibo.getWeiboContent();
        this.userId = weibo.getUserId();
        this.weiboPostTime = weibo.getWeiboPostTime();
        this.weiboCommentAmount = weibo.getWeiboCommentAmount();
        this.weiboHaveImg = weibo.getWeiboHaveImg();
    }

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
        Weibo other = (Weibo) that;
        return (this.getWeiboId() == null ? other.getWeiboId() == null : this.getWeiboId().equals(other.getWeiboId()))
            && (this.getWeiboContent() == null ? other.getWeiboContent() == null : this.getWeiboContent().equals(other.getWeiboContent()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getWeiboPostTime() == null ? other.getWeiboPostTime() == null : this.getWeiboPostTime().equals(other.getWeiboPostTime()))
            && (this.getWeiboCommentAmount() == null ? other.getWeiboCommentAmount() == null : this.getWeiboCommentAmount().equals(other.getWeiboCommentAmount()))
            && (this.getWeiboHaveImg() == null ? other.getWeiboHaveImg() == null : this.getWeiboHaveImg().equals(other.getWeiboHaveImg()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWeiboId() == null) ? 0 : getWeiboId().hashCode());
        result = prime * result + ((getWeiboContent() == null) ? 0 : getWeiboContent().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getWeiboPostTime() == null) ? 0 : getWeiboPostTime().hashCode());
        result = prime * result + ((getWeiboCommentAmount() == null) ? 0 : getWeiboCommentAmount().hashCode());
        result = prime * result + ((getWeiboHaveImg() == null) ? 0 : getWeiboHaveImg().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", weiboId=").append(weiboId);
        sb.append(", weiboContent=").append(weiboContent);
        sb.append(", userId=").append(userId);
        sb.append(", weiboPostTime=").append(weiboPostTime);
        sb.append(", weiboCommentAmount=").append(weiboCommentAmount);
        sb.append(", weiboHaveImg=").append(weiboHaveImg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}