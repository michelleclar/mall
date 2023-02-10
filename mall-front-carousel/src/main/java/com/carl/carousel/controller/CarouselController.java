package com.carl.carousel.controller;

import com.baomidou.mybatisplus.annotation.TableName;
import com.carl.carousel.service.CarouselService;
import com.carl.utils.R;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * @program: mall
 * @description: 轮播图控制类
 * @author: Mr.Carl
 **/
@RestController
@RequestMapping("carousel")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    /**
     * 查询首页数据,查询优先级最高的四条
     * @return
     */
    @PostMapping("list")
    public R list(){

        return  carouselService.list();
    }
}
