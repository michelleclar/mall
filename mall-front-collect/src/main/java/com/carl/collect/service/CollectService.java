package com.carl.collect.service;

import com.carl.pojo.Collect;
import com.carl.utils.R;

public interface CollectService {
    /**
     * 收藏保存服务
     * @param collect
     * @return
     */
    R save(Collect collect);

    /**
     * 查询收藏列表
     *
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 删除收藏业务
     *
     * @param collect
     * @return
     */
    R remove(Collect collect);

    R removeProduct(Integer productId);
}
