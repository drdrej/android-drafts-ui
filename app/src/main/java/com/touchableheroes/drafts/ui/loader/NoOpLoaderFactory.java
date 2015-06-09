package com.touchableheroes.drafts.ui.loader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.util.Log;

/**
 * Created by asiebert on 21.07.14.
 */
public class NoOpLoaderFactory implements LoaderFactory {

    @Override
    public Loader create(final Context ctx, final Bundle params, final int id) {
        Log.d( "NOOP", "-- skip loaderfactory." );

        return null;
    }

}
