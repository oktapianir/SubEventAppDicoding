//package com.okta.subeventappdicoding
//
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Intent
//import android.os.Build
//import androidx.core.app.NotificationCompat
//
//
//class ReminderReceiver : BroadcastReceiver() {
//
//    override fun onReceive(context: Context, intent: Intent) {
//        val eventName = intent.getStringExtra(ReminderManager.EXTRA_EVENT_NAME)
//        val eventDate = intent.getStringExtra(ReminderManager.EXTRA_EVENT_DATE)
//
//        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        val channelId = "event_reminder"
//
//        // Create notification channel for Android O and above
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                channelId,
//                "Event Reminder",
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//            notificationManager.createNotificationChannel(channel)
//        }
//
//        val notification = NotificationCompat.Builder(context, channelId)
//            .setSmallIcon(R.drawable.ic_notification)
//            .setContentTitle("Upcoming Event Reminder")
//            .setContentText("Don't forget: $eventName on $eventDate")
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            .setAutoCancel(true)
//            .build()
//
//        notificationManager.notify(NOTIFICATION_ID, notification)
//    }
//
//    companion object {
//        private const val NOTIFICATION_ID = 100
//    }
//}