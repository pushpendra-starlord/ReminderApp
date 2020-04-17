package com.starlord.reminderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class AlarmDialogPopUp extends AppCompatActivity {
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String title = intent.getExtras().getString("title");
        String msg = getString(R.string.alarmText) +" "+intent.getExtras().getString("content");
        mp = MediaPlayer.create(this,R.raw.alarm);
        mp.start();

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(title);
        alert.setMessage(msg);
        alert.setCancelable(false);
        alert.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mp.stop();
                mp.release();
                AlarmDialogPopUp.this.finish();
            }
        });
        AlertDialog alarm = alert.create();
        alarm.show();
    }
}
