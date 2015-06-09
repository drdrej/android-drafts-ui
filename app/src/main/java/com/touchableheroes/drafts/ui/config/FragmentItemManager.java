package com.touchableheroes.drafts.ui.config;

/**
 * Created by asiebert on 22.07.14.
 */
public class FragmentItemManager<E extends Enum> extends EnumConfigManager<E, FragmentItem>{

    public FragmentItemManager(final Class<E> type) {
        super(type);

    }

    @Override
    protected FragmentItem createConfig(final E en) {
        return new FragmentItemBean(en);
    }

}



/*


 */