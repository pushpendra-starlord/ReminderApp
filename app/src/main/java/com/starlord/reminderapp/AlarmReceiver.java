package com.starlord.reminderapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String Title = intent.getStringExtra("alertTitle");
        String content = intent.getStringExtra(context.getString(R.string.alert_content));
        Intent alarmIntent = new Intent("android.intent.action.MAIN");
        alarmIntent.setClass(context, AlarmDialogPopUp.class);
        alarmIntent.putExtra("title", Title);
        alarmIntent.putExtra("content", content);
        alarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(alarmIntent);
    }
}
