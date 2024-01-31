package com.gcu.public_examination_planet.dto;

import lombok.Data;

/**
 * @author HealMe
 * @Description
 * @date 2024/1/29 上午 2:59
 **/
@Data
public class SimpleUser {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 昵称
     */
    private String userName;

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
     * 普通用户每天免费观看直播的次数
     */
    private Integer userFreeWatch;


}
