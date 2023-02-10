package com.carl.product.listener;

import com.carl.product.service.ProductService;
import com.carl.to.OrderToProduct;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @program: mall
 * @description: 商品mp消息监听
 * @author: Mr.Carl
 **/
public class ProductListener {

    @Autowired
    private ProductService productService;

    /**
     * 修改库存数据
     * @param orderToProducts
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "sub.queue"),
            exchange = @Exchange("topic.ex"),
            key = "sub.number"
    ))
    public void subNumber(List<OrderToProduct> orderToProducts){
//        System.err.println("ProductListener.subNumber");
//        System.err.println("productNumberParams = " + orderToProducts);

        //调用业务修改库存即可
        productService.subNumber(orderToProducts);
    }
}
