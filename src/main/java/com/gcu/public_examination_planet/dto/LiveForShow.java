package com.gcu.public_examination_planet.dto;

import com.gcu.public_examination_planet.domain.Live;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/15 18:44
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LiveForShow extends Live {
    /**
     * 服务器地址
     */
    private String serverAddress;

    /**
     * 直播推流码
     */
    private String liveCode;

    /**
     * 直播标签
     */
    private List<String> tags;

    /**
     * 用户头像
     */
    private String userAvatarUrl;

    /**
     * 昵称
     */
    private String userName;

    public LiveForShow(Live live) {
        super(live);
    }
}
