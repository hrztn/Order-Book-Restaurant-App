package com.example.makanat.screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.makanat.LoginActivity;
import com.example.makanat.R;
import com.example.makanat.model.OrderItemModelClass;
import com.example.makanat.modules.ItemServerAdapter;
import com.example.makanat.modules.MyItemRecyclerViewAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class order_screen extends Fragment {
    RecyclerView itemsRecyclerView;
    ItemServerAdapter adapter;
    Spinner filterSpinner;
    private ArrayList<OrderItemModelClass> ItemsList = new ArrayList<>();
    String[] catagorys = new String[]{"All", "Western", "Eastern", "Dessert",
            "Local", "Drink", "Snacks"};

    public order_screen() {

    }

    private EditText order_search;
    private TextView user_logout;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.marketplace, container, false);

        itemsRecyclerView = view.findViewById(R.id.itemsRecyclerViewmain);
        filterSpinner = view.findViewById(R.id.catagory_filter);
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new ItemServerAdapter(getContext(), ItemsList);
        itemsRecyclerView.setAdapter(adapter);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter(getContext(),
                R.layout.row_spinner, catagorys);
        filterSpinner.setAdapter(spinnerAdapter);
        getdata();

        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String s = filterSpinner.getSelectedItem().toString();
                Log.d("tester", "onItemSelected: " + s);
                if (s.equals("All")) {
                    s = "";
                }
                if (adapter != null) {

                    adapter.getFilter().filter(s);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        order_search = view.findViewById(R.id.order_searchbar);
        user_logout = view.findViewById(R.id.user_logout);

        order_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence arg0, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable arg0) {


            }
        });

        user_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finishAffinity();
            }
        });


        return view;
    }

    public void getdata() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("order_items");
        Query getpost = reference;
        getpost.addChildEventListener(new ChildEventListener() {
            ArrayList<OrderItemModelClass> mItems = new ArrayList<>();

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                OrderItemModelClass mOrderItemModelClass = dataSnapshot.getValue(OrderItemModelClass.class);
                if (!mOrderItemModelClass.getTraderID().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {


                    if (ItemsList.add(mOrderItemModelClass)) {
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                }

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        adapter = new ItemServerAdapter(getContext(), ItemsList);

        itemsRecyclerView.setAdapter(adapter);

    }


}
