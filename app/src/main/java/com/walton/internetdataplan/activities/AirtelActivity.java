


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
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.walton.internetdataplan.AppManager;
import com.walton.internetdataplan.R;
import com.walton.internetdataplan.adapters.AirtelPostpaidAdapter;
import com.walton.internetdataplan.adapters.AirtelPrepaidAdapter;
import com.walton.internetdataplan.fragments.AirtelFnFFragment;
import com.walton.internetdataplan.fragments.AirtelMigrationFragment;
import com.walton.internetdataplan.fragments.AirtelMiscFragment;
import com.walton.internetdataplan.fragments.AirtelPostpaidFragment;
import com.walton.internetdataplan.fragments.AirtelPrepaidFragment;
import com.walton.internetdataplan.utitls.AppsSettings;
import com.walton.internetdataplan.utitls.KeyBoard;
import com.walton.internetdataplan.utitls.WHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */
public class AirtelActivity extends AppCompatActivity {
    public NavigationView navigationView;
    public AdView adView;
    public RelativeLayout adViewContainer;
    String mSearchItemValue = "";
    public static TextView mTitleBar, headerTitleBar;
    public static EditText mSearchItem;
    public static ImageView mSearchAction;
    public static LinearLayout mSearchActionL;
    public static LinearLayout mSearchState;
    public static LinearLayout mNormalState;
    public ImageView mDrawer_action;
    private DrawerLayout mDrawerLayout;
    public Context mContext;

    public static final String TAG = "MainActivity";
    public Activity mActivity;
    public InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.airtel_activity);
        mContext = this;
        mActivity = this;
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

        adView = new AdView(mContext, "1453443504734328_1463948700350475", AdSize.BANNER_HEIGHT_50);
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
        KeyBoard.hideKeyBoard(mActivity);



        /* Inside the activity */
// Sets the Toolbar to act as the ActionBar for this Activity window.
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
// Remove default title text
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
// Get access to the custom title view


        mTitleBar = (TextView) findViewById(R.id.titleBar);
        mTitleBar.setText("" + getResources().getString(R.string.airtel));
        mSearchItem = (EditText) findViewById(R.id.searchItem);
        mDrawer_action = (ImageView) findViewById(R.id.drawer_action);
        mSearchState = (LinearLayout) findViewById(R.id.searchState);
        mNormalState = (LinearLayout) findViewById(R.id.normalState);

        mSearchAction = (ImageView) findViewById(R.id.searchAction);
        mSearchActionL = (LinearLayout) findViewById(R.id.searchActionL);
        if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
        {
            convertEnglish();
        }
        else
        {
            convertBangla();
        }

