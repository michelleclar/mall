package com.carl.parma;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: mall
 * @description: 删除用户地址
 * @author: Mr.Carl
 **/
@Data
public class AddressRemoveParam {

    @NotNull
    private Integer id;
}

