package com.example.annoyingteammate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);

        getSupportActionBar().setTitle("Отчёт");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent();

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.messenger:
                MessengerDialogFragment messengerDialogFragment = new MessengerDialogFragment();
                messengerDialogFragment.show(getSupportFragmentManager(), "messenger");
                // setResult(RESULT_OK, intent);
                // finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_report, menu);

        /* if (addMode) {
            MenuItem item = menu.findItem(R.id.buttonDelete);
            item.setVisible(false);
        } */
        return true;
    }
}