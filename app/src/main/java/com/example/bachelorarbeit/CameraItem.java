package com.example.bachelorarbeit;

public class CameraItem {

    private int mImageResource;
    private String mText;

    public CameraItem(int imageResource, String text){
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
