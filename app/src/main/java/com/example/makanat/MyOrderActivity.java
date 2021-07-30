package com.example.makanat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyOrderActivity extends AppCompatActivity {
    Button btn_bookC, btn_bookO, btn_deliveryC, btn_deliveryO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        getSupportActionBar().hide();

        btn_bookC = findViewById(R.id.btnB);
        btn_bookO = findViewById(R.id.btnBO);
        btn_deliveryC = findViewById(R.id.btnDC);
        btn_deliveryO = findViewById(R.id.btnD);

        btn_bookC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyOrderActivity.this, BookCompletedActivity.class));
                finish();
            }
        });

        btn_deliveryC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyOrderActivity.this, DeliveryCompletedActivity.class));
                finish();
            }
        });

        btn_bookO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyOrderActivity.this, BookOngoingActivity.class));
                finish();
            }
        });

        btn_deliveryO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyOrderActivity.this, DeliveryOngoingActivity.class));
                finish();
            }
        });



    }
}