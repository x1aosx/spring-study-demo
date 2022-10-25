package com.xiaosx.auth.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.xiaosx.auth.entity.LoginUser;
import com.xiaosx.auth.entity.User;
import com.xiaosx.auth.entity.UserForm;
import com.xiaosx.common.utils.JwtUtil;
import com.xiaosx.common.utils.RedisUtil;
import com.xiaosx.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author x1aosx
 * @classname LoginController.java
 * @description 登录模块
 * @createTime 2022/10/09 16:19
 */
@RestController()
@Slf4j
@JsonIgnoreProperties("enabled")
public class LoginController {

    private final static String URL_LOGIN = "/auth/login";

    private final static String URL_INFO = "/auth/info";

    private final static String URL_LOGOUT = "/auth/logout";

    private final static String USERNAME_OR_PASSWORD_ERROR = "用户名或密码错误";

    private final static String STR_TOKEN = "token";

    private final static String STR_USERNAME = "username:";

    private final static Long REDIS_TTL = 3600L;

    @Resource
    RedisUtil redisUtil;

    @Resource
    AuthenticationManager authenticationManager;


    /*
     * @author x1aosx
     * @description 登录接口
     * @date 2022/10/9 16:56
     * @param userForm: 登录用户对象
     **/
    @PostMapping(URL_LOGIN)
    public Result login(@RequestBody UserForm userForm) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userForm.getUsername(), userForm.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (Objects.isNull(authenticate)) {
            log.warn(userForm.getUsername() + USERNAME_OR_PASSWORD_ERROR);
            throw new RuntimeException(USERNAME_OR_PASSWORD_ERROR);

        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        User user = loginUser.getUser();
        String userId = user.getId().toString();
        String username = user.getUsername();
        String jwt = JwtUtil.createJWT(username, userId);
        Map<String, Object> map = new HashMap<>();
        map.put(STR_TOKEN, jwt);
        redisUtil.set(STR_USERNAME + username, loginUser, REDIS_TTL);
        return Result.SUCCESS(map, URL_LOGIN);
    }

    /*
     * @author x1aosx
     * @description 返回用户信息
     * @date 2022/10/9 18:37
     * @param token: token
     **/
    @GetMapping(URL_INFO)
    @PreAuthorize("hasAuthority('" + URL_INFO + "')")
    public Result info(@RequestHeader(STR_TOKEN) String token) {
        String username = JwtUtil.parseJWT(token).getSubject();
        LoginUser loginUser = (LoginUser) redisUtil.get(STR_USERNAME + username);
        log.info("返回用户信息->" + loginUser.getUsername());
        loginUser.getUser().setPassword(null);
        return Result.SUCCESS(loginUser, URL_INFO);
    }

    /*
     * @author x1aosx
     * @description 退出登录
     * @date 2022/10/10 14:43
     **/
    @GetMapping(URL_LOGOUT)
    public Result logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String username = loginUser.getUsername();
        redisUtil.delete(STR_USERNAME + username);
        log.info(username + "退出登录");
        return Result.SUCCESS(URL_LOGOUT);
    }


}
