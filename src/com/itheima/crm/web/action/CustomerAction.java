package com.itheima.crm.web.action;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.service.CustomerService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 客户管理的action
 * @author BJXT-LXD
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
    private Customer customer = new Customer();
    @Override
    public Customer getModel() {
        return customer;
    }

    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * 保存客户
     */
    public String save(){
        customerService.save(customer);
        return SUCCESS;
    }

    /**
     * 保存客户UI:跳转到添加客户的界面
     */
    public String saveUI(){
        return "saveUI";
    }
}
