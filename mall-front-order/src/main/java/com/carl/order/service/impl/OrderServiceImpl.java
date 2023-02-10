package com.carl.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.carl.clients.ProductClient;
import com.carl.order.mapper.OrderMapper;
import com.carl.order.service.OrderService;
import com.carl.parma.Order;
import com.carl.parma.OrderParam;
import com.carl.parma.ProductCollectParam;
import com.carl.parma.ProductIdsParam;
import com.carl.pojo.Product;
import com.carl.to.OrderToProduct;
import com.carl.utils.R;
import com.carl.vo.CartVo;
import com.carl.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: mall
 * @description:
 * @author: Mr.Carl
 **/
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private ProductClient productClient;

    /**
     * 消息队列发送
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 订单保存业务
     * 库存和购物车使用mq异步,避免分布式事务!
     *
     * @param orderParam
     * @return
     */
    @Transactional
    @Override
    public R save(OrderParam orderParam) {

        //修改清空购物车的参数
        List<Integer> cartIds = new ArrayList<>();
        //修改批量插入数据库的参数
        List<Order> orderList = new ArrayList<>();
        //商品修改库存参数集合
        List<OrderToProduct> orderToProducts = new ArrayList<>();

        //生成数据
        Integer userId = orderParam.getUserId();
        //使用时间戳 + 做订单编号和事件
        long orderId = System.currentTimeMillis();
        //统一生成订单编号和创建时间
        //封装order实体类集合
        for (CartVo cartVo : orderParam.getProducts()) {
            cartIds.add(cartVo.getId()); //进行购物车订单保存
            //修改信息存储
            OrderToProduct orderToProduct = new OrderToProduct();
            orderToProduct.setProductNum(cartVo.getNum());
            orderToProduct.setProductId(cartVo.getProductID());
            orderToProducts.add(orderToProduct); //添加集合
            //订单信息保存
            Order order = new Order();
            order.setOrderId(orderId);
            order.setUserId(userId);
            order.setOrderTime(orderId);
            order.setProductId(cartVo.getProductID());
            order.setProductNum(cartVo.getNum());
            order.setProductPrice(cartVo.getPrice());
            orderList.add(order); //添加用户信息

        }
        //批量数据插入//
        this.saveBatch(orderList); //批量保存

        //修改商品库存 [product-service] [异步通知]
        /**
         *  交换机: topic.ex
         *  routingkey: sub.number
         *  消息: 商品id和减库存数据集合
         */
        rabbitTemplate.convertAndSend("topic.ex", "sub.number", orderToProducts);
        //清空对应购物车数据即可 [注意: 不是清空用户所有的购物车数据] [cart-service] [异步通知]
        /**
         * 交换机:topic.ex
         * routingkey: clear.cart
         * 消息: 要清空的购物车id集合
         */
        rabbitTemplate.convertAndSend("topic.ex", "clear.cart", cartIds);

        R ok = R.ok("订单生成成功!");
        log.info("OrderServiceImpl.save业务结束，结果:{}", ok);
        return ok;
    }

    /**
     * 订单数据查询业务
     *
     * @param userId
     * @return
     */
    @Override
    public R list(Integer userId) {
        //查询用户对应的全部订单数据
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("user_id",userId);
        List<Order> orderList = this.list(orderQueryWrapper);

        //数据按订单分组
        Map<Long, List<Order>> orderMap = orderList.stream().collect(Collectors.groupingBy(Order::getOrderId));
        //商品数据
//        Set<Integer> productIds = new HashSet<>();
//        for (Order order : orderList) {
//            productIds.add(order.getProductId());
//        }
        List<Integer> productIds = orderList.stream().map(Order::getProductId).collect(Collectors.toList());




        //结果集封装,返回即可
        ProductCollectParam productCollectParam = new ProductCollectParam();
        productCollectParam.setProductIds(productIds);
        List<Product> productList = productClient.cartList(productCollectParam);

        //商品数据
        Map<Integer, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, v -> v));

        //结果封装
        List<List<OrderVo>> result = new ArrayList<>();

        //订单集合
        for (List<Order> orders : orderMap.values()) {
            List<OrderVo> orderVos = new ArrayList<>();
            for (Order order : orders) {
                //返回vo数据封装
                OrderVo orderVo = new OrderVo();
                BeanUtils.copyProperties(order,orderVo);
                Product product = productMap.get(order.getProductId());
                orderVo.setProductName(product.getProductName());
                orderVo.setProductPicture(product.getProductPicture());
//                orderVo.setProductName(product.getProductName());
//                orderVo.setProductPicture(product.getProductPicture());
//                orderVo.setId(order.getId());
//                orderVo.setOrderId(order.getOrderId());
//                orderVo.setOrderTime(order.getOrderTime());
//                orderVo.setProductNum(order.getProductNum());
//                orderVo.setProductId(order.getProductId());
//                orderVo.setProductPrice(order.getProductPrice());
//                orderVo.setUserId(order.getUserId());
                orderVos.add(orderVo);
            }
            result.add(orderVos);
        }

        R ok = R.ok("订单数据获取成功",result);
        log.info("OrderServiceImpl.list业务结束，结果:{}",ok);
        return ok;
    }

}
