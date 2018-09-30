package org.ike.pms.api.service;

import org.ike.pms.api.entity.User;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	public User test(){
		User u = new User();
		u.setId(1);
		u.setName("ike");
		u.setDescription("this is a test user");
		return u;
	}
}
