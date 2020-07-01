package com.example.demo2.service.impl;

import com.example.demo2.exception.ManagementCockpitException;
import com.example.demo2.result.MsgUtil;
import com.example.demo2.service.TestService;
import com.example.demo2.util.CONST;
import com.example.demo2.util.ResultDto;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private MessageSource msgsrc;

    @Override
    public ResultDto<String> test(String name) throws ManagementCockpitException,Exception {
        return MsgUtil.success(name, CONST.RETURN_OK,msgsrc);
    }
}
