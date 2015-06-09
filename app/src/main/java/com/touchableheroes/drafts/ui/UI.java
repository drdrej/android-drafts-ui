package com.touchableheroes.drafts.ui;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.touchableheroes.drafts.core.ClassesUtil;
import com.touchableheroes.drafts.ui.annotations.BindView;
import com.touchableheroes.drafts.ui.loader.UIUpdate;

import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asiebert on 24.07.14.
 */
public class UI {

    private final WeakReference<Fragment> frgmt;

    public UI(final Fragment frgmt) {
        this.frgmt = new WeakReference<Fragment>(frgmt);
    }

    public int update(final UIUpdate uiUpdate, final Object value) {
        final Fragment fragment = frgmt.get();

        if( fragment == null ) {
            Log.d( "UI", "-- nothing up to date." );
            return 0;
        }

        final int[] ids = uiUpdate.ids();

        if( ids == null || ids.length < 1 ) {
            uiUpdate.afterUpdate();
            return 0;
        }

        final Field[] fields = findFields(uiUpdate);
        final View[] views = findViews(fragment, ids);

        map(uiUpdate, fields, views);

        uiUpdate.update(value);
        uiUpdate.afterUpdate();

        return 0;
    }

    private Constructor getConstructor(final UIUpdate uiUpdate, final View[] views) {
        try {
            final Class[] paramTypes = ClassesUtil.getClassesOf(views);

            return uiUpdate.getClass().getConstructor(paramTypes);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Need Constructor with compatible fields: [= "  + views + "]", e);
        }
    }

    private Constructor findConstructor(final UIUpdate uiUpdate, final View[] views) {

        try {
            Constructor rval = getConstructor(uiUpdate, views);

            return rval;
        } catch (final Throwable x) {
            throw new IllegalStateException("Couldn't find fields for updater:  " + uiUpdate, x);
        }
    }

    private void map(final UIUpdate obj, final Field[] fields, final View[] views) {
        for (int i = 0; i < fields.length; i++ ) {
            final Field field = fields[i];
            final View view = views[i];

            try {
                field.set( obj, view );
            } catch (IllegalAccessException e) {
                e.printStackTrace();

                throw new IllegalStateException( e );
            }
        }
    }

    private View[] findViews(Fragment fragment, int[] ids) {
        int count = 0;

        final View[] views = new View[ids.length];

        for (int id : ids) {
            final View view = fragment.getView().findViewById(id);
            if( view == null ) {
                Log.d("UI", "-- view with passed ID: " + id + " not found.");
                continue;
            }

            views[count] = view;
            count++;
        }

        return views;
    }

    private Field[] findFields(final UIUpdate uiUpdate) {
        try {
            final Field[] fields = uiUpdate.getClass().getDeclaredFields();

            final List<Field> rval = new ArrayList<Field>(fields.length);
            for (final Field field : fields) {
                final BindView bindView = field.getAnnotation(BindView.class);

                if (bindView == null)
                    continue;

                if( !Modifier.isPublic(field.getModifiers()) ) {
                    throw new IllegalStateException("Field [= " + field.getName() + "] is not accessible. Use public keyword." );
                }

                if( !View.class.isAssignableFrom(field.getType()) ) {
                    throw new IllegalStateException("Field [= " + field.getName() + "] has a wrong type. Must be a subclass of " + View.class.getName() + "." );
                }

                rval.add(field);
            }

            return rval.toArray(new Field[rval.size()]);
        } catch (final Throwable x) {
            throw new IllegalStateException("Couldn't find fields for updater:  " + uiUpdate, x);
        }
    }

}
