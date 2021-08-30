package com.example.annoyingteammate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class WarningDialogFragment extends DialogFragment {

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Предупреждение")
                .setMessage("Для того чтобы начать \"Поиск устройства\" необходимо принять соглашение!")
                .setPositiveButton("OK", null)
                .create();
    }
}
