package com.carl.parma;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @program: mall
 * @description: 热门商品参数接收对象
 * @author: Mr.Carl
 **/
@Data
public class ProductHotParam {

    @NotEmpty
    private List<String> categoryName;
}
