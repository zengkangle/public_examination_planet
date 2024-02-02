package com.gcu.public_examination_planet.controller;

import com.gcu.public_examination_planet.common.Result;
import com.gcu.public_examination_planet.domain.User;
import com.gcu.public_examination_planet.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author HealMe
 * @Description
 * @date 2024/1/27 下午 3:46
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    /**
     * 用户登录
     * @param loginMap
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody Map<String, Object> loginMap){
        return Result.success(userService.login(loginMap));
    }

    /**
     * 用户注册
     * @param registerUser
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody User registerUser) {
        return Result.success(userService.register(registerUser));
    }

    /**
     * 根据用户id查找用户信息
     * @param userId
     * @return
     */
    @GetMapping("/getUserMsg")
    public Result showWeiboList(@RequestParam("userId") Integer userId) {
        return Result.success(userService.selectUserById(userId));
    }

    /**
     * 分页获取用户列表
     * @return
     */
    @GetMapping("/getUserList")
    public Result getUserList(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize) {
        return Result.success(userService.getUserListByPage(currentPage,pageSize));
    }
}
