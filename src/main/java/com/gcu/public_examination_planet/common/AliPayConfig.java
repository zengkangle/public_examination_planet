package com.gcu.public_examination_planet.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/1 18:20
 **/
@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AliPayConfig {

    private String appId;

    private String appPrivateKey;

    private String alipayPublicKey;

    private String notifyUrl;

    private String returnUrl;

}