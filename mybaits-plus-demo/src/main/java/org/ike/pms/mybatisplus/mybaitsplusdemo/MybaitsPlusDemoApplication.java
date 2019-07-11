package org.ike.pms.mybatisplus.mybaitsplusdemo;

import org.ike.pms.mybatisplus.mybaitsplusdemo.config.MybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Configuration
@Import(MybatisPlusConfig.class)
@MapperScan("org.ike.pms.mybatisplus.mybaitsplusdemo.dao")
public class MybaitsPlusDemoApplication {

    @Bean
    public Object testBean(PlatformTransactionManager platformTransactionManager) {
        return new Object();
    }

    public static void main(String[] args) {
        SpringApplication.run(MybaitsPlusDemoApplication.class, args);
    }

}
