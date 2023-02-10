package com.carl.admin.service.impl;

import com.carl.admin.service.CategoryService;
import com.carl.clients.CategoryClient;
import com.carl.parma.PageParam;
import com.carl.pojo.Category;
import com.carl.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @program: mall
 * @description:
 * @author: Mr.Carl
 **/
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryClient categoryClient;

    @Cacheable(value="list.category",key = "#pageParam.currentPage+'-'+#pageParam.pageSize")
    @Override
    public R pageList(PageParam pageParam) {

        R r = categoryClient.pageList(pageParam);
        log.info("CategoryServiceImpl.listPage业务结束，结果:{}",r);
        return r;
    }

    /**
     * 类别数据保存
     *
     * @param category
     * @return
     */
    @CacheEvict(value = "list.category",allEntries = true)
    @Override
    public R save(Category category) {

        //类别数据保存
        R r = categoryClient.adminSave(category);

        log.info("CategoryServiceImpl.save业务结束，结果:{}",r);

        return r;
    }

    @CacheEvict(value = "list.category",allEntries = true)
    @Override
    public R remove(Integer categoryId) {

        R r = categoryClient.adminRemove(categoryId);

        log.info("CategoryServiceImpl.remove业务结束，结果:{}",r);
        return r;
    }

    @CacheEvict(value = "list.category",allEntries = true)
    @Override
    public R update(Category category) {
        R r = categoryClient.adminUpdate(category);

        log.info("CategoryServiceImpl.update业务结束，结果:{}",r);
        return r;
    }
}
