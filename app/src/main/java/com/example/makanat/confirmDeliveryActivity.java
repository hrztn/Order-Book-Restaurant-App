package com.example.makanat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class confirmDeliveryActivity extends AppCompatActivity {
    Button btn_googlePayD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_delivery);
        getSupportActionBar().hide();

        btn_googlePayD = findViewById(R.id.pay_nowBtnD);


        btn_googlePayD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(confirmDeliveryActivity.this, PaymentActivity.class));
                finish();
            }
        });


    }
}