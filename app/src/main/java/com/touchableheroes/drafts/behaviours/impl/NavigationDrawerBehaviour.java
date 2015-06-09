package com.touchableheroes.drafts.behaviours.impl;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.touchableheroes.drafts.behaviours.AbstractActivityBehaviour;
import com.touchableheroes.drafts.navidrawer.AppNavigationDrawerListAdapter;
import com.touchableheroes.drafts.ui.config.Declaration;
import com.touchableheroes.drafts.ui.fragments.NavigationDrawerFrgmt;

/**
 * Created by asiebert on 20.12.14.
 */
public class NavigationDrawerBehaviour extends AbstractActivityBehaviour {

    private final FragmentManager frgmtMgr;

    private final int drawerLayoutId;
    private final int anchorId;

    private final Class naviConfigEnum;

    private final Declaration decl;

    // private final int layoutId;
/* final int layoutId */

    private NavigationDrawerBehaviour(final Activity owner,
                                     final FragmentManager frgmtMgr,
                                     final int rootDrawerLayoutId,
                                     final int frgmtAnchorId,
                                     final Class naviConfigEnum) {
        super(owner);
        // getSupportedFragmentManager ??
        this.frgmtMgr = frgmtMgr;

        // this.layoutId = layoutId;

        this.anchorId = frgmtAnchorId;
        this.drawerLayoutId = rootDrawerLayoutId;
        this.naviConfigEnum = naviConfigEnum;
        decl = new Declaration(naviConfigEnum);
    }

    private NavigationDrawerBehaviour(final Activity owner,
                                     final FragmentManager frgmtMgr,
                                     final Class naviConfigEnum) {
        this(owner, frgmtMgr,
                com.touchableheroes.drafts.ui.R.id.drawer_layout,
                com.touchableheroes.drafts.ui.R.id.frgmt_navigation,
                naviConfigEnum);
    }

    // das gehört zum NaviBehaviour, muss aber in der Activity gesetzt werden!
    //  // owner.setContentView(com.touchableheroes.drafts.ui.R.layout.app);
    // evtl. ueber factory lösen!
    public NavigationDrawerBehaviour(final FragmentActivity owner,
                                     final Class naviConfigEnum) {
        this(owner, owner.getSupportFragmentManager(), naviConfigEnum);
    }

    /*
    public NavigationDrawerBehaviour(final Fragment owner) {
        this(
                owner.getFragmentManager());
    }
    */

    @Override
    public void onCreate() {
        super.onCreate();

        final NavigationDrawerFrgmt navi
                = (NavigationDrawerFrgmt) frgmtMgr.findFragmentById(anchorId);

        final AppNavigationDrawerListAdapter adapter = new AppNavigationDrawerListAdapter(getActivity(), decl.getType());

        navi.setNaviListAdapter(adapter);
        navi.setup( drawerLayoutId );

        // TODO: set flow -> was habe ich damit gemeint?
        /*
        Schaue im Incubator-1 nach!

        final OnItemSelectedListener flow = null;
        navi.setOnItemSelectedListener(flow);
        */

    }


    public Declaration config() {
        return decl;
    }
}
