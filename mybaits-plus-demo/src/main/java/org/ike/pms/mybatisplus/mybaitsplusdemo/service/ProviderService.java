package org.ike.pms.mybatisplus.mybaitsplusdemo.service;

import org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.WTMapper;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.WTService;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;

public interface ProviderService extends WTService<TUser> {

    WTMapper<TUser> getMapper();
}
