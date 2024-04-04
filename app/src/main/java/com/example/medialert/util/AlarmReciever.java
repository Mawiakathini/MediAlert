package com.example.medialert.util;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AlarmReciever extends BroadcastReceiver {

    private static final String TAG = "AlarmReciever";



    @Override
    public void onReceive(Context context, Intent intent) {
        String title = intent.getStringExtra("title");
        String message = intent.getStringExtra("message");
        String rid = intent.getStringExtra("rid");
        Log.d(TAG, "onReceive: "+rid);
        Notificationhelper.showNotification(context, title, message);
    }


}
