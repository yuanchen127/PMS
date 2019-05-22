package org.ike.pms.mybatisplus.mybaitsplusdemo.config.mapper;

public enum WTSqlMethod {
    WT_INSERT_ONE("saveWithTable", "动态表 插入一条数据（选择字段插入）", "<script>\nINSERT INTO %s %s VALUES %s\n</script>"),
    WT_UPDATE_BY_ID("updateWithTable", "根据ID 选择修改数据", "<script>\nUPDATE %s %s WHERE %s=#{%s} %s\n</script>");

    private final String method;
    private final String desc;
    private final String sql;

    private WTSqlMethod(String method, String desc, String sql) {
        this.method = method;
        this.desc = desc;
        this.sql = sql;
    }

    public String getMethod() {
        return this.method;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getSql() {
        return this.sql;
    }
}
