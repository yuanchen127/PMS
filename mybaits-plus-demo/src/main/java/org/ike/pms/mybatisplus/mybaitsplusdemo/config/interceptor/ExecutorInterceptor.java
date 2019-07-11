package org.ike.pms.mybatisplus.mybaitsplusdemo.config.interceptor;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class ExecutorInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("ExecutorInterceptor.intercept");
        getCurrentSql(invocation);
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

    private String getCurrentSql(Invocation invocation) {
        final Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        Object parameters = args[1];
        Map map = (Map) parameters;
//        map.put("table", "t_user");
        Class<? extends Object> clazz = map.get("obj").getClass();
        Object var1 = clazz.cast(map.get("obj"));

        TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);

        BoundSql boundSql = mappedStatement.getBoundSql(parameters);
        MappedStatement newMappedStatement = newMappedStatement(mappedStatement, new BoundSqlSource(boundSql));
//        MetaObject metaObject = MetaObject.forObject(newMappedStatement, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
//        String trimSql = trim(boundSql.getSql()).toUpperCase();
//        String setSql = getSubUtil(trimSql, "SET(.*?)WHERE");
//        StringBuilder fillSql = new StringBuilder();

        tableInfo.getFieldList().stream().filter(fieldInfo -> fieldInfo.getPropertyType().getName().contains("String") && "".equals(ReflectionKit.getMethodValue(clazz, var1, fieldInfo.getProperty()))).forEach(field -> {
            try {
                clazz.getMethod(StringUtils.concatCapitalize("set", field.getProperty()), field.getPropertyType()).invoke(var1, new Object[]{null});
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
//        metaObject.setValue("sqlSource.boundSql.sql", trimSql.replace(setSql, setSql + fillSql.toString()));
//        map.put("obj", var1);
        args[0] = newMappedStatement;
        return null;
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
                keyProperties.append(keyProperties).append(".");
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


    private String trim(String var1) {
        var1 = var1.replaceAll("\n", "");
        var1 = var1.replaceAll("  ", " ");
        var1 = var1.replaceAll("    ", "");
        return var1;
    }

    private String getSubUtil(String soap, String rgex) {
        List<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            int i = 1;
            list.add(m.group(i));
            i++;
        }
        return list.get(0);
    }

    class BoundSqlSource implements SqlSource {
        private BoundSql boundSql;

        public BoundSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object object) {
            return boundSql;
        }
    }


}
