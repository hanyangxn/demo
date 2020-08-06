package com.hy.demo.mapper;

import com.hy.demo.domain.Chinamfperformance;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChinamfperformanceMapper {
    Chinamfperformance selectByFundCode(@Param("fundCode") String fundCode);

    Chinamfperformance selectNewByFundCode(@Param("fundCode") String fundCode,@Param("tradeDt") String tradeDt);
}