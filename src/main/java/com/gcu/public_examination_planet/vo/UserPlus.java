package com.gcu.public_examination_planet.vo;

import com.gcu.public_examination_planet.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/4 4:16
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPlus extends User {
    /**
     * 教师id
     */
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
     * 教师封面
     */
    private String teacherImgUrl;

    /**
     * 教师标签
     */
    private List<String> tags;
}
