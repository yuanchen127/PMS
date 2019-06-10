package org.ike.pms.mybatisplus.mybaitsplusdemo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.mapper.WithTableService;
import org.ike.pms.mybatisplus.mybaitsplusdemo.dao.TestMapper;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;

import java.util.List;

public interface TestService extends WithTableService<TUser> {
    TestMapper getMapper();

    Page<TUser> testPage(Page<TUser> page, String table);

}
