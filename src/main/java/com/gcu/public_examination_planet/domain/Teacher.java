package com.gcu.public_examination_planet.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * 教师
 * @TableName teacher
 */
@TableName(value ="teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher implements Serializable {
    /**
     * 教师id
     */
    @TableId(type = IdType.AUTO)
    private Integer teacherId;

    /**
     * 教师概要信息
     */
    private String teacherOutline;

    /**
     * 教师详细描述
     */
    private String teacherDescribe;

    /**
     * 用户给改教师的评价平均分
     */
    private Double teacherRate;

    /**
     * 是否在师资列表中进行展示
     */
    private Integer isShow;

    /**
     * 直播推流码
     */
    private String liveCode;

    /**
     * 教师封面
     */
    private String teacherImgUrl;

    /**
     * 评价人数
     */
    private Integer teacherRateCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Teacher(Teacher teacher) {
        if (Objects.nonNull(teacher)){
            this.teacherId = teacher.getTeacherId();
            this.teacherOutline = teacher.getTeacherOutline();
            this.teacherDescribe = teacher.getTeacherDescribe();
            this.teacherRate = teacher.getTeacherRate();
            this.isShow = teacher.getIsShow();
            this.liveCode = teacher.getLiveCode();
            this.teacherImgUrl = teacher.getTeacherImgUrl();
            this.teacherRateCount = teacher.getTeacherRateCount();
        }
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
        Teacher other = (Teacher) that;
        return (this.getTeacherId() == null ? other.getTeacherId() == null : this.getTeacherId().equals(other.getTeacherId()))
            && (this.getTeacherOutline() == null ? other.getTeacherOutline() == null : this.getTeacherOutline().equals(other.getTeacherOutline()))
            && (this.getTeacherDescribe() == null ? other.getTeacherDescribe() == null : this.getTeacherDescribe().equals(other.getTeacherDescribe()))
            && (this.getTeacherRate() == null ? other.getTeacherRate() == null : this.getTeacherRate().equals(other.getTeacherRate()))
            && (this.getIsShow() == null ? other.getIsShow() == null : this.getIsShow().equals(other.getIsShow()))
            && (this.getLiveCode() == null ? other.getLiveCode() == null : this.getLiveCode().equals(other.getLiveCode()))
            && (this.getTeacherImgUrl() == null ? other.getTeacherImgUrl() == null : this.getTeacherImgUrl().equals(other.getTeacherImgUrl()))
            && (this.getTeacherRateCount() == null ? other.getTeacherRateCount() == null : this.getTeacherRateCount().equals(other.getTeacherRateCount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTeacherId() == null) ? 0 : getTeacherId().hashCode());
        result = prime * result + ((getTeacherOutline() == null) ? 0 : getTeacherOutline().hashCode());
        result = prime * result + ((getTeacherDescribe() == null) ? 0 : getTeacherDescribe().hashCode());
        result = prime * result + ((getTeacherRate() == null) ? 0 : getTeacherRate().hashCode());
        result = prime * result + ((getIsShow() == null) ? 0 : getIsShow().hashCode());
        result = prime * result + ((getLiveCode() == null) ? 0 : getLiveCode().hashCode());
        result = prime * result + ((getTeacherImgUrl() == null) ? 0 : getTeacherImgUrl().hashCode());
        result = prime * result + ((getTeacherRateCount() == null) ? 0 : getTeacherRateCount().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", teacherId=").append(teacherId);
        sb.append(", teacherOutline=").append(teacherOutline);
        sb.append(", teacherDescribe=").append(teacherDescribe);
        sb.append(", teacherRate=").append(teacherRate);
        sb.append(", isShow=").append(isShow);
        sb.append(", liveCode=").append(liveCode);
        sb.append(", teacherImgUrl=").append(teacherImgUrl);
        sb.append(", teacherRateCount=").append(teacherRateCount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}