// ************************* just preload Prepaid Fragment *****************************************//
        mNormalState.setVisibility(View.VISIBLE);
        mTitleBar.setVisibility(View.VISIBLE);
        mSearchItem.setText("");

        // Enter key press detect //
        mSearchItem.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            mSearchItemValue = mSearchItem.getText().toString();
                            if (mSearchItemValue.equals("") || mSearchItemValue.trim().length() <= 1) {
                                if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
                                {
                                    Toast.makeText(
                                            mContext,
                                            ""+getResources().getString(R.string.min_press_2_chars),
                                            Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(
                                            mContext,
                                            ""+getResources().getString(R.string.min_press_2_chars_bn),
                                            Toast.LENGTH_SHORT).show();
                                }


                            } else {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                                AppManager.getInstance(mContext).retrieveAirtelPrepaidDataPackList(mContext, mSearchItemValue);
                                AirtelPrepaidAdapter gpAdapter1 = new AirtelPrepaidAdapter(mContext, AppManager.mAirtelPrepaidDataPackList, true);
                                AirtelPrepaidFragment.recyclerView.setAdapter(gpAdapter1);


                            }
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
        // End of Enter key press detect //
        mSearchAction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSearchItem.isShown()) {
                    mSearchItemValue = mSearchItem.getText().toString();
                    if (mSearchItemValue.equals("") || mSearchItemValue.trim().length() <= 1) {

                        if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
                        {
                            Toast.makeText(
                                    mContext,
                                    ""+getResources().getString(R.string.min_press_2_chars),
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(
                                    mContext,
                                    ""+getResources().getString(R.string.min_press_2_chars_bn),
                                    Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        AppManager.getInstance(mContext).retrieveAirtelPrepaidDataPackList(mContext, mSearchItemValue);
                        AirtelPrepaidAdapter gpAdapter1 = new AirtelPrepaidAdapter(mContext, AppManager.mAirtelPrepaidDataPackList, true);
                        AirtelPrepaidFragment.recyclerView.setAdapter(gpAdapter1);
                        // start change //
                        mNormalState.setVisibility(View.VISIBLE);
                        mTitleBar.setVisibility(View.VISIBLE);
                        mSearchState.setVisibility(View.GONE);
                        mSearchItem.setText("");
                        mSearchAction.setImageResource(R.drawable.refresh_96);
                        // end of start change //

                    }

                } else {
                    mSearchItem.setText("");
                    if (AppManager.mAirtelPrepaidDataPackList.size() == 18) {

                    } else {
                        AppManager.getInstance(mContext).retrieveAirtelPrepaidDataPackList(mContext, "");
                        AirtelPrepaidAdapter gpAdapter1 = new AirtelPrepaidAdapter(mContext, AppManager.mAirtelPrepaidDataPackList, true);
                        AirtelPrepaidFragment.recyclerView.setAdapter(gpAdapter1);
                    }
                    if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
                    {
                        mSearchItem.setHint(getResources().getString(R.string.edittext_hint));
                    }
                    else
                    {
                        mSearchItem.setHint(getResources().getString(R.string.edittext_hint_bn));
                    }

                    mNormalState.setVisibility(View.GONE);
                    mSearchState.setVisibility(View.VISIBLE);
                    mSearchAction.setImageResource(R.drawable.search_96);
                }

            }
        });


