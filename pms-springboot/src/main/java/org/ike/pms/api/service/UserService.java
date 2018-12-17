package org.ike.pms.api.service;

import org.ike.pms.api.entity.User;

import java.util.List;

public interface UserService {

    int addUser(User user);

    List<User> findAllUser(int pageNum, int pageSize);
}

