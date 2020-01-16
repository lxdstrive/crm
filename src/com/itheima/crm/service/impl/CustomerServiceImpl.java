package com.itheima.crm.service.impl;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.CustomerService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage,Integer pageSize) {
        PageBean<Customer> pageBean = new PageBean<>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示的数目
        pageBean.setPageSize(pageSize);
        //调用dao查询总记录数
        Integer totalCount = customerDao.findCount(detachedCriteria);
        //封装总记录数
        pageBean.setTotalCount(totalCount);

        //封装总页数
        Double tc = totalCount.doubleValue();
        Double num = Math.ceil(tc/pageSize);
        pageBean.setTotalPage(num.intValue());
       /* if (totalCount % pageSize == 0){
            totalPage = totalCount / pageSize;
        }else {
            totalPage = totalCount /pageSize + 1;
        }*/

       //封装每页显示的数据的集合
        Integer begin = (currPage -1) * pageSize;
        List<Customer> list = customerDao.findByPage(detachedCriteria,begin,pageSize);
        //封装记录
        pageBean.setList(list);
        return pageBean;
    }

    /**
     * 根据客户id查询客户
     * @param cust_id
     * @return
     */
    @Override
    public Customer findById(Long cust_id) {
        return customerDao.findById(cust_id);
    }

    /**
     * 删除客户
     * @param customer
     */
    @Override
    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    /**
     * 业务层修改客户的方法
     * @param customer
     */
    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }

    /**
     * 查询所有客户
     * @return
     */
    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }
}
