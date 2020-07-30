package com.example.pushnotification.helpers;

import android.content.Context;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.pushnotification.R;

public class NotificationHelper {
    private static final String CHANEL_ID = "Push Notification";

    public static void displayNotification(Context context, String title, String body) {
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, CHANEL_ID)
                .setSmallIcon(R.id.icon_only)
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(1, notificationBuilder.build());

    }
}
