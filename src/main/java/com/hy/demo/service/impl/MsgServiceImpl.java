package com.hy.demo.service.impl;

import com.hy.demo.result.MsgUtil;
import com.hy.demo.service.MsgService;
import com.hy.demo.util.ResultDto;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hy
 * @description:
 * @date 2020/08/06
 */

@Service
public class MsgServiceImpl implements MsgService {

    @Resource
    private MessageSource msgsrc;

    @Override
    public ResultDto<?> test() {
        throw MsgUtil.warning("1002",msgsrc);
    }
}
