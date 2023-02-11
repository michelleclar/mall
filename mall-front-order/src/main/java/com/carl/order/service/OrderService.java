package com.carl.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.carl.parma.Order;
import com.carl.parma.OrderParam;
import com.carl.parma.PageParam;
import com.carl.utils.R;

/**
 * @program: mall
 * @description:
 * @author: Mr.Carl
 **/
public interface OrderService extends IService<Order> {
    R save(OrderParam orderParam);

    R list(Integer userId);

    R check(Integer productId);

    R adminList(PageParam pageParam);
}
