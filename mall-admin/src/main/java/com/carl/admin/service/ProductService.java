package com.carl.admin.service;

import com.carl.parma.ProductSaveParam;
import com.carl.parma.ProductSearchParam;
import com.carl.pojo.Product;
import com.carl.utils.R;

public interface ProductService {
    R search(ProductSearchParam productSearchParam);

    R save(ProductSaveParam productSaveParam);

    R update(Product product);

    R remove(Integer productId);
}
