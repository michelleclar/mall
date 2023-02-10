package com.carl.admin.service;

import com.carl.parma.PageParam;
import com.carl.pojo.Category;
import com.carl.utils.R;

public interface CategoryService {
    R pageList(PageParam pageParam);

    R save(Category category);

    R remove(Integer categoryId);

    R update(Category category);
}
