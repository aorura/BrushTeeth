package com.aorura.brushteeth.impl;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.aorura.brushteeth.model.DeviceService;
import com.aorura.brushteeth.model.NotificationSpec;
import com.aorura.brushteeth.service.DeviceCommunicationService;


public class GBDeviceService implements DeviceService {
    protected final Context mContext;
    protected final Class<? extends Service> mServiceClass;

    public GBDeviceService(Context context) {
        mContext = context;
        mServiceClass = DeviceCommunicationService.class;
    }

    protected Intent createIntent() {
        Intent startIntent = new Intent(mContext, mServiceClass);
        return startIntent;
    }

    protected void invokeService(Intent intent) {
        mContext.startService(intent);
    }

    protected void stopService(Intent intent) {
        mContext.stopService(intent);
    }

    @Override
    public void start() {
        Intent intent = createIntent().setAction(ACTION_START);
        invokeService(intent);
    }

    @Override
    public void connect() {
        connect(null, false);
    }

    @Override
    public void connect(@Nullable String deviceAddress) {
        connect(deviceAddress, false);
    }

    @Override
    public void connect(@Nullable String deviceAddress, boolean performPair) {
        Intent intent = createIntent().setAction(ACTION_CONNECT)
                .putExtra(EXTRA_DEVICE_ADDRESS, deviceAddress)
                .putExtra(EXTRA_PERFORM_PAIR, performPair);
        invokeService(intent);
    }

    @Override
    public void disconnect() {
        Intent intent = createIntent().setAction(ACTION_DISCONNECT);
        invokeService(intent);
    }

    @Override
    public void quit() {
        Intent intent = createIntent();
        stopService(intent);
    }

    @Override
    public void requestDeviceInfo() {
        Intent intent = createIntent().setAction(ACTION_REQUEST_DEVICEINFO);
        invokeService(intent);
    }

    @Override
    public void onNotification(NotificationSpec notificationSpec) {
        Intent intent = createIntent().setAction(ACTION_NOTIFICATION)
                .putExtra(EXTRA_NOTIFICATION_PHONENUMBER, notificationSpec.phoneNumber)
                .putExtra(EXTRA_NOTIFICATION_SENDER, notificationSpec.sender)
                .putExtra(EXTRA_NOTIFICATION_SUBJECT, notificationSpec.subject)
                .putExtra(EXTRA_NOTIFICATION_TITLE, notificationSpec.title)
                .putExtra(EXTRA_NOTIFICATION_BODY, notificationSpec.body)
                .putExtra(EXTRA_NOTIFICATION_ID, notificationSpec.id)
                .putExtra(EXTRA_NOTIFICATION_TYPE, notificationSpec.type)
                .putExtra(EXTRA_NOTIFICATION_SOURCENAME, notificationSpec.sourceName);
        invokeService(intent);
    }

}
