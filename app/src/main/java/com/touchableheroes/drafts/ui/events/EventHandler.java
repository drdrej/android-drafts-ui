package com.touchableheroes.drafts.ui.events;

/**
 * Created by asiebert on 13.09.14.
 */
public interface EventHandler<T> {

    public void onEvent(T event);

}
