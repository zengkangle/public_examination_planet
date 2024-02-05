package com.gcu.public_examination_planet.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程
 * @TableName course
 */
@TableName(value ="course")
@Data
public class Course implements Serializable {
    /**
     * 课程id
     */
    @TableId(type = IdType.AUTO)
    private Integer courseId;

    /**
     * 课程概要信息
     */
    private String courseOutline;

    /**
     * 课程创建时间
     */
    private Date createTime;

    /**
     * 课程售卖数量
     */
    private Integer courseOrder;

    /**
     * 课程价格
     */
    private Double coursePrice;

    /**
     * 课程种类（公考笔试，公考面试，事业单位）
     */
    private String courseType;

    /**
     * 课程状态（上架，下架，冻结）
     */
    private String courseStatus;

    /**
     * 课程标题
     */
    private String courseTitle;

    /**
     * 用户给课程的平均评分
     */
    private Double courseRate;

    /**
     * 课程包含的视频数量
     */
    private Integer coursePage;

    /**
     * 任教老师id
     */
    private Integer teacherId;

    /**
     * 评价人数
     */
    private Integer courseRateCount;

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
        Course other = (Course) that;
        return (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getCourseOutline() == null ? other.getCourseOutline() == null : this.getCourseOutline().equals(other.getCourseOutline()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCourseOrder() == null ? other.getCourseOrder() == null : this.getCourseOrder().equals(other.getCourseOrder()))
            && (this.getCoursePrice() == null ? other.getCoursePrice() == null : this.getCoursePrice().equals(other.getCoursePrice()))
            && (this.getCourseType() == null ? other.getCourseType() == null : this.getCourseType().equals(other.getCourseType()))
            && (this.getCourseStatus() == null ? other.getCourseStatus() == null : this.getCourseStatus().equals(other.getCourseStatus()))
            && (this.getCourseTitle() == null ? other.getCourseTitle() == null : this.getCourseTitle().equals(other.getCourseTitle()))
            && (this.getCourseRate() == null ? other.getCourseRate() == null : this.getCourseRate().equals(other.getCourseRate()))
            && (this.getCoursePage() == null ? other.getCoursePage() == null : this.getCoursePage().equals(other.getCoursePage()))
            && (this.getTeacherId() == null ? other.getTeacherId() == null : this.getTeacherId().equals(other.getTeacherId()))
            && (this.getCourseRateCount() == null ? other.getCourseRateCount() == null : this.getCourseRateCount().equals(other.getCourseRateCount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getCourseOutline() == null) ? 0 : getCourseOutline().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCourseOrder() == null) ? 0 : getCourseOrder().hashCode());
        result = prime * result + ((getCoursePrice() == null) ? 0 : getCoursePrice().hashCode());
        result = prime * result + ((getCourseType() == null) ? 0 : getCourseType().hashCode());
        result = prime * result + ((getCourseStatus() == null) ? 0 : getCourseStatus().hashCode());
        result = prime * result + ((getCourseTitle() == null) ? 0 : getCourseTitle().hashCode());
        result = prime * result + ((getCourseRate() == null) ? 0 : getCourseRate().hashCode());
        result = prime * result + ((getCoursePage() == null) ? 0 : getCoursePage().hashCode());
        result = prime * result + ((getTeacherId() == null) ? 0 : getTeacherId().hashCode());
        result = prime * result + ((getCourseRateCount() == null) ? 0 : getCourseRateCount().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", courseId=").append(courseId);
        sb.append(", courseOutline=").append(courseOutline);
        sb.append(", createTime=").append(createTime);
        sb.append(", courseOrder=").append(courseOrder);
        sb.append(", coursePrice=").append(coursePrice);
        sb.append(", courseType=").append(courseType);
        sb.append(", courseStatus=").append(courseStatus);
        sb.append(", courseTitle=").append(courseTitle);
        sb.append(", courseRate=").append(courseRate);
        sb.append(", coursePage=").append(coursePage);
        sb.append(", teacherId=").append(teacherId);
        sb.append(", courseRateCount=").append(courseRateCount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}