package com.itheima.crm.service.impl;

import com.itheima.crm.dao.BaseDictDao;
import com.itheima.crm.service.BaseDictService;

/**
 * @author BJXT-LXD
 */
public class BaseDictServiceImpl implements BaseDictService {
    private BaseDictDao baseDictDao;

    public void setBaseDictDao(BaseDictDao baseDictDao) {
        this.baseDictDao = baseDictDao;
    }
}
