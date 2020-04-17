package com.starlord.reminderapp;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.core.app.NotificationCompat;

public class NotificationManager extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String Title = intent.getStringExtra("alertTitle");
        String content = intent.getStringExtra(context.getString(R.string.alert_content));

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setSmallIcon(R.drawable.ic_action_alarms);
            builder.setContentTitle(Title);
            builder.setContentText(content).setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.getPackageName() + "/raw/notify"));

        // Add as notification
        android.app.NotificationManager manager = (android.app.NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
