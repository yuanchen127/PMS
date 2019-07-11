package org.ike.pms.mybatisplus.mybaitsplusdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.common.SpringContext;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.interceptor.ExecutorInterceptor;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;
import org.ike.pms.mybatisplus.mybaitsplusdemo.service.ITUserService;
import org.ike.pms.mybatisplus.mybaitsplusdemo.service.TestService;
import org.ike.pms.mybatisplus.mybaitsplusdemo.vo.BaseVo;
import org.ike.pms.mybatisplus.mybaitsplusdemo.vo.TUserBatchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private ITUserService itUserService;

    @RequestMapping(value = "provider", method = RequestMethod.GET)
    List<TUser> test(@RequestBody Map params) {

        QueryWrapper<TUser> wrapper = new QueryWrapper<TUser>();
        QueryWrapper<TUser> wrapper1 = new QueryWrapper<TUser>();
        wrapper.select("user_id,user_name");
        wrapper.eq("user_id","tt");
        wrapper.orderByAsc("age");
        wrapper.groupBy("user_id");
//        wrapper.and(wrapper1->{});


        return testService.getMapper().testWrapper(new Page(1,2),params.get("table").toString(), wrapper);
    }

    @ApiOperation("统计表数据")
    @RequestMapping(value = "count", method = RequestMethod.GET)
    public int count(@RequestBody Map param) {
        try {
            return testService.countWithTable((String) param.get("table"));
//            return testService.getMapper().countWithTable((String) param.get("table"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @ApiOperation("按条件统计表数据")
    @RequestMapping(value = "count/param", method = RequestMethod.GET)
    public int countByParam(@RequestBody Map param) {
        try {
            QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("user_id", "test");
            return testService.countWithTable((String) param.get("table"), queryWrapper);
//            return testService.getMapper().countWithTable((String) param.get("table"), queryWrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @ApiOperation("查询实体")
    @RequestMapping(value = "one", method = RequestMethod.GET)
    public TUser getOne(@RequestBody Map param) {
        try {
            return testService.getOneWithTable((String) param.get("table"));
//            return testService.getMapper().getOneWithTable((String)param.get("table"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation("按照条件查询实体")
    @RequestMapping(value = "one/param", method = RequestMethod.GET)
    public TUser getOneByParam(@RequestBody Map param) {
        try {
            QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("user_id", "test");
            queryWrapper.select("user_id");
            return testService.getOneWithTable((String) param.get("table"), queryWrapper);
//            return testService.getMapper().getOneWithTable((String) param.get("table"), queryWrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = {"mybatis/list", "mybatis/list/{pageNum}/{pageSize}"}, method = RequestMethod.GET)
    public List testList(@RequestBody Map param, @PathVariable(required = false) Integer pageNum, @PathVariable(required = false) Integer pageSize) {
        String table = (String) param.get("table");
        ExecutorInterceptor temp = SpringContext.getBean(ExecutorInterceptor.class);

        return testService.list();
    }


    @ApiOperation("查询列表")
    @RequestMapping(value = {"list", "list/{pageNum}/{pageSize}"}, method = RequestMethod.GET)
    public Page<Map> list(@RequestBody Map param, @PathVariable(required = false) Integer pageNum, @PathVariable(required = false) Integer pageSize) {
        String table = (String) param.get("table");
//        return testService.getMapper().listWithTable(table);
//        return testService.listWithTable(table);
        ExecutorInterceptor temp = SpringContext.getBean(ExecutorInterceptor.class);
        Page<Map> page;
        if (Objects.nonNull(pageNum) && Objects.nonNull(pageSize)) {
            page = new Page<>(pageNum, pageSize);
        } else {
            page = new Page<>();
        }

        return testService.getMapper().listWithTable(page, table);
    }

    @ApiOperation("按条件查询列表")
    @RequestMapping(value = "list/param", method = RequestMethod.GET)
    public List listByParam(@RequestBody Map param) {
        String table = (String) param.get("table");
        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_id");
        queryWrapper.like("user_id", "test");
        queryWrapper.isNotNull("user_id");
//        queryWrapper.exists("select user_id from pms.t_user where user_id='test'");
//        queryWrapper.groupBy("user_id");
//        return testService.getMapper().listWithTable(table, queryWrapper);
        return testService.listWithTable(table, queryWrapper);
    }

    @ApiOperation("通过实体对象添加数据")
    @RequestMapping(value = "save/entity", method = RequestMethod.GET)
    @Transactional
    public boolean saveByEntity(@RequestBody BaseVo baseVo) {
            try {
            if (StringUtils.isEmpty(baseVo.getParam().getUserId())) {
                TUser user = baseVo.getParam().setUserId(UUID.randomUUID().toString());
//                return testService.getMapper().saveWithTable(baseVo.getTable(), user);
                return testService.saveWithTable(baseVo.getTable(), baseVo.getParam());
            }

        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return false;
    }

    @ApiOperation("通过实体对象添加数据")
    @RequestMapping(value = "batch/save/entity", method = RequestMethod.GET)
    @Transactional
    public boolean saveBatchByEntity(@RequestBody TUserBatchVo batchVo) {
        try {
            List<TUser> list = batchVo.getList();
            for (TUser user : list) {
                if (StringUtils.isEmpty(user.getUserId())) {
                    user.setUserId(UUID.randomUUID().toString());
                }
            }
            return testService.saveBatchWithTable(batchVo.getTable(), list);
//            return testService.getMapper().saveBatchWithTable(batchVo.getTable(),list);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return false;
    }

    @ApiOperation("通过id删除数据")
    @RequestMapping(value = "delete/id", method = RequestMethod.GET)
    @Transactional
    public boolean removeById(@RequestBody Map<String, String> param) {
        try {
            return testService.removeByIdWithTable(param.get("table"), param.get("id"));
//            return testService.getMapper().removeByIdWithTable(param.get("table"),param.get("id"));
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return false;
    }

    @ApiOperation("通过id批量删除数据")
    @RequestMapping(value = "batch/delete/id", method = RequestMethod.GET)
    @Transactional
    public boolean removeByIds(@RequestBody Map map) {
        try {
            List list = (List) map.get("list");
            return testService.removeByIdsWithTable((String) map.get("table"), list);
//            return testService.getMapper().removeByIdsWithTable((String) map.get("table"),list);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return false;
    }

    @ApiOperation("按条件删除数据")
    @RequestMapping(value = "delete/param", method = RequestMethod.GET)
    @Transactional
    public boolean remove(@RequestBody Map map) {
        try {
            QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id","xml");
//            return testService.getMapper().removeWithTable((String) map.get("table"), queryWrapper);
            return testService.removeWithTable((String) map.get("table"), queryWrapper);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return false;
    }

    @ApiOperation("通过实体删除数据")
    @RequestMapping(value = "delete/entity", method = RequestMethod.GET)
    @Transactional
    public boolean removeByEntity(@RequestBody Map map) {
        try {
            TUser user = new TUser()
                    .setUserId("test3")
                    .setAge(23);
            return testService.removeWithTable((String) map.get("table"), user);
//            return testService.getMapper().removeWithTable((String) map.get("table"), user);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return false;
    }

    @ApiOperation("通过ID更新数据")
    @RequestMapping(value = "update/id", method = RequestMethod.GET)
    @Transactional
    public boolean updateByIdWithTable(@RequestBody BaseVo baseVo) {
        try {
//            return testService.getMapper().updateWithTable(baseVo.getTable(), baseVo.getParam());
//            TUser bean = SpringContext.getBean(TUser.class);
            return testService.updateWithTable(baseVo.getTable(), baseVo.getParam());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @ApiOperation("按条件更新数据")
    @RequestMapping(value = "update/param", method = RequestMethod.GET)
    @Transactional
    public boolean updateByParamWithTable(@RequestBody BaseVo baseVo) {
        try {
            UpdateWrapper<TUser> updateWrapper = new UpdateWrapper<>();
            updateWrapper.like("user_id", "test");
//            return testService.getMapper().updateWithTable(baseVo.getTable(), baseVo.getParam(), updateWrapper);
            return testService.updateWithTable(baseVo.getTable(), baseVo.getParam(), updateWrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @ApiOperation("通过wrapper更新数据")
    @RequestMapping(value = "update/wrapper", method = RequestMethod.GET)
    @Transactional
    public int UpdateByWrapperWithTable(@RequestBody BaseVo baseVo) {
        try {
//            return testService.getMapper().saveOrUpdateWithTable(baseVo.getTable(), baseVo.getParam());
            UpdateWrapper<TUser> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("age", "123");
            updateWrapper.eq("user_id", "tt");
            return testService.getMapper().updateWrapper(baseVo.getTable(), updateWrapper);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return 0;
    }

    @ApiOperation("通过ID批量更新数据")
    @RequestMapping(value = "batch/update/id", method = RequestMethod.GET)
    @Transactional
    public boolean updateBatchByIdWithTable(@RequestBody TUserBatchVo batchVo) {
        try {
//            return testService.getMapper().updateBatchByIdWithTable(batchVo.getTable(), batchVo.getList());
            return testService.updateBatchByIdWithTable(batchVo.getTable(), batchVo.getList());
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return false;
    }

    @ApiOperation("通过ID新增或更新数据")
    @RequestMapping(value = "save_update/id", method = RequestMethod.GET)
    @Transactional
    public boolean saveOrUpdateWithTable(@RequestBody BaseVo baseVo) {
        try {
//            return testService.getMapper().saveOrUpdateWithTable(baseVo.getTable(), baseVo.getParam());
            return testService.saveOrUpdateByIdWithTable(baseVo.getTable(), baseVo.getParam());
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return false;
    }



    @ApiOperation("通过ID批量新增或更新数据")
    @RequestMapping(value = "batch/save_update/id", method = RequestMethod.GET)
    @Transactional
    public boolean saveOrUpdateBatchWithTable(@RequestBody TUserBatchVo batchVo) {
        try {
            List<TUser> list = new ArrayList<>();
            list.add(new TUser().setUserId("oooo").setUserName("ooo").setPassword("ooo"));
            list.add(new TUser().setUserId("oooo1").setUserName("ooo1").setPassword("ooo1"));
//            itUserService.saveOrUpdateBatch(list);
            return testService.saveOrUpdateBatchByIdWithTable(batchVo.getTable(), batchVo.getList());
//            return testService.getMapper().saveOrUpdateBatchWithTable(batchVo.getTable(), batchVo.getList());
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return false;
    }

    @ApiOperation("按条件新增或更新数据")
    @RequestMapping(value = "save_update/param", method = RequestMethod.GET)
    @Transactional
    public boolean saveOrUpdateByParamWithTable(@RequestBody TUserBatchVo batchVo) {
        try {
//            itUserService.saveOrUpdateBatch(list);
            QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();

            queryWrapper.eq("user_id","qwe");
            queryWrapper.eq("user_name","qwe");
            return testService.saveOrUpdateWithTable(batchVo.getTable(), new TUser().setUserId("qwe").setUserName("qwe").setPassword("qwe"),queryWrapper);
//            return testService.getMapper().saveOrUpdateBatchWithTable(batchVo.getTable(), batchVo.getList());
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return false;
    }

    @ApiOperation("多主键新增或更新数据")
    @RequestMapping(value = "save_update/multi_id", method = RequestMethod.GET)
    @Transactional
    public boolean saveOrUpdateByMultiIdWithTable(@RequestBody TUserBatchVo batchVo) {
        try {
//            itUserService.saveOrUpdateBatch(list);
            return testService.saveOrUpdateWithTable(batchVo.getTable(), new TUser().setUserId("qwe").setUserName("qwe").setPassword("qwe"), new ArrayList<String>() {
                {
                    add("user_id");
                    add("user_name");
                }
            });
//            return testService.getMapper().saveOrUpdateBatchWithTable(batchVo.getTable(), batchVo.getList());
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return false;
    }

    @ApiOperation("按条件批量新增或更新数据")
    @RequestMapping(value = "batch/save_update/param", method = RequestMethod.GET)
    @Transactional
    public boolean saveOrUpdateBatchByParamWithTable(@RequestBody TUserBatchVo batchVo) {
        try {
//            itUserService.saveOrUpdateBatch(list);
            return testService.saveOrUpdateBatchWithTable(batchVo.getTable(), batchVo.getList(),new ArrayList<String>(){{
                add("user_id");
                add("user_name");
            }});

//            return testService.getMapper().saveOrUpdateBatchWithTable(batchVo.getTable(), batchVo.getList());
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return false;
    }

}
