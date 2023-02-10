package com.carl.admin.controller;

import com.carl.admin.service.ProductService;
import com.carl.parma.ProductSearchParam;
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
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("list")
    public R adminList(ProductSearchParam productSearchParam){

        return productService.search(productSearchParam);
    }
}
