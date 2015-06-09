package com.touchableheroes.drafts.ui.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.touchableheroes.drafts.Command;

/**
 * Based on this doc:
 * http://developer.android.com/reference/android/content/AsyncTaskLoader.html
 *
 * Created by asiebert on 23.07.14.
 */
public abstract class AbstractTaskLoader<E> extends AsyncTaskLoader<E> {

    private final Command task;
    protected E current;

    public AbstractTaskLoader(final Context context, final Command<E> cmd) {
        super(context);
        this.task = cmd;
    }

    @Override
    public E loadInBackground() {
        Log.d( "LOAD", "-- load in background" );

        final E result = (E) task.exec();

        if( result == null ) {
            doDeliver(null);
            return null;
        }

        return result;
    }

    @Override
    public void deliverResult(final E value) {
        if (isReset()) {
            cleanupValue(value);
            return;
        }

        final E old = current;
        this.current = value;

        if (isStarted()) {
            doDeliver(value);
        }

        if (hasValues(old)) {
            cleanupValue(old);
        }
    }

    private void doDeliver(final E value) {
        super.deliverResult(value);
    }

    protected abstract void cleanupValue(final E old);

    private boolean hasValues(E value) {
        return false;
    }

    @Override
    protected void onStartLoading() {
        if (hasValues(current)) {
            deliverResult(current);
        }

        if (takeContentChanged() || !hasValues(current)) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    public void onCanceled(final E value) {
        if (hasValues(value)) {
            cleanupValue(value);
        }
    }

    @Override
    protected void onReset() {
        super.onReset();

        onStopLoading();

        if (hasValues(current)) {
            cleanupValue(current);
        }

        doReset();
    }

    protected abstract void doReset();

}