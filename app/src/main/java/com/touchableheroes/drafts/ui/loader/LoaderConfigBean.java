package com.touchableheroes.drafts.ui.loader;

import com.touchableheroes.drafts.patterns.Factory;
import com.touchableheroes.drafts.patterns.Singleton;

/**
 * Dynamic LoaderConfig.
 *
 * Created by asiebert on 23.07.14.
 */
public class LoaderConfigBean implements LoaderConfig {

    private final com.touchableheroes.drafts.ui.annotations.Loader config;

    public LoaderConfigBean( final com.touchableheroes.drafts.ui.annotations.Loader config) {
        this.config = config;
    }

    @Override
    public int getID() {
        return System.identityHashCode( this );
    }

    @Override
    public LoaderFactory factory() {
        final Class<? extends LoaderFactory> loaderFactory = config.factory();
        final Factory factory = Singleton.instance().byType(Factory.class);

        return factory.create(loaderFactory);
    }


    @Override
    public int[] views() {
        return config.views();
    }

    @Override
    public UIUpdate updater() {
        final Class<? extends UIUpdate> updateBy = config.updateBy();
        final Factory factory = Singleton.instance().byType(Factory.class);

        final UIUpdate rval = factory.create(updateBy);

        rval.setIds( config.views() );

        return rval;
    }



}
