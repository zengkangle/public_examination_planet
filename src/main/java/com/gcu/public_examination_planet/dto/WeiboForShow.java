package com.gcu.public_examination_planet.dto;

import com.gcu.public_examination_planet.domain.Weibo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/18 18:42
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeiboForShow extends Weibo {

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

    /**
     * 微博图片列表
     */
    private List<String> weiboImgList;


    public WeiboForShow(Weibo weibo) {
        super(weibo);
    }
}
