package com.carl.collect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carl.clients.ProductClient;
import com.carl.collect.mapper.CollectMapper;
import com.carl.collect.service.CollectService;
import com.carl.parma.ProductCollectParam;
import com.carl.parma.ProductIdsParam;
import com.carl.pojo.Collect;
import com.carl.pojo.Product;
import com.carl.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: mall
 * @description:
 * @author: Mr.Carl
 **/
@Service
@Slf4j
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private ProductClient productClient;


    /**
     * 收藏保存服务
     *
     * @param collect
     * @return 001 004
     */
    @Override
    public R save(Collect collect) {

        //数据库查询
        QueryWrapper<Collect> queryWrapper
                = new QueryWrapper<>();
        queryWrapper.eq("user_id",collect.getUserId());
        queryWrapper.eq("product_id",collect.getProductId());
        Long count = collectMapper.selectCount(queryWrapper);

        if ( count> 0){
            log.info("CollectServiceImpl.save业务结束，结果:{}",count);
            return R.fail("商品已在收藏夹! 无需二次添加!");
        }

        //实体类封装
        collect.setCollectTime(System.currentTimeMillis());
        //数据库插入
        int rows = collectMapper.insert(collect);
        log.info("CollectServiceImpl.save业务结束,结果:{}",rows);
        //结果封装
        return R.ok("商品添加成功!");
    }

    /**
     * 查询收藏列表
     *
     * @param userId
     * @return
     */
    @Override
    public R list(Integer userId) {

        //查询商品id
            QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("user_id",userId);
        queryWrapper.select("product_id");
        List<Object> list = collectMapper.selectObjs(queryWrapper);

        //结果封装
        Integer[] idsArray = list.toArray(new Integer[]{});
        List<Integer> ids = new ArrayList<>();
        ids = Arrays.asList(idsArray);

        if (ids.size() == 0){
            log.info("CollectServiceImpl.list业务结束，结果:{}");
            return R.ok(ids);
        }

        //调用商品服务
        ProductCollectParam productCollectParam = new ProductCollectParam();
        productCollectParam.setProductIds(ids);
        R r = productClient.productIds(productCollectParam);
        log.info("CollectServiceImpl.list业务结束，结果:{}",r);
        //结果封装
        return r;
    }

    /**
     * 删除收藏业务
     *
     * @param collect
     * @return 001 003
     */
    @Override
    public R remove(Collect collect) {
        //参数封装
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",collect.getUserId());
        queryWrapper.eq("product_id",collect.getProductId());

        int rows = collectMapper.delete(queryWrapper);

        log.info("CollectServiceImpl.remove业务结束，结果:{}",rows);

        return R.ok("收藏移除成功!");
    }
}
