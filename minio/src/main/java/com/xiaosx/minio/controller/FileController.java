package com.xiaosx.minio.controller;

import com.xiaosx.minio.util.MinioUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;

@RestController
@RequestMapping(value = "/file")
public class FileController {

    @Resource
    MinioUtil minioUtil;

    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    public Result fileUpload(@RequestParam MultipartFile uploadFile, @RequestParam String bucket,
                                    @RequestParam(required = false) String objectName) throws Exception {
        minioUtil.createBucket(bucket);
        if (objectName != null) {
            minioUtil.uploadFile(uploadFile.getInputStream(), bucket, objectName + "/" + uploadFile.getOriginalFilename());
        } else {
            minioUtil.uploadFile(uploadFile.getInputStream(), bucket, uploadFile.getOriginalFilename());
        }
        return Result.success();
    }

    @RequestMapping(value = "/listBuckets", method = RequestMethod.GET)
    public Result listBuckets() throws Exception {
        return Result.success(minioUtil.listBuckets());
    }

    @RequestMapping(value = "/listFiles", method = RequestMethod.GET)
    public Result listFiles(@RequestParam String bucket) throws Exception {
        return Result.success(minioUtil.listFiles(bucket));
    }

    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public void downloadFile(@RequestParam String bucket, @RequestParam String objectName,
                             HttpServletResponse response) throws Exception {
        InputStream stream = minioUtil.download(bucket, objectName);
        ServletOutputStream output = response.getOutputStream();
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(objectName.substring(objectName.lastIndexOf("/") + 1), "UTF-8"));
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        IOUtils.copy(stream, output);
    }


    @RequestMapping(value = "/deleteFile", method = RequestMethod.GET)
    public Result deleteFile(@RequestParam String bucket, @RequestParam String objectName) throws Exception {
        minioUtil.deleteObject(bucket, objectName);
        return Result.success();
    }

    @RequestMapping(value = "/deleteBucket", method = RequestMethod.GET)
    public Result deleteBucket(@RequestParam String bucket) throws Exception {
        minioUtil.deleteBucket(bucket);
        return Result.success();
    }


    @GetMapping("/copyObject")
    public Result copyObject(@RequestParam String sourceBucket, @RequestParam String sourceObject, @RequestParam String targetBucket, @RequestParam String targetObject) throws Exception {
        minioUtil.copyObject(sourceBucket, sourceObject, targetBucket, targetObject);
        return Result.success();
    }

    @GetMapping("/getObjectInfo")
    public Result getObjectInfo(@RequestParam String bucket, @RequestParam String objectName) throws Exception {

        return Result.success(minioUtil.getObjectInfo(bucket, objectName));
    }

    @GetMapping("/getPresignedObjectUrl")
    public Result getPresignedObjectUrl(@RequestParam String bucket, @RequestParam String objectName, @RequestParam Integer expires) throws Exception {

        return Result.success(minioUtil.getPresignedObjectUrl(bucket, objectName, expires));
    }

    @GetMapping("/listAllFile")
    public Result listAllFile() throws Exception {

        return Result.success(minioUtil.listAllFile());
    }


}