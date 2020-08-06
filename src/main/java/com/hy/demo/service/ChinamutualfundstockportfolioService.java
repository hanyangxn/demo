package com.hy.demo.service;

import com.hy.demo.domain.Chinamutualfundstockportfolio;
import com.hy.demo.util.ResultDto;

public interface ChinamutualfundstockportfolioService {
    ResultDto<Chinamutualfundstockportfolio> selectByPrimaryKey(String sInfoWindcode);
}
