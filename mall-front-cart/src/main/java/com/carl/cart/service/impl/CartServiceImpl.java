package com.carl.cart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carl.cart.mapper.CartMapper;
import com.carl.cart.service.CartService;
import com.carl.clients.ProductClient;
import com.carl.parma.CartSaveParam;
import com.carl.parma.ProductCollectParam;
import com.carl.parma.ProductIdParam;
import com.carl.parma.ProductIdsParam;
import com.carl.pojo.Cart;
import com.carl.pojo.Product;
import com.carl.utils.R;
import com.carl.vo.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @program: mall
 * @description:
 * @author: Mr.Carl
 **/
@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductClient productClient;

    /**
     * 添加购物车
     *
     * @param cartSaveParam
     * @return 001成功 002已经存在 003没有库存
     */
    @Override
    public R save(CartSaveParam cartSaveParam) {
        //查询关联的商品信息
        ProductIdParam productIdParam = new ProductIdParam();
        productIdParam.setProductID(cartSaveParam.getProductId());
        Product product = productClient.productDetail(productIdParam);

        if (product == null) {
            return R.fail("商品已经被删除,无法添加到购物车");
        }

        if (product.getProductNum() == 0) {
            R ok = R.ok("没有库存!无法购买");
            ok.setCode("003");
            return ok;
        }
        //2.检查是不是第一次添加
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", cartSaveParam.getUserId());
        queryWrapper.eq("product_id", cartSaveParam.getProductId());
        Cart cart = cartMapper.selectOne(queryWrapper);
        if (cart != null) {
            //不是第一次,直接返回已经添加过即可!
            //更新属性 + 1
            cart.setNum(cart.getNum() + 1);
            cartMapper.updateById(cart);
            R ok = R.ok("商品已经在购物车,数量+1!");
            ok.setCode("002");
            log.info("CartServiceImpl.save业务结束,结果:{}", ok);
            return ok;
        }
        //3.第一次结果封装
        cart = new Cart();
        cart.setNum(1);
        cart.setProductId(cartSaveParam.getProductId());
        cart.setUserId(cartSaveParam.getUserId());

        int rows = cartMapper.insert(cart);
        log.info("CartServiceImpl.save业务结束，结果:{}", rows);
        //结果封装
        CartVo cartVo = new CartVo(product, cart);

        return R.ok("购物车添加成功", cartVo);
    }

    /**
     * 查询购物车数据集合
     *
     * @param userId
     * @return
     */
    @Override
    public R list(Integer userId) {
        //查询用户id对应的购物车数据
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Cart> cartList = cartMapper.selectList(queryWrapper);

        if (cartList == null || cartList.size() == 0) {
            return R.ok("购物车没有数据!", cartList);
        }
        //封装商品集合,查询商品数据
        List<Integer> ids = new ArrayList<>();
        for (Cart cart : cartList) {
            ids.add(cart.getProductId());
        }

        ProductCollectParam productCollectParam = new ProductCollectParam();
        productCollectParam.setProductIds(ids);

        List<Product> productList = productClient.cartList(productCollectParam);
        //集合转map!
        Map<Integer, Product> map = productList.stream().
                collect(Collectors.
                        toMap(Product::getProductId, v -> v));
        System.out.println("map = " + map);
        //结果封装即可
        List<CartVo> list = new ArrayList<>(cartList.size());
        for (Cart cart : cartList) {
            CartVo cartVo = new CartVo(map.get(cart.getProductId()), cart);
            list.add(cartVo);
        }

        R ok = R.ok("数据库查询成功", list);
        log.info("CartServiceImpl.list业务结束，结果:{}", ok);
        return ok;
    }

    /**
     * 修改购物车数量
     *
     * @param cart
     * @return
     */
    @Override
    public R update(Cart cart) {
        //1.查询商品对应的详情
        ProductIdParam productIdParam = new ProductIdParam();
        productIdParam.setProductID(cart.getProductId());
        Product product = productClient.productDetail(productIdParam);

        //判断库存
        if (cart.getNum() > product.getProductNum()) {
            log.info("CartServiceImpl.update业务开始，商品被移除,无法添加!");
            return R.fail("修改失败!库存不足!");
        }

        //数据修改
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", cart.getUserId());
        queryWrapper.eq("product_id", cart.getProductId());
        Cart newCart = cartMapper.selectOne(queryWrapper);
        newCart.setNum(cart.getNum());

        int rows = cartMapper.updateById(newCart);

        log.info("CartServiceImpl.update业务结束，结果:{}", rows);
        return R.ok("购物车数量修改成功!");
    }

    /**
     * 移除购物车数据
     *
     * @param cart
     * @return
     */
    @Override
    public R remove(Cart cart) {
        //删除参数封装
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",cart.getUserId());
        queryWrapper.eq("product_id",cart.getProductId());
        //删除数据
        int rows = cartMapper.delete(queryWrapper);
        log.info("CartServiceImpl.remove业务结束，结果:{}", rows);

        return R.ok("删除数据成功!");
    }
}
