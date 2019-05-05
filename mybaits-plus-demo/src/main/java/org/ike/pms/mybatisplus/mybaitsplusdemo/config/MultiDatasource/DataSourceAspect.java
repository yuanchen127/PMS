package org.ike.pms.mybatisplus.mybaitsplusdemo.config.MultiDatasource;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.MybatisPlusConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
@Order(0)
public class DataSourceAspect {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private MybatisPlusConfig mybatisPlusConfig;

    @Pointcut("@within(DataSource) || @annotation(DataSource)")
    public void pointCut(){

    }

    @Before("pointCut() && @annotation(dataSource)")
    public void doBefore(DataSource dataSource){
        try {
            log.info("选择数据源---" + dataSource.value().getValue());
//            if (DataSourceEnum.DB1 == dataSource.value()) {
//                sqlSessionFactory = mybatisPusConfig.sqlSessionFactory();
//            } else {
//                sqlSessionFactory = mybatisPusConfig.sqlSessionFactory_db2();
//            }

            DataSourceContextHolder.setDataSource(dataSource.value().getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @After("pointCut()")
    public void doAfter(){
        try {
            DataSourceContextHolder.clear();
//            sqlSessionFactory = mybatisPusConfig.sqlSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}