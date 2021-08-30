package com.example.annoyingteammate;

public class Language {

    private String language;
    private int flagImage;

    public Language(String countryName, int flagImage) {

        this.language = countryName;
        this.flagImage = flagImage;
    }

    public String getCountryName() {
        return language;
    }

    public void setCountryName(String countryName) {
        this.language = countryName;
    }

    public int getFlagImage() {
        return flagImage;
    }

    public void setFlagImage(int flagImage) {
        this.flagImage = flagImage;
    }
}
