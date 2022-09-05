package com.xiaosx.springsecurity.controller;

import com.xiaosx.springsecurity.pojo.User;
import com.xiaosx.springsecurity.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @PostMapping("/login")
    public Result login(User user){

        return null;
    }
}
