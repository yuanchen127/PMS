package org.ike.pms.mybatisplus.mybaitsplusdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Data;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.MultiDatasource.DataSource;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.MultiDatasource.DataSourceEnum;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.MultiDatasource.Ike;
import org.ike.pms.mybatisplus.mybaitsplusdemo.dao.TUserMapper;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;
import org.ike.pms.mybatisplus.mybaitsplusdemo.service.ITUserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ike
 * @since 2019-04-11
 */
@Service
@Ike("ike")
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {
    public TUserMapper getMapper() {
        return this.baseMapper;
    }

    @DataSource(DataSourceEnum.DB2)
    public List<TUser> testList(){
        return list();
    }

    @DataSource(DataSourceEnum.DB2)
    public List<TUser> testSelect(String user_name,String password){
        return this.baseMapper.testSelect(user_name,password);
    }

    @DataSource(DataSourceEnum.DB1)
    public void testInsert1() {
        TUser user = new TUser();
        user.setUserId("test-transaction");
        user.setUserName("transaction");
        user.setPassword("123");
        this.baseMapper.insert(user);
    }

    @DataSource(DataSourceEnum.DB2)
    public void testInsert2() {
        TUser user = new TUser();
        user.setUserId("test-transactions");
        user.setUserName("transaction");
        user.setPassword("123");
        this.baseMapper.insert(user);
    }

}
