package com.touchableheroes.drafts.sensors;

import android.hardware.Sensor;


public enum Sensors {

    Gravity(Sensor.TYPE_GRAVITY),
    Magnetic(Sensor.TYPE_MAGNETIC_FIELD);

    public final int type;

    Sensors(final int type) {
        this.type = type;
    }

}
