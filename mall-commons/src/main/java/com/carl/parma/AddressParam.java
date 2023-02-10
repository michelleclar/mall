package com.carl.parma;

import com.carl.pojo.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: mall
 * @description: 地址接收param
 * @author: Mr.Carl
 **/
@Data
public class AddressParam {

    @JsonProperty("user_id")
    @NotNull
    private Integer userId;

    private Address add;
}
