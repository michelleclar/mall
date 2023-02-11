package com.carl.admin.service.impl;

import com.carl.admin.service.ProductService;
import com.carl.clients.ProductClient;
import com.carl.clients.SearchClient;
import com.carl.parma.ProductSaveParam;
import com.carl.parma.ProductSearchParam;
import com.carl.pojo.Product;
import com.carl.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: mall
 * @description:
 * @author: Mr.Carl
 **/
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private SearchClient searchClient;

    @Autowired
    private ProductClient productClient;
    @Override
    public R search(ProductSearchParam productSearchParam) {

        R search = searchClient.search(productSearchParam);
        log.info("ProductServiceImpl.search业务结束,结果:{}",search);
        return search;
    }

    @Override
    public R save(ProductSaveParam productSaveParam) {
        R r = productClient.adminSave(productSaveParam);
        log.info("ProductServiceImpl.save业务结束,结果:{}",r);
        return r;
    }

    @Override
    public R update(Product product) {
        R r = productClient.adminUpdate(product);
        log.info("ProductServiceImpl.update业务结束,结果:{}",r);
        return r;
    }

    @Override
    public R remove(Integer productId) {
        R r = productClient.adminRemove(productId);
        log.info("ProductServiceImpl.remove业务结束,结果:{}",r);
        return r;
    }
}
