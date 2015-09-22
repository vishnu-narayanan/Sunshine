package com.vn.sunshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private String weather;
    private final String LOG_TAG = MapsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent i = getIntent();
        weather = i.getStringExtra("weather");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        // Add a marker in location and move the camera.

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        double latitude = Double.longBitsToDouble(sharedPref.getLong("Latitude", 0));
        double longitude = Double.longBitsToDouble(sharedPref.getLong("Longitude",0));

        LatLng location = new LatLng(latitude, longitude);
        Log.v(LOG_TAG,"coordinates " + location);
        Marker locationMarker = map.addMarker(new MarkerOptions().position(location).title(weather));
        locationMarker.showInfoWindow();
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 8));


    }
}