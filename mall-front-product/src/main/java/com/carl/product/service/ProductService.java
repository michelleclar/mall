package com.carl.product.service;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.service.IService;
import com.carl.parma.ProductHotParam;
import com.carl.parma.ProductIdsParam;
import com.carl.parma.ProductSearchParam;
import com.carl.pojo.Product;
import com.carl.to.OrderToProduct;
import com.carl.utils.R;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface ProductService extends IService<Product> {

    /**
     * 单类别名称 查询热门商品 至多7条数据
     *    1. 根据类别名称 调用 feign客户端访问类别服务获取类别的数据
     *    2. 成功 继续根据类别id查询商品数据  [热门 销售量倒序 查询7]
     *    3. 结果封装即可
     * @param categoryName 类别名称
     * @return r
     */
    R promo(String categoryName);

    /**
     * 多类别热门商品查询 根据类别名称集合! 至多查询7条!
     *   1. 调用类别服务
     *   2. 类别集合id查询商品
     *   3. 结果集封装即可
     * @param productHotParam 类别名称集合
     * @return r
     */
    R hots(ProductHotParam productHotParam);

    /**
     * 查询类别商品集合
     *
     * @return
     */
    R clist();

    /**
     * 类别商品查询 前端传递类别集合
     *
     * @param productIdsParam
     * @return
     */
    R byCategory(ProductIdsParam productIdsParam);

    /**
     * 全部商品查询,可以进行类别集合数据查询业务复用
     *
     * @param productIdsParam
     * @return
     */
    R all(ProductIdsParam productIdsParam);

    /**
     * 查询商品详情
     *
     * @param productID 商品id
     * @return
     */
    R detail(Integer productID);

    /**
     * 查询商品图片
     *
     * @param productID
     * @return
     */
    R picture(Integer productID);

    /**
     * Carl
     * TODO:搜索服务调用,获取全部商品数据
     * 进行同步
     * param:
     * return:
     */
    List<Product> allList();

    R search(ProductSearchParam productSearchParam);

    /**
     * 查询商品集合
     * @param productIds
     * @return
     */
    R ids(List<Integer> productIds);

    /**
     * 查询购物车数据集合
     *
     * @param productIds
     * @return
     */
    List<Product> cartList(List<Integer> productIds);

    /**
     * 修改库存,增加销售量
     */
    void subNumber(List<OrderToProduct> orderToProducts);
}
