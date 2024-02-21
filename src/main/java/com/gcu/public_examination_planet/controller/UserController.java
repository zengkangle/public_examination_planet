package com.gcu.public_examination_planet.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gcu.public_examination_planet.common.Result;
import com.gcu.public_examination_planet.domain.Live;
import com.gcu.public_examination_planet.domain.Tag;
import com.gcu.public_examination_planet.domain.Teacher;
import com.gcu.public_examination_planet.domain.User;
import com.gcu.public_examination_planet.service.LiveService;
import com.gcu.public_examination_planet.service.TagService;
import com.gcu.public_examination_planet.service.TeacherService;
import com.gcu.public_examination_planet.service.UserService;
import com.gcu.public_examination_planet.vo.UserPlus;
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

    @Resource
    TeacherService teacherService;

    @Resource
    LiveService liveService;

    @Resource
    TagService tagService;

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
    public Result getUserMsg(@RequestParam("userId") Integer userId) {
        return Result.success(userService.selectUserById(userId));
    }

    /**
     * 根据用户id获取个人中心的信息
     * @param userId
     * @return
     */
    @GetMapping("/getPlusUserMsg")
    public Result getPlusUserMsg(@RequestParam("userId") Integer userId) {
        return Result.success(userService.getPlusUserMsg(userId));
    }

    /**
     * 分页获取用户列表
     * @return
     */
    @GetMapping("/getUserList")
    public Result getUserList(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize) {
        return Result.success(userService.getUserListByPage(currentPage,pageSize));
    }

    /**
     * 修改用户基本信息
     * @param updateUser
     * @return
     */
    @PostMapping("/updateUserInfo")
    public Result updateUserInfo(@RequestBody User updateUser) {
        return Result.success(userService.updateById(updateUser));
    }

    /**
     * 修改用户、教师信息
     * @param userPlus
     * @return
     */
    @PostMapping("/updateUserInfoPlus")
    public Result updateUserInfoPlus(@RequestBody UserPlus userPlus) {
        User user = new User();
        BeanUtil.copyProperties(userPlus,user);
        userService.updateById(user);
        if (userPlus.getTeacherId() != null){
            Teacher teacher = new Teacher();
            BeanUtil.copyProperties(userPlus,teacher);
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+userPlus);
            teacherService.updateById(teacher);
            if (userPlus.getTags()!=null && userPlus.getTags().size()>0){
                tagService.remove(new QueryWrapper<Tag>().eq("teacher_id",userPlus.getTeacherId()));
                for (String tagStr : userPlus.getTags()) {
                    Tag tag = new Tag();
                    tag.setTagContent(tagStr);
                    tag.setTeacherId(userPlus.getTeacherId());
                    tag.setTagType("teacher");
                    tagService.save(tag);
                }
            }
        }
        return Result.success("success");
    }

    /**
     * 重置用户密码
     * @param resetPasswordUser
     * @return
     */
    @PostMapping("/resetPassword")
    public Result resetPassword(@RequestBody User resetPasswordUser) {
        return Result.success(userService.updateById(resetPasswordUser));
    }

    /**
     * 注销用户
     * @param userId
     * @return
     */
    @GetMapping("/deleteUser")
    public Result deleteUser(@RequestParam("userId") Integer userId) {
        return Result.success(userService.removeById(userId));
    }

    /**
     * 修改用户成为教师身份
     * @param userId
     * @return
     */
    @GetMapping("/changeUserLevelToTeacher")
    public Result changeUserLevelToTeacher(@RequestParam("userId") Integer userId) {
        //生成教师直播推流码
        String simpleUUID = IdUtil.simpleUUID();
        String liveCode = StrUtil.sub(simpleUUID,0,8);
        Teacher teacher = new Teacher();
        teacher.setLiveCode(liveCode);
        //插入一条新的教师记录
        teacherService.save(teacher);
        Live live = new Live();
        live.setTeacherId(teacher.getTeacherId());
        //插入一条新的直播间记录
        liveService.save(live);
        //修改用户为教师
        User user = new User();
        user.setUserId(userId);
        user.setUserLevel("teacher");
        user.setTeacherId(teacher.getTeacherId());
        userService.updateById(user);
        return Result.success("success");
    }

}
