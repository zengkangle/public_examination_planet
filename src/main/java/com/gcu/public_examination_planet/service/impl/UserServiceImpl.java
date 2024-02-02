package com.gcu.public_examination_planet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.public_examination_planet.common.Constants;
import com.gcu.public_examination_planet.domain.User;
import com.gcu.public_examination_planet.dto.LoginUser;
import com.gcu.public_examination_planet.dto.SimpleUser;
import com.gcu.public_examination_planet.exception.ServiceException;
import com.gcu.public_examination_planet.mapper.UserMapper;
import com.gcu.public_examination_planet.service.UserService;
import com.gcu.public_examination_planet.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author HealMe
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2024-01-27 15:43:21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService{

    @Resource
    private UserMapper userMapper;

    public LoginUser login(Map<String, Object> loginMap){
        User user = getOne(new QueryWrapper<User>().eq("user_phone", loginMap.get("userPhone")));
        if (user != null) {
            if (user.getUserPassword().equals(loginMap.get("userPassword"))) {
                LoginUser loginUser = new LoginUser();
                //将user和loginUser相同的属性赋值
                BeanUtils.copyProperties(user, loginUser);
                //设置token
                String token = TokenUtils.genToken(String.valueOf(user.getUserId()), user.getUserPassword());
                loginUser.setToken(token);
                return loginUser;
            } else {
                throw new ServiceException(Constants.CODE_600.getCode(), "密码错误,请重新输入！");
            }
        } else {
            throw new ServiceException(Constants.CODE_600.getCode(), "账号错误，请重新输入！");
        }

    }

    public String register(User registerUser){
        System.out.println(registerUser);
        //匹配密码格式,4-16位且不能含有中文
        Pattern pPassword = Pattern.compile("^[^\\u4e00-\\u9fa5]{4,16}$");
        Matcher pp = pPassword.matcher(registerUser.getUserPassword());
        //匹配手机号码的格式
        Pattern pPhone = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(178))\\d{8}$");
        Matcher mp = pPhone.matcher(registerUser.getUserPhone());
        if (!pp.matches()) {
            throw new ServiceException(Constants.CODE_600.getCode(), "密码格式有误,请输入4-16位且不能含有中文的有效字符!");
        }
        if (!mp.matches()) {
            throw new ServiceException(Constants.CODE_600.getCode(), "请输入正确的手机号码!");
        }
        //根据传入的参数,从数据库中查询一条记录
        User useN = getOne(new QueryWrapper<User>().eq("user_name", registerUser.getUserName()));
        User useP = getOne(new QueryWrapper<User>().eq("user_phone", registerUser.getUserPhone()));
        if (useN != null) {
            throw new ServiceException(Constants.CODE_600.getCode(), "该用户名已存在,请重新输入!");
        } else if (useP != null) {
            throw new ServiceException(Constants.CODE_600.getCode(), "注册失败，该手机号码已被注册!");
        }
        save(registerUser);
        return "注册成功!";
    }

    public SimpleUser selectUserById(Integer userId){
        User user = getById(userId);
        SimpleUser simpleUser = new SimpleUser();
        BeanUtils.copyProperties(user, simpleUser);
        return simpleUser;
    }

    public IPage<User> getUserListByPage(Integer currentPage, Integer pageSize){
        QueryWrapper<User> pageWrapper = new QueryWrapper<>();
        //查询指定某字段以外的数据
        pageWrapper.select(User.class, info ->!info.getColumn().equals("user_password")).orderByDesc("user_create_time");
        //第一个参数为查询第几页,第二个参数为每页多少条记录
        Page<User> page = new Page<>(currentPage, pageSize);
        IPage<User> selectPage = userMapper.selectPage(page, pageWrapper);
        return selectPage;
    }
}




