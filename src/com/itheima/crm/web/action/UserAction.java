package com.itheima.crm.web.action;

import com.itheima.crm.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户管理的action的类
 * @author lxd
 * @date 2020/1/11 19:08
 * @description
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
    private User user = new User();
    @Override
    public User getModel() {
        return user;
    }
}
