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

    public CourseForShow(Course course) {
        super(course);
    }
}
