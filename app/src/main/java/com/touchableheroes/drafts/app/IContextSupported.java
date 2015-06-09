package com.touchableheroes.drafts.app;

import android.content.Context;

/**
 * Created by asiebert on 30.12.14.
 */
public interface IContextSupported {

    public void bind(final Context context);

    public Context context();

}
