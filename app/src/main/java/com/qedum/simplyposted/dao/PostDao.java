package com.qedum.simplyposted.dao;

import com.j256.ormlite.support.ConnectionSource;
import com.qedum.simplyposted.dao.base.BaseDao;
import com.qedum.simplyposted.dao.base.DataBaseHelper;
import com.qedum.simplyposted.model.Post;

import java.sql.SQLException;

/**
 * Created by bogdan.aksonenko on 5/17/17.
 */

public class PostDao extends BaseDao<Post> {
    private static PostDao instance;

    private PostDao(ConnectionSource connectionSource, Class<Post> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public static PostDao getInstance() {
        if (instance == null) {
            try {
                instance = new PostDao(DataBaseHelper.getDataBaseHelper().getConnectionSource(), Post.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

}
