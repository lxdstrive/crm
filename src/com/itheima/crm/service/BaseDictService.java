package com.itheima.crm.service;

import com.itheima.crm.domain.BaseDict;

import java.util.List;

/**
 * @author BJXT-LXD
 */
public interface BaseDictService {
    List<BaseDict> findByTypeCode(String dict_type_code);
}
