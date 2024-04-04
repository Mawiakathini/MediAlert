package com.example.medialert.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

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

//        updateReminderInDb(rid);
    }

    private void updateReminderInDb(String rid) {
//        Map<String, Object> updates = new HashMap<>();
//
//        updates.put()
//        Constants.remindersReference.child(rid)
//                .updateChildren()
    }
}
