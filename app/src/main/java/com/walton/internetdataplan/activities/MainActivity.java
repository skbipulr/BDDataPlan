/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.walton.internetdataplan.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.TrafficStats;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.viewpagerindicator.CirclePageIndicator;
import com.walton.internetdataplan.AppManager;
import com.walton.internetdataplan.R;
import com.walton.internetdataplan.nvai.AppLocalDatabaseHelper;
import com.walton.internetdataplan.nvai.NetUsageFragmentViewpagerAdapter;
import com.walton.internetdataplan.services.DailyConsumedService;
import com.walton.internetdataplan.services.MobileWiFiDataUpdateService1;
import com.walton.internetdataplan.utitls.AddInfo;
import com.walton.internetdataplan.utitls.AppsSettings;
import com.walton.internetdataplan.utitls.KeyBoard;
import com.walton.internetdataplan.utitls.MyServiceRunning;
import com.walton.internetdataplan.utitls.WHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * TODO
 */
public class MainActivity extends AppCompatActivity {
    public NavigationView navigationView;
    public InterstitialAd interstitialAd;
    public AdView adView;
    public RelativeLayout adViewContainer;
    public TextView mTitleBar, headerTitleBar;
    public ImageView mDrawer_action;
    private DrawerLayout mDrawerLayout;
    public Context mContext;
    public static final String TAG = "MainActivity";

    public Activity mActivity;


    String data = "battery data:";
    AppLocalDatabaseHelper localDatabaseHelper;
    ArrayList<HashMap<String, String>> networkDataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mActivity = this;
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        // add inits ads //
        interstitialAd = new InterstitialAd(this, "1453443504734328_1453471424731536");
        interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });
        interstitialAd.loadAd();
        // end of add inits ads //
        // add  facebook benner ads //
        adViewContainer = (RelativeLayout) findViewById(R.id.adViewContainer);

        adView = new AdView(mContext, "1453443504734328_1453470934731585", AdSize.BANNER_HEIGHT_50);
        adViewContainer.addView(adView);
        adView.setAdListener(new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                Log.e("facebook_benner_ads","::error:"+adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                Log.e("facebook_benner_ads","::error:"+ad.getPlacementId());
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });
        adView.loadAd();
       // end of add facebook benner ads //
        // starting mobileWIFI data update service
        if (!MyServiceRunning.getInstance(this).isMyServiceRunning(MobileWiFiDataUpdateService1.class)) {
            // start service
            startService(new Intent(this, MobileWiFiDataUpdateService1.class));
            // end of start service
        }
        if (!MyServiceRunning.getInstance(this).isMyServiceRunning(DailyConsumedService.class)) {
            // start service
            startService(new Intent(this, DailyConsumedService.class));
            // end of start service
        }

        // ***********************inserting initial data to network data table for mobile and wifi chart **************


        localDatabaseHelper = new AppLocalDatabaseHelper(getApplicationContext());
        networkDataList = new ArrayList<>();
        networkDataList = localDatabaseHelper.getDeviceDataUsage();
        if (networkDataList.size() < 1) {

            long totalTrafficData = TrafficStats.getTotalRxBytes() + TrafficStats.getTotalTxBytes();
            long totalMobileTrafficData = TrafficStats.getMobileRxBytes() + TrafficStats.getMobileTxBytes();

            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();


            // changing here
            String year = calendar.get(Calendar.YEAR) + "";
            String month = (calendar.get(Calendar.MONTH) + 1) + "";
            String day = calendar.get(Calendar.DAY_OF_MONTH) + "";
            String customDateString = year + "-" + month + "-" + day;
            // changing end


            DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
            //   String dateString = formatter1.format(date);
            String dateString = customDateString;


            localDatabaseHelper.insertDeviceDataUsage(0 + "", totalTrafficData + "", 0 + "", dateString);
            localDatabaseHelper.insertDeviceMobileDataUsage(0 + "", totalMobileTrafficData + "", dateString);

            Log.d("_m_data", "****MainActivity****");
            Log.d("_m_data", "insert first row  totaldata : " + (TrafficStats.getTotalTxBytes() + TrafficStats.getTotalRxBytes()) + "");
        }


//*************************End of inserting initial data to network data table for mobile and wifi chart **************


        KeyBoard.hideKeyBoard(mActivity);
        Log.e("hello", "hello 123 234 345");
        /* Inside the activity */
