package com.example.demo2.mapper;

import com.example.demo2.domain.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PersonMapper {

    List<Person> selectList();
}