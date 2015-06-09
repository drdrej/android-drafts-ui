package com.touchableheroes.drafts.behaviours;

import android.content.res.Configuration;

/**
 * Created by asiebert on 30.12.14.
 */
public class AppBehavioursContainer
             extends AbstractBehaviourContainer<IAppBehaviour>
             implements IAppBehaviour {

    @Override
    public void onCreate() {
        for( final IAppBehaviour behaviour : bevahoirs()) {
            behaviour.onCreate();
        }
    }

    @Override
    public void onLowMemory() {
        for( final IAppBehaviour behaviour : bevahoirs()) {
            behaviour.onLowMemory();
        }
    }

    @Override
    public void onTerminate() {
        for( final IAppBehaviour behaviour : bevahoirs()) {
            behaviour.onTerminate();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        for( final IAppBehaviour behaviour : bevahoirs()) {
            behaviour.onConfigurationChanged(newConfig);
        }
    }
}





