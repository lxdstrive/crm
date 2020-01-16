package com.itheima.crm.service.impl;

import com.itheima.crm.dao.LinkManDao;
import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.LinkManService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author BJXT-LXD
 */
@Transactional
public class LinkManServiceImpl implements LinkManService {
    //注入linkMan的dao类
    private LinkManDao linkManDao;

    public void setLinkManDao(LinkManDao linkManDao) {
        this.linkManDao = linkManDao;
    }

    /**
     * 分页查询联系人
     * @param detachedCriteria
     * @param currPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
        PageBean<LinkMan> pageBean = new PageBean<>();
        pageBean.setCurrPage(currPage);
        pageBean.setPageSize(pageSize);
        Integer totalCount = linkManDao.findCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);

        //这种总页数
        double tc = totalCount;
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num.intValue());

        Integer begin = (currPage-1)*pageSize;
        List<LinkMan> linkMans = linkManDao.findByPage(detachedCriteria,begin,pageSize);
        pageBean.setList(linkMans);
        return pageBean;
    }
}
