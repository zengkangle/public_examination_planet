package com.gcu.public_examination_planet.dto;

import com.gcu.public_examination_planet.domain.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/6 14:07
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseForShow extends Course {
    /**
     * 课程标签列表
     */
    private List<String> tags;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatarUrl;

    /**
     * 教师详细描述
     */
    private String teacherDescribe;

    /**
     * 用户给改教师的评价平均分
     */
    private Double teacherRate;

    /**
     * 评价人数
     */
    private Integer teacherRateCount;

    public CourseForShow(Course course) {
        super(course);
    }
}
