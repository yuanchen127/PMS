package org.ike.pms.mybatisplus.mybaitsplusdemo.template.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ike.pms.mybatisplus.mybaitsplusdemo.template.dao.TUserMapper;
import org.ike.pms.mybatisplus.mybaitsplusdemo.template.model.TUser;

/**
*
* @author ike
* @since 2019-04-17
*/
public interface ITUserService extends IService<TUser>{

    TUserMapper getMapper();

}