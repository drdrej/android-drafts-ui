package com.touchableheroes.drafts.ui.loader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.Loader;

/**
 * Created by asiebert on 21.07.14.
 */
public interface LoaderFactory {

    public Loader create(final Context ctx, final Bundle params, final int id);

}
