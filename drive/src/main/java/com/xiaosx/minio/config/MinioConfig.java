package com.xiaosx.minio.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author x1aosx
 * @classname MinioConfig.java
 * @description TODO
 * @createTime 2022/10/20 18:32
 */
@Configuration
public class MinioConfig {
    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.port}")
    private int port;

    @Value("${minio.region}")
    private String region;

    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;

    @Bean
    public MinioClient getMinioClient() {
        return MinioClient.builder().endpoint(endpoint,port,true).region(region)
                .credentials(accessKey, secretKey).build();
    }
}
