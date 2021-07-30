package com.example.makanat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.makanat.model.OrderItemModelClass;
import com.example.makanat.model.UserModelClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class FoodInfoActivity extends AppCompatActivity {
    OrderItemModelClass orderItemModelClass = new OrderItemModelClass();
    UserModelClass Trader = new UserModelClass();
    ImageView itemDIV, userDIV;
    TextView userNameD, itemNameD, itemPriceD, itemDescD, itemIngreD, itemCondD, reviewsItemD;
    Button req_cart_btn, req_chat_btn;
    RatingBar ratingBarItemD;
    private String itemID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);
        getSupportActionBar().hide();
        orderItemModelClass = (OrderItemModelClass) getIntent().getSerializableExtra("order");
        init_Views();

    }

    private void init_Views() {
        itemID = getIntent().getStringExtra("pid");
        userNameD = findViewById(R.id.D_textView_username);
        itemNameD = findViewById(R.id.item_nameD);
        ratingBarItemD = findViewById(R.id.ratingBarItemDetails);
        reviewsItemD = findViewById(R.id.no_of_reviews);
        itemCondD = findViewById(R.id.D_item_condition);
        itemPriceD = findViewById(R.id.D_item_Price);
        itemDescD = findViewById(R.id.D_decriptiontextView);
        itemIngreD = findViewById(R.id.ingred_with);
        itemDIV = findViewById(R.id.D_imageView);
        userDIV = findViewById(R.id.D_user_imageView);
        req_cart_btn = findViewById(R.id.add_cartD_btn);
        reviewsItemD.setText(orderItemModelClass.getTotal_reviews() + " Reviews");
        float rating = (float) (orderItemModelClass.getTotalStars() / orderItemModelClass.getTotal_reviews());
        ratingBarItemD.setRating(rating);

        req_cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodInfoActivity.this, DashboardActivity.class);
                intent.putExtra("item", (Serializable) orderItemModelClass);
                Toast.makeText(FoodInfoActivity.this, "Add to cart successful.", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });


        getUserInfo(orderItemModelClass.getTraderID());
        itemNameD.setText(orderItemModelClass.getName());
        itemCondD.setText(orderItemModelClass.getCondition());
        itemPriceD.setText("MYR " + orderItemModelClass.getPrice());
        itemDescD.setText(orderItemModelClass.getDescription());
        itemIngreD.setText(orderItemModelClass.getIngredient());
        Picasso.get().load(orderItemModelClass.getImageUrl()).into(itemDIV);
        userNameD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FoodInfoActivity.this, RestaurantInfoActivity.class);
                i.putExtra("id", orderItemModelClass.getTraderID());
                startActivity(i);
            }
        });

    }

    private void setData() {

    }


    private void getUserInfo(String uID) {
        DatabaseReference rrr = FirebaseDatabase.getInstance().getReference().child("users").child(uID);
        // Read from the database
        rrr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, Object> map = (HashMap<String, Object>) dataSnapshot.getValue();
                String imageUrl = "" + map.get("userImage");
                String uname = "" + map.get("userName");
                userNameD.setText(uname);
                Picasso.get().load(imageUrl).into(userDIV);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Unable to identify value.", error.toException());
            }
        });
    }


}