package com.flipkart.pendingintentexample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    AlarmManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get access to alarm manager service
        manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    }

    public void register(View view) {
        //create intent for the activity that will be launched by alarm service
        Intent intent = new Intent(this, MainActivity.class);

        //wrap the intent into a pending intent, we cannot initialize the pending intent
        PendingIntent pIntent = PendingIntent.getActivity(this, 1001, intent,PendingIntent.FLAG_UPDATE_CURRENT);

        //tell alarm manager to run the activity every minute, RTC_wake up is for phone in sleep mode.
        manager.setRepeating(AlarmManager.RTC,System.currentTimeMillis(),60000,pIntent);
    }

    public void unregister(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent pIntent = PendingIntent.getActivity(this, 1001, intent,PendingIntent.FLAG_UPDATE_CURRENT);

        manager.cancel(pIntent);
    }
}
