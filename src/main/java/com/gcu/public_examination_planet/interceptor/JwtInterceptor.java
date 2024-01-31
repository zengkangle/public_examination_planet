package com.gcu.public_examination_planet.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gcu.public_examination_planet.common.Constants;
import com.gcu.public_examination_planet.domain.User;
import com.gcu.public_examination_planet.exception.ServiceException;
import com.gcu.public_examination_planet.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class JwtInterceptor implements HandlerInterceptor {
    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if (StrUtil.isBlank(token)) {
            throw new ServiceException(Constants.CODE_401.getCode(), "您还未登录，登录之后获取更多信息！");
        }
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
            System.out.println(userId);
        } catch (JWTDecodeException j) {
            throw new ServiceException(Constants.CODE_401.getCode(), "token验证失败");
        }
        User user = userService.getById(userId);
        if (user == null) {
            throw new ServiceException(Constants.CODE_401.getCode(), "用户不存在,请重新登录");
        }
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getUserPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new ServiceException(Constants.CODE_401.getCode(), "token验证失败,请重新登录");
        }

        return true;
    }
}

