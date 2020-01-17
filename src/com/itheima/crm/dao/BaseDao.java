package com.itheima.crm.dao;

import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

/**
 * @author BJXT-LXD
 */
public interface BaseDao<T> {

    public void save(T t);
    public void update(T t);
    public void delete(T t);

    public T findById(Serializable id);

    public List<T> findAll();

    public Integer findCount(DetachedCriteria detachedCriteria);

    public List<T> findByPage(DetachedCriteria detachedCriteria,Integer begin,Integer pageSize);
}
