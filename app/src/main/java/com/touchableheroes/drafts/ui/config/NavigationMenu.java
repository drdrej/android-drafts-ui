package com.touchableheroes.drafts.ui.config;




/**
 * Used to model a menu for navi-drawer
 * <p/>
 * Created by asiebert on 14.05.2014.
 */
public interface NavigationMenu {

    /*
    DEVICE_INFO(R.string.MENU_DEVICE_INFO, R.drawable.ic_launcher),
    OFFER(R.string.MENU_OFFER, R.drawable.ic_launcher),
    MARKET(R.string.MENU_MARKET, R.drawable.ic_launcher);
    */

    public int getIconResId();

    public int getLabelId();

    public int id();

    /*
    private final int labelResId;
    private final int iconResId;

    NavigationMenu(final int labelResId, final int iconResId) {
        this.labelResId = labelResId;
        this.iconResId = iconResId;
    }

    public int getIconResId() {
        return this.iconResId;
    }

    public int getLabelId() {
        return this.labelResId;
    }
    */

}
