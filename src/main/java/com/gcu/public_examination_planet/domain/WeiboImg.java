package com.gcu.public_examination_planet.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 微博图片
 * @TableName weibo_img
 */
@TableName(value ="weibo_img")
@Data
public class WeiboImg implements Serializable {
    /**
     * 微博图片id
     */
    @TableId(type = IdType.AUTO)
    private Integer weiboImgId;

    /**
     * 对应的微博id
     */
    private Integer weiboId;

    /**
     * 微博图片url
     */
    private String weiboImgUrl;

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
        WeiboImg other = (WeiboImg) that;
        return (this.getWeiboImgId() == null ? other.getWeiboImgId() == null : this.getWeiboImgId().equals(other.getWeiboImgId()))
            && (this.getWeiboId() == null ? other.getWeiboId() == null : this.getWeiboId().equals(other.getWeiboId()))
            && (this.getWeiboImgUrl() == null ? other.getWeiboImgUrl() == null : this.getWeiboImgUrl().equals(other.getWeiboImgUrl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWeiboImgId() == null) ? 0 : getWeiboImgId().hashCode());
        result = prime * result + ((getWeiboId() == null) ? 0 : getWeiboId().hashCode());
        result = prime * result + ((getWeiboImgUrl() == null) ? 0 : getWeiboImgUrl().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", weiboImgId=").append(weiboImgId);
        sb.append(", weiboId=").append(weiboId);
        sb.append(", weiboImgUrl=").append(weiboImgUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}