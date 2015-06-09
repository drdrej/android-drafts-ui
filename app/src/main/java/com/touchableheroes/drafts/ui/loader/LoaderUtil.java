package com.touchableheroes.drafts.ui.loader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.util.SparseArray;


import com.touchableheroes.drafts.ui.UI;
import com.touchableheroes.drafts.ui.fragments.AbstractHeadlessLoaderFragment;
import com.touchableheroes.drafts.utils.SparseIteratable;

import java.lang.ref.WeakReference;

import de.greenrobot.event.EventBus;


/**
 * Created by asiebert on 21.07.14.
 */
public class LoaderUtil<T extends LoaderConfig> implements LoaderManager.LoaderCallbacks {

    private SparseArray<LoaderConfig> configs = new SparseArray<LoaderConfig>(10);

    private SparseArray<LoaderConfig> stop;

    /**
     * lazy bound
     */
    private final WeakReference<Fragment> ctx;


    /**
     * dynamic loaders
     *
     * @param ctx
     */
    public LoaderUtil(final AbstractHeadlessLoaderFragment ctx) {
        this.ctx = new WeakReference<Fragment>(ctx);
    }

    public void stopLoading() {
        stop = configs;
        configs = new SparseArray<LoaderConfig>(10);
    }

    public void registerLoader(final LoaderConfig config) {
        this.configs.put(config.getID(), config);
    }

    public void startLoading() {
           handleStop();
           handleStart();
    }

    public Fragment getFragment() {
        return this.ctx.get();
    }

    private void handleStart() {
        final LoaderManager manager = getFragment().getLoaderManager();

        if( hasConfigs() )
            return;

        final Iterable<LoaderConfig> itr = SparseIteratable.iterable(configs);
        for (final LoaderConfig config : itr) {
            manager.initLoader(config.getID(), null, this);
        }
    }

    private void handleStop() {
        final LoaderManager manager = getFragment().getLoaderManager();

        if( stop == null )
            return;

        final Iterable<LoaderConfig> itr = SparseIteratable.iterable(stop);
        for (final LoaderConfig config : itr) {
            manager.destroyLoader(config.getID());
        }

        this.stop.clear();
    }

    private boolean hasConfigs() {
        return (configs.size() < 1);
    }

    @Override
    public Loader onCreateLoader(final int id, final Bundle args) {
        Log.d("LOADER", "-- load load load");

        final LoaderConfig config = extractLoaderConfig(id);
        final LoaderFactory loaderFactory = config.factory();

        final FragmentActivity context = getFragment().getActivity();

        Loader loader = loaderFactory.create(context, args, id);

        return loader;
    }

    private LoaderConfig extractLoaderConfig(final int id) {
        return configs.get(id);
    }

    @Override
    public void onLoadFinished(final Loader loader,
                               final Object data) {
        System.out.println("-- finish loader.");

        final LoaderConfig config = extractLoaderConfig(loader.getId());
        EventBus.getDefault().postSticky( new LoadFinishedEvent(config) );
    }

    @Override
    public void onLoaderReset(final Loader loader) {
        System.out.println( "-- reset loader." );
    }


    // TODO: remove
    @Deprecated
    public com.touchableheroes.drafts.ui.UI getUI() {
        return new UI(getFragment());
    }

}
