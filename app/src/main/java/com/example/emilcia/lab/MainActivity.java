package com.example.emilcia.lab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button listViewActivityButton = (Button) findViewById(R.id.list_view_button);
        final Button addHistoryButton = (Button) findViewById(R.id.add_history_button);

        final Intent listView = new Intent(this, ListFragmentActivity.class);
        final Intent addHistory = new Intent(this, AddHistoryActivity.class);


        listViewActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(listView);
            }
        });
        addHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(addHistory);
            }
        });

    }
}