// ************************* just preload Prepaid Fragment *****************************************//


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        // SETTING NAVIGATION MENU //
        navigationView.getMenu().clear(); //clear old inflated items.
        if (AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng")) {
            navigationView.inflateMenu(R.menu.drawer_view);
            View hea = navigationView.getHeaderView(0);
            headerTitleBar = (TextView) hea.findViewById(R.id.headerTitleBar);
            headerTitleBar.setText(getResources().getString(R.string.airtel));
        } else {
            navigationView.inflateMenu(R.menu.drawer_view_bn); //inflate new items.
            View hea = navigationView.getHeaderView(0);
            headerTitleBar = (TextView) hea.findViewById(R.id.headerTitleBar);
            headerTitleBar.setText(getResources().getString(R.string.airtel_bn));
        }
        // END OF SETTING NAVIGATION MENU //
        if (navigationView != null) {
            navigationView.getMenu().getItem(3).setChecked(true);
            setupDrawerContent();
        }
        mDrawer_action.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(5);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //*************************** omor code *********************************/
        //*************************** End of omor code *********************************/
        // ********************* changing here......................


        viewPager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.e("ontest",":onPageScrolled:");
            }

            @Override
            public void onPageSelected(int position) {

                Log.e("ontest", ":onPageSelected:");
                if (position == 0 || position == 1) {
                    mNormalState.setVisibility(View.VISIBLE);
                    mTitleBar.setVisibility(View.VISIBLE);
                    mSearchItem.setText("");
                    // for prepaid
                    if (position == 0) {
                        mSearchAction.setImageResource(R.drawable.search_96);
                        mSearchState.setVisibility(View.GONE);
                        mSearchActionL.setVisibility(View.VISIBLE);
                        if (AppManager.mAirtelPrepaidDataPackList.size() == 18) {
                            mSearchAction.setImageResource(R.drawable.search_96);
                        } else {
                            mSearchAction.setImageResource(R.drawable.refresh_96);
//                            AppManager.getInstance(mContext).retrieveAirtelPrepaidDataPackList(mContext, "");
//                            AirtelPrepaidAdapter gpAdapter = new AirtelPrepaidAdapter(mContext, AppManager.mAirtelPrepaidDataPackList, false);
//                            AirtelPrepaidFragment.recyclerView.setAdapter(gpAdapter);
                        }

                        // set load first time
                        AppsSettings.getAppsSettings(mContext).setAirtelPrepaidLoad(true);
                        // Enter key press detect //
                        mSearchItem.setOnKeyListener(new OnKeyListener() {
                            public boolean onKey(View v, int keyCode, KeyEvent event) {
                                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                                    switch (keyCode) {
                                        case KeyEvent.KEYCODE_DPAD_CENTER:
                                        case KeyEvent.KEYCODE_ENTER:
                                            mSearchItemValue = mSearchItem.getText().toString();
                                            if (mSearchItemValue.equals("") || mSearchItemValue.trim().length() <= 1) {
                                                if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
                                                {
                                                    Toast.makeText(
                                                            mContext,
                                                            ""+getResources().getString(R.string.min_press_2_chars),
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                                else
                                                {
                                                    Toast.makeText(
                                                            mContext,
                                                            ""+getResources().getString(R.string.min_press_2_chars_bn),
                                                            Toast.LENGTH_SHORT).show();
                                                }

                                            } else {
                                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                                                AppManager.getInstance(mContext).retrieveAirtelPrepaidDataPackList(mContext, mSearchItemValue);
                                                AirtelPrepaidAdapter gpAdapter = new AirtelPrepaidAdapter(mContext, AppManager.mAirtelPrepaidDataPackList, true);
                                                AirtelPrepaidFragment.recyclerView.setAdapter(gpAdapter);

                                                // start change //
                                                mNormalState.setVisibility(View.VISIBLE);
                                                mTitleBar.setVisibility(View.VISIBLE);
                                                mSearchState.setVisibility(View.GONE);
                                                mSearchItem.setText("");
                                                mSearchAction.setImageResource(R.drawable.refresh_96);
                                                // end of start change //
                                            }
                                            return true;
                                        default:
                                            break;
                                    }
                                }
                                return false;
                            }
                        });
                        // End of Enter key press detect //
                        mSearchAction.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (mSearchItem.isShown()) {
                                    mSearchItemValue = mSearchItem.getText().toString();
                                    if (mSearchItemValue.equals("") || mSearchItemValue.trim().length() <= 1) {

                                        if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
                                        {
                                            Toast.makeText(
                                                    mContext,
                                                    ""+getResources().getString(R.string.min_press_2_chars),
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(
                                                    mContext,
                                                    ""+getResources().getString(R.string.min_press_2_chars_bn),
                                                    Toast.LENGTH_SHORT).show();
                                        }

                                    } else {
                                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                        AppManager.getInstance(mContext).retrieveAirtelPrepaidDataPackList(mContext, mSearchItemValue);
                                        AirtelPrepaidAdapter gpAdapter = new AirtelPrepaidAdapter(mContext, AppManager.mAirtelPrepaidDataPackList, true);
                                        AirtelPrepaidFragment.recyclerView.setAdapter(gpAdapter);

                                        // start change //
                                        mNormalState.setVisibility(View.VISIBLE);
                                        mTitleBar.setVisibility(View.VISIBLE);
                                        mSearchState.setVisibility(View.GONE);
                                        mSearchItem.setText("");
                                        mSearchAction.setImageResource(R.drawable.refresh_96);
                                        // end of start change //
                                    }

                                } else {
                                    mSearchItem.setText("");
                                    if (AppManager.mAirtelPrepaidDataPackList.size() == 18) {

                                    } else {
                                        AppManager.getInstance(mContext).retrieveAirtelPrepaidDataPackList(mContext, "");
                                        AirtelPrepaidAdapter gpAdapter1 = new AirtelPrepaidAdapter(mContext, AppManager.mAirtelPrepaidDataPackList, true);
                                        AirtelPrepaidFragment.recyclerView.setAdapter(gpAdapter1);
                                    }
                                    if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
                                    {
                                        mSearchItem.setHint(getResources().getString(R.string.edittext_hint));
                                    }
                                    else
                                    {
                                        mSearchItem.setHint(getResources().getString(R.string.edittext_hint_bn));
                                    }
                                    mNormalState.setVisibility(View.GONE);
                                    mSearchState.setVisibility(View.VISIBLE);
                                    mSearchAction.setImageResource(R.drawable.search_96);
                                }
                            }

                        });

                    } else if (position == 1) {
                        mSearchAction.setImageResource(R.drawable.search_96);
                        mSearchState.setVisibility(View.GONE);
                        mSearchActionL.setVisibility(View.VISIBLE);
                        if (AppManager.mAirtelPostpaidDataPackList.size() == 18) {
                            mSearchAction.setImageResource(R.drawable.search_96);
                        } else {
                            mSearchAction.setImageResource(R.drawable.refresh_96);
//                            AppManager.getInstance(mContext).retrieveAirtelPostDataPackList(mContext, "");
//                            AirtelPostpaidAdapter gpAdapter = new AirtelPostpaidAdapter(mContext, AppManager.mAirtelPostpaidDataPackList, false);
//                            AirtelPostpaidFragment.recyclerView.setAdapter(gpAdapter);
                        }


                        // set load first time
                        AppsSettings.getAppsSettings(mContext).setAirtelPostpaidLoad(true);
                        // Enter key press detect //
                        mSearchItem.setOnKeyListener(new OnKeyListener() {
                            public boolean onKey(View v, int keyCode, KeyEvent event) {
                                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                                    switch (keyCode) {
                                        case KeyEvent.KEYCODE_DPAD_CENTER:
                                        case KeyEvent.KEYCODE_ENTER:
                                            mSearchItemValue = mSearchItem.getText().toString();
                                            if (mSearchItemValue.equals("") || mSearchItemValue.trim().length() <= 1) {

                                                if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
                                                {
                                                    Toast.makeText(
                                                            mContext,
                                                            ""+getResources().getString(R.string.min_press_2_chars),
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                                else
                                                {
                                                    Toast.makeText(
                                                            mContext,
                                                            ""+getResources().getString(R.string.min_press_2_chars_bn),
                                                            Toast.LENGTH_SHORT).show();
                                                }

                                            } else {
                                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                                                AppManager.getInstance(mContext).retrieveAirtelPostDataPackList(mContext, mSearchItemValue);
                                                AirtelPostpaidAdapter gpAdapter = new AirtelPostpaidAdapter(mContext, AppManager.mAirtelPostpaidDataPackList, true);
                                                AirtelPostpaidFragment.recyclerView.setAdapter(gpAdapter);

                                                // start change //
                                                mNormalState.setVisibility(View.VISIBLE);
                                                mTitleBar.setVisibility(View.VISIBLE);
                                                mSearchState.setVisibility(View.GONE);
                                                mSearchItem.setText("");
                                                mSearchAction.setImageResource(R.drawable.refresh_96);
                                                // end of start change //
                                            }
                                            return true;
                                        default:
                                            break;
                                    }
                                }
                                return false;
                            }
                        });
                        // End of Enter key press detect //
                        mSearchAction.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (mSearchItem.isShown()) {
                                    mSearchItemValue = mSearchItem.getText().toString();
                                    if (mSearchItemValue.equals("") || mSearchItemValue.trim().length() <= 1) {

                                        if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
                                        {
                                            Toast.makeText(
                                                    mContext,
                                                    ""+getResources().getString(R.string.min_press_2_chars),
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(
                                                    mContext,
                                                    ""+getResources().getString(R.string.min_press_2_chars_bn),
                                                    Toast.LENGTH_SHORT).show();
                                        }

                                    } else {
                                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                        AppManager.getInstance(mContext).retrieveAirtelPostDataPackList(mContext, mSearchItemValue);
                                        AirtelPostpaidAdapter gpAdapter = new AirtelPostpaidAdapter(mContext, AppManager.mAirtelPostpaidDataPackList, true);
                                        AirtelPostpaidFragment.recyclerView.setAdapter(gpAdapter);

                                        // start change //
                                        mNormalState.setVisibility(View.VISIBLE);
                                        mTitleBar.setVisibility(View.VISIBLE);
                                        mSearchState.setVisibility(View.GONE);
                                        mSearchItem.setText("");
                                        mSearchAction.setImageResource(R.drawable.refresh_96);
                                        // end of start change //
                                    }

                                } else {
                                    mSearchItem.setText("");
                                    if (AppManager.mAirtelPostpaidDataPackList.size() == 18) {

                                    } else {
                                        AppManager.getInstance(mContext).retrieveAirtelPostDataPackList(mContext, "");
                                        AirtelPostpaidAdapter gpAdapter = new AirtelPostpaidAdapter(mContext, AppManager.mAirtelPostpaidDataPackList, true);
                                        AirtelPostpaidFragment.recyclerView.setAdapter(gpAdapter);
                                    }

                                    if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
                                    {
                                        mSearchItem.setHint(getResources().getString(R.string.edittext_hint));
                                    }
                                    else
                                    {
                                        mSearchItem.setHint(getResources().getString(R.string.edittext_hint_bn));
                                    }
                                    mNormalState.setVisibility(View.GONE);
                                    mSearchState.setVisibility(View.VISIBLE);
                                    mSearchAction.setImageResource(R.drawable.search_96);
                                }
                            }

                        });
                    } else {
                        mSearchAction.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mNormalState.setVisibility(View.GONE);
                                mSearchState.setVisibility(View.VISIBLE);
                                //    Toast.makeText(mContext, ":pre:mSearchAction", Toast.LENGTH_SHORT).show();

                            }
                        });
                        mSearchActionL.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mNormalState.setVisibility(View.GONE);
                                mSearchState.setVisibility(View.VISIBLE);
                                //  Toast.makeText(mContext, ":pre:mSearchActionL", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                } else {
                    KeyBoard.hideKeyBoard(mActivity);
                    mSearchActionL.setVisibility(View.INVISIBLE);
                    // Toast.makeText(mContext, ":dfsf:" + position, Toast.LENGTH_SHORT).show();
                    mSearchState.setVisibility(View.GONE);
                    mNormalState.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    }


    @Override
    protected void onResume() {
        super.onResume();
        // END OF SETTING NAVIGATION MENU //
        if (navigationView != null) {
            navigationView.getMenu().getItem(3).setChecked(true);
            setupDrawerContent();
        }
        KeyBoard.hideKeyBoard(mActivity);
    }


    private void setupViewPager(ViewPager viewPager) {
        if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng")) {
            Adapter adapter = new Adapter(getSupportFragmentManager());
            adapter.addFragment(new AirtelPrepaidFragment(), "" + getResources().getString(R.string.prepaid));
            adapter.addFragment(new AirtelPostpaidFragment(), "" + getResources().getString(R.string.postpaid));
            adapter.addFragment(new AirtelMigrationFragment(), "" + getResources().getString(R.string.package_introduce));
            adapter.addFragment(new AirtelMiscFragment(), "" + getResources().getString(R.string.miscellaneous));
            adapter.addFragment(new AirtelFnFFragment(), "" + getResources().getString(R.string.fnf_service));
//        viewPager.setOffscreenPageLimit(5);
            viewPager.setAdapter(adapter);
        }
        else
        {
            Adapter adapter = new Adapter(getSupportFragmentManager());
            adapter.addFragment(new AirtelPrepaidFragment(), "" + getResources().getString(R.string.prepaid_bn));
            adapter.addFragment(new AirtelPostpaidFragment(), "" + getResources().getString(R.string.postpaid_bn));
            adapter.addFragment(new AirtelMigrationFragment(), "" + getResources().getString(R.string.package_introduce_bn));
            adapter.addFragment(new AirtelMiscFragment(), "" + getResources().getString(R.string.miscellaneous_bn));
            adapter.addFragment(new AirtelFnFFragment(), "" + getResources().getString(R.string.fnf_service_bn));
//        viewPager.setOffscreenPageLimit(5);
            viewPager.setAdapter(adapter);
        }
    }

    private void setupDrawerContent() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //0Closing drawer on item click
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

                    // For rest of the options we just show a toast on click

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
    private void convertEnglish() {
        mTitleBar.setText("" + getResources().getString(R.string.airtel));
    }

    private void convertBangla() {
        mTitleBar.setText("" + getResources().getString(R.string.airtel_bn));
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
        closeAlert();
        // super.onBackPressed();
    }
    public void quit() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }
    private void closeAlert() {
        new AlertDialog.Builder(mContext)
                .setTitle("Exit ")
                .setMessage("Are you sure to close?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        quit();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .show();
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
