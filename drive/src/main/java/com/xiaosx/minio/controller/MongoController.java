package com.xiaosx.minio.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiaosx.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther: xsx
 * @Date: 2023/1/9 22:25
 * @Description: //TODO
 */
@RestController
@RequestMapping()
@Slf4j
public class MongoController {

    private final static String URL_MONGO_INSERT = "/mongo/insert";

    @Resource
    MongoTemplate mongoTemplate;

    @PostMapping(URL_MONGO_INSERT)
    public Result insert(@RequestBody JSONObject object) {
        log.info(JSONObject.toJSONString(object));
        mongoTemplate.insert(object, "template");
        return Result.SUCCESS(URL_MONGO_INSERT);
    }

}
