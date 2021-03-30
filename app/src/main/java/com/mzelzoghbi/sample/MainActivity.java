package com.mzelzoghbi.sample;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.mzelzoghbi.zgallery.ZGallery;
import com.mzelzoghbi.zgallery.ZGrid;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // init sdk
        MobileAds.initialize(getApplicationContext(), getString(R.string.ad_mob_id));
        //  init banner
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
        // init interstital
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.ad_mob_interstitial));

        AdRequest interstitialAdRequest = new AdRequest.Builder()
                .build();

        mInterstitialAd.loadAd(interstitialAdRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    public void gridActivity(View v) {
        ZGrid.with(this, getDummyImageList())
                .setToolbarColorResId(R.color.colorPrimary)
                .setTitle("Zak Gallery")
                .setToolbarTitleColor(Color.WHITE)
                .setSpanCount(3)
                .setGridImgPlaceHolder(R.color.colorPrimary)
                .show();
    }


    public void galleryActivity(View v) {
        ZGallery.with(this, getDummyImageList())
                .setToolbarTitleColor(Color.WHITE)
                .setGalleryBackgroundColor(Color.WHITE)
                .setToolbarColorResId(R.color.colorPrimary)
                .setTitle("Zak Gallery")
                .show();
    }

    private ArrayList<String> getDummyImageList() {
        ArrayList<String> imagesList = new ArrayList<>();
        imagesList.add("https://images.freeimages.com/images/large-previews/d18/still-life-1325648.jpg");
        imagesList.add("https://images.freeimages.com/images/large-previews/58f/double-bass-1423720.jpg");
        imagesList.add("https://images.freeimages.com/images/large-previews/13f/natal-sofia-4-1431300.jpg");
        imagesList.add("https://images.freeimages.com/images/large-previews/1c1/blue-water-sailing-1-1437302.jpg");
        imagesList.add("https://images.freeimages.com/images/large-previews/815/xmas-bunny-1313404.jpg");
        imagesList.add("https://images.freeimages.com/images/large-previews/ce3/puppies-1-1308839.jpg");
        imagesList.add("https://images.freeimages.com/images/large-previews/e33/tate-weather-project-8-1473995.jpg");
        imagesList.add("https://images.freeimages.com/images/large-previews/44c/blue-and-yellow-macaw-1641749.jpg");
        return imagesList;
    }
}
