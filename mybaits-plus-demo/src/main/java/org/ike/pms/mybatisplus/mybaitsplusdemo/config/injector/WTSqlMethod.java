package org.ike.pms.mybatisplus.mybaitsplusdemo.config.injector;

public enum WTSqlMethod {
    INSERT_ONE("wt_insert", "插入一条数据（选择字段插入）", "<script>\nINSERT INTO %s %s VALUES %s\n</script>"),
    DELETE_BY_ID("wt_deleteById", "根据ID 删除一条数据", "<script>\nDELETE FROM %s WHERE %s=#{%s}\n</script>"),
    DELETE_BY_MAP("wt_deleteByMap", "根据columnMap 条件删除记录", "<script>\nDELETE FROM %s %s\n</script>"),
    DELETE("wt_delete", "根据 entity 条件删除记录", "<script>\nDELETE FROM %s %s %s\n</script>"),
    DELETE_BATCH_BY_IDS("wt_deleteBatchIds", "根据ID集合，批量删除数据", "<script>\nDELETE FROM %s WHERE %s IN (%s)\n</script>"),
    LOGIC_DELETE_BY_ID("wt_deleteById", "根据ID 逻辑删除一条数据", "<script>\nUPDATE %s %s WHERE %s=#{%s} %s\n</script>"),
    LOGIC_DELETE_BY_MAP("wt_deleteByMap", "根据columnMap 条件逻辑删除记录", "<script>\nUPDATE %s %s %s\n</script>"),
    LOGIC_DELETE("wt_delete", "根据 entity 条件逻辑删除记录", "<script>\nUPDATE %s %s %s %s\n</script>"),
    LOGIC_DELETE_BATCH_BY_IDS("wt_deleteBatchIds", "根据ID集合，批量逻辑删除数据", "<script>\nUPDATE %s %s WHERE %s IN (%s) %s\n</script>"),
    UPDATE_BY_ID("wt_updateById", "根据ID 选择修改数据", "<script>\nUPDATE %s %s WHERE %s=#{%s} %s\n</script>"),
    UPDATE_ALL_COLUMN_BY_ID("wt_updateAllColumnById", "根据ID 选择修改数据", "<script>\nUPDATE %s SET %s WHERE %s=#{%s} %s\n</script>"),
    UPDATE("wt_update", "根据 whereEntity 条件，更新记录", "<script>\nUPDATE %s %s %s %s\n</script>"),
    LOGIC_UPDATE_BY_ID("wt_updateById", "根据ID 修改数据", "<script>\nUPDATE %s %s WHERE %s=#{%s} %s\n</script>"),
    SELECT_BY_ID("wt_selectById", "根据ID 查询一条数据", "SELECT %s FROM %s WHERE %s=#{%s}"),
    SELECT_BY_MAP("wt_selectByMap", "根据columnMap 查询一条数据", "<script>\nSELECT %s FROM %s %s\n</script>"),
    SELECT_BATCH_BY_IDS("wt_selectBatchIds", "根据ID集合，批量查询数据", "<script>\nSELECT %s FROM %s WHERE %s IN (%s)\n</script>"),
    SELECT_ONE("wt_selectOne", "查询满足条件一条数据", "<script>\nSELECT %s FROM %s %s %s\n</script>"),
    SELECT_COUNT("wt_selectCount", "查询满足条件总记录数", "<script>\nSELECT COUNT(%s) FROM %s %s %s\n</script>"),
    SELECT_LIST("wt_selectList", "查询满足条件所有数据", "<script>\nSELECT %s FROM %s %s %s\n</script>"),
    SELECT_PAGE("wt_selectPage", "查询满足条件所有数据（并翻页）", "<script>\nSELECT %s FROM %s %s %s\n</script>"),
    SELECT_MAPS("wt_selectMaps", "查询满足条件所有数据", "<script>\nSELECT %s FROM %s %s %s\n</script>"),
    SELECT_MAPS_PAGE("wt_selectMapsPage", "查询满足条件所有数据（并翻页）", "<script>\nSELECT %s FROM %s %s %s\n</script>"),
    SELECT_OBJS("wt_selectObjs", "查询满足条件所有数据", "<script>\nSELECT %s FROM %s %s %s\n</script>"),
    LOGIC_SELECT_BY_ID("wt_selectById", "根据ID 查询一条数据", "SELECT %s FROM %s WHERE %s=#{%s} %s"),
    LOGIC_SELECT_BATCH_BY_IDS("wt_selectBatchIds", "根据ID集合，批量查询数据", "<script>\nSELECT %s FROM %s WHERE %s IN (%s) %s\n</script>");



    private final String method;
    private final String desc;
    private final String sql;

    WTSqlMethod(String method, String desc, String sql) {
        this.method = method;
        this.desc = desc;
        this.sql = sql;
    }

    public String getMethod() {
        return method;
    }

    public String getDesc() {
        return desc;
    }

    public String getSql() {
        return sql;
    }
}
