package com.hy.demo.service.impl;

import com.hy.demo.domain.Users;
import com.hy.demo.exception.ManagementCockpitException;
import com.hy.demo.mapper.UsersMapper;
import com.hy.demo.result.MsgUtil;
import com.hy.demo.service.TestService;
import com.hy.demo.util.CONST;
import com.hy.demo.util.ResultDto;
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
