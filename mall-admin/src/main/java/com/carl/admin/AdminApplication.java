package com.carl.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @program: mall
 * @description:
 * @author: Mr.Carl
 **/
@MapperScan("com.carl.admin.mapper")
@SpringBootApplication
@EnableCaching //开启缓存支持
public class AdminApplication  {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}

