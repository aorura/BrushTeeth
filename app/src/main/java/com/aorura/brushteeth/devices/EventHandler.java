package com.aorura.brushteeth.devices;

import com.aorura.brushteeth.model.NotificationSpec;


/**
 * Specifies all events that GadgetBridge intends to send to the gadget device.
 * Implementations can decide to ignore events that they do not support.
 * Implementations need to send/encode event to the connected device.
 */
public interface EventHandler {
    void onNotification(NotificationSpec notificationSpec);
}
