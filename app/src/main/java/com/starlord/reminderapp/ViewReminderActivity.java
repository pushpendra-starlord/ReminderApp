package com.starlord.reminderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewReminderActivity extends AppCompatActivity {
    SQLiteDatabase db;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reminder);
        final long id = getIntent().getExtras().getLong(getString(R.string.row_id));

        getSupportActionBar().setTitle("Detailed View");
        dbHelper = new DbHelper(this);
        db = dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + dbHelper.TABLE_NAME + " where " + dbHelper.C_ID + "=" + id, null);
        TextView title = findViewById(R.id.title);
        TextView detail = findViewById(R.id.detail);
        TextView noteType = findViewById(R.id.note_type_ans);
        TextView time = findViewById(R.id.alertvalue);
        TextView date = findViewById(R.id.datevalue);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                title.setText(cursor.getString(cursor.getColumnIndex(dbHelper.TITLE)));
                detail.setText(cursor.getString(cursor.getColumnIndex(dbHelper.DETAIL)));
                noteType.setText(cursor.getString(cursor.getColumnIndex(dbHelper.TYPE)));
                time.setText(cursor.getString(cursor.getColumnIndex(dbHelper.TIME)));
                date.setText(cursor.getString(cursor.getColumnIndex(dbHelper.DATE)));

            }
            cursor.close();
        }
    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this, HomeActivity.class);
        startActivity(setIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_reminder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final long id = getIntent().getExtras().getLong(getString(R.string.rowID));

        switch (item.getItemId()) {
            case R.id.action_back:
                Intent openHomeActivity = new Intent(this, HomeActivity.class);
                startActivity(openHomeActivity);
                return true;
            case R.id.action_edit:

                Intent openEditReminder = new Intent(ViewReminderActivity.this, EditReminderActivity.class);
                openEditReminder.putExtra(getString(R.string.intent_row_id), id);
                startActivity(openEditReminder);
                return true;

            case R.id.action_discard:
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewReminderActivity.this);
                builder
                        .setTitle(getString(R.string.delete_title))
                        .setMessage(getString(R.string.delete_message))
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Long id = getIntent().getExtras().getLong(getString(R.string.rodID));
                                db.delete(DbHelper.TABLE_NAME, DbHelper.C_ID + "=" + id, null);
                                db.close();
                                Intent openHomeActivity = new Intent(ViewReminderActivity.this, HomeActivity.class);
                                startActivity(openHomeActivity);

                            }
                        })
                        .setNegativeButton(getString(R.string.no), null)
                        .show();
                return true;
            case R.id.action_about:
                Intent openAboutActivity = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(openAboutActivity);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
