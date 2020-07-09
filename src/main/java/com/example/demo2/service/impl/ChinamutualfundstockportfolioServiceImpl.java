package com.example.demo2.service.impl;

import com.example.demo2.domain.Chinamutualfundstockportfolio;
import com.example.demo2.mapper.ChinamutualfundstockportfolioMapper;
import com.example.demo2.mapper.UsersMapper;
import com.example.demo2.result.MsgUtil;
import com.example.demo2.service.ChinamutualfundstockportfolioService;
import com.example.demo2.util.CONST;
import com.example.demo2.util.Constant;
import com.example.demo2.util.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ChinamutualfundstockportfolioServiceImpl implements ChinamutualfundstockportfolioService {

    @Autowired
    private ChinamutualfundstockportfolioMapper chinamutualfundstockportfolioMapper;

    @Resource
    private MessageSource msgsrc;

    @Override
    public ResultDto<Chinamutualfundstockportfolio> selectByPrimaryKey(String sInfoWindcode) {
        Chinamutualfundstockportfolio chinamutualfundstockportfolio = Optional.ofNullable(chinamutualfundstockportfolioMapper.selectByPrimaryKey(sInfoWindcode)).
                map(s-> {
                    BigDecimal multiply =BigDecimal.ZERO;
                    BigDecimal bigDecimal = new BigDecimal("2");
                    BigDecimal bigDecimal2 = new BigDecimal("9");
                    if (s.getCrncyCode() != null){
                        multiply = bigDecimal2.subtract(bigDecimal).
                                divide(bigDecimal2, Constant.DIVIDE_KEEP_DECIMALS, BigDecimal.ROUND_HALF_UP).
                                multiply(new BigDecimal("100"));

                        s.setCrncyCode( multiply.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
                    }
                    return s;
                }).
                orElse(new Chinamutualfundstockportfolio());
        return MsgUtil.success(chinamutualfundstockportfolio, CONST.RETURN_OK,msgsrc);
    }
}
