package com.example.demo2.service;

import com.example.demo2.bo.StageUpAndDownReturnBo;
import com.example.demo2.dto.StageUpAndDownDto;
import com.example.demo2.util.ResultDto;

import java.text.ParseException;

public interface StageUpAndDownService {
    ResultDto<StageUpAndDownReturnBo> getStageUpAndDown(StageUpAndDownDto stageUpAndDownDto) throws ParseException;
}
