package com.gcu.public_examination_planet.dto;

import com.gcu.public_examination_planet.domain.WeiboComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/19 3:18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeiboCommentForShow extends WeiboComment {
    /**
     * 昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatarUrl;

    /**
     * 用户层级（普通用户normal，VIP用户vip，管理员admin）
     */
    private String userLevel;
}
