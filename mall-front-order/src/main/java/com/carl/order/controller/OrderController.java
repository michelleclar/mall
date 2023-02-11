package com.carl.order.controller;

import com.carl.order.service.OrderService;
import com.carl.parma.CartListParam;
import com.carl.parma.OrderParam;
import com.carl.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: mall
 * @description: 订单
 * @author: Mr.Carl
 **/
@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    /**
     * 订单数据保存
     *
     * @param orderParam
     * @return
     */
    @PostMapping("save")
    public R save(@RequestBody OrderParam orderParam) {
        return orderService.save(orderParam);
    }

    /**
     * 订单集合查询,按照类别查询!
     * @param cartListParam
     * @return
     */
    @PostMapping("/list")
    public R list(@RequestBody @Validated CartListParam cartListParam, BindingResult result){

        if (result.hasErrors()){
            return R.fail("参数异常查询失败!");
        }

        return orderService.list(cartListParam.getUserId());
    }

    @PostMapping("remove/check")
    public R removeCheck(@RequestBody Integer productId){

        return orderService.check(productId);
    }
}
