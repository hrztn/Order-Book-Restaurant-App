package com.example.makanat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class StoreLocatorActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_locator);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        LatLng SSJ = new LatLng(3.103149, 101.606157);
        LatLng GOMBAK = new LatLng(3.275421, 101.642083);
        LatLng Putrajaya = new LatLng(2.949332, 101.682022);
        map.addMarker(new MarkerOptions().position(SSJ).title("Makan AT SSJ"));
        map.addMarker(new MarkerOptions().position(GOMBAK).title("Makan AT Gombak"));
        map.addMarker(new MarkerOptions().position(Putrajaya).title("Makan AT Putrajaya"));
        map.moveCamera(CameraUpdateFactory.newLatLng(SSJ));

    }
}