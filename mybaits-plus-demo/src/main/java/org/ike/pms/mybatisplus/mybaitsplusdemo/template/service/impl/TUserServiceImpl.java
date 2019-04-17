package org.ike.pms.mybatisplus.mybaitsplusdemo.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ike.pms.mybatisplus.mybaitsplusdemo.template.dao.TUserMapper;
import org.ike.pms.mybatisplus.mybaitsplusdemo.template.model.TUser;
import org.ike.pms.mybatisplus.mybaitsplusdemo.template.service.ITUserService;
import org.springframework.stereotype.Service;
/**
*
* @author ike
* @since 2019-04-17
*/

@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService{

    public TUserMapper getMapper() {
        return this.baseMapper;
    }
}