package com.itheima.crm.service;

import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

/**
 * @author BJXT-LXD
 */
public interface LinkManService {
    PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);
}
