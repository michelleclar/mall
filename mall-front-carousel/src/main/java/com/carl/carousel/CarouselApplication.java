package com.carl.carousel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @program: mall
 * @description: 轮播图启动类
 * @author: Mr.Carl
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.carl.carousel.mapper")
@EnableCaching
public class CarouselApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarouselApplication.class,args);
    }
}
