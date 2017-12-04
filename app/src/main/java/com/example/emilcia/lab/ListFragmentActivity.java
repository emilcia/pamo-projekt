package com.example.emilcia.lab;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;


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

    public void onArticleSelected(int position) {

        if (getResources().getConfiguration().orientation == 2) {

            MyWebViewFragment webViewFragment = (MyWebViewFragment) getSupportFragmentManager().findFragmentById(R.id.webviewfragment);
//            try {
//
//                WebView webView = webViewFragment.getWebView();
//                webView.getSettings().setJavaScriptEnabled(true);
//                webView.loadUrl(Consts.WEB_ADDRESS[position]);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            MyWebViewFragment.setLocation(name);
//            MyWebViewFragment.displayLocation();
        } else {
            MyWebViewFragment newFragment = new MyWebViewFragment();
            Bundle args = new Bundle();
            args.putString(MyWebViewFragment.ARG_POSITION, Consts.WEB_ADDRESS[position]);
            newFragment.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }


}
