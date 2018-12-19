package org.ike.pms.api.service.impl;

import com.github.pagehelper.PageHelper;
import org.ike.pms.api.entity.User;
import org.ike.pms.api.mapper.UserMapper;
import org.ike.pms.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public int addUser(User user) {
        int count = userMapper.insertSelective(user);
//        String a = null;
//        a.indexOf('c');
        return count;
    }

    @Override
    public List<User> findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectAllUser();
    }
}