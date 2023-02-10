package com.carl.admin.service;

import com.carl.parma.AdminUserParam;
import com.carl.pojo.AdminUser;

public interface AdminUserService {

    /**
     * 后台管理登录页面
     * @param adminUserParam
     * @return
     */
    AdminUser login(AdminUserParam adminUserParam);
}
