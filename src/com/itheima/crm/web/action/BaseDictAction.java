package com.itheima.crm.web.action;

import com.itheima.crm.domain.BaseDict;
import com.itheima.crm.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author BJXT-LXD
 */
public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {
    private BaseDict baseDict = new BaseDict();
    @Override
    public BaseDict getModel() {
        return baseDict;
    }
    private BaseDictService baseDictService;

    public void setBaseDictService(BaseDictService baseDictService) {
        this.baseDictService = baseDictService;
    }
    /**
     * 根据类型名称查询字典
     */
    public String findByTypeCode(){
        System.out.println("BaseDictAction中的findByTypeCode方法执行了....");
        return NONE;
    }
}
