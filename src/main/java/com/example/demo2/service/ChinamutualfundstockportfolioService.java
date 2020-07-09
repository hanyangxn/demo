package com.example.demo2.service;

import com.example.demo2.domain.Chinamutualfundstockportfolio;
import com.example.demo2.util.ResultDto;

public interface ChinamutualfundstockportfolioService {
    ResultDto<Chinamutualfundstockportfolio> selectByPrimaryKey(String sInfoWindcode);
}
