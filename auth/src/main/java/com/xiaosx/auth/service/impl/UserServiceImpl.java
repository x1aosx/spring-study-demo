package com.xiaosx.auth.service.impl;

import com.xiaosx.auth.entity.User;
import com.xiaosx.auth.mapper.UserMapper;
import com.xiaosx.auth.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaosx
 * @since 2022-10-291 21:47:25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
