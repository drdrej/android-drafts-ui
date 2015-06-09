package com.touchableheroes.drafts.behaviours;


import android.app.Activity;

import com.touchableheroes.drafts.app.IsEnableSupported;


/**
 * Created by asiebert on 15.12.14.
 */
public class AbstractActivityBehaviour
       implements IsEnableSupported, IActivityBehaviour {

    private final Activity owner;

    protected AbstractActivityBehaviour( final Activity owner) {
        this.owner = owner;
    }


    private boolean isEnabled;

    @Override
    public void onCreate() {
        ;
    }

    @Override
    public void onResume() {
        ;
    }

    @Override
    public void onPause() {
        ;
    }

    @Override
    public boolean isEnabled() {
        // import static com.touchableheroes.mrdoorkeeper.Constants.Logger.ACTIVITY;
        // Logger.ACTIVITY.debug( "call behaviour.isEnabled():" + isEnabled );

        return false;
    }

    @Override
    public void setEnabled(boolean isEnabled) {
        // Logger.ACTIVITY.debug( "call setEnabled(" + isEnabled + ")" );
        this.isEnabled = isEnabled;
    }

    public Activity getActivity() {
        return owner;
    }
}
