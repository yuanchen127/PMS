package org.ike.pms.mybatisplus.mybaitsplusdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;
import org.ike.pms.mybatisplus.mybaitsplusdemo.service.ProviderService;
import org.ike.pms.mybatisplus.mybaitsplusdemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @Autowired
    private TestService testService;

    @RequestMapping(value = "insert")
    public Integer insert() {
//        providerService.getMapper()
        return providerService.getMapper().pt_insert("pms.t_user", new TUser().setUserId("p1").setUserName("p1").setPassword("123"));
    }

    @RequestMapping("mybatis")
    public void testMybatisplus() {
        testService.save(new TUser().setUserId("p1").setUserName("p1").setPassword("123"));
    }


    @RequestMapping("list")
    public List testList() {
        QueryWrapper<TUser> wrapper = new QueryWrapper<>();
        wrapper.select("user_id");
        wrapper.eq("user_id", "tt");
        return providerService.getMapper().testList("pms.t_user", wrapper);
    }


}
