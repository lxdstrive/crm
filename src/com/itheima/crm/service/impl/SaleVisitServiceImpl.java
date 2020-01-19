package com.itheima.crm.service.impl;

import com.itheima.crm.dao.SaleVisitDao;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.domain.SaleVisit;
import com.itheima.crm.service.SaleVisitService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author BJXT-LXD
 */
@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {
    private SaleVisitDao saleVisitDao;

    public void setSaleVisitDao(SaleVisitDao saleVisitDao) {
        this.saleVisitDao = saleVisitDao;
    }

    /**
     * 查询所有记录
     * @return
     */
    @Override
    public PageBean<SaleVisit> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
        PageBean<SaleVisit> pageBean = new PageBean<>();
        pageBean.setCurrPage(currPage);
        pageBean.setPageSize(pageSize);
        Integer totalCount = saleVisitDao.findCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);

        double tc = totalCount.doubleValue();
        Double num = Math.ceil( tc / pageSize);
        pageBean.setTotalPage(num.intValue());

        Integer begin = (currPage-1) * pageSize;

        List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria,begin,pageSize);

        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public void save(SaleVisit saleVisit) {
        saleVisitDao.save(saleVisit);
    }
}
