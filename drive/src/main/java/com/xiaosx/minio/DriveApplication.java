package com.xiaosx.minio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication(scanBasePackages = "com.xiaosx")
@EnableEurekaClient
public class DriveApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriveApplication.class, args);
    }

}
