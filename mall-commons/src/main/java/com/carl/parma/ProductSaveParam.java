package com.carl.parma;

import com.carl.pojo.Product;
import lombok.Data;

/**
 * @program: mall
 * @description: 商品數據保存
 * @author: Mr.Carl
 **/
@Data
public class ProductSaveParam extends Product {

    //商品详情图片地址, 多图片地址使用 + 号拼接
    private String pictures;
}
