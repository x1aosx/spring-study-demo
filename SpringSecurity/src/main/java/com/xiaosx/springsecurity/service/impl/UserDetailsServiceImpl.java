package com.xiaosx.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaosx.springsecurity.mapper.UserMapper;
import com.xiaosx.springsecurity.pojo.LoginUser;
import com.xiaosx.springsecurity.pojo.User;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("没有此用户");
        }
        return new LoginUser(user);
    }
}
