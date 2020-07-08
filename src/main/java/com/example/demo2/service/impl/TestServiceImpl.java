package com.example.demo2.service.impl;

import com.example.demo2.domain.Users;
import com.example.demo2.exception.ManagementCockpitException;
import com.example.demo2.mapper.UsersMapper;
import com.example.demo2.result.MsgUtil;
import com.example.demo2.service.TestService;
import com.example.demo2.util.CONST;
import com.example.demo2.util.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service("test")
public class TestServiceImpl implements TestService {

    @Resource
    private MessageSource msgsrc;

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public ResultDto<String> test(String name) throws ManagementCockpitException,Exception {
        String username = Optional.ofNullable(usersMapper.selectByPrimaryKey(Integer.valueOf(name))).
                map(Users::getUsername).orElse(new String("lllll"));
        return MsgUtil.success(username.concat("1"), CONST.RETURN_OK,msgsrc);
    }
}
