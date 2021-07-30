package com.example.makanat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class deliveryActivity extends AppCompatActivity {
    private static final String TAG = "deliveryActivity";
    private TextView mDisplayDateD, tvTimer1D;
    int t1HourD, t1MinuteD;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Button confBtnD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        getSupportActionBar().hide();
        mDisplayDateD = (TextView) findViewById(R.id.dateDTV);
        tvTimer1D = findViewById(R.id.timerDTV);
        confBtnD = findViewById(R.id.btn_confD);

        Spinner s = findViewById(R.id.spinD);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(deliveryActivity.this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mDisplayDateD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(deliveryActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDataSet: mm/dd/yyyy " + month + "/" + dayOfMonth + "/" + year);
                String date = month + "/" + dayOfMonth + "/" + year;
                mDisplayDateD.setText(date);


            }
        };

        tvTimer1D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(deliveryActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        t1HourD = hourOfDay;
                        t1MinuteD = minute;

                        Calendar calendar = Calendar.getInstance();

                        calendar.set(0, 0, 0, t1HourD, t1MinuteD);

                        tvTimer1D.setText(DateFormat.format("hh:mm aa", calendar));

                    }
                }, 12, 0, false);

                timePickerDialog.updateTime(t1HourD, t1MinuteD);

                timePickerDialog.show();

            }


        });

        confBtnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent( deliveryActivity.this, confirmDeliveryActivity.class);
                Toast.makeText(deliveryActivity.this, "CONFIRMATION", Toast.LENGTH_SHORT).show();
                startActivity(intent);


            }
        });



    }
}