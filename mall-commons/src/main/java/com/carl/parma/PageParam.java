package com.carl.parma;

import lombok.Data;

/**
 * @program: mall
 * @description: 分页的属性
 * @author: Mr.Carl
 **/
@Data
public class PageParam {

    private int currentPage = 1;
    private int pageSize = 15;
}
