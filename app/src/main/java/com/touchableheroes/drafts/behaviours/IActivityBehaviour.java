package com.touchableheroes.drafts.behaviours;

import com.touchableheroes.drafts.app.ICreateSupported;
import com.touchableheroes.drafts.app.IsEnableSupported;

/**
 * Created by asiebert on 29.11.14.
 */
public interface IActivityBehaviour extends IsEnableSupported, ICreateSupported {

    public void onResume();

    public void onPause();


}
