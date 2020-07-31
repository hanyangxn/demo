package com.example.demo2.service.impl;

import com.example.demo2.domain.Person;
import com.example.demo2.mapper.PersonMapper;
import com.example.demo2.result.MsgUtil;
import com.example.demo2.service.PersonService;
import com.example.demo2.util.CONST;
import com.example.demo2.util.PageHelperUtil;
import com.example.demo2.util.PagedResult;
import com.example.demo2.util.ResultDto;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Resource
    private MessageSource msgsrc;

    @Override
    public ResultDto<PagedResult<Person>> selectList(Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Person> people = personMapper.selectList();
        PagedResult<Person> personPagedResult = PageHelperUtil.toPagedResult(people);
        return MsgUtil.success(personPagedResult, CONST.RETURN_OK, msgsrc);
    }
}
