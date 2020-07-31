package com.example.demo2.controller;

import com.example.demo2.dto.StageUpAndDownDto;
import com.example.demo2.service.StageUpAndDownService;
import com.example.demo2.util.DateUtil;
import com.example.demo2.util.ResultDto;
import com.example.demo2.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@CrossOrigin
@RestController
public class StageUpAndDownController {

    final static Logger logger = LoggerFactory.getLogger(StageUpAndDownController.class);

    @Autowired
    private StageUpAndDownService stageUpAndDownService;

    @ResponseBody
    @RequestMapping(value = "/cockpit2/getStageUpAndDown", method = RequestMethod.POST)
    public ResultDto<?> getMonetaryFundBasicInfo(@RequestBody StageUpAndDownDto stageUpAndDownDto, HttpServletRequest request) {
        try {
            return stageUpAndDownService.getStageUpAndDown(stageUpAndDownDto);
        } catch (Exception e) {
            logger.warn(DateUtil.now("yyyy-MM-dd HH:mm:ss.SSS") + " - Warning of " +
                    Thread.currentThread().getStackTrace()[1].getMethodName() + ": " + e.getMessage());
            return ResultUtil.error(null, e);
        }
    }
}
