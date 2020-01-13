package com.itheima.crm.service;

import com.itheima.crm.domain.User;

/**
 * @author lxd
 * @date 2020/1/11 19:05
 * @description
 */
public interface UserService {
    void regist(User user);

    User login(User user);
}
