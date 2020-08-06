package com.hy.demo.service;

import com.hy.demo.bo.StageUpAndDownReturnBo;
import com.hy.demo.dto.StageUpAndDownDto;
import com.hy.demo.util.ResultDto;

import java.text.ParseException;

public interface StageUpAndDownService {
    ResultDto<StageUpAndDownReturnBo> getStageUpAndDown(StageUpAndDownDto stageUpAndDownDto) throws ParseException;
}
