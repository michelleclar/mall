package com.carl.search.service;

import com.carl.parma.ProductSearchParam;
import com.carl.pojo.Product;
import com.carl.utils.R;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface SearchService {
    /**
     * Carl
     * TODO: 根据关键字进行数据库查询
     * param:
     * return:
     */
    R search(ProductSearchParam productSearchParam);

    R save(Product product) throws IOException;

    R remove(Integer productId) throws IOException;
}
