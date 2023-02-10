package com.carl.user.controller;

import com.carl.parma.CartListParam;
import com.carl.parma.PageParam;
import com.carl.pojo.User;
import com.carl.user.service.UserService;
import com.carl.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: mall
 * @description:
 * @author: Mr.Carl
 **/
@RestController
@RequestMapping("user")
public class UserAdminController {
    @Autowired
    private UserService userService;

    @PostMapping("admin/list")
    public R listPage(@RequestBody PageParam pageParam){

        return userService.listPage(pageParam);
    }

    @PostMapping("admin/remove")
    public R remove(@RequestBody CartListParam cartListParam){

        return userService.remove(cartListParam.getUserId());
    }

    /**
     * 后台管理调用,修改用户数据
     * @param user
     * @return
     */
    @PostMapping("admin/update")
    public  R update(@RequestBody User user){

        return userService.update(user);
    }

    @PostMapping("admin/save")
    public R save(@RequestBody User user){

        return userService.save(user);
    }
}
