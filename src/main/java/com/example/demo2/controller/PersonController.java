package com.example.demo2.controller;

import com.example.demo2.domain.Person;
import com.example.demo2.service.PersonService;
import com.example.demo2.util.PagedResult;
import com.example.demo2.util.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/person")
    public ResultDto<PagedResult<Person>> test(Integer pageNo, Integer pageSize) {
        return personService.selectList(pageNo,pageSize);
    }

}
