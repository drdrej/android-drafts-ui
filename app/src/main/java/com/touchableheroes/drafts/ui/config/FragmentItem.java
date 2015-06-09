package com.touchableheroes.drafts.ui.config;

import android.support.v4.app.Fragment;

import com.touchableheroes.drafts.ui.loader.LoaderConfig;

import java.io.Serializable;

/**
 * Created by asiebert on 14.06.14.
 */
public interface FragmentItem extends Serializable {

    public String name();

    public Class<? extends Fragment> impl();

    public Fragment newInstance(final Object... values);

    public int id();

    public LoaderConfig[] loaders();
}
