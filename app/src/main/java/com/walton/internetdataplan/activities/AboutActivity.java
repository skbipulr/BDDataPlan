package com.walton.internetdataplan.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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
import com.walton.internetdataplan.R;
import com.walton.internetdataplan.utitls.AppsSettings;

/**
 * Created by Faruq on 1/1/2017.
 */

public class AboutActivity extends AppCompatActivity {
    public InterstitialAd interstitialAd;
    public AdView adView;
    public RelativeLayout adViewContainer;
    public Context mContext;
    public ImageView backToHome;
    public TextView titleBar, moreApps, walton, mVersionName,txtAppsName,txtApplicationDescription,txtPoweredBy,txtcopyRight;
    public Button closeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);

        mContext = this;
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
              //  Log.e("facebook_ads","about:interstial ads:error:"+adError.getErrorMessage());
                //Toast.makeText(mContext,"about:interstial ads:error:"+adError.getErrorMessage(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoaded(Ad ad) {
               //  Toast.makeText(mContext, "onAdLoaded", Toast.LENGTH_SHORT).show();
              //  Log.e("facebook_ads",":interstial ads:onAdLoaded:"+ad.getPlacementId());
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

        adView = new AdView(mContext, "1453443504734328_1463949757017036", AdSize.BANNER_HEIGHT_50);
        adViewContainer = (RelativeLayout) findViewById(R.id.adViewContainer);
        adViewContainer.addView(adView);
//        AdSettings.addTestDevice("HASHED ID");
        adView.setAdListener(new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                Log.e("facebook_ads","about:banner ads:error:"+adError.getErrorMessage());
                // Toast.makeText(mContext,":banner ads:error:"+adError.getErrorMessage(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                Log.e("facebook_ads","::onAdLoaded:"+ad.getPlacementId());
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
        //
        titleBar=(TextView)findViewById(R.id.titleBar);
        txtApplicationDescription=(TextView)findViewById(R.id.txtApplicationDescription);
        txtAppsName=(TextView)findViewById(R.id.txtAppsName);
        mVersionName=(TextView)findViewById(R.id.versionName);
//        txtPoweredBy=(TextView)findViewById(R.id.txtPoweredBy);
        txtcopyRight=(TextView)findViewById(R.id.txtcopyRight);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
//            Drawable background = this.getResources().getDrawable(R.drawable.background);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.gp_button_color_default));
            window.setNavigationBarColor(getResources().getColor(R.color.gp_button_color_default));
//            window.setBackgroundDrawable(background);
        }


        backToHome = (ImageView) findViewById(R.id.backToHome);
        backToHome.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        // moreApps
        moreApps = (TextView) findViewById(R.id.moreApps);
        moreApps.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/developer?id=Walton+Digi-Tech+Industries+Limited")));
                finish();
            }
        });

        // about walton
        walton = (TextView) findViewById(R.id.walton);

        walton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri
                        .parse("http://waltonbd.com/")));
                finish();
            }
        });

        closeBtn = (Button) this.findViewById(R.id.back);
        closeBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
//				 finish();
                onBackPressed();
            }

        });

        if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
        {
            convertEnglish();
        }
        else
        {
            convertBangla();
        }

    }
    private void convertEnglish() {
        closeBtn.setText(getResources().getString(R.string.backButton));
        titleBar.setText(getResources().getString(R.string.aboutT));
        moreApps.setText(getResources().getString(R.string.more_apps_from_walton));
        walton.setText(getResources().getString(R.string.about_walton));
        mVersionName.setText(getResources().getString(R.string.versionName));
        txtAppsName.setText(getResources().getString(R.string.app_name));
        txtApplicationDescription.setText(getResources().getString(R.string.applicationDescription));
//        txtPoweredBy.setText(getResources().getString(R.string.poweredBy));
        txtcopyRight.setText(getResources().getString(R.string.copyRight));
    }
    private void convertBangla() {
        closeBtn.setText(getResources().getString(R.string.backButton_bn));
        titleBar.setText(getResources().getString(R.string.aboutT_bn));
        moreApps.setText(getResources().getString(R.string.more_apps_from_walton_bn));
        walton.setText(getResources().getString(R.string.about_walton_bn));
        mVersionName.setText(getResources().getString(R.string.versionName_bn));
        txtAppsName.setText(getResources().getString(R.string.app_name_bn));
        txtApplicationDescription.setText(getResources().getString(R.string.applicationDescription_bd));
//        txtPoweredBy.setText(getResources().getString(R.string.poweredBy_bn));
        txtcopyRight.setText(getResources().getString(R.string.copyRight_bn));
    }

    @Override
    public void onBackPressed() {
        if (interstitialAd.isAdLoaded()) {
            interstitialAd.show();
        }
     //   closeAlert();
         super.onBackPressed();
    }
    public void quit() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }
//    private void closeAlert() {
//        new AlertDialog.Builder(mContext)
//                .setTitle("Exit ")
//                .setMessage("Are you sure to close?")
//                .setCancelable(false)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        quit();
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        finish();
//                    }
//                })
//                .show();
//    }


    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        if (interstitialAd != null) {
            interstitialAd.destroy();
        }
        super.onDestroy();
    }

}
