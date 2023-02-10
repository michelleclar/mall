package com.carl.user.controller;

import com.carl.parma.AddressListParam;
import com.carl.parma.AddressRemoveParam;
import com.carl.pojo.Address;
import com.carl.user.service.AddressService;
import com.carl.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: mall
 * @description: 地址的控制controller
 * @author: Mr.Carl
 **/
@RestController
@RequestMapping("user/address")
@Slf4j
public class AddressController {
    @Autowired
    private AddressService addressService;

    /**
     * 查询地址列表
     * @param addressListParam
     * @return
     */
    @PostMapping("list")
    public R list(@RequestBody @Validated AddressListParam addressListParam, BindingResult result){

        if (result.hasErrors()){

            return R.fail("参数异常,查询失败!");
        }

        return  addressService.list(addressListParam.getUserId());
    }

    /**
     * Carl
     * TODO:保存用户地址
     * @param address
     * return:
     */
    @PostMapping("save")
    public R save(@RequestBody @Validated Address address,BindingResult result){
        if (result.hasErrors()){

            return R.fail("参数异常,保存失败!");
        }
        return addressService.save(address);
    }

    /**
     * Carl
     * TODO:删除用户地址
     * @param addressRemoveParam
     * return:
     */
    @PostMapping("remove")
    public R remove(@RequestBody @Validated AddressRemoveParam addressRemoveParam, BindingResult result){

        if (result.hasErrors()){

            return R.fail("参数异常,删除失败!");
        }

        return addressService.remove(addressRemoveParam.getId());
    }
}
