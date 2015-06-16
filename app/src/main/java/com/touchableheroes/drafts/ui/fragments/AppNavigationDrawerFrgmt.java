package com.touchableheroes.drafts.ui.fragments;

import com.touchableheroes.drafts.ui.fragments.NavigationDrawerFrgmt;
import com.touchableheroes.drafts.ui.R;


// TODO: refactor als enum
/**
 * Created by asiebert on 20.06.14.
 */
@Deprecated
public class AppNavigationDrawerFrgmt extends NavigationDrawerFrgmt {

    @Override
    public int getListViewId() {
        return 0;
    }

    @Override
    public int getDrawerOpenDescriptionResId() {
        return 0;
    }

    @Override
    public int getDrawerClosedDescriptionResId() {
        return 0;
    }

    @Override
    public int getRootLayoutId() {
        return 0;
    }

    /*
    @Override
    public int getListViewId() {
        return R.id.list_navi;
    }

    @Override
    public int getDrawerOpenDescriptionResId() {
        return R.string.drawer_open;
    }

    @Override
    public int getDrawerClosedDescriptionResId() {
        return R.string.drawer_close;
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_navi_drawer;
    }
    */
}
