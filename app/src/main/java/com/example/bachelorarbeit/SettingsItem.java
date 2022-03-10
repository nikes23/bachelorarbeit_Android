package com.example.bachelorarbeit;

public class SettingsItem {

    private int mImageResource;
    private String mText;

    public SettingsItem(int imageResource, String text){
        mImageResource = imageResource;
        mText = text;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public String getmText() {
        return mText;
    }
}
