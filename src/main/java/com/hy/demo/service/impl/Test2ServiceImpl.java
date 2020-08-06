package com.hy.demo.service.impl;


import com.hy.demo.exception.ManagementCockpitException;
import com.hy.demo.result.MsgUtil;
import com.hy.demo.service.TestService;
import com.hy.demo.util.CONST;
import com.hy.demo.util.ResultDto;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("test2")
public class Test2ServiceImpl implements TestService {

    @Resource
    private MessageSource msgsrc;

    @Override
    public ResultDto<String> test(String name) throws ManagementCockpitException, Exception {
        return MsgUtil.success(name.concat("2"), CONST.RETURN_OK, msgsrc);
    }
}
