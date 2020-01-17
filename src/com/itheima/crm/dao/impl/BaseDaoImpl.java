package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.BaseDao;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

/**
 * @author BJXT-LXD
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    @Override
    public void save(T t) {
        this.getHibernateTemplate().save(t);
    }

    @Override
    public void update(T t) {
        this.getHibernateTemplate().update(t);
    }

    @Override
    public void delete(T t) {
        this.getHibernateTemplate().delete(t);
    }


}
