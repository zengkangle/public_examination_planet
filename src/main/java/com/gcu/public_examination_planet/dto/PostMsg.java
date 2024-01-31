package com.gcu.public_examination_planet.dto;

import lombok.Data;

import java.util.List;

/**
 * @author HealMe
 * @Description
 * @date 2024/1/28 下午 8:03
 **/
@Data
public class PostMsg {

    /**
     * 微博内容
     */
    private String text;

    /**
     * 微博的发布者id
     */
    private Integer userId;

    /**
     * 微博图片列表
     */
    private List<String> imageUploadList;
}
