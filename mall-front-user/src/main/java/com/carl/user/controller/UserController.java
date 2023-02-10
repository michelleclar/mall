package com.carl.user.controller;


import com.carl.parma.UserCheckParam;
import com.carl.parma.UserLoginParam;
import com.carl.pojo.User;
import com.carl.user.service.UserService;
import com.carl.utils.R;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * @program: mall
 * @description: 用户的控制模块
 * @author: Mr.Carl
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Carl
     * TODO:检查账号是否可用
     * @param userCheckParam 接收检查的账号实体 内部有参数校验注解
     * @param result 获取校验结果的实体对象
     * @return 返回封装结果R对象即可
     */
    @PostMapping("check")
    public R check(@RequestBody  @Validated UserCheckParam userCheckParam, BindingResult result){

        //检查是否符合检验注解的规则  符合 false  不符合 true
        boolean b = result.hasErrors();

        if (b){
            return R.fail("账号为null,不可使用!");
        }

        return userService.check(userCheckParam);
    }

    //注册
    @PostMapping("register")
    public R register(@RequestBody @Validated User user, BindingResult result){

        if (result.hasErrors()){
            //如果存在异常,证明请求参数不符合注解要求
            return  R.fail("参数异常,不可注册!");
        }

        return userService.register(user);
    }

    //登录
    @PostMapping("login")
    public R login(@RequestBody @Validated UserLoginParam userLoginParam, BindingResult result){

        if (result.hasErrors()){
            //如果存在异常,证明请求参数不符合注解要求
            return  R.fail("参数异常,不可登录!");
        }

        return userService.login(userLoginParam);
    }
}
