package com.gcu.public_examination_planet.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gcu.public_examination_planet.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/21 1:55
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlusUser extends User {

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
     * 教师封面
     */
    private String teacherImgUrl;

    /**
     * 教师标签列表
     */
    private List<String> tags;
}
