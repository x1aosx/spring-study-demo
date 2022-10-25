package com.xiaosx.auth.handler;

import com.xiaosx.common.utils.Result;
import com.xiaosx.common.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author x1aosx
 * @classname AccessDeniedHandlerImpl.java
 * @description 权限异常处理
 * @createTime 2022/10/11 19:05
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    private final static String PERMISSION_DENIED = "权限不足";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result result = Result.FORBIDDEN(PERMISSION_DENIED, request.getServletPath());
        WebUtils.renderString(response, result);
    }
}
