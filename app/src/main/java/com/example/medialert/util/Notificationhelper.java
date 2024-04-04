package com.example.medialert.util;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.medialert.HomeScreen;
import com.example.medialert.R;
import com.example.medialert.model.Reminder;

import java.util.Calendar;

public class Notificationhelper {
    private static final String CHANNEL_ID = "Reminders";
    private static final String CHANNEL_NAME = "Reminders";
    private static final int NOTIFICATIONS_ID = 123;
    private static final int ALARM_REQUEST_CODE = 100;

    public static void scheduleNotification(Context context, Reminder reminder) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.HOUR_OF_DAY, reminder.getTimestamp().getHour());
        calendar.set(Calendar.MINUTE, reminder.getTimestamp().getMinute());
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(context, AlarmReciever.class);
        intent.putExtra("rid", reminder.getId());
        intent.putExtra("title", reminder.getMedicineName());
        intent.putExtra("message", String.format("please remember to take the dose of %s at %d %s", reminder.getMedicineName(), reminder.getDose().getDose(), reminder.getDose().getForm()));


        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ALARM_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);


        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }

    }

    public static void showNotification(Context context, String title, String content) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(context, HomeScreen.class);
	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP); // Ensure only one instance of HomeScreen
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
	builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        notificationManager.notify(NOTIFICATIONS_ID, builder.build());
    }
}
