package com.aorura.brushteeth.devices;

import android.net.Uri;
import android.support.annotation.Nullable;

import com.aorura.brushteeth.model.Alarm;
import com.aorura.brushteeth.model.NotificationSpec;
import com.aorura.brushteeth.model.ServiceCommand;

import java.util.ArrayList;
import java.util.UUID;


/**
 * Specifies all events that GadgetBridge intends to send to the gadget device.
 * Implementations can decide to ignore events that they do not support.
 * Implementations need to send/encode event to the connected device.
 */
public interface EventHandler {
    void onNotification(NotificationSpec notificationSpec);

    void onSetTime();

    void onSetAlarms(ArrayList<? extends Alarm> alarms);

    void onSetCallState(@Nullable String number, @Nullable String name, ServiceCommand command);

    void onSetMusicInfo(String artist, String album, String track);

    void onEnableRealtimeSteps(boolean enable);

    void onInstallApp(Uri uri);

    void onAppInfoReq();

    void onAppStart(UUID uuid, boolean start);

    void onAppDelete(UUID uuid);

    void onFetchActivityData();

    void onReboot();

    void onFindDevice(boolean start);

    void onScreenshotReq();
}
