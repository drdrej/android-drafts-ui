package com.touchableheroes.drafts.ui.adapter;

import android.app.Activity;
import android.widget.BaseAdapter;

import com.touchableheroes.drafts.flow.Configuration;
import com.touchableheroes.drafts.ui.config.Declaration;


/**
 * used as ListAdapter to fill the NaviDrawer
 *
 * @author asiebert
 */
public abstract class NavigationDrawerListAdapter<T extends Enum> extends BaseAdapter {

    private final Activity activity;

    private final Configuration<T> config;

    /**
     *
     * @param activity activity, die auf den Navigator initialisiert.
     * @param configType Klasse, die die Konfiguration
     */
    public NavigationDrawerListAdapter(final Activity activity,
                                       final Class<T> configType) {
        this.activity = activity;
        this.config = new Configuration<T>(configType);
    }

    @Override
    public final int getCount() {
        return this.config.countEntries();
    }

    @Override
    public Object getItem(int position) {
        return config.get(position);
    }

    /*this.config.id(*/
    @Override
    public long getItemId(final int position) {
        return position;
    }


    public Activity getActivity() {
        return activity;
    }

    public Declaration<T> getConfig() {
        return config.config();
    }

}
