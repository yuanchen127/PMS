package org.ike.pms.mybatisplus.mybaitsplusdemo.config.interceptor;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Intercepts({@Signature(
        type= ParameterHandler.class,
        method = "query",
        args = {MappedStatement.class,Object.class})})
@Component
public class ParameterHandlerInterceptor implements Interceptor{
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
