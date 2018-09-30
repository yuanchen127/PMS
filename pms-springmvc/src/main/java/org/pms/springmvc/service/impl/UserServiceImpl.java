package org.pms.springmvc.service.impl;

import java.util.List;

import org.pms.springmvc.bean.User;
import org.pms.springmvc.dao.UserDao;
import org.pms.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;

	public void regist(User user) {
		// TODO Auto-generated method stub
		userDao.addUser(user);
	}

	public void login(String name, String password) {
		// TODO Auto-generated method stub
		userDao.findUserByNameAndPwd(name, password);
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userDao.getUsers();
	}
}
