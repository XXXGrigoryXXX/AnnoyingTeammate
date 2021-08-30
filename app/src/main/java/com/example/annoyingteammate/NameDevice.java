package com.example.annoyingteammate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import static com.example.annoyingteammate.MainActivity.APPLICATION_CONFIGURATION;

public class NameDevice extends AppCompatActivity {

    public final static String APPLICATION_CONFIGURATION_NAME_DEVICE = "name_device";

    private EditText editTextTextPersonName;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_device);

        getSupportActionBar().setTitle("Имя устройства");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(APPLICATION_CONFIGURATION, MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPersonName.setText(sharedPreferences.getString(APPLICATION_CONFIGURATION_NAME_DEVICE, ""));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent();

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.save:
                sharedPreferencesEditor.putString(APPLICATION_CONFIGURATION_NAME_DEVICE, editTextTextPersonName.getText().toString());
                sharedPreferencesEditor.apply();
                intent.putExtra(APPLICATION_CONFIGURATION_NAME_DEVICE, editTextTextPersonName.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_create_edit, menu);

        /* if (addMode) {
            MenuItem item = menu.findItem(R.id.buttonDelete);
            item.setVisible(false);
        } */
        return true;
    }
}