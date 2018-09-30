package org.pms.springmvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.pms.springmvc.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author ike
 *
 */

@Controller
@RequestMapping("/")
public class IndexController {
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String test(HttpServletRequest request,String name,String password,Model model) {
		System.out.println("接收到的用户名："+name);
		Map<String,Object> map = new HashMap<String,Object>(16);
		map.put("ss", "ok");
		User user = new User();
		user.setName("ike");
		user.setPassword("123");
		model.addAttribute("msg", user);
		return "/test/saveUserSuccess";
	}
	
	@RequestMapping(value="/index")
	public String index() {
		
		return "index.html";
	}

}
