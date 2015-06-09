package com.touchableheroes.drafts.ui.loader;

/**
 * Created by asiebert on 27.06.14.
 */
public interface LoaderConfig {

    public int getID() ;

    public LoaderFactory factory();

    public int[] views();

    public UIUpdate updater();


}
