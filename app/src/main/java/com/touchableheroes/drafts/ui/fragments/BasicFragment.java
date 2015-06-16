package com.touchableheroes.drafts.ui.fragments;

//import android.app.Fragment;

// import com.touchableheroes.merlock2.events.AppIsInstalledEvent;

import de.greenrobot.event.EventBus;
import android.support.v4.app.Fragment;

/**
 * Created by asiebert on 01.08.14.
 */
public class BasicFragment extends Fragment {

    public BasicFragment() {
        // der code muss in die klasse, wo das fragment initialisiert wird.
        EventBus.getDefault().registerSticky(this);
        ;
    }

    // public void onEvent()

    @Override
    public void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }


}

