package com.carl.parma;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: mall
 * @description: 商品id参数接收
 * @author: Mr.Carl
 **/
@Data
public class ProductIdParam {

    @NotNull
    private Integer productID;
}
