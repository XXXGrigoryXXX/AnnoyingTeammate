package com.example.annoyingteammate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NotificationsAdapter extends ArrayAdapter<Notifications> {

    public NotificationsAdapter(Context context, ArrayList<Notifications> notificationsList) {

        super(context, 0, notificationsList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return initView(position, convertView, parent);
    }

    private  View initView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(

                    R.layout.spinner_item_notifications, parent, false
            );
        }

        TextView textName = convertView.findViewById(R.id.textViewNotification);

        Notifications currentItem = getItem(position);

        if (currentItem != null) {

            textName.setText(currentItem.getNotifications());
        }

        return convertView;
    }
}
