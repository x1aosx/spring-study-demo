package com.xiaosx.auth.controller;

import com.xiaosx.auth.entity.LoginUser;
import com.xiaosx.common.utils.RedisUtil;
import com.xiaosx.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaosx
 * @since 2022-10-292 21:16:34
 */
@RestController
@RequestMapping()
@Slf4j
public class MenuController {

    private final static String MENU_LIST = "/menu/list";

    private final static String STR_USERNAME = "username:";

    @Resource
    private RedisUtil redisUtil;

    @GetMapping(MENU_LIST)
    public Result menuList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//        String username = loginUser.getUsername();
        Collection<? extends GrantedAuthority> authorities = loginUser.getAuthorities();
//        loginUser.getAuthorities().toArray();
        return Result.SUCCESS(authorities,MENU_LIST);
    }
}
