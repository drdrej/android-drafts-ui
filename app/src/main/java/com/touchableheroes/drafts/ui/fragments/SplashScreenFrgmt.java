package com.touchableheroes.drafts.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.touchableheroes.drafts.ui.flow.FragmentXML;
// import com.touchableheroes.merlock2.R;


/**
 * Created by asiebert on 14.06.14.
 */
// @FragmentXML(layout=R.layout.fragment_solash_screen)
// TODO: umsteigen auf @LayoutId()
public class SplashScreenFrgmt extends AbstractFragment {

    private Handler handleSplashLoaded = new Handler() {

        @Override
        public void handleMessage(final Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
