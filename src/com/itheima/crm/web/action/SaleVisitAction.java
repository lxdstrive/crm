package com.itheima.crm.web.action;

import com.itheima.crm.domain.PageBean;
import com.itheima.crm.domain.SaleVisit;
import com.itheima.crm.service.SaleVisitService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.Date;

/**
 * @author BJXT-LXD
 */
public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {
    private SaleVisit saleVisit = new SaleVisit();
    @Override
    public SaleVisit getModel() {
        return saleVisit;
    }

    private SaleVisitService saleVisitService;

    public void setSaleVisitService(SaleVisitService saleVisitService) {
        this.saleVisitService = saleVisitService;
    }

    private Integer currPage =1;

    public void setCurrPage(Integer currPage) {
        if (currPage == null){
            currPage = 1;
        }
        this.currPage = currPage;
    }
    private Integer pageSize =3;

    public void setPageSize(Integer pageSize) {
        if (pageSize == null){
            pageSize = 3;
        }
        this.pageSize = pageSize;
    }

    private Date visit_end_time;

    public Date getVisit_end_time() {
        return visit_end_time;
    }

    public void setVisit_end_time(Date visit_end_time) {
        this.visit_end_time = visit_end_time;
    }

    /**
     * 查询所有
     * @return
     */
    public String findAll(){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);

        //设置条件
        if (saleVisit.getVisit_time()!=null){
            detachedCriteria.add(Restrictions.ge("visit_time",saleVisit.getVisit_time()));
        }

        if (visit_end_time !=null){
            detachedCriteria.add(Restrictions.le("visit_time",visit_end_time));
        }

        //调用业务层
        PageBean<SaleVisit> pageBean = saleVisitService.findAll(detachedCriteria,currPage,pageSize);

        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
    }

    /**
     * 添加，跳转到UI
     */
    public String saveUI(){

        return "saveUI";
    }

    /**
     * 保存
     */
    public String save(){
        saleVisitService.save(saleVisit);
        return "saveSuccess";
    }
}
