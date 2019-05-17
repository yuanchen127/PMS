package org.ike.pms.mybatisplus.mybaitsplusdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.ApiOperation;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;
import org.ike.pms.mybatisplus.mybaitsplusdemo.service.TestService;
import org.ike.pms.mybatisplus.mybaitsplusdemo.vo.BaseVo;
import org.ike.pms.mybatisplus.mybaitsplusdemo.vo.TUserBatchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    private TestService testService;


    @ApiOperation("查询列表")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List list(@RequestBody Map param) {
        String table = (String) param.get("table");
        return testService.getMapper().listWithTable(table);
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
        queryWrapper.groupBy("user_id");
        return testService.getMapper().listWithTable(table, queryWrapper);
    }

    @ApiOperation("通过实体对象添加数据")
    @RequestMapping(value = "save/entity", method = RequestMethod.GET)
    @Transactional
    public int saveByEntity(@RequestBody BaseVo baseVo) {
            try {
            if (StringUtils.isEmpty(baseVo.getParam().getUserId())) {
                return testService.getMapper().saveWithTable(baseVo.getTable(), baseVo.getParam().setUserId(UUID.randomUUID().toString()));
            }

        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return testService.getMapper().saveWithTable(baseVo.getTable(), baseVo.getParam());
    }

    @ApiOperation("通过实体对象添加数据")
    @RequestMapping(value = "batch/save/entity", method = RequestMethod.GET)
    @Transactional
    public int saveBatchByEntity(@RequestBody TUserBatchVo batchVo) {
        try {
            List<TUser> list = batchVo.getList();
            for (TUser user : list) {
                if (StringUtils.isEmpty(user.getUserId())) {
                    user.setUserId(UUID.randomUUID().toString());
                }
            }
            return testService.getMapper().saveBatchWithTable(batchVo.getTable(),list);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return 0;
    }

    @ApiOperation("通过id删除数据")
    @RequestMapping(value = "delete/id", method = RequestMethod.GET)
    @Transactional
    public int removeById(@RequestBody Map<String, String> param) {
        try {
            return testService.getMapper().removeByIdWithTable(param.get("table"),param.get("id"));
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return 0;
    }

    @ApiOperation("通过id批量删除数据")
    @RequestMapping(value = "batch/delete/id", method = RequestMethod.GET)
    @Transactional
    public int removeByIds(@RequestBody Map map) {
        try {
            List list = (List) map.get("list");
            return testService.getMapper().removeByIdsWithTable((String) map.get("table"),list);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return 0;
    }

    @ApiOperation("按条件删除数据")
    @RequestMapping(value = "delete/param", method = RequestMethod.GET)
    @Transactional
    public int remove(@RequestBody Map map) {
        try {
            QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("user_id","xml");
            return testService.getMapper().removeWithTable((String) map.get("table"), queryWrapper);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return 0;
    }

    @ApiOperation("通过ID更新数据")
    @RequestMapping(value = "update/id", method = RequestMethod.GET)
    @Transactional
    public int updateByIdWithTable(@RequestBody BaseVo baseVo) {
        try {
            return testService.getMapper().updateWithTable(baseVo.getTable(), baseVo.getParam());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @ApiOperation("按条件更新数据")
    @RequestMapping(value = "update/param", method = RequestMethod.GET)
    @Transactional
    public int updateByParamWithTable(@RequestBody BaseVo baseVo) {
        try {
            UpdateWrapper<TUser> updateWrapper = new UpdateWrapper<>();
            updateWrapper.like("user_id", "test");
            return testService.getMapper().updateWithTable(baseVo.getTable(), baseVo.getParam(), updateWrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @ApiOperation("通过ID批量更新数据")
    @RequestMapping(value = "batch/update/id", method = RequestMethod.GET)
    @Transactional
    public int updateBatchByIdWithTable(@RequestBody TUserBatchVo batchVo) {
        try {
            return testService.getMapper().updateBatchByIdWithTable(batchVo.getTable(), batchVo.getList());
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return 0;
    }

    @ApiOperation("通过ID新增或更新数据")
    @RequestMapping(value = "save_update/id", method = RequestMethod.GET)
    @Transactional
    public int saveOrUpdateWithTable(@RequestBody BaseVo baseVo) {
        try {
            return testService.getMapper().saveOrUpdateWithTable(baseVo.getTable(), baseVo.getParam());
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return 0;
    }

    @ApiOperation("通过ID批量新增或更新数据")
    @RequestMapping(value = "batch/save_update/id", method = RequestMethod.GET)
    @Transactional
    public int saveOrUpdateBatchWithTable(@RequestBody TUserBatchVo batchVo) {
        try {
            return testService.getMapper().saveOrUpdateBatchWithTable(batchVo.getTable(), batchVo.getList());
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return 0;
    }



}
