package com.walton.internetdataplan.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.walton.internetdataplan.R;
import com.walton.internetdataplan.utitls.AppsSettings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faruq on 1/1/2017.
 */

public class SettingsActivity extends AppCompatActivity {
    public static Context mmContext;
    public static NativeAd _nativeAd;
    public static LinearLayout _nativeAdContainer;
    public static LinearLayout _adView;
    public InterstitialAd interstitialAd;
    public AdView adView;
    public RelativeLayout adViewContainer;
    // native ads //
    public NativeAd nativeAd;
    public LinearLayout nativeAdContainer;
    public LinearLayout aadView;
    public Context mContext;
    public ImageView backToHome;
    public Button closeBtn;
    public RadioGroup setting_language_radio_group;
    public TextView settings_titleBar, txtSelectLanguageTxt;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        mContext = this;
        mmContext = this;
        sshowNativeAd();
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

        adView = new AdView(mContext, "1453443504734328_1463949117017100", AdSize.BANNER_HEIGHT_50);
        adViewContainer.addView(adView);
        adView.loadAd();
// end of add facebook benner ads //
        settings_titleBar = (TextView) findViewById(R.id.settings_titleBar);
        txtSelectLanguageTxt = (TextView) findViewById(R.id.txtSelectLanguageTxt);
        closeBtn = (Button) this.findViewById(R.id.back);
        closeBtn.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
//				 finish();
                onBackPressed();
            }

        });
        setting_language_radio_group = (RadioGroup) findViewById(R.id.setting_language_radio_group);
        RadioButton english = (RadioButton) findViewById(R.id.setting_language_radio_english);
        RadioButton bangla = (RadioButton) findViewById(R.id.setting_language_radio_bangla);
        if (AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng")) {
            english.setChecked(true);
            convertEnglish();
        } else {
            bangla.setChecked(true);
            convertBangla();
        }


        setting_language_radio_group.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.setting_language_radio_english) {
                    AppsSettings.getAppsSettings(mContext).setLanguage("eng");
                    convertEnglish();
//                    finish();
//                    startActivity(getIntent());
                } else if (checkedId == R.id.setting_language_radio_bangla) {
                    AppsSettings.getAppsSettings(mContext).setLanguage("bn");
                    convertBangla();
//                    finish();
//                    startActivity(getIntent());
                } else {
                }
            }

        });
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


    }

//    @Override
//    public void onBackPressed() {
//        if (interstitialAd.isAdLoaded()) {
//            interstitialAd.show();
//        }
//        super.onBackPressed();
//        Intent i = new Intent(mContext, MainActivity.class);
//        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(i);
//    }


    @Override
    public void onBackPressed() {
//        if (interstitialAd.isAdLoaded()) {
//            interstitialAd.show();
//        }
        super.onBackPressed();
        Intent i = new Intent(mContext, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    private void convertEnglish() {
        settings_titleBar.setText(getResources().getString(R.string.settings_title));
        txtSelectLanguageTxt.setText(getResources().getString(R.string.select_language));
        closeBtn.setText(getResources().getString(R.string.backButton));
    }

    private void convertBangla() {
        settings_titleBar.setText(getResources().getString(R.string.settings_title_bn));
        txtSelectLanguageTxt.setText(getResources().getString(R.string.select_language_bn));
        closeBtn.setText(getResources().getString(R.string.backButton_bn));
    }

    private void sshowNativeAd() {
        _nativeAd = new NativeAd(mmContext, "1453443504734328_1469353243143354");
        _nativeAd.setAdListener(new AdListener() {

            @Override
            public void onError(Ad ad, AdError error) {
                // Ad error callback
                Log.e("onError", "onError::" + error.toString());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Add the Ad view into the ad container.
                _nativeAdContainer = (LinearLayout) findViewById(R.id.native_ad_container1);
                LayoutInflater inflater = LayoutInflater.from(mmContext);
                _adView = (LinearLayout) inflater.inflate(R.layout.native_ad_layout, _nativeAdContainer, false);
                _nativeAdContainer.addView(_adView);

                // Create native UI using the ad metadata.
                ImageView nativeAdIcon = (ImageView) _adView.findViewById(R.id.native_ad_icon1);
                TextView nativeAdTitle = (TextView) _adView.findViewById(R.id.native_ad_title1);
                MediaView nativeAdMedia = (MediaView) _adView.findViewById(R.id.native_ad_media1);
                TextView nativeAdSocialContext = (TextView) _adView.findViewById(R.id.native_ad_social_context1);
                TextView nativeAdBody = (TextView) _adView.findViewById(R.id.native_ad_body1);
                Button nativeAdCallToAction = (Button) _adView.findViewById(R.id.native_ad_call_to_action1);

                // Set the Text.
                nativeAdTitle.setText(_nativeAd.getAdTitle());
                nativeAdSocialContext.setText(_nativeAd.getAdSocialContext());
                nativeAdBody.setText(_nativeAd.getAdBody());
                nativeAdCallToAction.setText(_nativeAd.getAdCallToAction());

                // Download and display the ad icon.
                NativeAd.Image adIcon = _nativeAd.getAdIcon();
                NativeAd.downloadAndDisplayImage(adIcon, nativeAdIcon);

                // Download and display the cover image.
                nativeAdMedia.setNativeAd(_nativeAd);

                // Add the AdChoices icon
                LinearLayout adChoicesContainer = (LinearLayout) findViewById(R.id.ad_choices_container1);
                AdChoicesView adChoicesView = new AdChoicesView(mmContext, _nativeAd, true);
                adChoicesContainer.addView(adChoicesView);

                // Register the Title and CTA button to listen for clicks.
                List<View> clickableViews = new ArrayList<>();
                clickableViews.add(nativeAdTitle);
                clickableViews.add(nativeAdCallToAction);
                _nativeAd.registerViewForInteraction(_nativeAdContainer, clickableViews);
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });

        // Request an ad
        _nativeAd.loadAd();
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
