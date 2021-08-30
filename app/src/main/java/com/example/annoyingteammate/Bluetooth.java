package com.example.annoyingteammate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import static com.example.annoyingteammate.MainActivity.APPLICATION_CONFIGURATION;
import static com.example.annoyingteammate.NameDevice.APPLICATION_CONFIGURATION_NAME_DEVICE;

import java.util.ArrayList;

public class Bluetooth extends AppCompatActivity {

    private final int REQUEST_ACCESS_TYPE = 1;

    private ConstraintLayout constraintLayoutNameDevice;

    private TextView textViewNameDeviceData;

    private SharedPreferences sharedPreferences;

    private RecyclerView recyclerViewBluetooth;
    private RecyclerView.Adapter adapterBluetooth;
    private RecyclerView.LayoutManager layoutManagerBluetooth;

    private BluetoothAdapter bluetoothAdapter;
    private Switch switchBluetooth;

    // private int positionRecyclerView;

    private ArrayList<Device> arrayListBluetooth = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth);

        constraintLayoutNameDevice = findViewById(R.id.constraintLayoutNameDevice);

        sharedPreferences = getSharedPreferences(APPLICATION_CONFIGURATION, MODE_PRIVATE);

        textViewNameDeviceData = findViewById(R.id.textViewNameDeviceData);
        textViewNameDeviceData.setText(sharedPreferences.getString(APPLICATION_CONFIGURATION_NAME_DEVICE, ""));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewBluetooth = (RecyclerView) findViewById(R.id.recyclerViewBluetooth);
        layoutManagerBluetooth = new LinearLayoutManager(this);
        recyclerViewBluetooth.setLayoutManager(layoutManagerBluetooth);
        recyclerViewBluetooth.setHasFixedSize(true);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        switchBluetooth = findViewById(R.id.switchBluetooth);

        DBBluetooth dbBluetooth = new DBBluetooth(this);

        SQLiteDatabase db = dbBluetooth.getWritableDatabase();

        if (bluetoothAdapter.isEnabled()) {

            switchBluetooth.setChecked(true);
        }

        switchBluetooth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked) {

                    bluetoothAdapter.enable();
                }

                else {

                    bluetoothAdapter.disable();
                }
            }
        });

        Cursor c = db.query("bluetooth", null, null, null, null, null, null, null);

        if (c.moveToFirst()) {

            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("name");
            int descriptionColIndex = c.getColumnIndex("description");
            int notificationColIndex = c.getColumnIndex("notification");

            do {
                int id = c.getInt(idColIndex);
                String name = c.getString(nameColIndex);
                String description = c.getString(descriptionColIndex);
                String notification = c.getString(notificationColIndex);

                Device device = new Device(id, name, description, notification);
                arrayListBluetooth.add(device);
            } while (c.moveToNext());
        }
        c.close();

        dbBluetooth.close();

        adapterBluetooth = new DeviceAdapter(arrayListBluetooth, (dbId, position) -> {

            // positionRecyclerView = position;
            // startActivity(new Intent(Bluetooth.this, CreateChangeBluetooth.class));
            startActivity(new Intent(Bluetooth.this, Report.class));
        });

        recyclerViewBluetooth.setAdapter(adapterBluetooth);
        
        constraintLayoutNameDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Bluetooth.this, NameDevice.class);
                startActivityForResult(intent, REQUEST_ACCESS_TYPE);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_bluetooth, menu);

        /* if (addMode) {
            MenuItem item = menu.findItem(R.id.buttonDelete);
            item.setVisible(false);
        } */
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if (requestCode == REQUEST_ACCESS_TYPE) {

            if (resultCode == RESULT_OK) {

                String personName = data.getStringExtra(APPLICATION_CONFIGURATION_NAME_DEVICE);
                textViewNameDeviceData.setText(personName);
                bluetoothAdapter.setName(personName);

            }
        }
        else {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}