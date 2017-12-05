package com.example.emilcia.lab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import database.DatabaseHelper;
import entity.History;

/**
 * Created by Emilcia on 11.11.2017.
 */

public class MyWebViewFragment extends Fragment implements OnMapReadyCallback{

    private GoogleMap mMap;
    private DatabaseHelper db;
    private View mView;
    private TextView myWebActivityName;
    private TextView myWebActivityLiters;
    private TextView myWebActivityPrice;
    private TextView myWebActivityPriceLiter;
    private History history;
    private String liters;
    private String price;
    private String pricePerLiter;

    public MyWebViewFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.webview_fragment, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        myWebActivityName = (TextView) mView.findViewById(R.id.details_activity_name);
        myWebActivityLiters = (TextView) mView.findViewById(R.id.details_activity_liters);
        myWebActivityPrice = (TextView) mView.findViewById(R.id.details_activity_price);
        myWebActivityPriceLiter = (TextView) mView.findViewById(R.id.details_activity_price_liter);

        if(getArguments() != null) {
            String date = getArguments().getString(getString(R.string.date_string));
            setHistory(date);
        }
        return mView;
    }
    public void setHistory(String date) {
        history = db.getByDate(date);
        myWebActivityName.setText("station name:"+history.getName());
        Double x = history.getLiters()*history.getPrice();
        liters = Double.toString(history.getLiters());
        myWebActivityLiters.setText("liters:"+liters);
        price = Double.toString(x);
        myWebActivityPrice.setText("price:"+price+" zł");
        pricePerLiter = Double.toString(history.getPrice());
        myWebActivityPriceLiter.setText("price per liter: "+pricePerLiter);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        if(history != null) {
            display();
        }
    }
    public void display(){
        LatLng locationCoords = new LatLng(history.getLatitude(), history.getLongitude());
        mMap.addMarker(new MarkerOptions().position(locationCoords).title(history.getName()));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(locationCoords, 10));
    }
}
