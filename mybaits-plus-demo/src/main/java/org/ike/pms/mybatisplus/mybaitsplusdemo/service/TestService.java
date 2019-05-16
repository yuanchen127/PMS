package org.ike.pms.mybatisplus.mybaitsplusdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ike.pms.mybatisplus.mybaitsplusdemo.dao.TestMapper;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;

public interface TestService extends IService<TUser> {
    TestMapper getMapper();
}
