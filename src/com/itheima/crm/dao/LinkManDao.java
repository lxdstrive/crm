package com.itheima.crm.dao;

import com.itheima.crm.domain.LinkMan;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * @author BJXT-LXD
 */
public interface LinkManDao extends BaseDao<LinkMan> {
    Integer findCount(DetachedCriteria detachedCriteria);

    List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);



    LinkMan findById(Long lkm_id);

}
