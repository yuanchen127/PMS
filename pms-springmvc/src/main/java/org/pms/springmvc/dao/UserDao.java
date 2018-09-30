package org.pms.springmvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.pms.springmvc.bean.User;

public interface UserDao {
	public void addUser(User user);
	public void findUserByNameAndPwd(@Param("name")String name, @Param("password")String password);
	public List<User> getUsers();
}
