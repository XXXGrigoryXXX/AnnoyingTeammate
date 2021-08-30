package com.example.annoyingteammate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public final static String APPLICATION_CONFIGURATION = "ApplicationConfiguration";
    private final static String APPLICATION_CONFIGURATION_SWEAR = "swear";

    private final int REQUEST_ACCESS_TYPE = 1;

    private ArrayList<Language> languageList;
    private LanguageAdapter languageAdapter;

    private Button buttonDeviceSearch;
    private CheckBox checkBoxSwear;

    private TextView textViewDetailedInformation;

    private ConstraintLayout constraintLayoutAgreement;

    private Spinner spinnerLanguage;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;

    private ConstraintLayout.LayoutParams layoutParams;

    // private final String LOG_TAG = "myLogs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonDeviceSearch = findViewById(R.id.buttonDeviceSearch);
        checkBoxSwear = findViewById(R.id.checkBoxSwear);

        textViewDetailedInformation = findViewById(R.id.textViewDetailedInformation);
        textViewDetailedInformation.setMovementMethod(LinkMovementMethod.getInstance());

        constraintLayoutAgreement = findViewById(R.id.constraintLayoutAgreement);

        sharedPreferences = getSharedPreferences(APPLICATION_CONFIGURATION, MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        initList();

        spinnerLanguage = findViewById(R.id.spinnerLanguages);
        languageAdapter = new LanguageAdapter(this, languageList);
        spinnerLanguage.setAdapter(languageAdapter);

        if (sharedPreferences.contains(APPLICATION_CONFIGURATION_SWEAR)) {

            closureOfTheAgreement();
        }

        buttonDeviceSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkBoxSwear.isChecked()) {

                    sharedPreferencesEditor.putBoolean(APPLICATION_CONFIGURATION_SWEAR, checkBoxSwear.isChecked());
                    sharedPreferencesEditor.apply();

                    Intent intent = new Intent(MainActivity.this, Bluetooth.class);
                    startActivityForResult(intent, REQUEST_ACCESS_TYPE);
                }

                else {

                    WarningDialogFragment warningDialogFragment = new WarningDialogFragment();
                    warningDialogFragment.show(getSupportFragmentManager(), "warning");
                }
            }
        });

        textViewDetailedInformation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("https://ru.wikipedia.org/wiki/%D0%93%D0%B0%D1%80%D1%80%D0%B8_%D0%9F%D0%BE%D1%82%D1%82%D0%B5%D1%80"));
                startActivity(browserIntent);
            }
        });

        /* spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Language selectedItem = (Language) parent.getItemAtPosition(position);
                // String selectedCountryName = selectedItem.getCountryName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }); */
    }

    private void initList() {

        languageList = new ArrayList<>();
        languageList.add(new Language("Русский", R.drawable.russia));
        languageList.add(new Language("English", R.drawable.great_britain));
        languageList.add(new Language("Deutsche", R.drawable.germany));
        languageList.add(new Language("Español", R.drawable.spain));
        languageList.add(new Language("Italiano", R.drawable.italy));
        languageList.add(new Language("Français", R.drawable.france));
    }

    private int pxToDp(int px) {

        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    private void closureOfTheAgreement() {

        constraintLayoutAgreement.setVisibility(View.GONE);

        layoutParams = (ConstraintLayout.LayoutParams) buttonDeviceSearch.getLayoutParams();
        layoutParams.topMargin = pxToDp(-550);
        checkBoxSwear.setChecked(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if (requestCode == REQUEST_ACCESS_TYPE) {

            if (resultCode == RESULT_OK) {

                sharedPreferences.getBoolean(APPLICATION_CONFIGURATION_SWEAR, false);
                closureOfTheAgreement();
            }
        }
        else {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}