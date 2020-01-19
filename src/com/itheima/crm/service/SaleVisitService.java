package com.itheima.crm.service;

import com.itheima.crm.domain.PageBean;
import com.itheima.crm.domain.SaleVisit;
import org.hibernate.criterion.DetachedCriteria;

/**
 * @author BJXT-LXD
 */
public interface SaleVisitService {
    PageBean<SaleVisit> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

    void save(SaleVisit saleVisit);
}
