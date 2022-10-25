package com.xiaosx.auth.service;

import com.xiaosx.auth.entity.User;
import com.xiaosx.common.utils.Result;

/**
 * @author x1aosx
 * @classname UserService.java
 * @description TODO
 * @createTime 2022/09/07 14:59
 */
public interface UserService {
    Result login(User user);
}
