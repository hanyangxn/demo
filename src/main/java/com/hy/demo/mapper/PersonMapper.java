package com.hy.demo.mapper;

import com.hy.demo.domain.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PersonMapper {

    List<Person> selectList();
}