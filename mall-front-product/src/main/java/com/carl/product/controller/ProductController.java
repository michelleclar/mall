package com.carl.product.controller;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.carl.parma.*;
import com.carl.product.service.ProductService;
import com.carl.utils.R;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Map;

/**
 * @program: mall
 * @description: 商品
 * @author: Mr.Carl
 **/
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/promo")
    public R promo(@RequestBody @Validated ProductPromoParam productPromoParam, BindingResult result){

        if (result.hasErrors()){
            return R.fail("数据查询失败!");
        }

        return  productService.promo(productPromoParam.getCategoryName());
    }

    @PostMapping("hots")
    public R hots(@RequestBody @Validated ProductHotParam productHotParam, BindingResult result){

        if (result.hasErrors()){
            return R.fail("数据查询失败!");
        }

        return productService.hots(productHotParam);
    }

    @PostMapping("category/list")
    public R clist(){

        return productService.clist();
    }

    /**
     * 类别查询
     * @param productIdsParam
     * @return
     */
    @PostMapping("bycategory")
    public R byCategory(@RequestBody @Validated ProductIdsParam productIdsParam,BindingResult result){

        if (result.hasErrors()){
            return R.fail("类别商品查询失败!");
        }

        return productService.byCategory(productIdsParam);
    }

    /**
     * 查询全部商品,可以复用业务!
     * @param productIdsParam
     * @return
     */
    @PostMapping("all")
    public R all(@RequestBody @Validated ProductIdsParam productIdsParam,BindingResult result){

        if (result.hasErrors()){
            return R.fail("类别商品查询失败!");
        }

        return productService.byCategory(productIdsParam);
    }

    @PostMapping("detail")
    public R detail(@RequestBody @Validated ProductIdParam productIdParam,BindingResult result){

        if (result.hasErrors()){
            return R.fail("类别详情查询失败!");
        }

        return productService.detail(productIdParam.getProductID());
    }

    @PostMapping("pictures")
    public R Pictures(@RequestBody @Validated ProductIdParam productIdParam,BindingResult result){
        if (result.hasErrors()){
            return R.fail("商品图片详情查询失败!");
        }
        return productService.picture(productIdParam.getProductID());
    }

    @PostMapping("search")
    public R search(@RequestBody ProductSearchParam productSearchParam){

        return productService.search(productSearchParam);
    }


}
