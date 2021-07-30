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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookActivity extends AppCompatActivity {
    private static final String TAG = "BookActivity";
    private TextView mDisplayDate, tvTimer1;
    int t1Hour, t1Minute;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Button confBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        getSupportActionBar().hide();
        mDisplayDate = (TextView) findViewById(R.id.dateTV);
        tvTimer1 = findViewById(R.id.timerTV);
        confBtn = findViewById(R.id.btn_conf);

        Spinner s = findViewById(R.id.spin);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(BookActivity.this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(BookActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);

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
                mDisplayDate.setText(date);


            }
        };

        tvTimer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(BookActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        t1Hour = hourOfDay;
                        t1Minute = minute;

                        Calendar calendar = Calendar.getInstance();

                        calendar.set(0, 0, 0, t1Hour, t1Minute);

                        tvTimer1.setText(DateFormat.format("hh:mm aa", calendar));

                    }
                }, 12, 0, false);

                timePickerDialog.updateTime(t1Hour, t1Minute);

                timePickerDialog.show();

            }


        });

        confBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent( BookActivity.this, confirmActivity.class);
                Toast.makeText(BookActivity.this, "CONFIRMATION", Toast.LENGTH_SHORT).show();
                startActivity(intent);


            }
        });




    }
}