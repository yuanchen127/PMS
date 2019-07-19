package org.ike.pms.mybatisplus.mybaitsplusdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;
import org.ike.pms.mybatisplus.mybaitsplusdemo.service.ProviderService;
import org.ike.pms.mybatisplus.mybaitsplusdemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "wt")
public class ProviderController {

    private static String TABLE_NAME = "pms.t_user";

    @Autowired
    private ProviderService providerService;

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public boolean insert(@RequestBody TUser user) {
//        return providerService.getMapper().wt_insert(TABLE_NAME, user);
        return providerService.saveWithTable(TABLE_NAME, user);
    }

    @RequestMapping(value = "delete/id", method = RequestMethod.POST)
    public boolean deleteById(@RequestBody TUser user) {
//        return providerService.getMapper().wt_deleteById(TABLE_NAME, user.getUserId());
        return providerService.removeByIdWithTable(TABLE_NAME, user.getUserId());
    }

    @RequestMapping(value = "delete/map", method = RequestMethod.POST)
    public boolean deleteByMap(@RequestBody Map<String, Object> map) {
//        return providerService.getMapper().wt_deleteByMap(TABLE_NAME, map);
        return providerService.removeWithTable(TABLE_NAME, map);
    }

    @RequestMapping(value = "delete/wrapper", method = RequestMethod.POST)
    public boolean delete(@RequestBody TUser user) {
        QueryWrapper<TUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", user.getUserId());
//        return providerService.getMapper().wt_delete(TABLE_NAME, wrapper);
        return providerService.removeWithTable(TABLE_NAME, wrapper);
    }

    @RequestMapping(value = "delete/ids", method = RequestMethod.POST)
    public boolean deleteBatchIds(@RequestBody List<TUser> users) {
//        return providerService.getMapper().wt_deleteBatchIds(TABLE_NAME, users.stream().map(user->user.getUserId()).collect(Collectors.toList()));
        return providerService.removeBatchIdsWithTable(TABLE_NAME, users.stream().map(user->user.getUserId()).collect(Collectors.toList()));
    }

    @RequestMapping(value = "update/id", method = RequestMethod.POST)
    public boolean updateById(@RequestBody TUser user) {
//        return providerService.getMapper().wt_updateById(TABLE_NAME, user);
        return providerService.updateWithTable(TABLE_NAME, user);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public boolean update(@RequestBody TUser user) {
        QueryWrapper<TUser> wrapper = new QueryWrapper<>();
        wrapper.eq("1", "1");
//        return providerService.getMapper().wt_update(TABLE_NAME, user, wrapper);
        return providerService.updateWithTable(TABLE_NAME, user, wrapper);
    }


    @RequestMapping(value = "select/id", method = RequestMethod.POST)
    public TUser selectById(@RequestBody TUser user) {
//        return providerService.getMapper().wt_selectById(TABLE_NAME, user.getUserId());
        return providerService.getByIdWithTable(TABLE_NAME, user.getUserId());
    }

    @RequestMapping(value = "select/ids", method = RequestMethod.POST)
    public List<TUser> selectBatchIds(@RequestBody List<TUser> users) {
//        return providerService.getMapper().wt_selectBatchIds(TABLE_NAME, users.stream().map(user -> user.getUserId()).collect(Collectors.toList()));
        return providerService.listWithTable(TABLE_NAME, users.stream().map(user -> user.getUserId()).collect(Collectors.toList()));
    }

    @RequestMapping(value = "select/map", method = RequestMethod.POST)
    public List<TUser> selectByMap() {
//        return providerService.getMapper().wt_selectByMap(TABLE_NAME, new HashMap<String, Object>(){{
//            put("1", "1");
//        }});
        return providerService.listWithTable(TABLE_NAME, new HashMap<String, Object>(){{
            put("1", "1");
        }});
    }

    @RequestMapping(value = "select/one", method = RequestMethod.POST)
    public TUser selectOne() {
        QueryWrapper<TUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", "1");
//        return providerService.getMapper().wt_selectOne(TABLE_NAME, wrapper);
        return providerService.getOneWithTable(TABLE_NAME, wrapper);
    }

    @RequestMapping(value = "select/count", method = RequestMethod.POST)
    public Integer selectCount() {
        QueryWrapper<TUser> wrapper = new QueryWrapper<>();
//        return providerService.getMapper().wt_selectCount(TABLE_NAME, wrapper);
        return providerService.countWithTable(TABLE_NAME, wrapper);
    }

    @RequestMapping(value = "select/list", method = RequestMethod.POST)
    public List<TUser> selectList() {
        QueryWrapper<TUser> wrapper = new QueryWrapper<>();
//        return providerService.getMapper().wt_selectList(TABLE_NAME, wrapper);
        return providerService.listWithTable(TABLE_NAME, wrapper);
    }

    @RequestMapping(value = "list/map", method = RequestMethod.POST)
    public List<Map<String, Object>> selectMaps() {
        QueryWrapper<TUser> wrapper = new QueryWrapper<>();
//        return providerService.getMapper().wt_selectMaps(TABLE_NAME, wrapper);
        return providerService.listMapsWithTable(TABLE_NAME, wrapper);
    }

    @RequestMapping(value = "list/obj")
    public List<Object> selectObjs() {
        QueryWrapper<TUser> wrapper = new QueryWrapper<>();
//        return providerService.getMapper().wt_selectObjs(TABLE_NAME, wrapper);
        return providerService.listObjWithTable(TABLE_NAME, wrapper);
    }

    @RequestMapping(value = "list/page", method = RequestMethod.POST)
    public IPage<TUser> selectPage() {
//        return providerService.getMapper().wt_selectPage(new Page<TUser>(), TABLE_NAME, new QueryWrapper<TUser>());
        return providerService.pageWithTable(new Page<>(1, 10), TABLE_NAME);
    }

    @RequestMapping(value = "list/map/page",method = RequestMethod.POST)
    public IPage<Map<String, Object>> selectMapPage() {
//        return providerService.getMapper().wt_selectMapsPage(new Page<TUser>(), TABLE_NAME, new QueryWrapper<TUser>());
        return providerService.pageMapsWithTable(new Page<>(1, 10), TABLE_NAME);
    }

}
