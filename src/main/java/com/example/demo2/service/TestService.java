package com.example.demo2.service;

import com.example.demo2.util.ResultDto;

public interface TestService {
    public ResultDto<String> test(String name) throws Exception;
}
