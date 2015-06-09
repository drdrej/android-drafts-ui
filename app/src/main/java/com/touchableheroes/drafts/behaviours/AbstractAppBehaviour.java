package com.touchableheroes.drafts.behaviours;

import android.content.Context;

import com.touchableheroes.drafts.app.IContextSupported;

/**
 * Created by asiebert on 30.12.14.
 */
public abstract class AbstractAppBehaviour implements IAppBehaviour, IContextSupported {

    private Context ctx;

    @Override
    public void bind(final Context context) {
        if( context == null )
            return;

        this.ctx = context;
    }

    @Override
    public Context context() {
        return this.ctx;
    }
}
