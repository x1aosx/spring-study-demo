package com.xiaosx.security.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtils {

    private final static Integer STATUS = 200;

    private final static String CONTENT_TYPE = "application/json";

    private final static String ENCODING = "utf-8";


    /*
     * @author x1aosx
     * @description 将result返回给前端
     * @date 2022/10/12 14:33
     * @param response:
     * @param object:
     **/
    public static void renderString(HttpServletResponse response, Result result) {
        try {
            String string = JSON.toJSONString(result);
            response.setStatus(STATUS);
            response.setContentType(CONTENT_TYPE);
            response.setCharacterEncoding(ENCODING);
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}