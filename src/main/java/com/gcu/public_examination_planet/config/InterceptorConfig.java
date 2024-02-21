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
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**").excludePathPatterns(
                "/course/getCourseList",
                "/article/getArticleListOfType",
                "/article/getArticleInfo",
                "/barrage/getBarrageList",
                "/alipay/**",
                "/files/**",
                "/comment/getRateByPage",
                "/live/getLiveList",
                "/tag/getTagsById",
                "/teacher/getSimpleTeacherList",
                "/teacher/getTeacherById",
                "/user/login",
                "/user/register",
                "/weibo/showWeiboList",
                "/weibo/showWeiboCommentList",
                "/weibo/getRecentReplyUserName",
                "/weibo/getReplyList",
                "/video/getCourseVideoList"
        );
    }

}
