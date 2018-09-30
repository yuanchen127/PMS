package org.ike.pms.api.controller;

import org.ike.pms.api.entity.User;
import org.ike.pms.api.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@RequestMapping("/test")
	public User test(){
		return testService.test();
	}
}
