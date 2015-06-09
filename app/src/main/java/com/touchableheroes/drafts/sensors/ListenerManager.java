package com.touchableheroes.drafts.sensors;

import android.hardware.SensorEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asiebert on 06.12.14.
 */
public class ListenerManager {

    final List<ISensorListener> listeners = new ArrayList<ISensorListener>();


    public void handle(final SensorEvent event) {

    }

}
