package com.qedum.simplyposted.dao.base;


import com.qedum.simplyposted.model.Post;

import java.util.ArrayList;
import java.util.List;


public enum DataBaseTables {
    VOUCHER(Post.class);

    private Class<? extends IDbEntity> tableClass;

    public Class<? extends IDbEntity> getTableClass() {
        return tableClass;
    }

    DataBaseTables(Class<? extends IDbEntity> tableClass) {
        this.tableClass = tableClass;
    }

    public static List<Class<? extends IDbEntity>> getAllClasses() {
        List<Class<? extends IDbEntity>> list = new ArrayList<>();
        for (DataBaseTables item : DataBaseTables.values()) {
            list.add(item.getTableClass());
        }
        return list;
    }
}
