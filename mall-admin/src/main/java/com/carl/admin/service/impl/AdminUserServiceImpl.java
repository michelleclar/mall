package com.carl.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carl.admin.mapper.AdminUserMapper;
import com.carl.admin.service.AdminUserService;
import com.carl.parma.AdminUserParam;
import com.carl.pojo.AdminUser;
import com.carl.constants.UserConstants;
import com.carl.utils.MD5Util;
import com.carl.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: mall
 * @description:
 * @author: Mr.Carl
 **/
@Service
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;
    /**
     * 后台管理登录页面
     *
     * @param adminUserParam
     * @return
     */
    @Override
    public AdminUser login(AdminUserParam adminUserParam) {
        //密码加密处理
        //代码加密处理,注意加盐,生成常量
        String newPwd = MD5Util.encode(adminUserParam.getUserPassword() +
                UserConstants.USER_SLAT);

        //数据库登录查询
        QueryWrapper<AdminUser> adminUserQueryWrapper = new QueryWrapper<>();

        adminUserQueryWrapper.eq("user_account",adminUserParam.getUserAccount());
        adminUserQueryWrapper.eq("user_password",newPwd);

        AdminUser adminUser = adminUserMapper.selectOne(adminUserQueryWrapper);
        //结果封装
        log.info("AdminUserServiceImpl.login业务结束，结果:{}",adminUser);

        return adminUser;
    }
}
