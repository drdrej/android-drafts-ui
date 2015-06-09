package com.touchableheroes.drafts.ui.config;

import java.lang.annotation.Annotation;

/**
 * Created by asiebert on 30.12.14.
 */
public class Declaration<T extends Enum> {

    private final T[] items;
    private final Class<? extends T> itemsType;

    public Declaration(final Class<? extends T> items) {

        if( !items.isEnum() ) {
            throw new IllegalArgumentException( "PARAM:ITEMS is not an Enum");
        }

        this.itemsType = items;
        this.items = items.getEnumConstants();
    }

    public Entry<T> entry(final int id) {
        return Entry.create(value(id));
    }

    public Entry<T> entry(final T entrySrc) {
        return Entry.create(entrySrc);
    }

    public T value(final int id) {
        return items[id];
    }

    public int size() {
        return items.length;
    }

    public Class<? extends T> getType() {
        return itemsType;
    }

    /**
     * Annotation des Typs selbst
     */
    public <A extends Annotation> A annotation(final Class<A> attr) {
        if( !attr.isAnnotation() ) {
            throw new IllegalArgumentException("Only Class of Type annotation is acceptable. passed param:attr = " + attr.getName() );
        }

        return this.itemsType.getAnnotation(attr);
    }
}
