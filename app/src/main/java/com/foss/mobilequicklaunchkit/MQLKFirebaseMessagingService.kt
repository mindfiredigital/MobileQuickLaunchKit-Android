package com.foss.mobilequicklaunchkit

import com.foss.mobilequicklaunchkit.R.drawable
import com.foss.utility.MQLKNotificationService
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MQLKFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        MQLKNotificationService(this).showBasicNotification(
            title = message.notification?.title,
            contentText = message.notification?.body,
            channelId = Consts.NOTIFICATION_CHANNEL_ID,
            icon = drawable.ic_launcher_foreground,
        )
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }


}