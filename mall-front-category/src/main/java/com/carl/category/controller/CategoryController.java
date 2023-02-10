package com.carl.category.controller;

import com.carl.category.service.CategoryService;
import com.carl.parma.ProductHotParam;
import com.carl.parma.ProductIdsParam;
import com.carl.utils.R;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: mall
 * @description: 类别
 * @author: Mr.Carl
 **/
@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/promo/{categoryName}")
    public R byName(@PathVariable String categoryName){

        if (StringUtils.isEmpty(categoryName)){
            return R.fail("类别名称为null,无法查询类别数据!");
        }

        return categoryService.byName(categoryName);
    }

    /**
     * 热门类别id查询!
     * @param productHotParam
     * @param result
     * @return
     */
    @PostMapping("hots")
    public R hotsCategory(@RequestBody @Validated ProductHotParam productHotParam, BindingResult result){

        if (result.hasErrors()){
            return R.fail("类别集合查询失败!");
        }

        return categoryService.hotsCategory(productHotParam);
    }

    /**
     * Carl
     * TODO:
     * param:
     * return:
     */
    @GetMapping("list")
    public R list(){

        return categoryService.list();
    }


}
