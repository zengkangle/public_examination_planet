package com.gcu.public_examination_planet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author HealMe
 * @Description
 * @date 2024/1/20 上午 12:23
 **/
@SpringBootApplication
@MapperScan("com.gcu.public_examination_planet.mapper")
public class PublicExaminationPlanetApplication {
    public static void main(String[] args) {
        SpringApplication.run(PublicExaminationPlanetApplication.class,args);
    }
}
