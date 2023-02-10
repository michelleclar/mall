package com.carl.admin.service.impl;

import com.carl.admin.service.UserService;
import com.carl.clients.UserClient;
import com.carl.parma.CartListParam;
import com.carl.parma.PageParam;
import com.carl.pojo.User;
import com.carl.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * @program: mall
 * @description:
 * @author: Mr.Carl
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserClient userClient;

    /**
     * 后台管理调用
     * @param pageParam
     * @return
     */
    //添加缓存功能! 修改 和 删除 以及添加用户 要清空 list.user对应缓存
    @Cacheable(value = "list.user",key = "#pageParam.currentPage+'-'+#pageParam.pageSize")
    @Override
    public R userList(PageParam pageParam) {
        R r = userClient.adminListPage(pageParam);
        log.info("UserServiceImpl.userList业务结束,结果:{}",r);
        return r;
    }
    /**
     * 删除用户数据
     *
     * @param cartListParam
     * @return
     */
    @CacheEvict(value = "list.user",allEntries = true)
    @Override
    public R userRemove(CartListParam cartListParam) {

        R r = userClient.adminRemove(cartListParam);

        log.info("UserServiceImpl.remove业务结束，结果:{}",r);
        return r;
    }

    @CacheEvict(value = "list.user",allEntries = true)
    @Override
    public R userUpdate(User user) {
        R r = userClient.adminUpdate(user);
        log.info("UserServiceImpl.userUpdate业务结束，结果:{}",r);
        return r;
    }

    @CacheEvict(value = "list.user",allEntries = true)
    @Override
    public R userSave(User user) {
        R r = userClient.adminSave(user);
        log.info("UserServiceImpl.userSave业务结束，结果:{}",r);
        return r;
    }


}
