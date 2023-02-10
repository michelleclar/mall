package com.carl.to;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: mall
 * @description: 订单发送商品服务的实体类
 * @author: Mr.Carl
 **/
@Data
public class OrderToProduct implements Serializable {

    public static final Long serialVersionUID = 1L;
    //商品id
    private Integer productId;
    //购买数量
    private Integer productNum;
}
