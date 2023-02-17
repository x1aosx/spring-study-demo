package com.xiaosx.minio.controller;

import com.xiaosx.common.utils.Result;
import com.xiaosx.common.utils.WebUtils;
import com.xiaosx.minio.handler.VideoResourceHttpRequestHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author x1aosx
 * @classname PlayerController.java
 * @description TODO
 * @createTime 2023/02/16 18:47
 */
@RequestMapping()
@Controller
public class PlayerController {

    private final static String PLAYER_VIDEO = "/player/video";

    @Resource
    private VideoResourceHttpRequestHandler handler;

    @GetMapping(PLAYER_VIDEO)
    public void playerVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "C:\\Workspace\\Java\\demo\\drive\\src\\main\\resources\\media\\test.mp4";
        File file = new File(path);
        if (file.exists()){
            request.setAttribute(VideoResourceHttpRequestHandler.ATTR_FILE,path);
            handler.handleRequest(request, response);
        }else {
            Result gone = Result.GONE(PLAYER_VIDEO);
            WebUtils.renderString(response,gone);
        }
    }
}
