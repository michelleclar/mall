package com.carl.user.service;


import com.carl.pojo.Address;
import com.carl.utils.R;
import lombok.Data;

/**
 * projectName: b2c_cloud_store
 *
 * @author: carl
 * time: 2022/12/17 11:15 周一
 * description:
 */
public interface AddressService {

    /**
     * TODO:查询地址列表
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * TODO:插入地址数据,插入成功以后,要返回新的数据集合!
     *
     * @param address 地址数据已经校验完毕哦!
     * @return 数据集合
     */
    R save(Address address);

    /**
     *
     * TODO:
     *   1.定义接收参数的param 并且添加参数校验注解
     *   2.定义controller
     *   3.定义service
     *   4.定义mapper
     *
     * 根据id 删除地址数据
     *
     * @param id 地址id
     * @return 结果 001  004
     */
    R remove(Integer id);
}
