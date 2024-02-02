package com.gcu.public_examination_planet.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author HealMe
 * @Description
 * @date 2024/1/27 下午 4:12
 **/
@Data
public class LoginUser {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 用户性别（1：男，0：女）
     */
    private Integer userGender;

    /**
     * 用户的手机（登录的账号）
     */
    private String userPhone;

    /**
     * 用户头像
     */
    private String userAvatarUrl;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户层级（普通用户normal，VIP用户vip，管理员admin）
     */
    private String userLevel;

    /**
     * 对应的教师id
     */
    private Integer teacherId;

    /**
     * 用户创建时间
     */
    private Date userCreateTime;

    /**
     * 用户登录token标识
     */
    private String token;
}
