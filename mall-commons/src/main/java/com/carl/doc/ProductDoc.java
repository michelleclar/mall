package com.carl.doc;

import com.carl.pojo.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: mall
 * @description: 用来存储商品搜索数据的实体类
 * @author: Mr.Carl
 **/
@Data
@NoArgsConstructor
public class ProductDoc extends Product {
    /**
     * Carl
     * TODO:名称 标题 描述
     * param:
     * return:
     */
    private String all;

    public ProductDoc(Product product){
        super(product.getProductId(),product.getProductName(),
                product.getCategoryId(),product.getProductTitle(),
                product.getProductIntro(),product.getProductPicture(),
                product.getProductPrice(),product.getProductSellingPrice(),
                product.getProductNum(),product.getProductSales());
        this.all = product.getProductName()+product.getProductTitle()+product.getProductIntro();
    }
}
