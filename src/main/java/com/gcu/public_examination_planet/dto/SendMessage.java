package com.gcu.public_examination_planet.dto;

import lombok.Data;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/15 22:27
 **/
@Data
public class SendMessage {
    /**
     * 消息类型：系统消息system，用户消息user,断开消息close
     */
    private String messageType;

    /**
     * 发送者昵称
     */
    private String userName;

    /**
     * 消息内容
     */
    private String messageContent;

    /**
     * 在线人数
     */
    private Integer onlineAmount;
}
