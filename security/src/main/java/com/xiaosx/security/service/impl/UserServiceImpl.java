package com.xiaosx.security.service.impl;

import com.xiaosx.security.entity.User;
import com.xiaosx.security.mapper.UserMapper;
import com.xiaosx.security.service.IUserService;
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
