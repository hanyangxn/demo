package com.hy.demo.test;

import com.hy.demo.dto.UserDto;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Test01 {
    public static void main(String[] args) {
        List<String> codeList = AnalysisDimension.getCodeList();
        String nameByCode = AnalysisDimension.getNameByCode("1");
        System.out.println(nameByCode);
        System.out.println(codeList);
        UserDto userDto = new UserDto();
        userDto.setAge(19);
        UserDto userDto2 = new UserDto();
        userDto2.setAge(14);
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(userDto);
        userDtoList.add(userDto2);
       // Collections.sort(userDtoList,(a,b)->a.getAge().compareTo(b.getAge()));
        // 与下面相等
        userDtoList = userDtoList.stream().sorted(Comparator.comparing(UserDto::getAge)).collect(Collectors.toList());
        System.out.println(userDtoList);

        Integer integer = Optional.ofNullable(userDto).map(i -> i.getAge()).get();
        UserDto opt = Test01.opt(userDtoList);
        System.out.println(opt);
        BigDecimal calcResult = BigDecimal.ZERO;
        System.out.println(calcResult);

        userDtoList.forEach(s-> System.out.println(s.getAge()));
        userDtoList.stream().forEach(s-> System.out.println(s.getAge()));
        Consumer<UserDto> consumer =UserDto::new;
        consumer.accept(userDto);
        System.out.println(consumer);


    }

    public static UserDto userDtoList(List<UserDto> userDtos) {
//        UserDto userDto = userDtos.get(0);

        return  Optional.ofNullable(userDtos).
                map(s->s.get(0)).
                orElse(new UserDto());
    }

    public static UserDto opt(List<UserDto> userDtoList){
        UserDto userDto1 = Optional.ofNullable(userDtoList).
                map(item -> {
                    return userDtoList(item);
                }).orElse(new UserDto());
        return userDto1;
    }


}
