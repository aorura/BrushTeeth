package com.aorura.brushteeth.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aorura.brushteeth.devices.SampleProvider;
import com.aorura.brushteeth.model.ActivitySample;
import com.aorura.brushteeth.util.GBApplication;

import java.util.List;


public interface DBHandler {
    SQLiteOpenHelper getHelper();

    /**
     * Releases the DB handler. No access may be performed after calling this method.
     * Same as calling {@link GBApplication#releaseDB()}
     */
    void release();

    List<ActivitySample> getAllActivitySamples(int tsFrom, int tsTo, SampleProvider provider);

    List<ActivitySample> getActivitySamples(int tsFrom, int tsTo, SampleProvider provider);

    List<ActivitySample> getSleepSamples(int tsFrom, int tsTo, SampleProvider provider);

    void addGBActivitySample(int timestamp, byte provider, short intensity, short steps, byte kind);

    void addGBActivitySamples(ActivitySample[] activitySamples);

    SQLiteDatabase getWritableDatabase();
}
