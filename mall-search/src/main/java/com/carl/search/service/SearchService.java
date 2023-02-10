package com.carl.search.service;

import com.carl.parma.ProductSearchParam;
import com.carl.utils.R;

public interface SearchService {
    /**
     * Carl
     * TODO: 根据关键字进行数据库查询
     * param:
     * return:
     */
    R search(ProductSearchParam productSearchParam);
}
