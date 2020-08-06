package com.hy.demo.service.impl;

import com.hy.demo.domain.Person;
import com.hy.demo.mapper.PersonMapper;
import com.hy.demo.result.MsgUtil;
import com.hy.demo.util.CONST;
import com.hy.demo.util.PageHelperUtil;
import com.hy.demo.util.ResultDto;
import com.hy.demo.service.PersonService;
import com.hy.demo.util.PagedResult;
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
