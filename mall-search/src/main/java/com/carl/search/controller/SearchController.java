package com.carl.search.controller;

import com.carl.parma.ProductSearchParam;
import com.carl.pojo.Product;
import com.carl.search.service.SearchService;
import com.carl.utils.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @program: mall
 * @description:
 * @author: Mr.Carl
 **/
@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("product")
    public R searchProduct(@RequestBody ProductSearchParam productSearchParam) throws JsonProcessingException {

        return searchService.search(productSearchParam);
    }
    /**
     * Carl
     * TODO:同步商品插入,覆蓋更新
     */
    @PostMapping("save")
    public R saveProduct(@RequestBody Product Product) throws IOException {

        return searchService.save(Product);
    }

    @PostMapping("remove")
    public R removeProduct(@RequestBody Integer productId) throws IOException {

        return searchService.remove(productId);
    }
}
