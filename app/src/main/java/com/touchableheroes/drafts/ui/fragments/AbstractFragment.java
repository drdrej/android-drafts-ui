package com.touchableheroes.drafts.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.touchableheroes.drafts.app.App;
import com.touchableheroes.drafts.ui.flow.FragmentXML;

/**
 * Created by asiebert on 27.05.2014.
 */
public class AbstractFragment extends android.support.v4.app.Fragment {

    private View root;

    public App getApp() {
        return (App) getActivity().getApplication();
    }

    @Override
    public final View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.root = inflater.inflate(getLayoutID(), container, false);

        return root;
    }

    public int getLayoutID() {
        final FragmentXML xml = getClass().getAnnotation(FragmentXML.class);

        if( xml == null ) {
            Log.d("d", "miss xml annotatoin. default == 0" );
            return 0;
        }

        return xml.layout();
    }

    public View getRoot() {
        return root;
    }
}
