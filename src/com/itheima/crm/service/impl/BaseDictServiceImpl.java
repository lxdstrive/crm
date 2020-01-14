package com.itheima.crm.service.impl;

import com.itheima.crm.dao.BaseDictDao;
import com.itheima.crm.domain.BaseDict;
import com.itheima.crm.service.BaseDictService;

import java.util.List;

/**
 * 字典业务层的实现类
 * @author BJXT-LXD
 */
public class BaseDictServiceImpl implements BaseDictService {
    private BaseDictDao baseDictDao;

    public void setBaseDictDao(BaseDictDao baseDictDao) {
        this.baseDictDao = baseDictDao;
    }

    @Override
    public List<BaseDict> findByTypeCode(String dict_type_code) {

        return baseDictDao.findByTypeCode(dict_type_code);
    }
}
