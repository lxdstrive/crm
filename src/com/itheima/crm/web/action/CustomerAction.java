package com.itheima.crm.web.action;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.CustomerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.criterion.DetachedCriteria;

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

    //使用set方法的方式来接收数据
    private Integer currPage=1;

    public void setCurrPage(Integer currPage) {
        if (currPage==null){
            currPage = 1;
        }
        this.currPage = currPage;
    }

    private Integer pageSize=3;

    public void setPageSize(Integer pageSize) {
        if (pageSize==null){
            pageSize = 3;
        }
        this.pageSize = pageSize;
    }


    /**
     * 保存客户
     */
    public String save(){
        customerService.save(customer);
        return "list";
    }

    /**
     * 保存客户UI:跳转到添加客户的界面
     */
    public String saveUI(){
        return "saveUI";
    }

    /**
     * 分页查询客户的方法
     */
    public String findAll(){
        //最好使用detachedCriteria对象
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
        //业务层查询
        PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria,currPage,pageSize);

        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
    }
}
