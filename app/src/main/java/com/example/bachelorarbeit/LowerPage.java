package com.example.bachelorarbeit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

public class LowerPage extends AppCompatActivity {

    private Toolbar lower_page_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lower_page);
        InitializeFields();
    }

    private void InitializeFields() {
        lower_page_toolbar = findViewById(R.id.lower_page_toolbar);
        setSupportActionBar(lower_page_toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        TextView t = findViewById(R.id.title) ;
        t.setText(getString(R.string.lower_page)) ;
    }
}