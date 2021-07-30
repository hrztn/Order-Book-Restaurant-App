package com.example.makanat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shreyaspatil.EasyUpiPayment.EasyUpiPayment;
import com.shreyaspatil.EasyUpiPayment.listener.PaymentStatusListener;
import com.shreyaspatil.EasyUpiPayment.model.TransactionDetails;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PaymentActivity extends AppCompatActivity implements PaymentStatusListener {
    private EditText amountEdt, upiEdt, nameEdt, descEdt;
    private TextView transactionDetailsTV;
    private Button payBtn, DoneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getSupportActionBar().hide();

        amountEdt = findViewById(R.id.idEdtAmount);
        upiEdt = findViewById(R.id.idEdtUpi);
        nameEdt = findViewById(R.id.idEdtName);
        descEdt = findViewById(R.id.idEdtDescription);
        payBtn = findViewById(R.id.idBtnMakePayment);
        DoneBtn = findViewById(R.id.btnDashboard);
        transactionDetailsTV = findViewById(R.id.idTVTranscationDetails);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault());
        String transcId = df.format(c);

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = amountEdt.getText().toString();
                String upi = upiEdt.getText().toString();
                String name = nameEdt.getText().toString();
                String desc = descEdt.getText().toString();
                if (TextUtils.isEmpty(amount) && TextUtils.isEmpty(upi) && TextUtils.isEmpty(name) && TextUtils.isEmpty(desc)) {
                    Toast.makeText(PaymentActivity.this, "Please enter all the details..", Toast.LENGTH_SHORT).show();
                } else {
                    makePayment(amount, upi, name, desc, transcId);
                }
            }
        });

        DoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( PaymentActivity.this,  DashboardActivity.class));
                Toast.makeText(PaymentActivity.this, "Payment is successful.", Toast.LENGTH_SHORT).show();
                notification();
                finish();
            }
        });

    }

    private void notification(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("e","e", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);


        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "e").setContentTitle("Thank You for Your Order!")
                .setSmallIcon(R.drawable.logo_transparent).setAutoCancel(true).setContentText("You order is received successfully.");


        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(999, builder.build());

    }


    private void makePayment(String amount, String upi, String name, String desc, String transcationId) {
        final EasyUpiPayment easyUpiPayment = new EasyUpiPayment.Builder()
                .with(this)
                .setPayeeVpa(upi)
                .setPayeeName(name)
                .setTransactionId(transcationId)
                .setTransactionRefId(transcationId)
                .setDescription(desc)
                .setAmount(amount)
                .build();
        easyUpiPayment.startPayment();
        easyUpiPayment.setPaymentStatusListener(this);
    }

    @Override
    public void onTransactionCompleted(TransactionDetails transactionDetails) {
        String transcDetails = transactionDetails.getStatus().toString() + "\n" + "Transaction ID : " + transactionDetails.getTransactionId();
        transactionDetailsTV.setVisibility(View.VISIBLE);
        transactionDetailsTV.setText(transcDetails);
    }

    @Override
    public void onTransactionSuccess() {
        Toast.makeText(this, "Transaction successfully completed..", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionSubmitted() {
        Log.e("TAG", "TRANSACTION SUBMIT");
    }

    @Override
    public void onTransactionFailed() {
        Toast.makeText(this, "Failed to complete transaction", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionCancelled() {
        Toast.makeText(this, "Transaction cancelled..", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAppNotFound() {
        Toast.makeText(this, "No app found for making transaction..", Toast.LENGTH_SHORT).show();
    }



}