package org.ike.pms.mybatisplus.mybaitsplusdemo.service.impl;

import org.ike.pms.mybatisplus.mybaitsplusdemo.config.mapper.WithTableServiceImpl;
import org.ike.pms.mybatisplus.mybaitsplusdemo.dao.TestMapper;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;
import org.ike.pms.mybatisplus.mybaitsplusdemo.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl extends WithTableServiceImpl<TestMapper, TUser> implements TestService {

}
