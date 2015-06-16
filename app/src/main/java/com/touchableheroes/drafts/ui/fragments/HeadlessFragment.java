package com.touchableheroes.merlock2.fragments;

import android.support.v4.app.FragmentManager;

import com.touchableheroes.drafts.ui.fragments.AbstractHeadlessLoaderFragment;

// import com.touchableheroes.merlock2.config.Loaders; Loaders.class

/**
 * Use Headless Fragment to do work in background.
 *
 * Created by asiebert on 27.06.14.
 */
public class HeadlessFragment extends AbstractHeadlessLoaderFragment {

    public HeadlessFragment() {
        ;
    }

    public void setup(final FragmentManager owner) {
        super.setup("WORK", owner);
    }

}
