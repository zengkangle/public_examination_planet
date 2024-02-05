package com.gcu.public_examination_planet.vo;

import lombok.Data;

import java.util.List;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/6 3:45
 **/
@Data
public class CreateCourse {

    /**
     * 课程概要信息
     */
    private String courseOutline;

    /**
     * 课程价格
     */
    private Double coursePrice;

    /**
     * 课程种类（公考笔试，公考面试，事业单位）
     */
    private String courseType;

    /**
     * 课程标题
     */
    private String courseTitle;


    /**
     * 任教老师id
     */
    private Integer teacherId;

    /**
     * 课程标签列表
     */
    private List<String> tags;

}
