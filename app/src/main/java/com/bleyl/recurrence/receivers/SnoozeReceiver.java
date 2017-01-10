package com.bleyl.recurrence.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.bleyl.recurrence.database.DatabaseHelper;
import com.bleyl.recurrence.models.Reminder;
import com.bleyl.recurrence.utils.NotificationUtil;

public class SnoozeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        DatabaseHelper database = DatabaseHelper.getInstance(context);
        int reminderId = intent.getIntExtra("REMINDER_ID", 0);
        if (reminderId != 0 && database.isReminderPresent(reminderId)) {
            Reminder reminder = database.getReminder(reminderId);
            NotificationUtil.createNotification(context, reminder, false);
        }
        database.close();
    }
}