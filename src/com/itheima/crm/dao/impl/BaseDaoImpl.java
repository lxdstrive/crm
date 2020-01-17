package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.BaseDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author BJXT-LXD
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    private Class clazz;

    //提供构造方法，再构造方法中穿入具体类型的class

    /**
     * 不想子类上有构造方法，必须在父类中提供无参的构造方法，在无参构造中获得具体类型的class
     * 具体类型的Class是参数类型中的实际类型参数
     */
    public BaseDaoImpl(){
        //反射：第一步获得Class
        Class clazz = this.getClass();//正在背调用那个类的Class，CustomerDaoImpl的class，或者LinkManDaoImpl的class
        //查看JDK的API
        //Type getGenericSuperclass() 获得带有泛型的父类
        ParameterizedType pType = (ParameterizedType) clazz.getGenericSuperclass();//参数化类型：BaseDaoImpl<Customer>,BaseDaoImpl<LinkMan>
        //通过参数化类型，获得实际类型参数,为什么返回的是数组  Map<String,Integer>
        Type[] types = pType.getActualTypeArguments();

        this.clazz = (Class) types[0]; //Custoemr,LinkMan..
    }


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

    @Override
    public T findById(Serializable id) {

        return (T) this.getHibernateTemplate().get(clazz,id);
    }

    /**
     * 查询所有
     */
    @Override
    public List<T> findAll() {
        return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
    }
    /**
     * 查询总数目
     */
    @Override
    public Integer findCount(DetachedCriteria detachedCriteria) {
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() >0){
            return list.get(0).intValue();
        }
        return null;
    }

    /**
     * 查询分页
     * @param detachedCriteria
     * @param begin
     * @param pageSize
     * @return
     */
    @Override
    public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        detachedCriteria.setProjection(null);
        return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
    }
}
