package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import java.util.List;

/**
 * 客户管理的dao接口的实现类
 * @author BJXT-LXD
 */
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

    /**
     * dao中带条件统计个数的方法
     * @param detachedCriteria
     * @return
     */
    @Override
    public Integer findCount(DetachedCriteria detachedCriteria) {
        //select count(*) from XXX where 条件
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size()>0){
            return list.get(0).intValue();
        }
        return null;
    }

    /**
     * dao中分页查询客户的方法
     * @param detachedCriteria
     * @param begin
     * @param pageSize
     * @return
     */
    @Override
    public List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        detachedCriteria.setProjection(null);
        return (List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
    }

    /**
     * 根据客户id查询客户
     * @param cust_id
     * @return
     */
    @Override
    public Customer findById(Long cust_id) {

        return this.getHibernateTemplate().get(Customer.class,cust_id);
    }

    /**
     * 查询所有客户
     * @return
     */
    @Override
    public List<Customer> findAll() {

        return (List<Customer>) this.getHibernateTemplate().find("from Customer");
    }

}
