package com.touchableheroes.drafts.behaviours.impl;

import android.app.Activity;
import android.provider.SyncStateContract;

import com.touchableheroes.drafts.behaviours.AbstractActivityBehaviour;
import com.touchableheroes.drafts.behaviours.IActivityBehaviour;
import com.touchableheroes.drafts.log.Logger;

/**
 * Created by asiebert on 15.12.14.
 */
public class LogLifecycleBehaviour extends AbstractActivityBehaviour implements IActivityBehaviour {

    private static Logger LOG = Logger.create(LogLifecycleBehaviour.class);

    private final Logger logger;

    public LogLifecycleBehaviour(Activity owner) {
        super(owner);

        this.logger = Logger.create(owner.getClass());
    }

    @Override
    public void onCreate() {
        logger.debug( "call activity.onCreate()" );
    }

    @Override
    public void onResume() {
        logger.debug( "call activity.onResume()" );
    }

    @Override
    public void onPause() {
        logger.debug( "call activity.onPause()" );
    }

}
