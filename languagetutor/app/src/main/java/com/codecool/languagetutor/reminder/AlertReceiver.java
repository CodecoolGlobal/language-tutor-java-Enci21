package com.codecool.languagetutor.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.codecool.languagetutor.reminder.NotificationHelper;

import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannel(context);
        notificationHelper.getManager().notify(1, nb.build());
    }
}
