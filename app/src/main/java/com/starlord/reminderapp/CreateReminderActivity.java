package com.starlord.reminderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateReminderActivity extends AppCompatActivity {
    SQLiteDatabase db;
    DbHelper mDbHelper;
    Calendar mCurrentDate;
    EditText mTitleText;
    EditText mDescriptionText;
    Spinner mSpinner;
    TextView timeValue;
    TextView dateValue;
    Button date, time;
    CheckBox checkBoxAlarm,checkBoxNotify;
    int day,month,year;
    String currentTime;
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reminder);
        mDbHelper = new DbHelper(this);
        db = mDbHelper.getWritableDatabase();

        mTitleText = findViewById(R.id.txtTitle);
        mDescriptionText = findViewById(R.id.description);
        mSpinner = findViewById(R.id.spinnerNoteType);
        time = findViewById(R.id.btnTime);
        timeValue = findViewById(R.id.txtTimeValue);
        dateValue = findViewById(R.id.txtDateValue);
        date = findViewById(R.id.btnDate);
        checkBoxAlarm = findViewById(R.id.checkBox);
        checkBoxNotify = findViewById(R.id.checkBox2);

        mCurrentDate = Calendar.getInstance();
        day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrentDate.get(Calendar.MONTH);
        year = mCurrentDate.get(Calendar.YEAR);
        currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        hour = Integer.parseInt(new SimpleDateFormat("HH", Locale.getDefault()).format(new Date()));
        minute = Integer.parseInt(new SimpleDateFormat("mm", Locale.getDefault()).format(new Date()));

        checkBoxAlarm.setEnabled(false);
        checkBoxNotify.setEnabled(false);
        date.setVisibility(View.INVISIBLE);
        dateValue.setVisibility(View.INVISIBLE);
        time.setVisibility(View.INVISIBLE);
        timeValue.setVisibility(View.INVISIBLE);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.reminder_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(id == 0){
                    checkBoxAlarm.setEnabled(true);
                }
                else {
                    checkBoxAlarm.setEnabled(false);
                    checkBoxAlarm.setChecked(false);
                }
                if(id == 1){
                    checkBoxNotify.setEnabled(true);
                }
                else {
                    checkBoxNotify.setEnabled(false);
                    checkBoxNotify.setChecked(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        checkBoxAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked==true){
                    date.setVisibility(View.VISIBLE);
                    dateValue.setVisibility(View.VISIBLE);
                    time.setVisibility(View.VISIBLE);
                    timeValue.setVisibility(View.VISIBLE);
                    dateValue.setText(day+"/"+month+"/"+year);
                    timeValue.setText(currentTime);
                    date.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DatePickerDialog datePickerDialog = new DatePickerDialog(CreateReminderActivity.this, new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker datePicker, int mYear, int monthOfYear, int dayOfMonth) {
                                    dateValue.setText(dayOfMonth+"/"+monthOfYear+"/"+mYear);
                                    day = dayOfMonth;
                                    month = monthOfYear;
                                    year = mYear;
                                }
                            },year,month,day);
                            datePickerDialog.show();
                        }
                    });
                    time.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TimePickerDialog timePickerDialog = new TimePickerDialog(CreateReminderActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker timePicker, int mHour, int mMinute) {
                                    timeValue.setText(mHour+":"+mMinute);
                                    hour = mHour;
                                    minute = mMinute;
                                }
                            },hour,minute,false);
                            timePickerDialog.show();
                        }
                    });
                }
            }
        });
        checkBoxNotify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked==true){
                    date.setVisibility(View.VISIBLE);
                    dateValue.setVisibility(View.VISIBLE);
                    time.setVisibility(View.VISIBLE);
                    timeValue.setVisibility(View.VISIBLE);
                    dateValue.setText(day+"/"+month+"/"+year);
                    timeValue.setText(currentTime);
                    date.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DatePickerDialog datePickerDialog = new DatePickerDialog(CreateReminderActivity.this, new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker datePicker, int mYear, int monthOfYear, int dayOfMonth) {
                                    dateValue.setText(dayOfMonth+"/"+monthOfYear+"/"+mYear);
                                    day = dayOfMonth;
                                    month = monthOfYear;
                                    year = mYear;
                                }
                            },year,month,day);
                            datePickerDialog.show();
                        }
                    });
                    time.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TimePickerDialog timePickerDialog = new TimePickerDialog(CreateReminderActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker timePicker, int mHour, int mMinute) {
                                    timeValue.setText(mHour+":"+mMinute);
                                    hour = mHour;
                                    minute = mMinute;
                                }
                            },hour,minute,false);
                            timePickerDialog.show();
                        }
                    });
                }
            }
        });
    }

    void showToast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_reminder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                String title = mTitleText.getText().toString();
                String detail = mDescriptionText.getText().toString();
                String type =  mSpinner.getSelectedItem().toString();
                ContentValues cv = new ContentValues();
                cv.put(mDbHelper.TITLE, title);
                cv.put(mDbHelper.DETAIL, detail);
                cv.put(mDbHelper.TYPE, type);
                cv.put(mDbHelper.TIME, getString(R.string.Not_Set));
                if (checkBoxAlarm.isChecked()){
                    Calendar calender = Calendar.getInstance();
                    calender.clear();
                    calender.set(Calendar.MONTH, month);
                    calender.set(Calendar.DAY_OF_MONTH, day);
                    calender.set(Calendar.YEAR, year);
                    calender.set(Calendar.HOUR, hour);
                    calender.set(Calendar.MINUTE, minute);
                    calender.set(Calendar.SECOND, 00);

                    SimpleDateFormat formatter = new SimpleDateFormat(getString(R.string.hour_minutes));
                    String timeString = formatter.format(new Date(calender.getTimeInMillis()));
                    SimpleDateFormat dateFormatter = new SimpleDateFormat(getString(R.string.dateFormat));
                    String dateString = dateFormatter.format(new Date(calender.getTimeInMillis()));

                    AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(this, AlarmReceiver.class);

                    String alertTitle = mTitleText.getText().toString();
                    String alertContent=mDescriptionText.getText().toString();
                    intent.putExtra(getString(R.string.alert_content), alertContent);
                    intent.putExtra("alertTitle", alertTitle);

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    alarmMgr.set(AlarmManager.RTC_WAKEUP, calender.getTimeInMillis(), pendingIntent);
                    cv.put(mDbHelper.TIME, timeString);
                    cv.put(mDbHelper.DATE, dateString);
                }
                else if (checkBoxNotify.isChecked()){
                    Calendar calender = Calendar.getInstance();
                    calender.clear();
                    calender.set(Calendar.MONTH, month);
                    calender.set(Calendar.DAY_OF_MONTH, day);
                    calender.set(Calendar.YEAR, year);
                    calender.set(Calendar.HOUR, hour);
                    calender.set(Calendar.MINUTE, minute);
                    calender.set(Calendar.SECOND, 00);

                    SimpleDateFormat formatter = new SimpleDateFormat(getString(R.string.hour_minutes));
                    String timeString = formatter.format(new Date(calender.getTimeInMillis()));
                    SimpleDateFormat dateFormatter = new SimpleDateFormat(getString(R.string.dateFormat));
                    String dateString = dateFormatter.format(new Date(calender.getTimeInMillis()));

                    AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(this, NotificationManager.class);

                    String alertTitle = mTitleText.getText().toString();
                    String alertContent=mDescriptionText.getText().toString();
                    intent.putExtra("alertTitle", alertTitle);
                    intent.putExtra(getString(R.string.alert_content), alertContent);

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    alarmMgr.set(AlarmManager.RTC_WAKEUP, calender.getTimeInMillis(), pendingIntent);
                    cv.put(mDbHelper.TIME, timeString);
                    cv.put(mDbHelper.DATE, dateString);
                }
                db.insert(mDbHelper.TABLE_NAME, null, cv);

                Intent openMainScreen = new Intent(this, HomeActivity.class);
                openMainScreen.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(openMainScreen);
                return true;

            case R.id.action_back:
                Intent openHomeActivity = new Intent(this, HomeActivity.class);
                startActivity(openHomeActivity);
                return true;

            case R.id.action_about:
                Intent openAboutActivity = new Intent(this, AboutActivity.class);
                startActivity(openAboutActivity);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
