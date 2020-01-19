package com.itheima.crm.service;

import com.itheima.crm.domain.User;

import java.util.List;

/**
 * @author lxd
 * @date 2020/1/11 19:05
 * @description
 */
public interface UserService {
    void regist(User user);

    User login(User user);

    List<User> findAllUser();
}
