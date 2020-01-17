package com.itheima.crm.dao;

/**
 * @author BJXT-LXD
 */
public interface BaseDao<T> {

    public void save(T t);
    public void update(T t);
    public void delete(T t);


}
