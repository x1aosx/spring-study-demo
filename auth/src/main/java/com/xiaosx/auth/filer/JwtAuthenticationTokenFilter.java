package com.xiaosx.auth.filer;


import com.xiaosx.auth.entity.LoginUser;
import com.xiaosx.common.utils.JwtUtil;
import com.xiaosx.common.utils.RedisUtil;
import com.xiaosx.common.utils.Result;
import com.xiaosx.common.utils.WebUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final static String STR_TOKEN = "token";

    private final static String STR_USERNAME = "username:";

    private final static String STR_TOKEN_INVALID = "TOKEN 非法";

    private final static String STR_USER_INVALID = "用户登录无效,清重新登录";

    @Resource
    private RedisUtil redisUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader(STR_TOKEN);
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String username;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            username = claims.getSubject();
        } catch (Exception e) {
            log.warn(STR_TOKEN_INVALID + "->" + token);
            Result result = Result.UNAUTHORIZED(STR_TOKEN_INVALID, request.getServletPath());
            WebUtils.renderString(response, result);
            return;
        }
        //从redis中获取用户信息
        String redisKey = STR_USERNAME + username;
        LoginUser loginUser = (LoginUser) redisUtil.get(redisKey);
        if (Objects.isNull(loginUser)) {
            Result result = Result.UNAUTHORIZED(STR_USER_INVALID, request.getServletPath());
            WebUtils.renderString(response, result);
            return;
        }
        //存入SecurityContextHolder
        //TODO 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}
