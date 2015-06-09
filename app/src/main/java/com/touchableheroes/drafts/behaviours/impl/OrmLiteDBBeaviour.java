package com.touchableheroes.drafts.behaviours.impl;

import android.content.res.Configuration;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.touchableheroes.drafts.behaviours.AbstractAppBehaviour;
import com.touchableheroes.drafts.db.DB;

/**
 * Created by asiebert on 30.12.14.
 */
public class OrmLiteDBBeaviour extends AbstractAppBehaviour {

    private DB db;

    private final Class<?>[] entities;
    private final String dbName;

    public OrmLiteDBBeaviour(
            final String dbName,
            final Class<?>... entities) {
        this.dbName = dbName;
        this.entities = entities;
    }


    @Override
    public void onCreate() {
            DB.configure( this.dbName, 3);

            for ( final Class<?> entity : entities ) {
                DB.entity( entity );
            }

            this.db = OpenHelperManager.getHelper(context(), DB.class);
    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onTerminate() {
        OpenHelperManager.releaseHelper();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

    }
}
