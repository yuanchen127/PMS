package org.ike.pms.mybatisplus.mybaitsplusdemo.config.interceptor;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;

import java.util.Map;
import java.util.Properties;
@Slf4j
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class NullHandlerInterceptor implements Interceptor{
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("ExecutorInterceptor.intercept");
        handleNull(invocation);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        log.info("ExecutorInterceptor.plugin");
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }

    private void handleNull(Invocation invocation) {
        final Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        Object parameters = args[1];
        Map map = (Map) parameters;
        Class<?> clazz = map.get("obj").getClass();
        Object var1 = clazz.cast(map.get("obj"));

        TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);

        BoundSql boundSql = mappedStatement.getBoundSql(parameters);
        MappedStatement newMappedStatement = newMappedStatement(mappedStatement, new BoundSqlSource(boundSql));
        tableInfo.getFieldList().stream().filter(fieldInfo -> fieldInfo.getPropertyType().getName().equals(String.class.getName()) && Constants.EMPTY.equals(ReflectionKit.getMethodValue(clazz, var1, fieldInfo.getProperty()))).forEach(field -> {
            try {
                clazz.getMethod(StringUtils.concatCapitalize("set", field.getProperty()), field.getPropertyType()).invoke(var1, new Object[]{null});
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        args[0] = newMappedStatement;
    }

    private MappedStatement newMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuilder keyProperties = new StringBuilder();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(".");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }

    class BoundSqlSource implements SqlSource {
        private BoundSql boundSql;

        BoundSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object object) {
            return boundSql;
        }
    }
}