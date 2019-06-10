package org.ike.pms.mybatisplus.mybaitsplusdemo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.mapper.WithTableServiceImpl;
import org.ike.pms.mybatisplus.mybaitsplusdemo.dao.TestMapper;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;
import org.ike.pms.mybatisplus.mybaitsplusdemo.service.TestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl extends WithTableServiceImpl<TestMapper, TUser> implements TestService {
    @Override
    public TestMapper getMapper() {
        return this.baseMapper;
    }

    @Override
    public Page<TUser> testPage(Page<TUser> page, String table) {
//        return page.setRecords(this.withTableMapper.listWithTable(page, table));
        return page;
    }
}
