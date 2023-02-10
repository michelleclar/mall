package com.carl.user.service;

import com.carl.parma.UserCheckParam;
import com.carl.parma.UserLoginParam;
import com.carl.pojo.User;
import com.carl.utils.R;

import java.util.List;

/**
 * projectName: b2c_cloud_store
 *
 * @author: carl
 * time: 2022/12/16 21:30 周日
 * description:
 */
public interface UserService{

    /**
     * 检查账号是否可用
     * @param userCheckParam
     * @return
     */
    R check(UserCheckParam userCheckParam);

    /**
     * TODO:
     *  注册业务
     *   2. 检查账号是否存在
     *   1. 密码加密处理
     *   3. 插入数据库数据
     *   4. 返回结果封装
     * @param user 参数已经校验,但是密码是明文!
     * @return 结果 001 004
     */
    R register(User user);

    /**
     * TODO:
     *  登录业务
     *   1. 密码的加密和加盐处理
     *   2. 账号和密码进行数据库查询.返回一个完整的数据库user对象
     *   3. 判断返回结果
     * @param userLoginParam 账号和密码 已经校验 但是密码是明文!
     * @return 结果 001 004
     */
    R login(UserLoginParam userLoginParam);

}
