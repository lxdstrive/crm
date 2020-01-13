package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

/**
 * 客户管理的dao接口的实现类
 * @author BJXT-LXD
 */
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {
    /**
     * 保存客户
     * @param customer
     */
    @Override
    public void save(Customer customer) {
        this.getHibernateTemplate().save(customer);
    }
}
