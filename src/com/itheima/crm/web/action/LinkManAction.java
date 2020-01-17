package com.itheima.crm.web.action;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.CustomerService;
import com.itheima.crm.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * @author BJXT-LXD
 */
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
    private LinkMan linkMan = new LinkMan();
    @Override
    public LinkMan getModel() {
        return linkMan;
    }
    //注入linkMan的业务类
    private LinkManService linkManService;

    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }
    //注入客户管理的service
    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    //分页参数
    private Integer currPage = 1;//当前页
    private Integer pageSize = 3;//每页显示的条数

    public void setCurrPage(Integer currPage) {
        if (currPage == null){
            currPage =1;
        }
        this.currPage = currPage;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize == null){
            pageSize = 3;
        }
        this.pageSize = pageSize;
    }

    /**
     * 查询所有联系人
     */
    public String findAll(){
        //PageBean<LinkMan> pageBean = new PageBean<>();
        //创建离线条件查询
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);

        //添加条件
        //按名称查询
        if (linkMan.getLkm_name()!=null){
            detachedCriteria.add(Restrictions.like("lkm_name","%"+linkMan.getLkm_name()+"%"));
        }
        //按性别查询
        if (linkMan.getLkm_gender()!=null&& !"".equals(linkMan.getLkm_gender())){
            detachedCriteria.add(Restrictions.eq("lkm_gender",linkMan.getLkm_gender()));
        }
        PageBean<LinkMan> pageBean = linkManService.findAll(detachedCriteria,currPage,pageSize);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
    }

    /**
     * 跳转到添加页面的方法saveUI
     * @return
     */
    public String saveUI(){
        //查询所有客户
        List<Customer> list = customerService.findAll();
        ActionContext.getContext().getValueStack().set("list",list);
        return "saveUI";
    }

    /**
     *保存联系人
     */
    public String save(){
        linkManService.save(linkMan);
        return "saveSuccess";
    }
    /**
     * 编辑联系人
     */
    public String edit(){
        //查询所有客户
        List<Customer> list = customerService.findAll();
        ActionContext.getContext().getValueStack().set("list",list);
        linkMan = linkManService.findById(linkMan.getLkm_id());

        ActionContext.getContext().getValueStack().push(linkMan);
        return "editSuccess";
    }
    /**
     * 修改联系人
     */
    public String update(){
        linkManService.update(linkMan);
        return "updateSuccess";
    }
    /**
     * 删除联系人
     */
    public String delete(){
        linkMan = linkManService.findById(linkMan.getLkm_id());
        linkManService.delete(linkMan);
        return "deleteSuccess";
    }
}
