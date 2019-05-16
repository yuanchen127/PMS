package org.ike.pms.mybatisplus.mybaitsplusdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ike.pms.mybatisplus.mybaitsplusdemo.dao.TestMapper;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;
import org.ike.pms.mybatisplus.mybaitsplusdemo.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, TUser> implements TestService {
    public TestMapper getMapper() {
        return this.baseMapper;
    }
}
