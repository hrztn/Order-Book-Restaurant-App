package com.example.makanat.screen;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.makanat.CartActivity;
import com.example.makanat.CartViewActivity;
import com.example.makanat.MyOrderActivity;
import com.example.makanat.R;
import com.example.makanat.RewardActivity;
import com.example.makanat.StoreLocatorActivity;
import com.example.makanat.model.UserModelClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class profile_screen extends Fragment {
    TextView nameTV, emailTV, phoneTV, addressTV;
    ImageView userIV;
    ImageButton phoneEdit, addressEdit;
    RecyclerView myItemsRV;
    Button cartBtn, viewCartBtn, orderBtn, locationBtn, rewardBtn, shareBtn;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    String userID;

    public profile_screen() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile, container, false);

        user = FirebaseAuth.getInstance().getCurrentUser();

        nameTV=view.findViewById(R.id.myProfile_name);
        emailTV=view.findViewById(R.id.myProfile_email);
        phoneTV=view.findViewById(R.id.myProfile_phone);
        addressTV=view.findViewById(R.id.myProfile_address);
        userIV=view.findViewById(R.id.userImageView);
        phoneEdit=view.findViewById(R.id.myProfile_phoneEdit);
        addressEdit=view.findViewById(R.id.myProfile_addEdit);
        cartBtn=view.findViewById(R.id.CartBtn);
        viewCartBtn=view.findViewById(R.id.CartView_Btn);
        orderBtn=view.findViewById(R.id.ManageOrder_Btn);
        locationBtn=view.findViewById(R.id.StoreLocation_Btn);
        rewardBtn=view.findViewById(R.id.Reward_Btn);
        shareBtn=view.findViewById(R.id.share_Btn);
        nameTV.setText(user.getDisplayName());
        emailTV.setText(user.getEmail());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        getUserInfo(user.getUid());
        Picasso.get().load(user.getPhotoUrl()).into(userIV);


        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getActivity(), CartActivity.class);
                startActivity(i);
            }
        });

        viewCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getContext(), CartViewActivity.class);
                startActivity(i);
            }
        });

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getContext(), MyOrderActivity.class);
                startActivity(i);
            }
        });

        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getContext(), StoreLocatorActivity.class);
                startActivity(i);
            }
        });

        rewardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getContext(), RewardActivity.class);
                Toast.makeText(getContext(), "Daily Reward Claimed.", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                String sharebody = "Makan AT is now available online!";
                i.putExtra(Intent.EXTRA_SUBJECT, sharebody);
                startActivity(Intent.createChooser(i, "MAKAN AT"));

            }
        });


        phoneEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBox = new AlertDialog.Builder(getContext());
                alertBox.setTitle("Edit Phone Number: ");

                final EditText changePhone = new EditText(getContext());
                changePhone.setInputType(InputType.TYPE_CLASS_PHONE);

                alertBox.setView(changePhone);

                alertBox.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String myPhone = changePhone.getText().toString();
                        String userID = firebaseAuth.getCurrentUser().getUid();
                        DatabaseReference myPhoneRef = firebaseDatabase.getReference("users").child(userID).child("userPhoneNo");
                        myPhoneRef.setValue(myPhone).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getContext(), "Phone number updated", Toast.LENGTH_SHORT).show();
                                phoneTV.setText(myPhone);
                            }
                        });
                    }
                });

                alertBox.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alertBox.show();
            }
        });

        addressEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBox = new AlertDialog.Builder(getContext());
                alertBox.setTitle("Edit Address: ");

                final EditText changeAddress = new EditText(getContext());
                changeAddress.setInputType(InputType.TYPE_CLASS_TEXT);

                alertBox.setView(changeAddress);

                alertBox.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String myAddress = changeAddress.getText().toString();
                        String userID = firebaseAuth.getCurrentUser().getUid();
                        DatabaseReference myPhoneRef = firebaseDatabase.getReference("users")
                                .child(userID).child("userAddress");
                        myPhoneRef.setValue(myAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getContext(), "Address updated", Toast.LENGTH_SHORT).show();
                                addressTV.setText(myAddress);
                            }
                        });
                    }
                });

                alertBox.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertBox.show();
            }
        });

        return view;




    }



    private void getUserInfo(String uID) {
        DatabaseReference rrr = FirebaseDatabase.getInstance().getReference().child("users").child(uID);
        rrr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserModelClass userModel = dataSnapshot.getValue(UserModelClass.class);
                nameTV.setText(userModel.getUserName());
                emailTV.setText(userModel.getUserEmail());
                phoneTV.setText(userModel.getUserPhoneNo());
                addressTV.setText(userModel.getUserAddress());
                Picasso.get().load(userModel.getUserImage()).into(userIV);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }




}
