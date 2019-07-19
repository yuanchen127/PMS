package org.ike.pms.mybatisplus.mybaitsplusdemo.service.impl;

import org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.WTMapper;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.WTServiceImpl;
import org.ike.pms.mybatisplus.mybaitsplusdemo.dao.ProviderMapper;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;
import org.ike.pms.mybatisplus.mybaitsplusdemo.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl extends WTServiceImpl<ProviderMapper, TUser> implements ProviderService {
    @Autowired
    private WTMapper<TUser> ptMapper;

    public WTMapper<TUser> getMapper() {
        return ptMapper;
    }
}
