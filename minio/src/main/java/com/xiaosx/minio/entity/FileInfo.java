package com.xiaosx.minio.entity;

import lombok.Data;

/**
 * @author x1aosx
 * @classname Fileinfo.java
 * @description TODO
 * @createTime 2022/10/20 18:38
 */
@Data
public class FileInfo {
    private String filename;
    private boolean directory;
}
