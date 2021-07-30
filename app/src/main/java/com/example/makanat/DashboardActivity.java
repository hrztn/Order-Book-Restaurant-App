package com.example.makanat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.makanat.screen.order_screen;
import com.example.makanat.screen.profile_screen;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();

        BottomNavigationView navView = findViewById(R.id.db_btmNav);
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        order_screen orderScreen = new order_screen();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.db_frame, orderScreen).commit();



    }

    private final BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()){

                case R.id.btm_order:

                    order_screen orderScreen = new order_screen();
                    FragmentManager marketmanager = getSupportFragmentManager();
                    marketmanager.beginTransaction().replace(R.id.db_frame, orderScreen).commit();


                    return true;




                case R.id.btm_userProfile:

                    profile_screen profileScreen = new profile_screen();
                    FragmentManager profileManager = getSupportFragmentManager();
                    profileManager.beginTransaction().replace(R.id.db_frame, profileScreen).commit();
                    return true;

            }

            return false;
        }
    };
}