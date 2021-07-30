package com.example.makanat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DeliveryOngoingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_ongoing);
        getSupportActionBar().hide();
    }
}