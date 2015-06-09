package com.touchableheroes.drafts.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.touchableheroes.drafts.ui.loader.LoaderConfig;
import com.touchableheroes.drafts.ui.loader.LoaderUtil;

/**
 * Created by asiebert on 21.07.14.
 */
public abstract class AbstractHeadlessLoaderFragment extends Fragment {

    private String tag;
    private LoaderUtil loader;

    private FragmentManager mgr;

    protected AbstractHeadlessLoaderFragment(){}

    /**
     * should be called in onCreate()
     *
     * @param tag
     * @param owner
     */
    protected void setup(final String tag,
                       final FragmentManager owner) {

        this.tag = tag;
        this.mgr = owner;

        bind();

        this.loader = new LoaderUtil(this);
    }

    private void bind() {
        final FragmentTransaction tx = mgr.beginTransaction();
        tx.add(this, tag);
        tx.commit();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);

        initLoader();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ;
    }

    private void initLoader() {
        this.loader.startLoading();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void stopAll() {
        loader.stopLoading();
    }


    public void use(final LoaderConfig[] configs) {
        for( final LoaderConfig config : configs ) {
            loader.registerLoader( config );
        }
    }
}
