package com.example.annoyingteammate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> {

    private final ArrayList<Device> arrayList;
    private final OnDeviceItemClick listener;

    DeviceAdapter(ArrayList<Device> arrayList, OnDeviceItemClick listener) {
        this.listener = listener;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_bluetooth, parent, false);
        return new DeviceViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(DeviceViewHolder holder, int position) {
        Device device = arrayList.get(position);
        holder.bind(device);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class DeviceViewHolder extends RecyclerView.ViewHolder {

        TextView name, description, notification;
        private final TextView dbId;
        private Device device;

        DeviceViewHolder(View view, OnDeviceItemClick listener) {
            super(view);
            dbId = view.findViewById(R.id.textViewMemberId);
            name = view.findViewById(R.id.textViewNameDeviceRV);
            description = view.findViewById(R.id.textViewDescriptionRV);
            notification = view.findViewById(R.id.textViewNotificationRV);

            view.setOnClickListener(v -> {
                if (listener == null || device == null) {
                    return;
                }
                listener.onDeviceClicked(device.getDbId(), getAdapterPosition());
            });
        }

        public void bind(Device device) {
            this.device = device;
            dbId.setText(Integer.toString(device.getDbId()));
            name.setText(String.valueOf(device.getName()));
            description.setText(String.valueOf(device.getDescription()));
            notification.setText(String.valueOf(device.getNotification()));
        }
    }

    public interface OnDeviceItemClick {
        void onDeviceClicked(int dbId, int position);
    }
}
