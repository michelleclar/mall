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
 * @description: 轮播图实体类
 * @author: Mr.Carl
 **/
@Data
@TableName("carousel")
public class Carousel  implements Serializable {

    public static final Long serialVersionUID = 1L;

    @TableId(value = "carousel_id",type = IdType.AUTO)
    @JsonProperty("carousel_id")
    private Integer carouselId;
    @TableField("img_path")
    private String  imgPath;
    private String  describes;

    @JsonProperty("product_id")
    @TableField("product_id")
    private Integer productId;
    //优先级
    private Integer priority;

}
