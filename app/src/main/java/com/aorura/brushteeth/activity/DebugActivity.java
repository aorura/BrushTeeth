package com.aorura.brushteeth.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aorura.brushteeth.R;
import com.aorura.brushteeth.impl.GBDeviceService;
import com.aorura.brushteeth.model.DeviceService;
import com.aorura.brushteeth.model.NotificationSpec;
import com.aorura.brushteeth.model.NotificationType;

public class DebugActivity extends Activity {

    private Button sendSMSButton;
    private Button sendEmailButton;
    private Button incomingCallButton;
    private Button outgoingCallButton;
    private Button startCallButton;
    private Button endCallButton;
    private Button testNotificationButton;
    private Button setMusicInfoButton;
    private Button setTimeButton;
    private Button rebootButton;
    private Button exportDBButton;
    private Button importDBButton;
    private EditText editContent;
    private DeviceService mDeviceService = null;

    public static final String ACTION_QUIT
            = "com.aorura.brushteeth.controlcenter.action.quit";

    public static final String ACTION_REFRESH_DEVICELIST
            = "com.aorura.brushteeth.controlcenter.action.set_version";

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION_QUIT)) {
                finish();
            }
        }
    };

    protected DeviceService createDeviceService() {
        return new GBDeviceService(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (mDeviceService == null) {
            mDeviceService = createDeviceService();
        }

        registerReceiver(mReceiver, new IntentFilter(ACTION_QUIT));


        sendSMSButton = (Button) findViewById(R.id.sendSMSButton);
        sendSMSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationSpec notificationSpec = new NotificationSpec();
                notificationSpec.sender = getResources().getText(R.string.app_name).toString();
                notificationSpec.body = editContent.getText().toString();
                notificationSpec.type = NotificationType.SMS;
                notificationSpec.id = -1;
                mDeviceService.onNotification(notificationSpec);
            }
        });
        sendEmailButton = (Button) findViewById(R.id.sendEmailButton);
        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationSpec notificationSpec = new NotificationSpec();
                notificationSpec.sender = getResources().getText(R.string.app_name).toString();
                notificationSpec.subject = editContent.getText().toString();
                notificationSpec.body = editContent.getText().toString();
                notificationSpec.type = NotificationType.EMAIL;
                notificationSpec.id = -1;
                mDeviceService.onNotification(notificationSpec);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

}
