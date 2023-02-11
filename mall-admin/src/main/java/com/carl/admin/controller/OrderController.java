package com.carl.admin.controller;

import com.carl.admin.service.OrderService;
import com.carl.parma.PageParam;
import com.carl.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: mall
 * @description:
 * @author: Mr.Carl
 **/
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public R list(PageParam pageParam){

        return orderService.list(pageParam);
    }
}
