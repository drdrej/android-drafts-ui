package com.touchableheroes.drafts.db;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * Created by asiebert on 21.06.14.
 */
public class BasicLongIDDao<T> extends BaseDaoImpl<T, Long> {

    protected BasicLongIDDao(final ConnectionSource connectionSource, final Class<T> config) throws SQLException {
        super(connectionSource, config);
    }


    public void write(final T sysInfoEntity) {
        try {
            createOrUpdate(sysInfoEntity);
            System.out.println( "WROTE to DB: " + sysInfoEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}