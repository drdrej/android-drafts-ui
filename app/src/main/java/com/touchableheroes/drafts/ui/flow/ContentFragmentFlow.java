package com.touchableheroes.drafts.ui.flow;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import com.touchableheroes.drafts.ui.config.FragmentItem;
import com.touchableheroes.drafts.ui.config.FragmentItemManager;
import com.touchableheroes.drafts.ui.fragments.AbstractHeadlessLoaderFragment;
import com.touchableheroes.drafts.ui.fragments.navi.OnItemSelectedListener;
import com.touchableheroes.drafts.ui.loader.LoadFinishedEvent;
import com.touchableheroes.drafts.ui.loader.LoaderConfig;

import de.greenrobot.event.EventBus;


/**
 * Created by asiebert on 14.06.14.
 */
public class ContentFragmentFlow implements OnItemSelectedListener {

    private final ActionBarActivity activity;

    private final FragmentManager fragmentManager;

    private final FragmentItemManager states;

    private final int defaultSlotId;

    private final AbstractHeadlessLoaderFragment headless;


    private FragmentItem current;

    public ContentFragmentFlow(final ActionBarActivity activity,
                               final int defaultContentSlotId,
                               final FragmentItemManager states,
                               final AbstractHeadlessLoaderFragment headless) {
        this.activity = activity;
        this.defaultSlotId = defaultContentSlotId;
        this.states = states;

        this.fragmentManager = this.activity.getSupportFragmentManager();
        this.headless = headless;

        EventBus.getDefault().registerSticky(this);
    }


    // eigentlich kann diese Methode abstrahiert werden.
    public void onEvent(final LoadFinishedEvent finished) {
        System.out.println( "-- load is finished: " + finished);

        /*
           Sobald ein Task fertig ist,
           muss aus der Config bestimmt

         */
        // finished.getConfig().getID()
        next();
    }

    public void open(final int slotId, final FragmentItem item) {
        open(slotId, item, null);
    }

    public void open(final int slotId, final FragmentItem item, final Bundle bundle) {
        if( item == null )
            throw new IllegalArgumentException( "couldn't open frame in slot: " + slotId + ". passed frame is NULL." );

        if( slotId < 1 )
            throw new IllegalArgumentException( "couldn't open frame in slot: " + slotId + ". passed slotID < 0" );

        System.out.println( "-- open( in slot: " + slotId+ ", fragment: " + item.name() + ") ");

        final FragmentItem old = this.current;
        this.current = item;

        final Fragment fragment = factoryLoad(item);
        final LoaderConfig[] loaders = item.loaders();

        this.headless.stopAll();

        if (fragment == null) {
            throw new IllegalStateException("Couldn't open fragment in slot. Slot-ID: " + slotId );
        }

        if( bundle != null )
            fragment.setArguments(bundle);

        this.headless.use(loaders);


        /**
        new Thread() {

            @Override
            public void run() {
                super.run();

                // if( fragmentManager != null )
                fragmentManager.beginTransaction()
                        .replace(slotId, fragment, item.name())
                        .commit();

                // this.in
            }

        }.start();
         */

        // sollte ein event in den haupt thread senden.

        new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] objects) {
                fragmentManager.beginTransaction()
                        .replace(slotId, fragment, item.name())
                        .commitAllowingStateLoss();

                return null;
            }
        }.execute();

        // EventBus.getDefault().post();


        updateUI();
    }

    private void updateUI() {
        // TODO: um das update muss sich die ui kümmern.
    }

    private Fragment factoryLoad(final FragmentItem item) {
        return item.newInstance(item);
    }


    // TODO: validiere diese Methode. Wird sie noch benötigt?
    @Override
    public void onItem(final int pos) {
        final FragmentItem item = (FragmentItem) states.getItem(pos);
        open(defaultSlotId, item);
    }

    public void start() {
        final FragmentItem item = (FragmentItem) states.getItem(0);
        open(defaultSlotId, item);
    }

    public void next() {
        final int id;

        if( this.current == null ) {
            id = 0;
        } else {
            id = this.current.id() + 1;
        }

        final FragmentItem item = (FragmentItem) states.getItem(id);
        open(defaultSlotId, item);
    }


    public void onStop() {
        EventBus.getDefault().unregister(this);
    }

}
