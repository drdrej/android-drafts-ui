package com.touchableheroes.drafts.ui.config;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.touchableheroes.drafts.patterns.Factory;
import com.touchableheroes.drafts.patterns.Singleton;
import com.touchableheroes.drafts.ui.annotations.Loader;
import com.touchableheroes.drafts.ui.annotations.Loaders;
import com.touchableheroes.drafts.ui.loader.LoaderConfig;
import com.touchableheroes.drafts.ui.loader.LoaderConfigBean;

import java.lang.reflect.Field;


/**
 * Created by asiebert on 25.07.14.
 */
public class FragmentItemBean<E extends Enum> implements FragmentItem {

    private final E element;

    private final Class<? extends Fragment> fragmentType;
    private final LoaderConfig[] loaders;

    public FragmentItemBean( final E element ) {
        this.element = element;

        final Field field = getFieldAnnotations(element);
        this.fragmentType = initFragmentType(field);

        this.loaders = initLoaderConfig(field);
    }

    private LoaderConfig[] initLoaderConfig(final Field field) {
        final Loaders config = field.getAnnotation(Loaders.class);

        if( config == null ) {
            return new LoaderConfig[0];
        }

        final Loader[] loaders = config.value();

        final LoaderConfigBean[] rval = new LoaderConfigBean[loaders.length];

        for(int i = 0; i < loaders.length; i++ ) {
            final Loader loader = loaders[i];
            final LoaderConfigBean bean = new LoaderConfigBean(loader);
            rval[i] = bean;
        }

        return rval;
    }

    private Class<? extends Fragment> initFragmentType(final Field field) {
        if( field == null ) {
            final String msg = "No Commands to load, because no field passed.";
            Log.d("FragmentItemManager", "No Commands to load, because no field passed.");
            throw new IllegalArgumentException();
        }

        final com.touchableheroes.drafts.ui.annotations.Fragment config = field.getAnnotation(com.touchableheroes.drafts.ui.annotations.Fragment.class);

        if( config == null ) {
            Log.d( "FragmentItemManager", "No Commands found for enum/field: " + field.getName() );
            return null;
        }

        return config.component();
    }


    @Override
    public String name() {
        return element.name();
    }

    @Override
    public Class<? extends Fragment> impl() {
        return this.fragmentType;
    }

    @Override
    public Fragment newInstance(final Object... values) {
        final Factory factory = Singleton.byMagic(Factory.class);
        return factory.create( impl() );
    }

    @Override
    public int id() {
        return element.ordinal();
    }


    public static Field getFieldAnnotations(final Enum item) {
        final String name = item.name();

        try {
            return item.getClass().getField(name);
        } catch (final NoSuchFieldException e) {
            e.printStackTrace();

            return null;
        }
    }

    @Override
    public LoaderConfig[] loaders() {
        return this.loaders;
    }

}
