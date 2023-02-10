package com.carl.admin.service;

import com.carl.parma.ProductSearchParam;
import com.carl.utils.R;

public interface ProductService {
    R search(ProductSearchParam productSearchParam);
}
