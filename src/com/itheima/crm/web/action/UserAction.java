package com.itheima.crm.web.action;

import com.itheima.crm.domain.User;
import com.itheima.crm.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.List;

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

    public String findAllUser() throws IOException {
        List<User> users = userService.findAllUser();

        JsonConfig jsonConfig= new JsonConfig();
        jsonConfig.setExcludes(new String[]{"user_code","user_password","user_state"});

        JSONArray jsonArray = JSONArray.fromObject(users,jsonConfig);

        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return NONE;
    }

}
