package com.foss.utility

import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import kotlin.random.Random

/**
 * MQLKNotificationService is a utility class for showing notifications.
 * It provides methods to show basic notifications and expandable notifications.
 *
 * @param context The context in which the notifications will be shown.
 */
class MQLKNotificationService(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)

    /**
     * Shows a basic notification with the provided parameters.
     *
     * @param title The title of the notification.
     * @param contentText The text content of the notification.
     * @param icon The resource ID of the icon to be displayed in the notification.
     * @param notificationPriority The priority level of the notification.
     * @param channelId The ID of the notification channel.
     */
    fun showBasicNotification(
        title: String?,
        contentText: String?,
        icon: Int,
        notificationPriority: Int = NotificationManager.IMPORTANCE_HIGH,
        channelId: String,
    ) {
        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setSmallIcon(icon)
            .setContentText(contentText)
            .setPriority(notificationPriority)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }

    /**
     * Shows an expandable notification with the provided parameters.
     *
     * @param imageUrl The URL of the image to be displayed in the expanded view of the notification.
     * @param title The title of the notification.
     * @param contentText The text content of the notification.
     * @param icon The resource ID of the icon to be displayed in the notification.
     * @param notificationPriority The priority level of the notification.
     * @param channelId The ID of the notification channel.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun showExpandableNotification(
        imageUrl: String, title: String,
        contentText: String,
        icon: Int,
        notificationPriority: Int = NotificationManager.IMPORTANCE_HIGH,
        channelId: String,
    ) {
        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(contentText)
            .setSmallIcon(icon)
            .setPriority(notificationPriority)
            .setAutoCancel(true)
            .setStyle(
                NotificationCompat
                    .BigPictureStyle()
                    .bigPicture(
                        getBitmapFromURL(imageUrl)
                    )
            )
            .build()
        notificationManager.notify(Random.nextInt(), notification)
    }

    /**
     * Retrieves a Bitmap image from the specified URL.
     *
     * @param src The URL of the image.
     * @return The Bitmap image retrieved from the URL, or null if an error occurs.
     */
    private fun getBitmapFromURL(src: String?): Bitmap? {
        return try {
            val url = URL(src)
            val connection =
                url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            // Log exception
            null
        }
    }
}
