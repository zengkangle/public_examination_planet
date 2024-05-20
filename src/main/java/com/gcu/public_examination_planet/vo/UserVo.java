package com.gcu.public_examination_planet.vo;

import lombok.Data;

/**
 * @author HealMe
 * @Description
 * @date 2024/3/31 21:52
 **/
@Data
public class UserVo {
    /**
     * 用户的手机（登录的账号）
     */
    private String userPhone;

    /**
     * 用户密码
     */
    private String userPassword;
}
