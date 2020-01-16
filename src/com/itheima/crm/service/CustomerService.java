package com.itheima.crm.service;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 客户管理的service接口
 * @author BJXT-LXD
 */
public interface CustomerService {
    void save(Customer customer);

    PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

    Customer findById(Long cust_id);

    void delete(Customer customer);

    void update(Customer customer);

    List<Customer> findAll();
}
