package com.itheima.crm.dao;

import com.itheima.crm.domain.Customer;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 客户管理的dao接口
 * @author BJXT-LXD
 */
public interface CustomerDao {
    void save(Customer customer);

    Integer findCount(DetachedCriteria detachedCriteria);

    List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);
}
