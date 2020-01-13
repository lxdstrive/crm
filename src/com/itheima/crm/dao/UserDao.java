package com.itheima.crm.dao;

import com.itheima.crm.domain.User;

/**
 * @author lxd
 * @date 2020/1/11 19:03
 * @description
 */
public interface UserDao {
    void save(User user);

    User login(User user);
}
