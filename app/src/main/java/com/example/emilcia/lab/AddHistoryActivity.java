package com.example.emilcia.lab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import database.DatabaseHelper;
import entity.History;


public class AddHistoryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_history);

        final EditText addName = (EditText) findViewById(R.id.add_name);
        final EditText addLiters = (EditText) findViewById(R.id.add_liters);
        final EditText addPrice = (EditText) findViewById(R.id.add_price);
        final Button addLocationActivitySaveButton = (Button) findViewById(R.id.save_button);

        final Intent mapActivityIntent = new Intent(this, MapsActivity.class);

        Log.d("", "onCreate: -----------------------------------------------------------------------------------------------------"+addName.getText().toString());
        Log.d("", "onCreate: -----------------------------------------------------------------------------------------------------"+addLiters.getText().toString());
        Log.d("", "onCreate: -----------------------------------------------------------------------------------------------------"+addPrice.getText().toString());

        addLocationActivitySaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapActivityIntent.putExtra(getString(R.string.name_string), addName.getText().toString());
                mapActivityIntent.putExtra(getString(R.string.liters_string), addLiters.getText().toString());
                mapActivityIntent.putExtra(getString(R.string.price_string), addPrice.getText().toString());

                startActivity(mapActivityIntent);
            }
        });
    }}
