package com.itheima.crm.web.action;

import com.itheima.crm.domain.User;
import com.itheima.crm.service.UserService;
import com.opensymphony.xwork2.ActionContext;
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
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User getModel() {
        return user;
    }

    //用户注册的方法
    public String regist(){
        userService.regist(user);
        return LOGIN;
    }
    /**
     * 用户登录的方法
     */
    public  String login(){
        User existUser = userService.login(user);
        if (existUser == null){
            //登录失败
            this.addActionError("用户名或密码错误");
            return LOGIN;
        }else {
            //登录成功
            //将existUser放到session对象中去
            //ServletActionContext.getRequest().getSession().setAttribute("existUser",existUser);
            ActionContext.getContext().getSession().put("existUser",existUser);
            return SUCCESS;
        }
    }
}
