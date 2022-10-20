package com.xiaosx.security.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

@Slf4j
public class MyBatisPlusGenerator {

    private final static String URL = "jdbc:mariadb://www.shawsx.com:3306/spring?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&nullCatalogMeansCurrent=true";

    private final static String USERNAME = "spring";

    private final static String PASSWORD = "spring@2022";

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        log.info("当前路径为->"+projectPath);

        // 数据源配置
        DataSourceConfig.Builder dataSourceConfig = new DataSourceConfig
                .Builder(URL, USERNAME, PASSWORD)
                .dbQuery(new MySqlQuery())
                .typeConvert(new MySqlTypeConvert())
                .keyWordsHandler(new MySqlKeyWordsHandler());

        FastAutoGenerator.create(dataSourceConfig)
                .globalConfig(builder -> {
                    builder.author("xiaosx") //设置作者
                            .commentDate("YYYY-MM-DD HH:mm:ss")//注释日期
                            .outputDir(projectPath + "\\SpringSecurity\\src\\main\\java"); //指定输出目录

                })
                .packageConfig(builder -> {
                    builder.parent("com.xiaosx.security")// 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "\\SpringSecurity\\src\\main\\resources\\mapper"));
                })
                .strategyConfig(builder -> {

                    builder.addInclude("menu","role_menu","role","user_role"); // 设置需要生成的表名
//                            .addTablePrefix("tb_"); // 设置过滤表前缀
                    builder.entityBuilder().enableLombok().enableFileOverride();//开启 lombok 模型,并开启覆盖写入
                    builder.entityBuilder().enableTableFieldAnnotation();//开启生成实体时生成字段注解
                    builder.controllerBuilder().enableRestStyle();//开启生成@RestController 控制器

                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}