package com.touchableheroes.drafts.flow;

import com.touchableheroes.drafts.ui.config.Declaration;

/**
 * Created by asiebert on 24.02.15.
 */
public class Configuration<T extends Enum> {

    private final Declaration<T> config;

    private final Class<? extends T> type;

    public Configuration( /* final Activity activity,*/
                          final Class<T> menuItems) {

        this.type = menuItems;
        this.config = new Declaration<T>(menuItems);
    }


    public Declaration<T> getDeclaration() {
        return config;
    }

    public final T get(final int idx) {
        return this.config.value(idx);
    }

    public final Declaration<T> config() {
        return config;
    }

    public Class<?> getType() {
        return type;
    }


    public int countEntries() {
        return this.config.size();
    }


    public int attr(final Enum attr) {
        return 0;
    }
}
