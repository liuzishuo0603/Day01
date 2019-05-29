package com.example.dell.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DetailsActivity extends AppCompatActivity {

    private WebView mWbDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();
    }

    private void initView() {
        mWbDetails = (WebView) findViewById(R.id.wb_details);
        if (getIntent() != null) {
            String url = getIntent().getStringExtra("url");
            mWbDetails.getSettings().setBuiltInZoomControls(true);
            mWbDetails.getSettings().setSupportZoom(true);
            mWbDetails.setWebViewClient(new WebViewClient());
            mWbDetails.loadUrl(url);
            mWbDetails.getSettings().setUseWideViewPort(true);
            mWbDetails.getSettings().setLoadsImagesAutomatically(true);
            mWbDetails.getSettings().setLoadWithOverviewMode(true);
            mWbDetails.getSettings().setDisplayZoomControls(false);
        }
    }
}
