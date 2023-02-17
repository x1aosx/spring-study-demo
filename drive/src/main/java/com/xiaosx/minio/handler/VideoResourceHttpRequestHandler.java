package com.xiaosx.minio.handler;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author x1aosx
 * @classname VideoResourceHttpRequestHandler.java
 * @description TODO
 * @createTime 2023/02/16 19:07
 */
@Component
public class VideoResourceHttpRequestHandler extends ResourceHttpRequestHandler {

    public final static String ATTR_FILE = "NON-STATIC-FILE";

    @Override
    protected Resource getResource(HttpServletRequest request) throws IOException {
        String filepath = (String) request.getAttribute(ATTR_FILE);
        return new FileSystemResource(filepath);
    }
}
