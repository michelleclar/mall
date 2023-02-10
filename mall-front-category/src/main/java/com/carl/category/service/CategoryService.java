package com.carl.category.service;

import com.carl.parma.PageParam;
import com.carl.parma.ProductHotParam;
import com.carl.pojo.Category;
import com.carl.utils.R;

public interface CategoryService {

    /**
     * 根据类别名称,查询类别对象
     *
     * @param categoryName
     * @return
     */
    R byName(String categoryName);

    /**
     * 根据传入的热门类别名称集合!返回类别对应的id集合
     *
     * @param productHotParam
     * @return
     */
    R hotsCategory(ProductHotParam productHotParam);

    /**
     * 查询类别数据,进行返回!
     *
     * @return r 类别数据集合
     */
    R list();

    R listPage(PageParam pageParam);

    R adminSave(Category category);

    R adminRemove(Integer categoryId);

    R adminUpdate(Category category);
}
