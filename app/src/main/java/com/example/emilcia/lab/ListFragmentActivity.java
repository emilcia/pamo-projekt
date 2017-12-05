package com.example.emilcia.lab;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;


public class ListFragmentActivity extends FragmentActivity
        implements WebAddressFragment.OnWebAddressSelectedListener {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.new_articles);

            if (findViewById(R.id.fragment_container) != null) {

                if (savedInstanceState != null) {
                    return;
                }

                WebAddressFragment firstFragment = new WebAddressFragment();
                firstFragment.setArguments(getIntent().getExtras());

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, firstFragment).commit();
            }
        }

    public void onArticleSelected(String date) {
        if (getResources().getConfiguration().orientation == 2) {

            MyWebViewFragment webViewFragment = (MyWebViewFragment) getSupportFragmentManager().findFragmentById(R.id.webviewfragment);
            webViewFragment.setHistory(date);
            webViewFragment.display();

        } else {
            MyWebViewFragment newFragment = new MyWebViewFragment();
            Bundle args = new Bundle();
            args.putString(getString(R.string.date_string), date);
            newFragment.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }


}
