package com.xiaosx.security.service;

import com.xiaosx.security.entity.User;
import com.xiaosx.security.utils.Result;

/**
 * @author x1aosx
 * @classname UserService.java
 * @description TODO
 * @createTime 2022/09/07 14:59
 */
public interface UserService {
    Result login(User user);
}
