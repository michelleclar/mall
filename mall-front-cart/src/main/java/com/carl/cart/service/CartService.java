package com.carl.cart.service;

import com.carl.parma.CartSaveParam;
import com.carl.pojo.Cart;
import com.carl.utils.R;

public interface CartService {

    /**
     * 添加购物车
     *
     * @param cartSaveParam
     * @return 001成功 002已经存在 003没有库存
     */
    R save(CartSaveParam cartSaveParam);

    /**
     * 查询购物车数据集合
     *
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 修改购物车数量
     *
     * @param cart
     * @return
     */
    R update(Cart cart);

    /**
     * 移除购物车数据
     *
     * @param cart
     * @return
     */
    R remove(Cart cart);
}
