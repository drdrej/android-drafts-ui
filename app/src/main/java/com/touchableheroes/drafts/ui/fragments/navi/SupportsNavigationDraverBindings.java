package com.touchableheroes.drafts.ui.fragments.navi;

/**
 * Created by asiebert on 20.06.14.
 */
public interface SupportsNavigationDraverBindings {

    public int getListViewId();

    public int getDrawerOpenDescriptionResId();

    public int getDrawerClosedDescriptionResId();

    public abstract int getRootLayoutId();

}
