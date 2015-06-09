package com.touchableheroes.drafts.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.touchableheroes.drafts.patterns.Singleton;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;


/**
 * Created by asiebert on 21.06.14.
 */
public class DB
        extends OrmLiteSqliteOpenHelper {

    private static DBConfig config = new DBConfig();

    public DB(final Context context) {
        super(context, config.getDBName(), null, config.getDBVersion());
        Singleton.instance().register(this);
    }


    public static void configure( final String dbName, final int dbVersion ) {
        config.setDBVersion(dbVersion);
        config.setDBName(dbName);
    }

    public static void entity(final Class<?>... tables) {
        if( tables == null)
            throw new IllegalArgumentException( "passed param:tables is NULL.");

        config.setTables(tables);
    }


    @Override
    public void onCreate(final SQLiteDatabase sqLiteDatabase, final ConnectionSource connectionSource) {
        System.out.println("Load db");

        try {
            for (final Class<?> dao : config.types()) {
                TableUtils.createTableIfNotExists(connectionSource, dao);
            }
        } catch (final SQLException e) {
            throw new RuntimeException("problem in onCreate() database", e);
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }



    @Override
    public void onUpgrade(final SQLiteDatabase sqLiteDatabase,
                          final ConnectionSource connectionSource, final int oldVersion, final int newVersion) {

        if( newVersion < 1000 ) {
            simpleUpgrade(sqLiteDatabase);
        } else {
            throw new UnsupportedOperationException( "Upgrade not supported, please use older version." );
        }

    }

    private void simpleUpgrade(final SQLiteDatabase sqLiteDatabase) {
    try {
        for (final Class<?> dao : this.config.types()) {
            TableUtils.dropTable(connectionSource, dao, true);
        }

        onCreate(sqLiteDatabase);
    } catch (final SQLException e) {
        throw new RuntimeException("broken upgrade", e);
    }
    }

    public <T extends BasicLongIDDao> T dao(final Class<T> table) {
        // TODO: Logging
        final Singleton singleton = Singleton.instance();
        final T rval = (T) singleton.byType(table, null);
        if( rval != null )
            return rval;

        final BasicLongIDDao<?> dao = initDao(table);
        return (T) singleton.register(dao);
    }

    private <T> BasicLongIDDao<T> initDao(final Class<T> type) {
        try {
            final Constructor<T> constructor = type.getConstructor(ConnectionSource.class);
            return (BasicLongIDDao<T>) constructor.newInstance(getConnectionSource());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}