package com.carl.product.controller;

import com.carl.pojo.Product;
import com.carl.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: mall
 * @description: 搜索服务调用的controller
 * @author: Mr.Carl
 **/
@RestController
@RequestMapping("product")
public class ProductSearchController {
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public List<Product> allList(){

        return productService.allList();
    }
}
