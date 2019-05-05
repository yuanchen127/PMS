package org.ike.pms.mybatisplus.mybaitsplusdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ike.pms.mybatisplus.mybaitsplusdemo.dao.TUserMapper;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ike
 * @since 2019-04-11
 */
public interface ITUserService extends IService<TUser> {

    TUserMapper getMapper();

    List<TUser> testList();

    List<TUser> testSelect(String user_name, String password);

    void testInsert1();

    void testInsert2();
}
