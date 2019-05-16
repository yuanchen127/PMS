package org.ike.pms.mybatisplus.mybaitsplusdemo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.MultiDatasource.DataSource;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.MultiDatasource.DataSourceEnum;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;
import org.ike.pms.mybatisplus.mybaitsplusdemo.service.ITUserService;
import org.ike.pms.mybatisplus.mybaitsplusdemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/test/xml/", method = RequestMethod.GET)
    public List testXml() {
        TUser user = new TUser();
        user.setUserName("123");
        List<TUser> list = itUserService.getMapper().listByParamWithTable(user, "pms.t_user");
//        List<TUser> list = itUserService.getMapper().testXml();
        return list;
    }

    @RequestMapping("test/map/{pageNum}/{pageSize}")
    public Page<Map<String, Object>> testMapSelect(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        Page<Map<String, Object>> page = new Page(pageNum, pageSize);

        return page.setRecords(itUserService.getMapper().testMapSelect(page ,"qwe","qwe"));
    }

    @RequestMapping("test")
    public List<TUser> testSelect(String v) {
        return itUserService.getMapper().testSelect("qwe","qwe");
    }

    @RequestMapping("test/provider/{pageNum}/{pageSize}")
    public Page<TUser> testProviderSelect(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        Page page = new Page(pageNum, pageSize);
        return page.setRecords(itUserService.getMapper().testProviderSelect(page, "qwe","qwe"));
    }

    @RequestMapping("list")
    public List<TUser> list(){
        return itUserService.testList();
    }

    @RequestMapping("list1")
    @DataSource(DataSourceEnum.DB2)
    public List<TUser> list1() {
        return itUserService.list();
    }

    @RequestMapping("/test/transaction")
    @Transactional
    public int testMultiTransaction() {
        itUserService.testInsert1();
//        int i = 1/0;
        itUserService.testInsert2();
        return 0;
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

