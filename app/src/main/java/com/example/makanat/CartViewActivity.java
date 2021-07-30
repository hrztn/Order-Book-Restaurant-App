package com.example.makanat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CartViewActivity extends AppCompatActivity {
    private EditText quantity1, quantity2, quantity3;
    private Button bookBtn, deliveryBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);
        getSupportActionBar().hide();

        quantity1 = findViewById(R.id.q1);
        quantity2 = findViewById(R.id.q2);
        quantity3 = findViewById(R.id.q3);
        bookBtn = findViewById(R.id.btn_book);
        deliveryBtn = findViewById(R.id.btn_delivery);

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String quan1 = quantity1.getText().toString();
                String quan2 = quantity2.getText().toString();
                String quan3 = quantity3.getText().toString();

                if (quan1.isEmpty()){
                    quantity1.setError("Please enter item quantity.");
                    return;
                }

                if (quan2.isEmpty()){
                    quantity2.setError("Please enter item quantity.");
                    return;
                }
                if (quan3.isEmpty()){
                    quantity3.setError("Please enter item quantity.");
                    return;
                }

                Intent intent=new Intent( CartViewActivity.this, BookActivity.class);
                Toast.makeText(CartViewActivity.this, "Enter Booking Details", Toast.LENGTH_SHORT).show();
                startActivity(intent);


            }
        });

        deliveryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String quan1 = quantity1.getText().toString();
                String quan2 = quantity2.getText().toString();
                String quan3 = quantity3.getText().toString();

                if (quan1.isEmpty()){
                    quantity1.setError("Please enter item quantity.");
                    return;
                }

                if (quan2.isEmpty()){
                    quantity2.setError("Please enter item quantity.");
                    return;
                }
                if (quan3.isEmpty()){
                    quantity3.setError("Please enter item quantity.");
                    return;
                }

                Intent intent=new Intent( CartViewActivity.this, deliveryActivity.class);
                Toast.makeText(CartViewActivity.this, "Select Delivery Method", Toast.LENGTH_SHORT).show();
                startActivity(intent);


            }
        });




    }
}