package com.touchableheroes.drafts.ui.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by asiebert on 24.07.14.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Fragment {

    public Class<? extends android.support.v4.app.Fragment> component();

    public int layoutId() default -1;


}
