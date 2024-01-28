package com.gcu.public_examination_planet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.public_examination_planet.domain.User;
import com.gcu.public_examination_planet.service.UserService;
import com.gcu.public_examination_planet.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author HealMe
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2024-01-27 15:43:21
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




