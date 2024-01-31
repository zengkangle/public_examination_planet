package com.gcu.public_examination_planet.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gcu.public_examination_planet.domain.User;
import com.gcu.public_examination_planet.service.UserService;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Data
@Component
public class TokenUtils {

    private static UserService staticUserService;
    @Resource
    private UserService userService;

    @PostConstruct
    public void setUserService() {
        staticUserService = userService;
    }

    /**
     * 生成token
     *
     * @param userId
     * @param password
     * @return
     */
    public static String genToken(String userId, String password) {
        return JWT.create().withAudience(userId) //将 userId 保存到 token 里面，作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 3)) // 3小时后token过期
                .sign(Algorithm.HMAC256(password)); // 以password和userid作为token的密钥
    }

    ;

    /**
     * 获取当前登录的用户信息
     *
     * @return
     */
    public static User getCurrentUser() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (!StrUtil.isBlank(token)) {
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserService.getById(userId);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}





