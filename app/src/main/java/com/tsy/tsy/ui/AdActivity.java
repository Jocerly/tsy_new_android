package com.tsy.tsy.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.search.DynamicHeightSearchAdRequest;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.ads.search.SearchAdView;
import com.tsy.tsy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.tsy.base.uitls.JCLoger;

/**
 * 广告
 * Created by zxy on 2018/5/3.
 */

public class AdActivity extends Activity {
    @BindView(R.id.adView)
    AdView mAdView;
    @BindView(R.id.adSerView)
    LinearLayout linearLayout;
    private SearchAdView mSearchAdView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        ButterKnife.bind(this);

//        MobileAds.initialize(this, getResources().getString(R.string.app_id));
//        initAdView();
        initSearchAdView();
    }

    private void initAdView() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.setAdListener(adListener);
        /**
         * 开始载入广告条
         */
        mAdView.loadAd(adRequest);
    }

    private void initSearchAdView() {
        mSearchAdView = new SearchAdView(this);
        // Create a search ad. The ad size and ad unit ID must be set before calling loadAd.
        mSearchAdView.setAdSize(AdSize.SEARCH);
        mSearchAdView.setAdUnitId("vert-pla-mobile-app-pricetrace-srp");//clientID:vert-pla-mobile-app-pricetrace-srp
        mSearchAdView.setAdListener(adListener2);

        // 这个类封装了广告请求参数。这与在AFSh桌面和移动网站的JavaScript广告请求对象（页面选项，单元选项）中设置参数类似。
        //用适当的设置器设置参数（例如，通过调用设置查询参数setQuery()）
        DynamicHeightSearchAdRequest.Builder builder = new DynamicHeightSearchAdRequest.Builder();
        // Set the query.https://github.com/googleads/googleads-mobile-android-examples
        builder.setQuery("bike");//running shoes、bike
        // Optionally populate the ad request builder.
        builder.setAdTest(false);
        // Set adType to "plas" to request for AFShMA ads.
        // Key must be prefixed with "csa_".
        builder.setAdvancedOptionValue("csa_adType", "plas");//plas为多渠道
        // Set height and width for AFShMA ad block.
        builder.setAdvancedOptionValue("csa_height", "400");
        builder.setAdvancedOptionValue("csa_width", "400");

//        builder.setNumber(20);

        linearLayout.addView(mSearchAdView);
        // 发布广告请求，请 使用可从对象构建的实例调用该loadAd()方法
        mSearchAdView.loadAd(builder.build());

    }

    AdListener adListener = new AdListener() {
        @Override
        public void onAdLoaded() {
            // Called when an ad is loaded.
            super.onAdLoaded();
            Toast.makeText(AdActivity.this, "Ad Loaded", Toast.LENGTH_SHORT).show();
            JCLoger.debug("Ad Loaded");
        }

        @Override
        public void onAdOpened() {
            // Called when an ad opens an overlay that covers the screen.
            super.onAdOpened();
            Toast.makeText(AdActivity.this, "Ad Opened", Toast.LENGTH_SHORT).show();
            JCLoger.debug("Ad Opened");
        }

        @Override
        public void onAdLeftApplication() {
            // Called when an ad leaves the application (e.g., to go to the browser).
            super.onAdLeftApplication();
            Toast.makeText(AdActivity.this, "Ad Left Application", Toast.LENGTH_SHORT).show();
            JCLoger.debug("Ad Left Application");
        }

        @Override
        public void onAdFailedToLoad(int errorCode) {
            // Called when an ad request failed.
            super.onAdFailedToLoad(errorCode);
            Toast.makeText(AdActivity.this, "Ad Failed to Load: " + errorCode, Toast.LENGTH_SHORT).show();
            JCLoger.debug("Ad Failed to Load: " + errorCode);
        }
    };

    AdListener adListener2 = new AdListener() {
        @Override
        public void onAdImpression() {
            super.onAdImpression();
            Toast.makeText(AdActivity.this, "Ad2 onAdImpression", Toast.LENGTH_SHORT).show();
            JCLoger.debug("Ad2 onAdImpression");
        }

        @Override
        public void onAdLoaded() {
            // Called when an ad is loaded.
            super.onAdLoaded();
            Toast.makeText(AdActivity.this, "Ad2 Loaded", Toast.LENGTH_SHORT).show();
            JCLoger.debug("Ad2 Loaded");
        }

        @Override
        public void onAdOpened() {
            // Called when an ad opens an overlay that covers the screen.
            super.onAdOpened();
            Toast.makeText(AdActivity.this, "Ad2 Opened", Toast.LENGTH_SHORT).show();
            JCLoger.debug("Ad2 Opened");
        }

        @Override
        public void onAdLeftApplication() {
            // Called when an ad leaves the application (e.g., to go to the browser).
            super.onAdLeftApplication();
            Toast.makeText(AdActivity.this, "Ad2 Left Application", Toast.LENGTH_SHORT).show();
            JCLoger.debug("Ad2 Left Application");
        }

        @Override
        public void onAdFailedToLoad(int errorCode) {
            // Called when an ad request failed.
            super.onAdFailedToLoad(errorCode);
            Toast.makeText(AdActivity.this, "Ad2 Failed to Load: " + errorCode, Toast.LENGTH_SHORT).show();
            JCLoger.debug("Ad2 Failed to Load: " + errorCode);
        }
    };

    @Override
    public void onResume() {
        super.onResume();

        // Resume the SearchAdView.
        if (mSearchAdView != null) {

            mSearchAdView.resume();
        }
        mAdView.resume();
    }

    @Override
    public void onPause() {
        // Pause the SearchAdView.
        if (mSearchAdView != null) {
            mSearchAdView.pause();
        }
        mAdView.pause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        // Destroy the SearchAdView.
        if (mSearchAdView != null) {
            mSearchAdView.destroy();
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
