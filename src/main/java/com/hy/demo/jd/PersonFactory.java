package com.hy.demo.jd;

import com.hy.demo.domain.User;

public interface PersonFactory<p extends User> {
    p create(Integer id,String username,String password);
}
