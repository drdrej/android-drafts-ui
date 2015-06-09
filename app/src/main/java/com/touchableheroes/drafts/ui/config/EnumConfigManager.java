package com.touchableheroes.drafts.ui.config;

import android.util.Log;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by asiebert on 24.07.14.
 */
public abstract class EnumConfigManager<E extends Enum, T> {

    private final Class<E> type;
    private final T[] states;

    public EnumConfigManager(final Class<E> type) {
        if( !type.isEnum() ) {
            throw new IllegalArgumentException("Wrong param:type. Supports only Enums of Compatible Type passed in Generic Var T." );
        }

        this.type = type;
        this.states = initStates();
    }


    private T[] initStates() {
        final E[] enums = type.getEnumConstants();
        final Class arrClazz = extractArrayClazz(enums);

        final T[] states = (T[]) Array.newInstance(arrClazz, enums.length);

        int idx = 0;
        for (final E en : enums) {
            Log.d("INIT_STATES", "-- parse state : " + en);
            try {
                final T value = createConfig(en);
                states[idx] = value;
                idx++;
            }catch (final Throwable x) {
                Log.d( "INIT_STATES", "-- couldn't create state");
                continue;
            }
        }

        return states;
    }

    private Class extractArrayClazz(E[] enums) {
        final ParameterizedType generics = (ParameterizedType) this.getClass().getGenericSuperclass();

        final Type[] args = generics.getActualTypeArguments();

        final Type configItemType = args[1];

        if( configItemType instanceof Class) {
            return (Class) configItemType;

        }

        throw new IllegalStateException( "Couldn't extract type of config-items." );
    }


    public Class<?> getType() {
        return type;
    }


    public T getItem(final int pos) {
        if( pos < 0 || pos >= states.length) {
            Log.d("ENUM", "Couldn't find config-item for position:" + pos);
            return null;
        }

        return states[pos];
    }


    protected abstract T createConfig(final E en) ;

    public int count() {
        return states.length;
    }
}
