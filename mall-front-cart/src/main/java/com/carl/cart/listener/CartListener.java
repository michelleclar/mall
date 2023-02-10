package com.carl.cart.listener;

import com.carl.cart.service.CartService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: mall
 * @description: 监听消息
 * @author: Mr.Carl
 **/
@Component
public class CartListener {


    @Autowired
    private CartService cartService;

    /**
     * 购物车数据清空监听
     * @param cartIds //要清空的购物车数据集合
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "clear.queue"),
            exchange = @Exchange("topic.ex"),
            key = "clear.cart"
    ))
    public void subNumber(List<Integer> cartIds){
//        System.out.println("CartListener.subNumber");
//        System.out.println("cartIds = " + cartIds);

        //调用业务修改库存即可
        cartService.clearIds(cartIds);
    }

}

