package com.carl.carousel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carl.carousel.mapper.CarouselMapper;
import com.carl.carousel.service.CarouselService;
import com.carl.pojo.Carousel;
import com.carl.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: mall
 * @description:
 * @author: Mr.Carl
 **/
@Slf4j
@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    /**
     * 查询优先级最高的四条轮播图数据
     *
     * @return
     */
    @Cacheable(value = "list.carousel",key = "#root.methodName",cacheManager = "cacheManagerDay")
    @Override
    public R list() {
        //声明数量
        int limit = 4 ; //至多查询四条

        //查询数据库
        IPage<Carousel> iPage = new Page<>(1,limit);
        QueryWrapper<Carousel> carouselQueryWrapper = new QueryWrapper<>();
        carouselQueryWrapper.orderByDesc("priority");
        IPage<Carousel> page = carouselMapper.selectPage(iPage, carouselQueryWrapper);

        List<Carousel> carouselList = page.getRecords();

        return R.ok(carouselList);
    }
}
