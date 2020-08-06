package com.hy.demo.controller;

import com.hy.demo.annotation.ParamCheck;
import com.hy.demo.annotation.SystemLog;
import com.hy.demo.annotation.Test;
import com.hy.demo.dto.TypeService;
import com.hy.demo.dto.UserDto;
import com.hy.demo.exception.ManagementCockpitException;
import com.hy.demo.service.TestService;
import com.hy.demo.util.ReflectionUtil;
import com.hy.demo.util.ResultDto;
import com.hy.demo.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    @Qualifier("test")
    private TestService testService;

    /**
     * 加载对应接口实现
     * */
    @Autowired
    private Map<String, TestService> events;

    @ParamCheck({"name:name不能为空","age<=10:age 必须大于10"})
//    @Test
    @RequestMapping("/we")
    @SystemLog
    public ResultDto<?> test(@RequestBody UserDto userDto) {
        try {
            String name = userDto.getName();
            Integer age = userDto.getAge();
            logger.info(String.valueOf(age));
            logger.info("123" + name);
            return testService.test(name);
        } catch (ManagementCockpitException e) {
            logger.error(e.getCode() + ":" + e.getMessage());
            return ResultUtil.warning(null, e);
        } catch (Exception e) {
            return ResultUtil.error(null, e);
        }
    }

    @Test
    @RequestMapping("/test2")
    @SystemLog
    public ResultDto<?> test2(@RequestBody UserDto userDto) {
        try {
            TypeService type = userDto.getType();
            logger.info(type.getName());
            TestService testService = events.get(type.getName());
//            return (ResultDto) ReflectionUtil.invokeMethod(testService, "test",new Class[]{String.class}, new Object[]{userDto.getName()});
            return (ResultDto) ReflectionUtil.invokeMethodByName(testService, "test", new Object[]{userDto.getName()});
        } catch (ManagementCockpitException e) {
            logger.error(e.getCode() + ":" + e.getMessage());
            return ResultUtil.warning(null, e);
        } catch (Exception e) {
            return ResultUtil.error(null, e);
        }
    }
}
