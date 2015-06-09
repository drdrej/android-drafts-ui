package com.touchableheroes.drafts.app;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by asiebert on 14.05.2014.
 */
public abstract class App extends Application {

    private static AppRef INSTANCE = new AppRef();

    private static class AppRef {
        private App ref;

        public void bind( final App app ) {
            ref = app;
        }

        public App current() {
            return ref;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE.bind(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    /**
     * current globally availiable instance of this app.
     *
     * @return
     */
    public static App current() {
        return INSTANCE.current();
    }

}
