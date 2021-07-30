package com.example.makanat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.makanat.modules.SlideViewAdapter;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private SlideViewAdapter svadapter;
    private Button startBtn;
    private ImageView indicator1, indicator2, indicator3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, DashboardActivity.class));
            finish();
        }

        indicator1 = findViewById(R.id.ss_indicator1);
        indicator2 = findViewById(R.id.ss_indicator2);
        indicator3 = findViewById(R.id.ss_indicator3);

        startBtn = findViewById(R.id.ss_btnstart);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });

        viewPager = findViewById(R.id.viewpager_intro);
        svadapter = new SlideViewAdapter(this);
        viewPager.setAdapter(svadapter);

        viewPager.addOnPageChangeListener(changeListener);

    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0:
                    indicator1.setImageResource(R.drawable.selected_indi);
                    indicator2.setImageResource(R.drawable.unselected_indi);
                    indicator3.setImageResource(R.drawable.unselected_indi);
                    break;
                case 1:
                    indicator1.setImageResource(R.drawable.unselected_indi);
                    indicator2.setImageResource(R.drawable.selected_indi);
                    indicator3.setImageResource(R.drawable.unselected_indi);
                    break;
                case 2:
                    indicator1.setImageResource(R.drawable.unselected_indi);
                    indicator2.setImageResource(R.drawable.unselected_indi);
                    indicator3.setImageResource(R.drawable.selected_indi);
                    break;

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}