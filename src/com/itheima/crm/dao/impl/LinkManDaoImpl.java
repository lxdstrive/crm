package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.LinkManDao;
import com.itheima.crm.domain.LinkMan;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * @author BJXT-LXD
 */
public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {

    @Override
    public Integer findCount(DetachedCriteria detachedCriteria) {
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size()>0){
            return list.get(0).intValue();
        }
        return null;
    }

    //dao的分页查询
    @Override
    public List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        detachedCriteria.setProjection(null);

        return (List<LinkMan>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
    }
}
