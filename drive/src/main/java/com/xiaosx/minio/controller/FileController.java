package com.xiaosx.minio.controller;

import com.xiaosx.common.utils.Result;
import com.xiaosx.minio.util.MinioUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;

@RestController
@RequestMapping()
public class FileController {

    private final static String URL_UPLOAD_FILE = "/file/upload/file";

    private final static String URL_LIST_BUCKETS = "/file/list/buckets";

    private final static String URL_LIST_FILES = "/file/list/files";

    private final static String URL_DOWNLOAD = "/file/download";

    private final static String URL_DELETE_FILE = "/file/delete/file";

    private final static String URL_DELETE_BUCKET = "/file/delete/bucket";

    private final static String URL_COPY = "/file/copy";

    private final static String URL_INFO = "/file/info";

    private final static String URL_SIGN_URL = "/file/sign";

    private final static String URL_LIST_ALL_FILES = "/file/list/all/files";


    @Resource
    MinioUtil minioUtil;

    /*
     * @author x1aosx
     * @description 上传文件
     * @date 2022/10/20 21:43
     * @param uploadFile:
     * @param bucket:
     * @param objectName:
     **/
    @PostMapping(URL_UPLOAD_FILE)
    public Result fileUpload(@RequestParam MultipartFile uploadFile, @RequestParam String bucket,
                             @RequestParam(required = false) String objectName) throws Exception {
        minioUtil.createBucket(bucket);
        if (objectName != null) {
            minioUtil.uploadFile(uploadFile.getInputStream(), bucket, objectName + "/" + uploadFile.getOriginalFilename());
        } else {
            minioUtil.uploadFile(uploadFile.getInputStream(), bucket, uploadFile.getOriginalFilename());
        }
        return Result.SUCCESS(URL_UPLOAD_FILE);
    }

    /*
     * @author x1aosx
     * @description 显示所有的桶
     * @date 2022/10/20 21:42

     **/
    @GetMapping(URL_LIST_BUCKETS)
    public Result listBuckets() throws Exception {
        return Result.SUCCESS(minioUtil.listBuckets(), URL_LIST_BUCKETS);
    }

    /*
     * @author x1aosx
     * @description 返回桶内的所有文件
     * @date 2022/10/20 21:45
     * @param bucket: 桶名
     **/
    @GetMapping(URL_LIST_FILES)
    public Result listFiles(@RequestParam String bucket) throws Exception {
        return Result.SUCCESS(minioUtil.listFiles(bucket), URL_LIST_FILES);
    }

    /*
     * @author x1aosx
     * @description 下载文件
     * @date 2022/10/20 22:00
     * @param bucket:
     * @param objectName:
     * @param response:
     **/
    @GetMapping(URL_DOWNLOAD)
    public void download(@RequestParam String bucket, @RequestParam String objectName,
                         HttpServletResponse response) throws Exception {
        InputStream stream = minioUtil.download(bucket, objectName);
        ServletOutputStream output = response.getOutputStream();
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(objectName.substring(objectName.lastIndexOf("/") + 1), "UTF-8"));
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        IOUtils.copy(stream, output);
    }

    /*
     * @author x1aosx
     * @description 删除文件
     * @date 2022/10/20 22:33
     * @param bucket:
     * @param objectName:
     **/
    @GetMapping(URL_DELETE_FILE)
    public Result deleteFile(@RequestParam String bucket, @RequestParam String objectName) throws Exception {
        minioUtil.deleteObject(bucket, objectName);
        return Result.SUCCESS(URL_DELETE_FILE);
    }

    /*
     * @author xsx
     * @description
     * @date 2022/10/20 22:36
     * @param bucket: 删除桶
     **/
    @GetMapping(URL_DELETE_BUCKET)
    public Result deleteBucket(@RequestParam String bucket) throws Exception {
        minioUtil.deleteBucket(bucket);
        return Result.SUCCESS(URL_DELETE_BUCKET);
    }


    
    /*
     * @author xsx
     * @description //TODO 
     * @date 22:12 2022/12/30 
     * @param sourceBucket
     * @param sourceObject
     * @param targetBucket
     * @param targetObject
     **/
    @GetMapping(URL_COPY)
    public Result copyObject(@RequestParam String sourceBucket, @RequestParam String sourceObject, @RequestParam String targetBucket, @RequestParam String targetObject) throws Exception {
        minioUtil.copyObject(sourceBucket, sourceObject, targetBucket, targetObject);
        return Result.SUCCESS(URL_COPY);
    }
    
    /*
     * @author xsx
     * @description 获取对象信息
     * @date 11:16 2022/12/31 
     * @param bucket
     * @param objectName
     **/
    @GetMapping(URL_INFO)
    public Result getObjectInfo(@RequestParam String bucket, @RequestParam String objectName) throws Exception {

        return Result.SUCCESS(minioUtil.getObjectInfo(bucket, objectName), URL_INFO);
    }

    @GetMapping(URL_SIGN_URL)
    public Result getPreSignedObjectUrl(@RequestParam String bucket, @RequestParam String objectName, @RequestParam Integer expires) throws Exception {

        return Result.SUCCESS(minioUtil.getPreSignedObjectUrl(bucket, objectName, expires), URL_SIGN_URL);
    }

    @GetMapping(URL_LIST_ALL_FILES)
    public Result listAllFile() throws Exception {

        return Result.SUCCESS(minioUtil.listAllFile(), URL_LIST_ALL_FILES);
    }

}