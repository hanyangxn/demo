package com.example.demo2.mapper;

import com.example.demo2.domain.Chinamutualfundstockportfolio;import com.example.demo2.domain.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChinamutualfundstockportfolioMapper {


    Chinamutualfundstockportfolio selectByPrimaryKey(String sInfoWindcode);


}