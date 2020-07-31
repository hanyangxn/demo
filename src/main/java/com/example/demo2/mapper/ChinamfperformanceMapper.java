package com.example.demo2.mapper;

import com.example.demo2.bo.StageUpAndDownReturnBo;
import com.example.demo2.domain.Chinamfperformance;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChinamfperformanceMapper {
    Chinamfperformance selectByFundCode(@Param("fundCode") String fundCode);

    Chinamfperformance selectNewByFundCode(@Param("fundCode") String fundCode,@Param("tradeDt") String tradeDt);
}