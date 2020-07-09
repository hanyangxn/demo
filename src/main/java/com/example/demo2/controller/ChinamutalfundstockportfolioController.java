package com.example.demo2.controller;

import com.example.demo2.annotation.ParamCheck;
import com.example.demo2.annotation.SystemLog;
import com.example.demo2.domain.Chinamutualfundstockportfolio;
import com.example.demo2.dto.ChinamutualfundstockportfolioDto;
import com.example.demo2.dto.UserDto;
import com.example.demo2.exception.ManagementCockpitException;
import com.example.demo2.service.ChinamutualfundstockportfolioService;
import com.example.demo2.util.ResultDto;
import com.example.demo2.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChinamutalfundstockportfolioController {

    final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private ChinamutualfundstockportfolioService chinamutualfundstockportfolioService;

    @ParamCheck({"wind:wind不能为空"})
//    @Test
    @RequestMapping("/getWind")
    @SystemLog
    public ResultDto<?> getWind(@RequestBody ChinamutualfundstockportfolioDto dto) {
        try {
            if(!dto.getWind().contains(".")) {
                dto.setWind(dto.getWind() + ".__");
            }
            return chinamutualfundstockportfolioService.selectByPrimaryKey(dto.getWind());
        } catch (ManagementCockpitException e) {
            logger.error(e.getCode() + ":" + e.getMessage());
            return ResultUtil.warning(null, e);
        } catch (Exception e) {
            return ResultUtil.error(null, e);
        }
    }
}
