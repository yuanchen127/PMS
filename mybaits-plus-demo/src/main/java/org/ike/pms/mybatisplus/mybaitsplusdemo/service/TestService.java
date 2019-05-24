package org.ike.pms.mybatisplus.mybaitsplusdemo.service;

import org.ike.pms.mybatisplus.mybaitsplusdemo.config.mapper.WithTableService;
import org.ike.pms.mybatisplus.mybaitsplusdemo.dao.TestMapper;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;

public interface TestService extends WithTableService<TUser> {
    TestMapper getMapper();
}
