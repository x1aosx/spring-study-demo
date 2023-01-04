package com.xiaosx.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaosx.auth.mapper.UserMapper;
import com.xiaosx.auth.entity.LoginUser;
import com.xiaosx.auth.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author x1aosx
 * @classname UserDetailsServiceImpl.java
 * @description TODO
 * @createTime 2022/09/05 14:37
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    private final static String USER_NOT_EXIST = "没有此用户";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(USER_NOT_EXIST);
        }
        return new LoginUser(user,null);
    }
}
