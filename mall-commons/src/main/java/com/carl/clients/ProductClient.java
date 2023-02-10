package com.carl.clients;

import com.carl.parma.ProductCollectParam;
import com.carl.parma.ProductIdParam;
import com.carl.parma.ProductIdsParam;
import com.carl.pojo.Product;
import com.carl.utils.R;
import javafx.geometry.Pos;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@FeignClient("product-service")
public interface ProductClient {

    /**
     * Carl
     * TODO:搜索服务调用,进行全部数据查询,用于搜索数据库同步数据
     * param:
     * return:
     */
    @GetMapping ("/product/list")
    List<Product> allList();

    /**
     * 收藏模块调用
     * @param productCollectParam
     * @return
     */
    @PostMapping("/product/collect/list")
    R productIds(@RequestBody ProductCollectParam productCollectParam);

    /**
     * 购物车模块调用
     * @param productIdParam
     * @return
     */
    @PostMapping("/product/cart/detail")
    Product productDetail(@RequestBody ProductIdParam productIdParam);

    @PostMapping("/product/cart/list")
    List<Product> cartList(@RequestBody ProductCollectParam productCollectParam);
    @PostMapping("/product/admin/count")
    Long adminCount(@RequestBody Integer categoryId);
}
