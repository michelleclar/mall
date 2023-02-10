package com.carl.admin.controller;

import com.carl.admin.service.AdminUserService;
import com.carl.parma.AdminUserParam;
import com.carl.pojo.AdminUser;
import com.carl.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @program: mall
 * @description:
 * @author: Mr.Carl
 **/
@Slf4j
@RequestMapping("/user")
@RestController
public class AdminUserController {


    @Autowired
    private AdminUserService adminUserService;

    /**
     * 后台登录功能实现
     *
     * @param adminUserParam
     * @return
     */
    @PostMapping("login")
    public Object login(@Validated AdminUserParam adminUserParam, BindingResult bindingResult, HttpSession session) {

        //参数校验
        if (bindingResult.hasErrors()) {
            log.info("AdminUserController.login业务,参数异常!");
            return R.fail("登录失败,核心参数为null");
        }
        //校验验证码
        String varCode = adminUserParam.getVerCode();
        String captcha = (String) session.getAttribute("captcha");
        if (!varCode.equalsIgnoreCase(captcha)) {

            return R.fail("登录失败,验证码错误!");
        }
        //验证码通过验证数据
        AdminUser user = adminUserService.login(adminUserParam);

        if (user == null) {
            return R.fail("登录失败!账号或密码错误");
        }
        //获取数据存储到session共享域,后期登录访问判断
        //存储到session共享域  key = userInfo 其他页面固定读取
        session.setAttribute("userInfo", user);
        return R.ok("登录成功");
    }

    @GetMapping("logout")
    public R logout(HttpSession session){
        //清空session
        session.invalidate();

        return R.ok("退出登录成功!");
    }


}
