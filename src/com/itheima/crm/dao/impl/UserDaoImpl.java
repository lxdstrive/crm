package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.UserDao;
import com.itheima.crm.domain.User;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

/**
 * @author lxd
 * @date 2020/1/11 19:05
 * @description
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
    @Override
    //dao保存注入的方法
    public void save(User user) {
        this.getHibernateTemplate().save(user);
    }
}
