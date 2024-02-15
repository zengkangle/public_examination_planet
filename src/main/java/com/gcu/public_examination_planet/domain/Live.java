package com.gcu.public_examination_planet.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 直播
 * @TableName live
 */
@TableName(value ="live")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Live implements Serializable {
    /**
     * 直播id
     */
    @TableId(type = IdType.AUTO)
    private Integer liveId;

    /**
     * 直播标题
     */
    private String liveTitle;

    /**
     * 直播对应的教师id
     */
    private Integer teacherId;

    /**
     * 直播状态（开播中，未开播）
     */
    private String liveStatus;

    /**
     * 直播种类（vip，公开试听课）
     */
    private String liveType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Live(Live live) {
        this.liveId = live.getLiveId();
        this.liveTitle = live.getLiveTitle();
        this.teacherId = live.getTeacherId();
        this.liveStatus = live.getLiveStatus();
        this.liveType = live.getLiveType();
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
        Live other = (Live) that;
        return (this.getLiveId() == null ? other.getLiveId() == null : this.getLiveId().equals(other.getLiveId()))
            && (this.getLiveTitle() == null ? other.getLiveTitle() == null : this.getLiveTitle().equals(other.getLiveTitle()))
            && (this.getTeacherId() == null ? other.getTeacherId() == null : this.getTeacherId().equals(other.getTeacherId()))
            && (this.getLiveStatus() == null ? other.getLiveStatus() == null : this.getLiveStatus().equals(other.getLiveStatus()))
            && (this.getLiveType() == null ? other.getLiveType() == null : this.getLiveType().equals(other.getLiveType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLiveId() == null) ? 0 : getLiveId().hashCode());
        result = prime * result + ((getLiveTitle() == null) ? 0 : getLiveTitle().hashCode());
        result = prime * result + ((getTeacherId() == null) ? 0 : getTeacherId().hashCode());
        result = prime * result + ((getLiveStatus() == null) ? 0 : getLiveStatus().hashCode());
        result = prime * result + ((getLiveType() == null) ? 0 : getLiveType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", liveId=").append(liveId);
        sb.append(", liveTitle=").append(liveTitle);
        sb.append(", teacherId=").append(teacherId);
        sb.append(", liveStatus=").append(liveStatus);
        sb.append(", liveType=").append(liveType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}