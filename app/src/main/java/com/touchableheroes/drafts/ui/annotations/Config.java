package com.touchableheroes.drafts.ui.annotations;

import com.touchableheroes.drafts.ui.config.EnumConfigManager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by asiebert on 24.07.14.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Config {

   public Class<? extends EnumConfigManager> value();

}
