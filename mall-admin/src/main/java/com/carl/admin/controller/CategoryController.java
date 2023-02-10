package com.carl.admin.controller;

import com.carl.admin.service.CategoryService;
import com.carl.parma.PageParam;
import com.carl.pojo.Category;
import com.carl.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: mall
 * @description:
 * @author: Mr.Carl
 **/
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public R pageList(PageParam pageParam){

        return categoryService.pageList(pageParam);
    }

    @PostMapping("save")
    public R save(Category category){

        return categoryService.save(category);
    }

    @PostMapping("remove")
    public R remove(Integer categoryId){

        return categoryService.remove(categoryId);
    }

    @PostMapping("update")
    public R update(Category category){

        return categoryService.update(category);
    }
}
