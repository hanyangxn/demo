package com.hy.demo.service;

import com.hy.demo.domain.Person;
import com.hy.demo.util.ResultDto;
import com.hy.demo.util.PagedResult;

public interface PersonService {
    ResultDto<PagedResult<Person>> selectList(Integer start, Integer end);
}
