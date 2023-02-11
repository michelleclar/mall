package com.carl.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carl.parma.Order;
import com.carl.vo.AdminOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: mall
 * @description:
 * @author: Mr.Carl
 **/
public interface OrderMapper extends BaseMapper<Order> {
    List<AdminOrderVo> selectAdminOrder(@Param("offset") int offset,@Param("pageSize") int pageSize);
}
