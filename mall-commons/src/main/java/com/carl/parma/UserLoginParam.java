package com.carl.parma;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: mall
 * @description: 用户登录实体参数
 * @author: Mr.Carl
 **/
@Data
public class UserLoginParam {

    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
