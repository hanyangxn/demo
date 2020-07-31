package com.example.demo2.service;

import com.example.demo2.domain.Person;
import com.example.demo2.util.PagedResult;
import com.example.demo2.util.ResultDto;

import java.util.List;

public interface PersonService {
    ResultDto<PagedResult<Person>> selectList(Integer start, Integer end);
}
