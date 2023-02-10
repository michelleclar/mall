package com.carl.product.controller;

import com.carl.parma.ProductCollectParam;
import com.carl.parma.ProductIdsParam;
import com.carl.pojo.Product;
import com.carl.product.service.ProductService;
import com.carl.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: mall
 * @description: 商品被收藏调用的controller
 * @author: Mr.Carl
 **/
@RestController
@RequestMapping("product")
public class ProductCollectController {

    @Autowired
    private ProductService productService;

    /**
     * 供收藏服务使用,根据传入的id,查询商品集合!
     * @return
     */
    @PostMapping("collect/list")
    public R list(@RequestBody @Validated ProductCollectParam productCollectParam, BindingResult result){

        if (result.hasErrors()){
            return R.ok("没有收藏数据");
        }
        return productService.ids(productCollectParam.getProductIds());
    }
}
