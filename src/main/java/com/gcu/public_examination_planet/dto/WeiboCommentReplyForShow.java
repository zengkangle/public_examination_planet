package com.gcu.public_examination_planet.dto;

import com.gcu.public_examination_planet.domain.WeiboCommentReply;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/19 15:23
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeiboCommentReplyForShow extends WeiboCommentReply {

    /**
     * 昵称
     */
    private String userName;

    /**
     * 用户层级（普通用户normal，VIP用户vip，管理员admin）
     */
    private String userLevel;

    /**
     * target昵称
     */
    private String targetUserName;

    /**
     * target用户层级（普通用户normal，VIP用户vip，管理员admin）
     */
    private String targetUserLevel;

}
