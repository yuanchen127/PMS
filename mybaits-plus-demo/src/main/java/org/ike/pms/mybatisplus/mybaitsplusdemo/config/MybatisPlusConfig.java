package org.ike.pms.mybatisplus.mybaitsplusdemo.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.MultiDatasource.DataSourceEnum;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.MultiDatasource.MultiDataSourceTransactionFactory;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.MultiDatasource.MultipleDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = {"org.ike.pms.mybatisplus.mybaitsplusdemo.dao"})
public class MybatisPlusConfig {

    @Autowired
    private Environment env;

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    @Bean
    public ISqlInjector iSqlInjector(){
        return new LogicSqlInjector();
    }

    @Bean(name = "db1")
    @ConfigurationProperties(prefix = "spring.datasource.db1" )
    public DataSource db1() {
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        atomikosDataSourceBean.setUniqueResourceName("db1");
        atomikosDataSourceBean.setPoolSize(5);
        Properties properties = build(env, "spring.datasource.db1.");
        atomikosDataSourceBean.setXaProperties(properties);
        return atomikosDataSourceBean;
//        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "db2")
    @ConfigurationProperties(prefix = "spring.datasource.db2" )
    @Primary
    public DataSource db2() {
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        atomikosDataSourceBean.setUniqueResourceName("db2");
        atomikosDataSourceBean.setPoolSize(5);
        Properties properties = build(env, "spring.datasource.db2.");
        atomikosDataSourceBean.setXaProperties(properties);
        return atomikosDataSourceBean;
//        return DruidDataSourceBuilder.create().build();
    }

    @Autowired
    @Bean
//    @Primary
    public DataSource multipleDataSource(@Qualifier("db1") DataSource db1, @Qualifier("db2") DataSource db2) {
        MultipleDataSource multipleDataSource = new MultipleDataSource();
        Map< Object, Object > targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEnum.DB1.getValue(), db1);
        targetDataSources.put(DataSourceEnum.DB2.getValue(), db2);
        //添加数据源
        multipleDataSource.setTargetDataSources(targetDataSources);
        //设置默认数据源
        multipleDataSource.setDefaultTargetDataSource(db1);
        return multipleDataSource;
    }

    @Bean(name = "transactionManager")
    @Primary
    public JtaTransactionManager regTransactionManager () {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }

    @Bean("sqlsSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(multipleDataSource(db1(), db2()));
        sqlSessionFactory.setTransactionFactory(new MultiDataSourceTransactionFactory());
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:org/ike/pms/mybatisplus/mybaitsplusdemo/dao/**/*Mapper.xml"));

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setPlugins(new Interceptor[]{ //PerformanceInterceptor(),OptimisticLockerInterceptor()
                paginationInterceptor() //添加分页功能
        });
        return sqlSessionFactory.getObject();
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(multipleDataSource(db1(env), db2(env)));

//        return sqlSessionFactoryBean.getObject();
    }

//    @Autowired
//    @Bean("sqlSessionFactory_db2")
//    @Primary
    public SqlSessionFactory sqlSessionFactory_db2() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(db2());

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setPlugins(new Interceptor[]{ //PerformanceInterceptor(),OptimisticLockerInterceptor()
                paginationInterceptor() //添加分页功能
        });
        return sqlSessionFactory.getObject();
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(multipleDataSource(db1(env), db2(env)));

//        return sqlSessionFactoryBean.getObject();
    }

    private Properties build(Environment env, String prefix) {
        Properties properties = new Properties();
        properties.put("driverClassName", env.getProperty("spring.datasource.driver-class-name"));
        properties.put("url", env.getProperty(prefix+"url"));
        properties.put("username", env.getProperty(prefix+"username"));
        properties.put("password", env.getProperty(prefix+"password"));
        return properties;
    }

}