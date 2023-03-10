package com.carl.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.carl.clients.*;
import com.carl.parma.ProductHotParam;
import com.carl.parma.ProductIdsParam;
import com.carl.parma.ProductSaveParam;
import com.carl.parma.ProductSearchParam;
import com.carl.pojo.Picture;
import com.carl.pojo.Product;
import com.carl.product.mapper.PictureMapper;
import com.carl.product.mapper.ProductMapper;
import com.carl.product.service.ProductService;
import com.carl.to.OrderToProduct;
import com.carl.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: mall
 * @description: 商品服务实现类
 * @author: Mr.Carl
 **/
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper,Product> implements ProductService {


    //引入feign客户端需要,在启动类添加配置注解
    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private CartClient cartClient;


    @Autowired
    private CollectClient collectClient;

    @Autowired
    private SearchClient searchClient;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private PictureMapper pictureMapper;



//    @Autowired
//    private RabbitTemplate rabbitTemplate;

    /**
     * 单类别名称 查询热门商品 至多7条数据
     * 1. 根据类别名称 调用 feign客户端访问类别服务获取类别的数据
     * 2. 成功 继续根据类别id查询商品数据  [热门 销售量倒序 查询7]
     * 3. 结果封装即可
     *
     * @param categoryName 类别名称
     * @return r
     */
    @Cacheable(value = "list.product", key = "#categoryName")
    @Override
    public R promo(String categoryName) {

        R r = categoryClient.byName(categoryName);

        if (r.getCode().equals(R.FAIL_CODE)) {
            log.info("ProductServiceImpl.promo业务结束，结果:{}", "类别查询失败!");
            return r;
        }

        // 类别服务中 data = category --- feign {json}  ----- product服务 LinkedHashMap jackson

        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) r.getData();

        Integer categoryId = (Integer) map.get("category_id");
        //封装查询参数
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        queryWrapper.orderByDesc("product_sales");

        IPage<Product> page = new Page<>(1, 7);

        //返回的是包装数据! 内部有对应的商品集合,也有分页的参数 例如: 总条数 总页数等等
        page = productMapper.selectPage(page, queryWrapper);

        List<Product> productList = page.getRecords(); //指定页的数据
        long total = page.getTotal(); //获取总条数

        log.info("ProductServiceImpl.promo业务结束，结果:{}", productList);

        return R.ok("数据查询成功", productList);
    }

    /**
     * 多类别热门商品查询 根据类别名称集合! 至多查询7条!
     * 1. 调用类别服务
     * 2. 类别集合id查询商品
     * 3. 结果集封装即可
     *
     * @param productHotParam 类别名称集合
     * @return r
     */
    @Override
    @Cacheable(value = "list.product", key = "#productHotParam.categoryName")
    public R hots(ProductHotParam productHotParam) {

        R r = categoryClient.hots(productHotParam);

        if (r.getCode().equals(R.FAIL_CODE)) {
            log.info("ProductServiceImpl.hots业务结束，结果:{}", r.getMsg());
            return r;
        }

        List<Object> ids = (List<Object>) r.getData();

        //进行商品数据查询
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_id", ids);
        queryWrapper.orderByDesc("product_sales");

        IPage<Product> page = new Page<>(1, 7);

        page = productMapper.selectPage(page, queryWrapper);

        List<Product> records = page.getRecords();

        R ok = R.ok("多类别热门商品查询成功!", records);

        log.info("ProductServiceImpl.hots业务结束，结果:{}", ok);

        return ok;
    }

    /**
     * 查询类别商品集合
     *
     * @return
     */
    @Cacheable(value = "list.category", key = "#root.methodName", cacheManager = "cacheManagerDay")
    @Override
    public R clist() {
        R r = categoryClient.list();
        log.info("ProductServiceImpl.clist业务结束，结果:{}", r);

        return r;
    }

    /**
     * 类别商品查询 前端传递类别集合
     *
     * @param productIdsParam
     * @return
     */
    @Cacheable(value = "list.product", key = "#productIdsParam.categoryID+'-'+#productIdsParam.currentPage + '-'+#productIdsParam.pageSize")
    @Override
    public R byCategory(ProductIdsParam productIdsParam) {

        //1.拆分请求参数
        List<Integer> categoryID = productIdsParam.getCategoryID();

        //2.请求条件封装
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        if (!categoryID.isEmpty()) {
            productQueryWrapper.in("category_id", categoryID);
        }
        IPage<Product> page = new Page<>(productIdsParam.getCurrentPage(), productIdsParam.getPageSize());
        //3.数据查询
        page = productMapper.selectPage(page, productQueryWrapper);
        //4.结果封装
        List<Product> productList = page.getRecords();
        long total = page.getTotal();

        R ok = R.ok("查询成功", productList, total);

        log.info("ProductServiceImpl.byCategory业务结束，结果:{}", ok);
        return ok;
    }

    /**
     * 查询商品集合
     *
     * @param productIds
     * @return
     */
    @Cacheable(value = "list.product", key = "#productIds")
    @Override
    public R ids(List<Integer> productIds) {

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("product_id", productIds);

        List<Product> productList = productMapper.selectList(queryWrapper);

        R r = R.ok("类别信息查询成功", productList);

        log.info("ProductServiceImpl.ids业务结束，结果:{}", r);
        return r;
    }

    /**
     * 全部商品查询,可以进行类别集合数据查询业务复用
     *
     * @param productIdsParam
     * @return
     */
    public R all(ProductIdsParam productIdsParam) {

        return byCategory(productIdsParam);
    }

    /**
     * 查询商品详情
     *
     * @param productID 商品id
     * @return
     */
    @Override
    @Cacheable(value = "product", key = "#productID")
    public R detail(Integer productID) {

        Product product = productMapper.selectById(productID);

        R ok = R.ok(product);

        log.info("ProductServiceImpl.detail业务结束，结果:{}", ok);

        return ok;
    }

    /**
     * 查询商品图片
     *
     * @param productID
     * @return
     */
    @Override
    @Cacheable(value = "picture", key = "#productID")
    public R picture(Integer productID) {

        //参数封装
        QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productID);
        //数据库查询
        List<Picture> pictureList = pictureMapper.selectList(queryWrapper);
        //结果封装
        R r = R.ok(pictureList);

        log.info("ProductServiceImpl.pictures业务结束，结果:{}", r);

        return r;
    }

    /**
     * Carl
     * TODO:搜索服务调用,获取全部商品数据
     * 进行同步
     * param:
     * return:
     */
    @Override
    public List<Product> allList() {

        List<Product> productList = productMapper.selectList(null);
        log.info("ProductServiceImpl.allList业务结束, 结果:{}", productList);
        return productList;
    }

    @Override
    public R search(ProductSearchParam productSearchParam) {

        R r = searchClient.search(productSearchParam);
        log.info("ProductServiceImpl.search业务结束，结果:{}", r);
        return r;
    }

    /**
     * 查询购物车数据集合
     *
     * @param productIds
     * @return
     */
    @Override
    public List<Product> cartList(List<Integer> productIds) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("product_id", productIds);

        List<Product> productList = productMapper.selectList(queryWrapper);

        log.info("ProductServiceImpl.cartList业务结束，结果:{}", productList);
        return productList;
    }

    /**
     * 修改库存,增加销售量
     *
     * @param orderToProducts
     */
    @Override
    public void subNumber(List<OrderToProduct> orderToProducts) {
        //将productNumberParams转成map
        //使用id作为key, item做值, 比较相邻的两次key,如果相同,去掉重读!
        Map<Integer, OrderToProduct> productNumberParamMap = orderToProducts.stream()
                .collect(Collectors.toMap(OrderToProduct::getProductId, v -> v));

        //封装商品集合
        Set<Integer> productIds = productNumberParamMap.keySet();

        //查询
        List<Product> productList = productMapper.selectBatchIds(productIds);
        //修改

        for (Product product : productList) {
            Integer num = productNumberParamMap.get(product.getProductId()).getProductNum();
            //设置新库存
            product.setProductNum(product.getProductNum() - num);
            //设置销售量
            product.setProductSales(product.getProductSales() + num);
        }


        //批量数据更新
        this.updateBatchById(productList);
        log.info("ProductServiceImpl.subNumber业务结束，结果:{}", productList);
    }

    @Override
    public Long adminCount(Integer categoryId) {

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id",categoryId);
        Long count = baseMapper.selectCount(queryWrapper);
        log.info("ProductServiceImpl.adminCount业务结束，结果:{}", count);
        return count;
    }

    /**
     * 保存商品信息
     *   1.保存商品信息
     *   2.保存商品图片信息
     *   3.发送消息,es库进行插入
     * @param productSaveParam
     * @return
     */
    @CacheEvict(value = "list.product",allEntries = true)
    @Transactional
    @Override
    public R adminSave(ProductSaveParam productSaveParam) {

        Product product = new Product();
        //参数赋值
        BeanUtils.copyProperties(productSaveParam,product);

        int rows = productMapper.insert(product);

        log.info("ProductServiceImpl.adminSave业务结束，结果:{}", rows);
        //进行Picture对象封装
        String pictures = productSaveParam.getPictures();

        if (!StringUtils.isEmpty(pictures)){
            //$ + - * | / ？^符号在正则表达示中有相应的不同意义。
            //一般来讲只需要加[]、或是\\即可
            String[] pics = pictures.split("\\+");
            for (String pic : pics) {
                Picture picture = new Picture();
                picture.setIntro(null);
                picture.setProductId(product.getProductId());
                picture.setProductPicture(pic);
                //插入圖片
                pictureMapper.insert(picture);
            }
        }

        searchClient.saveOrUpdate(product);


//        //商品数据保存
//        int rows = productMapper.insert(product);
//
//        if (rows == 0){
//            return R.fail("商品保存失败!");
//        }
//
//        //保存成功,进行发送消息,product插入到es库中
//        rabbitTemplate.convertAndSend("topic.ex","insert.product",product);
        return R.ok("商品数据保存成功!");
    }

    /**
     * 商品数据进行更新
     *   1.更新数据
     *   2.通知es服务,进行更新数据
     * @param product
     * @return
     */
    @Override
    public R adminUpdate(Product product) {

        int rows = baseMapper.updateById(product);

        if (rows == 0){
            return R.fail("商品数据更新失败!");
        }
        searchClient.saveOrUpdate(product);
//        //es更新就是插入覆盖即可~
//        rabbitTemplate.convertAndSend("topic.ex",
//                "insert.product",product);

        return R.ok("商品数据更新成功!");
    }


    @Caching(
            evict = {
                    @CacheEvict(value = "list.product",allEntries = true),
                    @CacheEvict(value = "product",key = "#productId")
            }
    )
    @Override
    public R adminRemove(Integer productId) {
        //检查购物车
        R check = cartClient.check(productId);
        if ("004".equals(check.getCode())){
            log.info("ProductServiceImpl.adminRemove业务结束，结果:{}", check.getMsg());
        }
        //检查订单
        check = orderClient.check(productId);
        if ("004".equals(check.getCode())){
            log.info("ProductServiceImpl.adminRemove业务结束，结果:{}", check.getMsg());
        }
        //删除商品,商品图片
        productMapper.deleteById(productId);
        QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",productId);
        pictureMapper.delete(queryWrapper);
        //删除收藏
        collectClient.remove(productId);
        //进行es数据同步
        searchClient.saveOrRemove(productId);
        //清空缓存

        return R.ok("商品删除成功");
    }
}

