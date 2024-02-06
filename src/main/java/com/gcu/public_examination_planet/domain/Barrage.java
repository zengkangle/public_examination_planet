package com.gcu.public_examination_planet.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 弹幕
 * @TableName barrage
 */
@TableName(value ="barrage")
@Data
public class Barrage implements Serializable {
    /**
     * 弹幕id
     */
    @TableId(type = IdType.AUTO)
    private Integer barrageId;

    /**
     * 弹幕颜色
     */
    private String color;

    /**
     * 弹幕内容
     */
    private String text;

    /**
     * 弹幕发送对应的视频时间点
     */
    private Integer time;

    /**
     * 弹幕类型（0：滚动，1：顶部，2：底部）
     */
    private Integer type;

    /**
     * 弹幕的创建时间
     */
    private Date createtime;

    /**
     * 对应的视频id
     */
    private Integer videoId;

    /**
     * 弹幕发送者id
     */
    private Integer userId;

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
        Barrage other = (Barrage) that;
        return (this.getBarrageId() == null ? other.getBarrageId() == null : this.getBarrageId().equals(other.getBarrageId()))
            && (this.getColor() == null ? other.getColor() == null : this.getColor().equals(other.getColor()))
            && (this.getText() == null ? other.getText() == null : this.getText().equals(other.getText()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getVideoId() == null ? other.getVideoId() == null : this.getVideoId().equals(other.getVideoId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBarrageId() == null) ? 0 : getBarrageId().hashCode());
        result = prime * result + ((getColor() == null) ? 0 : getColor().hashCode());
        result = prime * result + ((getText() == null) ? 0 : getText().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getVideoId() == null) ? 0 : getVideoId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", barrageId=").append(barrageId);
        sb.append(", color=").append(color);
        sb.append(", text=").append(text);
        sb.append(", time=").append(time);
        sb.append(", type=").append(type);
        sb.append(", createtime=").append(createtime);
        sb.append(", videoId=").append(videoId);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}