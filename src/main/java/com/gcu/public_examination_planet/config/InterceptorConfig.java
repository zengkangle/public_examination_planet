package com.gcu.public_examination_planet.config;

import com.gcu.public_examination_planet.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Resource
    JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**").excludePathPatterns("/user/**", "/alipay/**",
                "/files/**", "/chat/**", "/follow/**", "/liveroom/**", "/turnurl/**", "/msg/**","/worktime/**");
    }

}
