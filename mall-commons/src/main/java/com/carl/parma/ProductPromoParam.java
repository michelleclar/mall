package com.carl.parma;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: mall
 * @description: 类别热门商品参数接收
 * @author: Mr.Carl
 **/
@Data
public class ProductPromoParam {

    @NotBlank
    private String categoryName;
}
