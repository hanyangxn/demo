package com.hy.demo.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hy
 * @description:
 * @date 2020/08/13
 */
@ApiModel
@Data
public class User1 {
    @ApiModelProperty(value = "用户id")
    private Integer id;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户地址")
    private String address;
    //getter/setter
}