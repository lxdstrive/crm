package com.itheima.crm.dao;

import com.itheima.crm.domain.LinkMan;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * @author BJXT-LXD
 */
public interface LinkManDao {
    Integer findCount(DetachedCriteria detachedCriteria);

    List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);
}
