package com.touchableheroes.drafts.navidrawer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.touchableheroes.drafts.ui.adapter.NavigationDrawerListAdapter;

public class AppNavigationDrawerListAdapter<T extends Enum>
        extends NavigationDrawerListAdapter<T> {

    public AppNavigationDrawerListAdapter(final Activity activity, final Class<T> type) {
        super(activity, type);
    }

    @Override
    public View getView(final int idx, final View view, final ViewGroup container) {
        final View usedView;

        final NaviItem item = getConfig().entry(idx).get(NaviItem.class);

        if(item == null) {
            System.out.println( "warn: navi item is null, skip this item. index = " + idx );
            return view;
        }

        if (view == null) {
            usedView = LayoutInflater.from(getActivity()).inflate(item.layoutId(), null, false);
        } else {
            usedView = view;
        }

        ((TextView) usedView.findViewById(item.titleViewId()))
                .setText(item.title());

        return usedView;
    }

}