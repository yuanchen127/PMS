package org.pms.springmvc.controller;

import java.util.List;
import java.util.UUID;

import org.pms.springmvc.bean.User;
import org.pms.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author ike
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired  
	private UserService userService;
	
	@RequestMapping("regist")
	public String regist(User user, Model model) {
		System.out.println("用户注册：" + user.getName() + user.getPassword());
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		user.setId(uuid);
		userService.regist(user);
		model.addAttribute("msg", "注册成功");
		// 注册成功后跳转success.jsp页面
		return "../../success";
	}

	@RequestMapping("login")
	public String login(User user, Model model) {
		System.out.println("用户登录：" + user.getName() + user.getPassword());
		userService.login(user.getName(), user.getPassword());
		List<User> userList = userService.getUsers();
		model.addAttribute("users", userList);
		System.out.println(userList);
		return "user/userList";
	}

}