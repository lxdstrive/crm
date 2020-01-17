package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.UserDao;
import com.itheima.crm.domain.User;

import java.util.List;

/**
 * @author lxd
 * @date 2020/1/11 19:05
 * @description
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    /**
     * 完成用户登录的功能
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code=? and user_password=?", user.getUser_code(), user.getUser_password());
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
