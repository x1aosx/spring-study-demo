package com.xiaosx.security;

import com.xiaosx.security.entity.User;
import com.xiaosx.security.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserTest {

    @Resource
    private IUserService userService;

    @Test
    public void testInsert() {
        User user = new User();
        user.setUsername("xiaosx");
        user.setPassword("$2a$10$.xf/eQDq0humzSaPaoX/.u0UgLNe5ydLUNoZX.J063.c0rA83ERiu");
        user.setName("xiaoshuxian");
        userService.save(user);
    }
}
