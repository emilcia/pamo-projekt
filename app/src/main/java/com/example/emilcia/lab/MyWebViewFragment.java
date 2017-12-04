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

/**
 * Created by Emilcia on 11.11.2017.
 */

public class MyWebViewFragment extends Fragment implements OnMapReadyCallback{

    private GoogleMap mMap;
    public static final String ARG_POSITION = "address_position";
    private View mView;
    private boolean mIsWebViewAvailable;
    private TextView myWebViewActivityName;
    private TextView myWebViewActivityDescription;

    private String mUrl;

    public MyWebViewFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.webview_fragment, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return mView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        if (mWebView != null) {
//            mWebView.destroy();
//        }
//
//        if(getArguments()!=null){
//            mUrl = getArguments().getString(ARG_POSITION);
//        }
//
//        mWebView = new WebView(getActivity());
//        mWebView.setOnKeyListener(new View.OnKeyListener(){
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
//                    mWebView.goBack();
//                    return true;
//                }
//                return false;
//            }
//
//        });
//        mWebView.setWebViewClient(new InnerWebViewClient());
//        mIsWebViewAvailable = true;
//        WebSettings settings = mWebView.getSettings();
//        settings.setJavaScriptEnabled(true);
//        if (mUrl != null) {
//            loadUrl(mUrl);
//        }
//        return mWebView;
//    }
//
//    public void loadUrl(String url) {
//        if (mIsWebViewAvailable) {
//            getWebView().loadUrl(url);
//        }
//    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        mWebView.onPause();
//    }
//
//    @Override
//    public void onResume() {
//        mWebView.onResume();
//        super.onResume();
//    }
//
//    @Override
//    public void onDestroyView() {
//        mIsWebViewAvailable = false;
//        super.onDestroyView();
//    }
//
//    @Override
//    public void onDestroy() {
//        if (mWebView != null) {
//            mWebView.destroy();
//            mWebView = null;
//        }
//        super.onDestroy();
//    }
//
//    public WebView getWebView() {
//        return mIsWebViewAvailable ? mWebView : null;
//    }
//
//
//    private class InnerWebViewClient extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return true;
//        }
//
//
//    }
}