// Sets the Toolbar to act as the ActionBar for this Activity window.
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
// Remove default title text
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
// Get access to the custom title view

        AppLocalDatabaseHelper appLocalDatabaseHelper = new AppLocalDatabaseHelper(this);


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        CirclePageIndicator circlePageIndicator = (CirclePageIndicator) findViewById(R.id.circle_indicator);
        NetUsageFragmentViewpagerAdapter netUsageFragmentViewpagerAdapter = new NetUsageFragmentViewpagerAdapter(this);
        viewPager.setAdapter(netUsageFragmentViewpagerAdapter);
        circlePageIndicator.setViewPager(viewPager);
        final float density = getResources().getDisplayMetrics().density;
        //Set circle indicator radius
        circlePageIndicator.setRadius(5 * density);

        appLocalDatabaseHelper.insertBootStatusData("yes");

        mTitleBar = (TextView) findViewById(R.id.titleBar);
        if (AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng")) {
            mTitleBar.setText("" + getResources().getString(R.string.app_name));
        } else {
            mTitleBar.setText("" + getResources().getString(R.string.app_name_bn));
        }
        mDrawer_action = (ImageView) findViewById(R.id.drawer_action);
        AppManager.getInstance(mContext);
        // airtel prepaid and postpaid same
        if (AppManager.mAirtelPostpaidDataPackList.size() != 18) {
            AppManager.getInstance(mContext).retrieveAirtelPostDataPackList(mContext, "");
            if (AppManager.mAirtelPostpaidDataPackList.size() < 18) {
                AddInfo.addAirtelDataPackInfo();
            }
        }
        if (AppManager.mBLPrepaidDataPackList.size() != 17) {
            AppManager.getInstance(mContext).retrieveBLPrepaidDataPackList(mContext, "");
            if (AppManager.mBLPrepaidDataPackList.size() < 17) {
                AddInfo.addBLPrepaidDataPackInfo();
            }
        }
        if (AppManager.mBLPostpaidDataPackList.size() != 16) {
            AppManager.getInstance(mContext).retrieveBLPostpaidDataPackList(mContext, "");
            if (AppManager.mBLPostpaidDataPackList.size() < 16) {
                AddInfo.addBLPostpaidDataPackInfo();
            }
        }
        // Gp prepaid and postpaid same
        if (AppManager.mGPPrepaidDataPackList.size() != 15) {
            AppManager.getInstance(mContext).retrieveGPPrepaidDataPackList(mContext, "");
            if (AppManager.mGPPrepaidDataPackList.size() < 15) {
                AddInfo.addGPDataPackInfo();
            }
        }

        // Robi prepaid and postpaid same
        if (AppManager.mRobiPrepaidDataPackList.size() != 37) {
            AppManager.getInstance(mContext).retrieveRobiPrepaidDataPackList(mContext, "");
            if (AppManager.mRobiPrepaidDataPackList.size() < 37) {
                AddInfo.addRobiDataPackInfo();
            }
        }

        // Teletalk prepaid and postpaid same
        if (AppManager.mTeletalkPrepaidDataPackList.size() != 90) {
            AppManager.getInstance(mContext).retrieveTeletalkPrepaidDataPackList(mContext, "");
            if (AppManager.mTeletalkPrepaidDataPackList.size() < 90) {
                AddInfo.addTeletalkDataPackInfo();
            }
        }
        if (AppManager.mMigrationList.size() != 20) {
            AppManager.getInstance(mContext).retrieveMigrationList(mContext, "", "");
            if (AppManager.mMigrationList.size() < 20) {
                AddInfo.addMigrationInfo();
            }
        }


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);

        // SETTING NAVIGATION MENU //
        navigationView.getMenu().clear(); //clear old inflated items.
        if (AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng")) {
            navigationView.inflateMenu(R.menu.drawer_view);
            View hea = navigationView.getHeaderView(0);
            headerTitleBar = (TextView) hea.findViewById(R.id.headerTitleBar);
            headerTitleBar.setText(getResources().getString(R.string.app_name));
        } else {
            navigationView.inflateMenu(R.menu.drawer_view_bn); //inflate new items.
            View hea = navigationView.getHeaderView(0);
            headerTitleBar = (TextView) hea.findViewById(R.id.headerTitleBar);
            headerTitleBar.setText(getResources().getString(R.string.app_name_bn));
        }
        // END OF SETTING NAVIGATION MENU //

        if (navigationView != null) {
            navigationView.getMenu().getItem(0).setChecked(true);
            setupDrawerContent();
        }
        mDrawer_action.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

//        AppManager.getInstance(mContext).retrieveTeletalkPostpaidDataPackList(mContext, "");
//        AppManager.getInstance(mContext).retrieveTeletalkPrepaidDataPackList(mContext, "");
//
//

//        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
//        if (viewPager != null) {
//            setupViewPager(viewPager);
//        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (navigationView != null) {
            navigationView.getMenu().getItem(0).setChecked(true);
            setupDrawerContent();
        }
        KeyBoard.hideKeyBoard(mActivity);
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }


    private void setupDrawerContent() {
//        navigationView.setNavigationItemSelectedListener(
//                new NavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(MenuItem menuItem) {
//                        menuItem.setChecked(true);
//                        mDrawerLayout.closeDrawers();
//                        return true;
//                    }
//                });
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                mDrawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {


                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        Intent i = new Intent(mContext, MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
//                                ContentFragment fragment = new ContentFragment();
//                                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                                fragmentTransaction.replace(R.id.frame,fragment);
//                                fragmentTransaction.commit();
                        return true;


                    case R.id.grameenphone:
                        Intent gphone = new Intent(mContext, GrameenphoneActivity.class);
                        gphone.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(gphone);
                        return true;
                    case R.id.banglalink:
                        Intent blhone = new Intent(mContext, BanglalinkActivity.class);
                        blhone.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(blhone);
                        return true;
                    case R.id.airtel:
                        Intent airtel = new Intent(mContext, AirtelActivity.class);
                        airtel.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(airtel);
                        return true;
                    case R.id.robi:
                        Intent robi = new Intent(mContext, RobiActivity.class);
                        robi.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(robi);
                        return true;
                    case R.id.teletalk:
                        Intent teletalk = new Intent(mContext, TeletalkActivity.class);
                        teletalk.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(teletalk);
                        return true;
                    case R.id.setting:
                        // Setting
                        Intent setting = new Intent(mContext, SettingsActivity.class);
                        setting.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(setting);
                        return true;
                    case R.id.share:
                        WHelper.getInstance(mContext).share();
                        return true;
                    case R.id.about:
                        // about
                        Intent about = new Intent(mContext, AboutActivity.class);
                        about.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(about);
                        return true;

                    default:
                        Intent de = new Intent(mContext, MainActivity.class);
                        de.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(de);
                        return true;

                }
            }
        });
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    @Override
    public void onBackPressed() {

        if (interstitialAd.isAdLoaded()) {
            interstitialAd.show();
        }
        super.onBackPressed();
    }
    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        if (interstitialAd != null) {
            interstitialAd.destroy();
        }
        super.onDestroy();
    }
}
