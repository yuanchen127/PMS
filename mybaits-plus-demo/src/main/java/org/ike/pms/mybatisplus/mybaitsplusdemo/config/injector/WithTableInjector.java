package org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector.method.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WithTableInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        //增加自定义方法
        methodList.addAll(Stream.of(
                new WT_Insert(),
                new WT_Delete(),
                new WT_DeleteByMap(),
                new WT_DeleteById(),
                new WT_DeleteBatchByIds(),
                new WT_Update(),
                new WT_UpdateById(),
                new WT_SelectById(),
                new WT_SelectBatchByIds(),
                new WT_SelectByMap(),
                new WT_SelectOne(),
                new WT_SelectCount(),
                new WT_SelectMaps(),
                new WT_SelectMapsPage(),
                new WT_SelectObjs(),
                new WT_SelectList(),
                new WT_SelectPage()).collect(Collectors.toList()));

        return methodList;
    }
}
