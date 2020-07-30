package com.example.pushnotification.services;

import com.example.pushnotification.helpers.NotificationHelper;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    String title;
    String body;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getNotification() != null) {
            title = remoteMessage.getNotification().getTitle();
            body = remoteMessage.getNotification().getBody();

            NotificationHelper.displayNotification(getApplicationContext(),title, body);



        }
    }

}
