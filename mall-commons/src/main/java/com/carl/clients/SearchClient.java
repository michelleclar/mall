package com.carl.clients;

import com.carl.parma.ProductSearchParam;
import com.carl.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "search-service")
public interface SearchClient {

    /**
     * 搜索服务 商品查询
     * @param productSearchParam
     * @return
     */
    @PostMapping("/search/product")
    R search(@RequestBody ProductSearchParam productSearchParam);

}

