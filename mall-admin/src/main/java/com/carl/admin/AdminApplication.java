package com.carl.admin;

import com.carl.clients.CategoryClient;
import com.carl.clients.SearchClient;
import com.carl.clients.UserClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: mall
 * @description:
 * @author: Mr.Carl
 **/
@MapperScan("com.carl.admin.mapper")
@SpringBootApplication
@EnableCaching //开启缓存支持
@EnableFeignClients(clients = {UserClient.class, CategoryClient.class, SearchClient.class})
public class AdminApplication  {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}

