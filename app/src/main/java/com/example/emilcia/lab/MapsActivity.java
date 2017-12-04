package com.example.emilcia.lab;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import database.DatabaseHelper;
import entity.History;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseHelper db;
    private LatLng currentSearchLocation;
    public static final String LATITUDE_STRING = "latitude";
    public static final String LONGITUDE_STRING = "longitude";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        db = new DatabaseHelper(getApplicationContext());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final Button searchButton = (Button) findViewById(R.id.search_location_button);
        final Button saveButton = (Button) findViewById(R.id.save_location_button);
        final EditText mapsActivityEditText = (EditText) findViewById(R.id.maps_activity_search_text);

        final String name = getIntent().getStringExtra(getString(R.string.name_string));
        final String liters = getIntent().getStringExtra(getString(R.string.liters_string));
        final String price = getIntent().getStringExtra(getString(R.string.price_string));

        final Intent mainActivityIntent = new Intent(this, MainActivity.class);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String history = mapsActivityEditText.getText().toString();
                search(history);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentSearchLocation!=null){

                History history = new History();
                history.setLatitude(currentSearchLocation.latitude);
                history.setLongitude(currentSearchLocation.longitude);
                history.setName(name);
                history.setLiters(Double.parseDouble(liters));
                history.setPrice(Double.parseDouble(price));
                history.setDate(new Date());

                db.insert(history);
                    startActivity(mainActivityIntent);
                } else {
                    Toast.makeText(MapsActivity.this, "Musisz wyszukać lokalizacje", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void search(String location){
        List<Address> addressList;

        if(!location.equals("")) {
            Geocoder geocoder = new Geocoder(this);

            try {
                addressList = geocoder.getFromLocationName(location, 1);
                Address address = addressList.get(0);
                currentSearchLocation = new LatLng(address.getLatitude(), address.getLongitude());

                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(currentSearchLocation).title(location));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentSearchLocation, 10));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(MapsActivity.this, "Podaj nazwe miejsca", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }
}
