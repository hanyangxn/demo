package com.hy.demo.controller;

import com.hy.demo.service.MsgService;
import com.hy.demo.exception.ManagementCockpitException;
import com.hy.demo.util.ResultDto;
import com.hy.demo.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hy
 * @description:
 * @date 2020/08/06
 */
@RestController
public class MsgController {

    final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private MsgService service;

    @RequestMapping("/msg")
    public ResultDto<?> msg() {
        try {
            return service.test();
        } catch (ManagementCockpitException e) {
            logger.error(e.getCode() + ":" + e.getMessage());
            return ResultUtil.warning(null, e);
        } catch (Exception e) {
            return ResultUtil.error(null, e);
        }
    }
}
