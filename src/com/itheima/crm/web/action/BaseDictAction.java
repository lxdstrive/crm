package com.itheima.crm.web.action;

import com.itheima.crm.domain.BaseDict;
import com.itheima.crm.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.List;

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
    public String findByTypeCode() throws IOException {
        List<BaseDict> list = baseDictService.findByTypeCode(baseDict.getDict_type_code());
        //因为是异步的，需要将list转成json类型

        //JSONConfig : 转json的配置对象
        //JSONArray : 将数组或者list集合转成JSon
        //JSONObject : 将对象或者map集合转成JSON

        //json的属性配置
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"dict_sort","dict_enable","dict_memo"});
        JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
//        System.out.println(jsonArray.toString());
        //将json打印到界面上
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return NONE;
    }
}
