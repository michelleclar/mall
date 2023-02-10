package com.carl.search.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.carl.parma.ProductSearchParam;
import com.carl.pojo.Product;
import com.carl.search.service.SearchService;
import com.carl.utils.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: mall
 * @description:
 * @author: Mr.Carl
 **/
@Slf4j
@Service
public class SearchServiceImpl  implements SearchService {
    @Autowired
    private RestHighLevelClient client;

    /**
     * 商品搜索
     * @param productSearchParam
     * @return
     */
    @Override
    public R search(ProductSearchParam productSearchParam) {

        SearchRequest searchRequest = new SearchRequest("product");
        String search = productSearchParam.getSearch();

        if (StringUtils.isEmpty(search)){
            //如果为null,查询全部
            searchRequest.source().query(QueryBuilders.matchAllQuery());
        }else{
            //不为空 all字段进行搜索
            searchRequest.source().query(QueryBuilders.matchQuery("all",search));
        }

        //设置分页参数
        searchRequest.source().from((productSearchParam.getCurrentPage()-1)*productSearchParam.getPageSize());
        searchRequest.source().size(productSearchParam.getPageSize());

        SearchResponse response = null;
        try {
            response = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw  new RuntimeException("查询错误");
        }

        //结果集解析
        //获取集中的结果
        SearchHits hits = response.getHits();
        //获取符合的数量
        long total = hits.getTotalHits().value;
        //数据集合
        SearchHit[] items = hits.getHits();

        List<Product> productList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for (SearchHit item : items) {
            //获取单挑json数据
            //查询的内容数据
            String json = item.getSourceAsString();
            Product product = null;
            try {
                product = objectMapper.readValue(json, Product.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            productList.add(product);
        }

        R ok = R.ok(null,productList,total);

        log.info("SearchServiceImpl.search业务结束,结果:{}",ok);
        return ok;
    }
}
