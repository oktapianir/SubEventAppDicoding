//package com.okta.subeventappdicoding
//
//import android.app.AlarmManager
//import android.app.PendingIntent
//import android.content.Context
//import android.content.Intent
//import com.okta.subeventappdicoding.model.Event
//import java.util.Calendar
//
//class ReminderManager(private val context: Context) {
//    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//
//    fun scheduleDaily(event: Event) {
//        val intent = Intent(context, ReminderReceiver::class.java).apply {
//            putExtra(EXTRA_EVENT_NAME, event.name)
//            putExtra(EXTRA_EVENT_DATE, event.beginTime)
//        }
//
//        val pendingIntent = PendingIntent.getBroadcast(
//            context,
//            REMINDER_REQUEST_CODE,
//            intent,
//            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
//        )
//
//        val calendar = Calendar.getInstance().apply {
//            timeInMillis = System.currentTimeMillis()
//            set(Calendar.HOUR_OF_DAY, 8)
//            set(Calendar.MINUTE, 0)
//            set(Calendar.SECOND, 0)
//
//            if (timeInMillis <= System.currentTimeMillis()) {
//                add(Calendar.DAY_OF_YEAR, 1)
//            }
//        }
//
//        alarmManager.setRepeating(
//            AlarmManager.RTC_WAKEUP,
//            calendar.timeInMillis,
//            AlarmManager.INTERVAL_DAY,
//            pendingIntent
//        )
//    }
//
//    fun cancelDaily() {
//        val intent = Intent(context, ReminderReceiver::class.java)
//        val pendingIntent = PendingIntent.getBroadcast(
//            context,
//            REMINDER_REQUEST_CODE,
//            intent,
//            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
//        )
//
//        alarmManager.cancel(pendingIntent)
//    }
//
//    companion object {
//        private const val REMINDER_REQUEST_CODE = 100
//        const val EXTRA_EVENT_NAME = "extra_event_name"
//        const val EXTRA_EVENT_DATE = "extra_event_date"
//    }
//}
