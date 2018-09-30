package org.pms.springmvc.service;

import java.util.List;

import org.pms.springmvc.bean.User;

public interface UserService {
	 //用户注册  
    void regist(User user);  
    //用户登录  
    void login(String name, String password);

    //查询用户
    public List<User> getUsers();
}
