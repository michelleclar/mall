package com.carl.parma;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @program: mall
 * @description: 类别商品展示
 * @author: Mr.Carl
 **/
@Data
public class ProductIdsParam extends PageParam{

    @NotNull
    private List<Integer> categoryID;
}
