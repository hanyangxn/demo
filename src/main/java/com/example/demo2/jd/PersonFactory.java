package com.example.demo2.jd;

import com.example.demo2.domain.User;

public interface PersonFactory<p extends User> {
    p create(Integer id,String username,String password);
}
