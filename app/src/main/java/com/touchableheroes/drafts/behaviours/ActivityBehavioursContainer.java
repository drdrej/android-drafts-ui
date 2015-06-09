package com.touchableheroes.drafts.behaviours;

import android.app.Activity;

/**
 * Created by asiebert on 15.12.14.
 */
public class ActivityBehavioursContainer
            extends AbstractBehaviourContainer<IActivityBehaviour>
            implements IActivityBehaviour {

    private final Activity owner;

    public ActivityBehavioursContainer(final Activity owner) {
        this.owner = owner;
    }

    public void onCreate() {
        for (final IActivityBehaviour behaviour :  bevahoirs()) {
            behaviour.onCreate();
        }
    }

    @Override
    public void onResume() {
        for (final IActivityBehaviour behaviour :  bevahoirs()) {
            behaviour.onResume();
        }
    }

    @Override
    public void onPause() {
        for (final IActivityBehaviour behaviour :  bevahoirs()) {
            behaviour.onPause();
        }
    }

}
