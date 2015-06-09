package com.touchableheroes.drafts.db;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asiebert on 21.06.14.
 */
public class DBConfig {

    // private final List<Class<? extends BasicLongIDDao>> types = new ArrayList<Class<? extends BasicLongIDDao>>(1);
    private final List<Class<?>> types = new ArrayList<Class<?>>(1);

    private int dbVersion;
    private String dbName;

    /*
    public void setTables(final Class<? extends BasicLongIDDao>... daos) {
        if( daos == null)
            throw new IllegalArgumentException( "passed param:daos is NULL.");

        types.clear();

        for (final Class<? extends BasicLongIDDao> dao : daos) {
            if( dao == null )
                throw new IllegalArgumentException( "passed param:daos[i] is NULL" );

            types.add( dao );
        }
    }
    */

    public void setTables(final Class<?>... tables) {
        if( tables == null)
            throw new IllegalArgumentException( "passed param:daos is NULL.");

        types.clear();

        for (final Class<?> table : tables) {
            if( table == null )
                throw new IllegalArgumentException( "passed param:daos[i] is NULL" );

            types.add( table );
        }
    }


    public void setDBVersion(final int dbVersion) {
        this.dbVersion = dbVersion;
    }

    public void setDBName(final String dbName) {
        this.dbName = dbName;
    }

    /*
    public List<Class<? extends BasicLongIDDao>> types() {
        return types;
    }*/

    public List<Class<?>> types() {
        return types;
    }

    public String getDBName() {
        return dbName;
    }

    public int getDBVersion() {
        return dbVersion;
    }
}
