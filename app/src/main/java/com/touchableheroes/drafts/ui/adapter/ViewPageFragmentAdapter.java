package com.touchableheroes.drafts.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.touchableheroes.drafts.ui.config.FragmentItem;
import com.touchableheroes.drafts.ui.config.FragmentItemManager;

/**
 * Created by asiebert on 20.06.14.
 */
public class ViewPageFragmentAdapter extends FragmentStatePagerAdapter {


    private final FragmentItemManager itemMgr;

    public ViewPageFragmentAdapter(final FragmentManager manager, final Class<? extends Enum> config) {
        super(manager);

        this.itemMgr = new FragmentItemManager(config);
    }

    @Override
    public Fragment getItem(final int position) {
        final FragmentItem item = (FragmentItem) itemMgr.getItem(position);
        return item.newInstance();
    }

    @Override
    public int getCount() {
        return itemMgr.count();
    }
}
