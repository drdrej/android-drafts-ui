package com.touchableheroes.drafts.ui.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by asiebert on 02.01.15.
 */
public class Entry<T extends Enum> {

    private final T item;

    public Entry(final T item) {
        this.item = item;
    }

    public <R extends Annotation> R get(final Class<R> key) {
        if( key == null) {
            System.out.println("key is null");
            return null;
        }

        if( !key.isAnnotation() ) {
            System.out.println("key is not annotation");
            return null;
        }

        return getEnumAsField(item.name()).getAnnotation(key);
    }

    private Field getEnumAsField(String field)  {
        try {
            return item.getDeclaringClass().getField( field );
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    public long id(int idx) {
        throw new UnsupportedOperationException();
    }


    public static <T extends Enum> Entry<T> create(final T item) {
        final Entry entry = new Entry<T>(item);

        return entry;
    }
}
