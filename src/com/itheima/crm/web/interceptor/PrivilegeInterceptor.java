package com.itheima.crm.web.interceptor;

import com.itheima.crm.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

/**
 * @author BJXT-LXD
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        //判断session中是否有用户登录的信息
        User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
        if (existUser == null){
            //有错误信息，页面跳转到登录界面
            ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();
            actionSupport.addActionError("您还没有登录！没有权限访问");
            return actionSupport.LOGIN;
        }else {
            return actionInvocation.invoke();
        }
    }
}
