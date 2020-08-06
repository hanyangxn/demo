package com.hy.demo.mapper;

import com.hy.demo.domain.ChinaMutualFundDescription;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChinaMutualFundDescriptionMapper {
    ChinaMutualFundDescription getFundByWindcode(ChinaMutualFundDescription input);
    // add by 2020/06/17 xlqi start
    /**
     * 根据wind code查询基金基本信息
     * @param windCode wind代码
     * @return 基金基本信息
     * */
    ChinaMutualFundDescription selectFundInfoByWindcode(@Param("windCode") String windCode);

    /**
     * 根据wind code fuzzy search fund basic info
     * @param windCode wind代码
     * @return 基金基本信息
     * */
    List<ChinaMutualFundDescription> selectFuzzyFundInfoByWindcode(@Param("windCode") String windCode);
    // add by 2020/06/17 xlqi end
}
