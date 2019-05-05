package org.ike.pms.mybatisplus.mybaitsplusdemo.config.MultiDatasource;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import javax.sql.DataSource;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;

public class MultiDataSourceTransactionFactory extends SpringManagedTransactionFactory {
    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new MultiDataSourceTransaction(dataSource);
    }
}
