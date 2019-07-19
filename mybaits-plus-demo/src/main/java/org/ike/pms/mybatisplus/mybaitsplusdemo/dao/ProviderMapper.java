package org.ike.pms.mybatisplus.mybaitsplusdemo.dao;

import org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.WTMapper;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;
import org.springframework.stereotype.Component;

@Component
public interface ProviderMapper extends WTMapper<TUser>  {


}
