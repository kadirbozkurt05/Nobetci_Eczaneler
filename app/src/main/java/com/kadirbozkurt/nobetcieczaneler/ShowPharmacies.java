package com.kadirbozkurt.nobetcieczaneler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.kadirbozkurt.nobetcieczaneler.databinding.ActivityShowPharmaciesBinding;

public class ShowPharmacies extends AppCompatActivity {
    ActivityShowPharmaciesBinding binding;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowPharmaciesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        adMob();
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        binding.webView.loadUrl(url);

    }

    private void adMob() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView adView = new AdView(this);

        adView.setAdSize(AdSize.FULL_BANNER);
        adView.setAdUnitId("ca-app-pub-6599833176243964/3887287198");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}