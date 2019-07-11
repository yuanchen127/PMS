package org.ike.pms.mybatisplus.mybaitsplusdemo.service.impl;

import org.ike.pms.mybatisplus.mybaitsplusdemo.config.provider.PtMapper;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;
import org.ike.pms.mybatisplus.mybaitsplusdemo.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    private PtMapper<TUser> ptMapper;

    public PtMapper getMapper() {
        return ptMapper;
    }
}
