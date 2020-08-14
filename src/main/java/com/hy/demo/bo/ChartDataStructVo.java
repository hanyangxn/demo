package com.hy.demo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wanghaoxin
 * @className ChartDataStructVo
 * @description 图表数据结构VO
 * @date 2020/6/9 14:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "图表数据结构体")
public class ChartDataStructVo<T> {
    /**
     * 默认展示横轴起始日期
     */
    @ApiModelProperty(value = "默认展示横轴起始日期")
    private String displayDate;
    /**
     * 横轴时间
     */
    @ApiModelProperty(value = "横轴时间")
    private List<String> dateUnion;
    /**
     * 子专题Code
     */
    @ApiModelProperty(value = "子专题Code")
    private String subtopicCode;
    /**
     * 子专题详细描述
     */
    @ApiModelProperty(value = "子专题详细描述")
    private String subtopicDesc;
    /**
     * 子专题表头标题
     */
    @ApiModelProperty(value = "子专题表头标题")
    private String subtopicTitle;
    /**
     * 图表数据
     */
    @ApiModelProperty(value = "图表数据")
    private GarphDataVo graphData;
    /**
     * 默认展示百分比
     */
    @ApiModelProperty(value = "默认展示百分比")
    private BigDecimal percent;

    /**
     * 下拉选项
     */
//    @ApiModelProperty(value = "下拉选项内容")
//    private List<SubItemVo> subItemList;
    /**
     * 其它数据，比如饼状图/表格数据/基础数据等附属数据
     */
    @ApiModelProperty(value = "其它数据，比如饼状图/表格数据/基础数据等附属数据")
    T otherData;
}
