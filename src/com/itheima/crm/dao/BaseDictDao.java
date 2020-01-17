package com.itheima.crm.dao;

import com.itheima.crm.domain.BaseDict;

import java.util.List;

/**
 * 字典dao层的接口类
 * @author BJXT-LXD
 */
public interface BaseDictDao extends BaseDao<BaseDict>{
    List<BaseDict> findByTypeCode(String dict_type_code);
}
