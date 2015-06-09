package com.touchableheroes.drafts.ui.loader;

/**
 * Created by asiebert on 23.07.14.
 */
public class LoaderConfigFactory<T extends LoaderConfig> {

    /**
     * static loaders
     */
    private final Class<T> loaders;

    public LoaderConfigFactory(final Class<T> loaders) {
        this.loaders = loaders;
    }

    public LoaderConfig load( final int id ) {
        final T[] states = this.loaders.getEnumConstants();
        return states[id];
    }
}
