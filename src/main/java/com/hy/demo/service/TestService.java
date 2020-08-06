package com.hy.demo.service;

import com.hy.demo.util.ResultDto;

public interface TestService {
    public ResultDto<String> test(String name) throws Exception;
}
