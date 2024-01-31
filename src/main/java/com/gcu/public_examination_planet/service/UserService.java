package com.gcu.public_examination_planet.service;

import com.gcu.public_examination_planet.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcu.public_examination_planet.dto.LoginUser;
import com.gcu.public_examination_planet.dto.SimpleUser;

import java.util.Map;

/**
* @author HealMe
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-01-27 15:43:21
*/
public interface UserService extends IService<User> {
    LoginUser login(Map<String, Object> loginMap);

    String register(User registerUser);

    SimpleUser selectUserById(Integer userId);
}
