package com.itheima.crm.web.action;

import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.criterion.DetachedCriteria;

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

        PageBean<LinkMan> pageBean = linkManService.findAll(detachedCriteria,currPage,pageSize);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
    }
}
