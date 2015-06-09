package com.touchableheroes.drafts.ui.annotations;

import com.touchableheroes.drafts.ui.loader.LoaderFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by asiebert on 24.07.14.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Loader {

    public Class<? extends LoaderFactory> factory();

    public Class<? extends com.touchableheroes.drafts.ui.loader.UIUpdate> updateBy();

    public int[] views();
}
