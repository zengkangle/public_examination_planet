package com.gcu.public_examination_planet.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 视频
 * @TableName video
 */
@TableName(value ="video")
@Data
public class Video implements Serializable {
    /**
     * 视频id
     */
    @TableId(type = IdType.AUTO)
    private Integer videoId;

    /**
     * 视频标题
     */
    private String videoTitle;

    /**
     * 视频url
     */
    private String videoUrl;

    /**
     * 视频所属的课程id
     */
    private Integer courseId;

    /**
     * 审核状态（审核中，不通过，通过）
     */
    private String videoStatus;

    /**
     * 视频对应课程的具体节数
     */
    private Integer coursePage;

    /**
     * 创建时间
     */
    private Date createTime;

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
        Video other = (Video) that;
        return (this.getVideoId() == null ? other.getVideoId() == null : this.getVideoId().equals(other.getVideoId()))
            && (this.getVideoTitle() == null ? other.getVideoTitle() == null : this.getVideoTitle().equals(other.getVideoTitle()))
            && (this.getVideoUrl() == null ? other.getVideoUrl() == null : this.getVideoUrl().equals(other.getVideoUrl()))
            && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getVideoStatus() == null ? other.getVideoStatus() == null : this.getVideoStatus().equals(other.getVideoStatus()))
            && (this.getCoursePage() == null ? other.getCoursePage() == null : this.getCoursePage().equals(other.getCoursePage()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getVideoId() == null) ? 0 : getVideoId().hashCode());
        result = prime * result + ((getVideoTitle() == null) ? 0 : getVideoTitle().hashCode());
        result = prime * result + ((getVideoUrl() == null) ? 0 : getVideoUrl().hashCode());
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getVideoStatus() == null) ? 0 : getVideoStatus().hashCode());
        result = prime * result + ((getCoursePage() == null) ? 0 : getCoursePage().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", videoId=").append(videoId);
        sb.append(", videoTitle=").append(videoTitle);
        sb.append(", videoUrl=").append(videoUrl);
        sb.append(", courseId=").append(courseId);
        sb.append(", videoStatus=").append(videoStatus);
        sb.append(", coursePage=").append(coursePage);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}