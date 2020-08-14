package com.hy.demo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author wanghaoxin
 * @className GarphDataVo
 * @description 图数据
 * @date 2020/6/9 15:16
 */
@Data
@ApiModel(value = "图数据")
public class GarphDataVo {


    /**
     * 图例数据
     */
    @ApiModelProperty(value = "图例数据")
    private List<String> legendData;

    /**
     * time/category
     */
//    @ApiModelProperty(value = "time/category,标识横轴是否是时间")
//    private String xAxisData = ChartConstants.XAXIS_DATA_TYPE;

    /**
     * 图表线条数据
     */
    @ApiModelProperty(value = "series数据")
    private List<SeriesVo> series;
}
