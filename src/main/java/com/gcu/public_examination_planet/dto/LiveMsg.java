package com.gcu.public_examination_planet.dto;

import com.gcu.public_examination_planet.domain.Live;
import lombok.Data;

import java.util.List;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/14 16:59
 **/
@Data
public class LiveMsg extends Live {
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
}
