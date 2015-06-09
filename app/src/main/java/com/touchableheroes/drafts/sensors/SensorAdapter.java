package com.touchableheroes.drafts.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import com.touchableheroes.drafts.behaviours.IActivityBehaviour;
import com.touchableheroes.drafts.ui2.UI;

// import android.support.annotation.Nullable;

/**
 * Created by asiebert on 29.11.14.
 */
public class SensorAdapter implements IActivityBehaviour, SensorEventListener {

    private static final String TAG = "sensor";

    private final SensorManager sensorMgr;

    private final int sensorDelay;

    private final Sensors sensorType;

    private Sensor sensor;

    private ListenerManager listeners = new ListenerManager();

    private UI ui;

    private SensorAdapter(final SensorManager sensorMgr, final Sensors sensor, final int sensorDelay) {
        this.sensorMgr = sensorMgr;
        this.sensorDelay = sensorDelay;
        this.sensorType = sensor;
    }

    @Override
    public void onCreate() {
        this.sensor = sensorMgr.getDefaultSensor(sensorType.type);
    }

    @Override
    public void onResume() {
        sensorMgr.registerListener(this, sensor, sensorDelay);
        Log.i(TAG, "Gravity sensor registred. (TYPE_GRAVITY)");
    }

    @Override
    public void onPause() {
        sensorMgr.unregisterListener(this);
        Log.i(TAG, "Gravity sensor available. (TYPE_GRAVITY)");
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void setEnabled(boolean isEnabled) {

    }


    public static final SensorAdapter create(final SensorManager sensorMgr,
                                             final Sensors sensor, final int sensorDelay) {
        return new SensorAdapter(sensorMgr, sensor, sensorDelay);
    }

    @Override
    public void onSensorChanged(final SensorEvent event) {
        Log.i(TAG, "Start listeners to handle event." );

        listeners.handle(event);

        final Bundle bundle = new Bundle();

        // TODO: NPE
        ui.update(bundle);
    }

    @Override
    public void onAccuracyChanged(final Sensor sensor, final int accuracy) {
        // TODO: another list of listeners
    }
}
