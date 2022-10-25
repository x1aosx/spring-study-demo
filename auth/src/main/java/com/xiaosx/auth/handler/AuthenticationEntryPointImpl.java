package com.xiaosx.auth.handler;

import com.xiaosx.common.utils.Result;
import com.xiaosx.common.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author x1aosx
 * @classname AAuthenticationEntryPointImpl.java
 * @description 登录异常处理
 * @createTime 2022/10/11 19:05
 */
@Component
@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    private final static String AUTHENTICATION_FAILED = "认证失败,请重新登录";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result result =  Result.UNAUTHORIZED(AUTHENTICATION_FAILED,request.getServletPath());
        //处理异常
        WebUtils.renderString(response, result);
    }
}
