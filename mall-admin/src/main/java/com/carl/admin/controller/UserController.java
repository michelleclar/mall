package com.carl.admin.controller;

import com.carl.parma.CartListParam;
import com.carl.parma.PageParam;
import com.carl.admin.service.UserService;
import com.carl.pojo.User;
import com.carl.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: mall
 * @description: 用户模块
 * @author: Mr.Carl
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public R list(PageParam pageParam){

        return userService.userList(pageParam);
    }

    @PostMapping("remove")
    public R list(CartListParam cartListParam){

        return userService.userRemove(cartListParam);
    }

    @PostMapping("update")
    public R update(User user){

        return userService.userUpdate(user);
    }

    @PostMapping("save")
    public R save(User user){

        return userService.userSave(user);
    }
}
