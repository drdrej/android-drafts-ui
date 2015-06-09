package com.touchableheroes.drafts.ui.flow;

import android.app.Activity;

import com.touchableheroes.drafts.ui.events.EventHandler;

/**
 * Created by asiebert on 10.09.14.
 */
public class EventPipe {

    public EventPipe then(Class<? extends EventHandler> handler) {
        return null;
    }

    public EventPipe updateView(Class<? extends EventHandler> handler){ return null;};

    public EventPipe forwardTo(Class<? extends Activity> handler){ return null;};

    public EventPipe forwardTo(String intent){ return null;};

    public EventPipe forwardBy(Class<? extends EventHandler> handler){ return null;};


}
