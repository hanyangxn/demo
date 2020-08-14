package com.hy.demo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wanghaoxin
 * @className SeriesVo
 * @description SeriesVo类
 * @date 2020/6/9 15:33
 */
@Data
@ApiModel(value = "Series数据")
public class SeriesVo {
    /**
     * 左右轴描述 0：左轴 ,1：右轴
     */
    @ApiModelProperty(value = "左右轴描述 0：左轴 ,1：右轴")
    private Integer axisDesc;
    /**
     * 指标频率
     */
    @ApiModelProperty(value = "指标频率")
    private String freq;
    /**
     * 展示类型 比如折线、柱状图
     */
    @ApiModelProperty(value = "展示类型 比如折线、柱状图")
    private String graphType;
    /**
     * 是否可叠加指标
     */
    @ApiModelProperty(value = "是否可叠加指标")
    private Boolean superFlag;
    /**
     * 特殊标识类型，0  -无任何特殊标记，
     *              1  - 标记均值水平线
     *              2  - 标记最高最低点
     *              3 -  标记最高点、最低点、均值点
     *              31 - 标记最高点、最低点、均值点 + 面积图边缘线加粗
     */
    @ApiModelProperty(value = "特殊标识类型" +
            "0  -无任何特殊标记， " +
            "1  - 标记均值水平线 ，" +
            "2  - 标记最高最低点 ，" +
            "3  - 标记最高点、最低点、均值点 " +
            "31 - 标记最高点、最低点、均值点 + 面积图边缘线加粗")
    private Integer specialFlag;
    /**
     * 最大值
     */
    @ApiModelProperty(value = "最大值")
    private BigDecimal max;
    /**
     * 最小值
     */
    @ApiModelProperty(value = "最小值")
    private BigDecimal min;

    /**
     * 平均值
     */
    @ApiModelProperty(value = "平均值")
    private BigDecimal avg;
    /**
     * 指标展示名称
     */
    @ApiModelProperty(value = "指标展示名称")
    private String indClsName;
    /**
     * 指标code
     */
    @ApiModelProperty(value = "指标code")
    private String indCode;
    /**
     * 指标Name
     */
    @ApiModelProperty(value = "指标Name")
    private String indName;
    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String unitName;
    /**
     * 日期，值，单位
     *[["2015-06-30", -4.81, "%"], ["2015-07-31", -5.37, "%"]]
     */
    @ApiModelProperty(value = "数据 eg:日期，值，单位 , [[\"2015-06-30\", -4.81, \"%\"], [\"2015-07-31\", " +
            "-5.37, \"%\"]]")
    private List<List<Object>> data;
}
