package com.qedum.simplyposted.dao.base;

import android.util.Log;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseDao<T extends IDbEntity> extends BaseDaoImpl<T, Long> {
    private static final String TAG = BaseDao.class.getSimpleName();

    public BaseDao(ConnectionSource connectionSource, Class<T> dataClass)
            throws SQLException {
        super(connectionSource, dataClass);
    }

    @Override
    public List<T> queryForAll() {
        try {
            return super.queryForAll();
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public T queryForId(Long id) {
        try {
            return super.queryForId(id);
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    public CreateOrUpdateStatus save(T arg0) {
        try {
            return super.createOrUpdate(arg0);
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    @Override
    public int delete(T arg0) {
        try {
            return super.delete(arg0);
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
        return 0;
    }

    @Override
    public long countOf() {
        try {
            return super.countOf();
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
        return 0;
    }

    @Override
    public int create(T data) {
        try {
            return super.create(data);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public CreateOrUpdateStatus createOrUpdate(T data) {
        try {
            return super.createOrUpdate(data);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createOrUpdate(List<T> data) {
        for (T t : data) {
            try {
                super.createOrUpdate(t);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<T> queryForEq(String fieldName, Object value) {
        try {
            return super.queryForEq(fieldName, value);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}