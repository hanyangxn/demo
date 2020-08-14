package com.hy.demo.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author hy
 * @description:
 * @date 2020/08/13
 */
@RestController
@Api(tags = "用户管理相关接口")
@RequestMapping("/user")
public class UserController {

    @GetMapping("/select")
    @ApiOperation("根据id查询用户的接口")
    @ApiImplicitParam(name = "id", value = "用户id", defaultValue = "99", required = true,dataType = "int")
    public User1 getUserById(@RequestParam Integer id) {
        User1 user = new User1();
        user.setId(id);
        return user;
    }
    @PutMapping("/{id}")
    @ApiOperation("根据id更新用户的接口")
    public User1 updateUserById(@RequestBody User1 user) {
        return user;
    }
}

