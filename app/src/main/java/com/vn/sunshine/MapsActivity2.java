package com.vn.sunshine;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity2 extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    DatabaseHandler db = new DatabaseHandler(this);

    private final String LOG_TAG = "MapsActivity2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);

        Intent i = getIntent();

        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

        List<Weather> weathers = db.getAllWeathers();

        int length = db.getWeathersCount();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(10.14,76.30), 7));
        int i =0;

        for (Weather wn : weathers) {
            //String log = "Id: "+wn.get_id()+" ,Latitude: " + wn.get_lat() + " ,Longitude: " + wn.get_long() + " ," + wn.get_weather();

            String[] places = new String[]{"Kannur","Kozhikode", "Thrissur", "Cochin", "Thiruvanthapuram"};
            Double latitude = wn.get_lat();
            Double longitude = wn.get_long();
            String data = wn.get_weather();

            Log.v(LOG_TAG, "Latitude " + latitude);
            Log.v(LOG_TAG, "Longitude " + longitude);
            Log.v(LOG_TAG, "weather: " + data);


            LatLng location = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(location).title(places[i]).snippet(data));
            i++;
            if (i>4)
                return;
            // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 8));

            // Writing Weathers to log
            //Log.d("Name: ", log);

        }

        //mMap.addMarker(new MarkerOptions().position(new LatLng(75.36, 11.87)).title("Marker"));


        // You can customize the marker image using images bundled with
        // your app, or dynamically generated bitmaps.
//        mMap.addMarker(new MarkerOptions()
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
//                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
//                .position(new LatLng(41.889, -87.622))
//                .title("Cochin")
//                .snippet("Thunderstorm 21/15"));

    }


}