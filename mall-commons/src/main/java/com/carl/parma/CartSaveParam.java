package com.carl.parma;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: mall
 * @description: 购物车添加参数接收
 * @author: Mr.Carl
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartSaveParam {

    @JsonProperty("user_id")
    @NotNull
    private Integer userId;
    @JsonProperty("product_id")
    @NotNull
    private Integer productId;
   // private Integer num;
}
