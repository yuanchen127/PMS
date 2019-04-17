package org.ike.pms.mybatisplus.mybaitsplusdemo.controller;


import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;
import org.ike.pms.mybatisplus.mybaitsplusdemo.service.ITUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ike
 * @since 2019-04-11
 */
@RestController
@RequestMapping("/user")
public class TUserController {

    @Autowired
    private ITUserService itUserService;

    @RequestMapping("test")
    public List<TUser> testSelect(String v) {
        return itUserService.testSelect("ike","123456");
    }

    @RequestMapping("list")
    public List<TUser> list(){
        return itUserService.testList();
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public boolean save(@RequestBody TUser tUser) {
        return itUserService.save(tUser);
    }

    @RequestMapping(value = "saveOrUpdate",method = RequestMethod.POST)
    public boolean update(@RequestBody TUser tUser) {
        tUser.setPhone("");
        return itUserService.saveOrUpdate(tUser);
    }

    @RequestMapping(value = "selectById",method = RequestMethod.POST)
    public TUser selectById(String id) {
        return itUserService.getMapper().selectUserById(id);
    }

    @RequestMapping(value = "selectUserByName",method = RequestMethod.POST)
    public List selectUserByName(String name) {
        return itUserService.getMapper().selectUserByName(name);
    }

    public Object test(){

        return null;
    }

}

