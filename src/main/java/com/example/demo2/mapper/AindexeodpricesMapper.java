package com.example.demo2.mapper;

import com.example.demo2.domain.Aindexeodprices;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AindexeodpricesMapper {

    List<Aindexeodprices> selectByWindCode(@Param("sInfoWindcode") String sInfoWindcode,
                                           @Param("startDate") String startDate,
                                           @Param("endDate") String endDate);
}