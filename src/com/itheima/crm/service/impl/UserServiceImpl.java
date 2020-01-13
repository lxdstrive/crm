package com.itheima.crm.service.impl;

import com.itheima.crm.dao.UserDao;
import com.itheima.crm.domain.User;
import com.itheima.crm.service.UserService;
import com.itheima.crm.utils.MD5Utils;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lxd
 * @date 2020/1/11 19:07
 * @description
 */
@Transactional
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void regist(User user) {
//        对密码进行加密的处理
        user.setUser_password(MD5Utils.md5(user.getUser_password()));
        user.setUser_state("1");
        //调用Dao
        userDao.save(user);
    }

    /**
     * 用户的登录功能
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        user.setUser_password(MD5Utils.md5(user.getUser_password()));

        return userDao.login(user);
    }
}
