package com.gcu.public_examination_planet.dto;

import com.gcu.public_examination_planet.domain.Tag;
import com.gcu.public_examination_planet.domain.Teacher;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/4 1:05
 **/
@Data
@NoArgsConstructor
public class TeacherForShow extends Teacher{
    /**
     * 昵称
     */
    private String userName;

    /**
     * 用户性别（1：男，0：女）
     */
    private Integer userGender;


    /**
     * 标签集合
     */
    private List<Tag> tags;

    public TeacherForShow(Teacher teacher) {
        super(teacher);
    }
}
