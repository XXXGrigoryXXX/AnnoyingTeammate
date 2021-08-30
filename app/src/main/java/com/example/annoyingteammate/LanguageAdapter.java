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

public class LanguageAdapter extends ArrayAdapter<Language> {

    public LanguageAdapter(Context context, ArrayList<Language> languageList) {

        super(context, 0, languageList);
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

                    R.layout.spinner_item_language, parent, false
            );
        }

        ImageView imageFlag = convertView.findViewById(R.id.imageViewFlag);
        TextView textName = convertView.findViewById(R.id.textViewLanguage);

        Language currentItem = getItem(position);

        if (currentItem != null) {

            imageFlag.setImageResource(currentItem.getFlagImage());
            textName.setText(currentItem.getCountryName());
        }

        return convertView;
    }
}
