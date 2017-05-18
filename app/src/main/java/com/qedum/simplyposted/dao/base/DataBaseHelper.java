package com.qedum.simplyposted.dao.base;

import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.qedum.simplyposted.SpApp;

import java.sql.SQLException;
import java.util.List;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String TAG = DataBaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "sales.db";
    private static final int DATABASE_VERSION = 5;
    private static DataBaseHelper helper;

    public DataBaseHelper() {
        super(SpApp.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHelper getDataBaseHelper() {
        if (helper == null) {
            helper = new DataBaseHelper();
        }
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        createTables();
    }

    private void createTables() {
        try {
            List<Class<? extends IDbEntity>> tablesClasses = DataBaseTables
                    .getAllClasses();
            for (Class<? extends IDbEntity> item : tablesClasses) {
                TableUtils.createTable(connectionSource, item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        dropTables();
        onCreate(db, connectionSource);
    }

    private void dropTables() {
        try {
            List<Class<? extends IDbEntity>> tablesClasses = DataBaseTables
                    .getAllClasses();
            for (Class<? extends IDbEntity> item : tablesClasses) {
                TableUtils.dropTable(connectionSource, item, true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearTables() {
        List<Class<? extends IDbEntity>> tablesClasses = DataBaseTables
                .getAllClasses();
        for (Class<? extends IDbEntity> item : tablesClasses) {
            clearTable(item);
        }
    }

    public void clearTable(Class<? extends IDbEntity> clazz) {
        try {
            TableUtils.clearTable(connectionSource, clazz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();
    }
}
