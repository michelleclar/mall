package com.carl.parma;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

import java.util.List;

/**
 * @program: mall
 * @description: 收藏调用商品传参
 * @author: Mr.Carl
 **/
@Data
public class ProductCollectParam {
    @NotEmpty
    private List<Integer> productIds;
}
