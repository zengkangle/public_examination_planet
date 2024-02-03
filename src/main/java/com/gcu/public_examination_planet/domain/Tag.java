package com.gcu.public_examination_planet.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 标签
 * @TableName tag
 */
@TableName(value ="tag")
@Data
public class Tag implements Serializable {
    /**
     * 标签id
     */
    @TableId(type = IdType.AUTO)
    private Integer tagId;

    /**
     * 标签种类（teacher：教师标签，course：课程标签，live：直播间标签）
     */
    private String tagType;

    /**
     * 标签内容
     */
    private String tagContent;

    /**
     * 对应的教师id
     */
    private Integer teacherId;

    /**
     * 对应的课程id
     */
    private Integer courseId;

    /**
     * 对应的直播id
     */
    private Integer liveId;

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
        Tag other = (Tag) that;
        return (this.getTagId() == null ? other.getTagId() == null : this.getTagId().equals(other.getTagId()))
            && (this.getTagType() == null ? other.getTagType() == null : this.getTagType().equals(other.getTagType()))
            && (this.getTagContent() == null ? other.getTagContent() == null : this.getTagContent().equals(other.getTagContent()))
            && (this.getTeacherId() == null ? other.getTeacherId() == null : this.getTeacherId().equals(other.getTeacherId()))
            && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getLiveId() == null ? other.getLiveId() == null : this.getLiveId().equals(other.getLiveId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTagId() == null) ? 0 : getTagId().hashCode());
        result = prime * result + ((getTagType() == null) ? 0 : getTagType().hashCode());
        result = prime * result + ((getTagContent() == null) ? 0 : getTagContent().hashCode());
        result = prime * result + ((getTeacherId() == null) ? 0 : getTeacherId().hashCode());
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getLiveId() == null) ? 0 : getLiveId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tagId=").append(tagId);
        sb.append(", tagType=").append(tagType);
        sb.append(", tagContent=").append(tagContent);
        sb.append(", teacherId=").append(teacherId);
        sb.append(", courseId=").append(courseId);
        sb.append(", liveId=").append(liveId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}