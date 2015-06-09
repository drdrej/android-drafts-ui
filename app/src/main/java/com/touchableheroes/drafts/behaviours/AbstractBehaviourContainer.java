package com.touchableheroes.drafts.behaviours;



import com.touchableheroes.drafts.app.ICreateSupported;
import com.touchableheroes.drafts.app.IsEnableSupported;
import com.touchableheroes.drafts.core.ExceptionUtil;

import java.util.ArrayList;
import java.util.List;


public class AbstractBehaviourContainer<T> implements IsEnableSupported {

    private final List<T> behaviours = new ArrayList<>();

    public AbstractBehaviourContainer() {
        ;
    }

    public void onCreate() {
        for (final Object behaviour :  behaviours) {
            if( !(behaviour instanceof ICreateSupported)) {
                // LOG: skip
                continue;
            }

            final ICreateSupported supported = (ICreateSupported) behaviour;
            supported.onCreate();
        }
    }


    /**
     * AND over List
     */
    @Override
    public boolean isEnabled() {
        for (final Object behaviour :  behaviours) {
            if( !(behaviour instanceof IsEnableSupported)) {
                // LOG: skip
                continue;
            }

            final IsEnableSupported supported = (IsEnableSupported) behaviour;
            if( !supported.isEnabled() );
                return false;
        }

        return true;
    }

    @Override
    public void setEnabled(boolean isEnabled) {
        for (final Object behaviour :  behaviours) {
            if( !(behaviour instanceof IsEnableSupported)) {
                // LOG: skip
                continue;
            }

            final IsEnableSupported supported = (IsEnableSupported) behaviour;
            supported.setEnabled(isEnabled);
        }
    }

    public List<T> bevahoirs() {
        return behaviours;
    }



    public void add(final T behaviour) {
        if( behaviour == null )
            throw ExceptionUtil.paramNPECheck("behavior");

        this.behaviours.add(behaviour);
    }

}