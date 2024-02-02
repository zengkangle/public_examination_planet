package com.gcu.public_examination_planet.common;

import lombok.Data;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/1 18:28
 **/
@Data
public class AliPay {
    private String traceNo;
    private double totalAmount;
    private String subject;
    private String alipayTraceNo;
}
