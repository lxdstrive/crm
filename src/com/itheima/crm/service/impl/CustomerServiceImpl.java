package com.itheima.crm.service.impl;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.service.CustomerService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客户管理service接口的实现类
 * @author BJXT-LXD
 */
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * 保存客户
     * @param customer
     */
    @Override
    public void save(Customer customer) {
        customerDao.save(customer);
    }
}
