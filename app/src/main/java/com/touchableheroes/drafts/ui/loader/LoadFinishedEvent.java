package com.touchableheroes.drafts.ui.loader;

/**
 * Created by asiebert on 03.08.14.
 */
public class LoadFinishedEvent {

    private final LoaderConfig config;

    public LoadFinishedEvent(final LoaderConfig config) {
        this.config = config;
    }

    public LoaderConfig getConfig() {
        return config;
    }


}
