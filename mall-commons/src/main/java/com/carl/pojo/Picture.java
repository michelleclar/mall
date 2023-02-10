package com.carl.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: mall
 * @description: 商品图片
 * @author: Mr.Carl
 **/
@Data
@TableName("product_picture")
public class Picture implements Serializable {

    public static final Long serialVersionUID = 1L;

    @TableId(type= IdType.AUTO)
    private Integer id;
    @JsonProperty("product_id")
    @TableField("product_id")
    private Integer productId;
    @JsonProperty("product_picture")
    @TableField("product_picture")
    private String  productPicture;
    private String  intro;
}

