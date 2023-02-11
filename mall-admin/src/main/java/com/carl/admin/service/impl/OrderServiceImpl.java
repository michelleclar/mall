package com.carl.admin.service.impl;

import com.carl.admin.service.OrderService;
import com.carl.clients.OrderClient;
import com.carl.parma.PageParam;
import com.carl.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: mall
 * @description:
 * @author: Mr.Carl
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderClient orderClient;
    @Override
    public R list(PageParam pageParam) {

        R list = orderClient.list(pageParam);
        log.info("ProductServiceImpl.list业务结束,结果:{}",list);
        return list;
    }
}
