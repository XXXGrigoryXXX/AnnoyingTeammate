package com.example.annoyingteammate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

import java.util.ArrayList;

public class CreateChangeBluetooth extends AppCompatActivity {

    private ArrayList<Notifications> notificationsList;
    private NotificationsAdapter notificationsAdapter;

    private Spinner spinnerNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_change_bluetooth);

        initList();

        spinnerNotifications = findViewById(R.id.spinnerNotification);
        notificationsAdapter = new NotificationsAdapter(this, notificationsList);
        spinnerNotifications.setAdapter(notificationsAdapter);
    }

    private void initList() {

        notificationsList = new ArrayList<>();
        notificationsList.add(new Notifications("вибровызов"));
        notificationsList.add(new Notifications("push-уведомление"));
        notificationsList.add(new Notifications("мелодия"));
    }
}