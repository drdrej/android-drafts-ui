package com.touchableheroes.drafts.ui.loader;

/**
 * Created by asiebert on 24.07.14.
 */
public class UIUpdate {

    private int[] ids;

    public void setIds( final int[] ids ) {
        this.ids = ids;
    }

    public int[] ids() {
        return this.ids;
    }

/*

    -------------------
    > swing:
    ===================

    web service / soap
    sql
    weblogic
    uml

*/




    public void afterUpdate() {
        System.out.println( "-- ui updated!!!" );
    }

    public void update(Object value) {

    }
}
