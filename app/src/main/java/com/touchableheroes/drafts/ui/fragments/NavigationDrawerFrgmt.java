package com.touchableheroes.drafts.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.touchableheroes.drafts.ui.adapter.NavigationDrawerListAdapter;
import com.touchableheroes.drafts.ui.fragments.navi.OnItemSelectedListener;
import com.touchableheroes.drafts.ui.fragments.navi.SupportsNavigationDraverBindings;

/**
 * Created by asiebert on 14.05.2014.
 */
public abstract class NavigationDrawerFrgmt  extends android.support.v4.app.Fragment implements SupportsNavigationDraverBindings {

    private ListView listView;

    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;

    private com.touchableheroes.drafts.ui.fragments.navi.OnItemSelectedListener OnItemSelectedListener = new OnItemSelectedListener() {
        @Override
        public void onItem(int pos) {
            ; // NoOP
        }
    };

    private NavigationDrawerListAdapter naviListAdapter;


    public void setOnItemSelectedListener( final OnItemSelectedListener listener ) {
        if( listener == null ) {
            throw new IllegalArgumentException("passed param:listener is NULL." );
        }

        this.OnItemSelectedListener = listener;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View root = inflater.inflate(
                getRootLayoutId(), container, false);

        listView = (ListView) root.findViewById(getListViewId());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("CALL: click in Navi drawer");

                selectItem(position);
            }
        });

        setHasOptionsMenu(true);

        return root;
    }

    public void selectItem(int position) {
        this.OnItemSelectedListener.onItem(position);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setup(final int rootDrawerViewId) {
        listView.setAdapter( getNaviListAdapter() );

        drawerLayout = (DrawerLayout) getActivity().findViewById(rootDrawerViewId);
        appendActionBarToggle(drawerLayout);

        final View naviContainer = getActivity().findViewById(getId());

        drawerToggle.syncState();
    }

    /**
     * based on google code example.
     *
     * @param drawerLayout
     */
    private void appendActionBarToggle(final DrawerLayout drawerLayout) {
        final ActionBarActivity activity = (ActionBarActivity) getActivity();

        final ActionBar actionBar = activity.getSupportActionBar();

        final ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(activity,
                drawerLayout,         /* DrawerLayout object */
                android.R.drawable.ic_menu_slideshow,  /* nav drawer icon to replace 'Up' caret */
                getDrawerOpenDescriptionResId()/* R.string.drawer_open */,  /* "open drawer" description */
                getDrawerClosedDescriptionResId()/* R.string.drawer_close */  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                // actionBar.setTitle(R.string.drawer_title_closed);
                actionBar.setTitle( "App" );
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // actionBar.setTitle(R.string.drawer_title_open);
                actionBar.setTitle("Mavi");
            }
        };

        // Set the drawer toggle as the DrawerListener
        drawerLayout.setDrawerListener(drawerToggle);

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        this.drawerToggle = drawerToggle;

    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public abstract int getListViewId();

    public abstract int getDrawerOpenDescriptionResId();

    public abstract int getDrawerClosedDescriptionResId();

    public abstract int getRootLayoutId();

    public void setNaviListAdapter( NavigationDrawerListAdapter naviListAdapter) {
        this.naviListAdapter = naviListAdapter;
    }

    public NavigationDrawerListAdapter getNaviListAdapter() {
        return naviListAdapter;
    }
}
