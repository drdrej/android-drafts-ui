package com.touchableheroes.drafts.ui.flow;

/**
 * Created by asiebert on 10.09.14.
 *
 * Abstrakte Version des UIUpdates.
 */
public abstract class UIUpdateCommand {

    public abstract void when(final Object event);

    public EventPipe then( ) {
        return null;
    }
}
