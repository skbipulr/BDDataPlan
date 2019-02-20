package com.walton.internetdataplan.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.walton.internetdataplan.R;
import com.walton.internetdataplan.utitls.AppsSettings;
import com.walton.internetdataplan.utitls.WHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faruq on 10/25/2016.
 */

/**
 * Created by Faruq on 10/25/2016.
 */
public class AirtelFnFServiceAdapter extends RecyclerView.Adapter<AirtelFnFServiceAdapter.AirtelViewHolder> {
    public Context mContext;
    public static Context mmContext;
    public static NativeAd nativeAd;
    public static LinearLayout nativeAdContainer;
    private static LinearLayout adView;

    public static NativeAd _nativeAd;
    public static LinearLayout _nativeAdContainer;
    public static LinearLayout _adView;

    public AirtelFnFServiceAdapter(Context context) {
        mContext = context;
        mmContext=context;

    }

    @Override
    public AirtelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.airtel_fnf_layout, parent, false);
        AirtelViewHolder mViewHolder = new AirtelViewHolder(view);
        return mViewHolder;
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public void onBindViewHolder(final AirtelViewHolder holder, int position) {

        holder.btn_fnf_list.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                WHelper.getInstance(mContext).DialingCode("*121*4*3#");
            }
        });
        holder.gp_btn_add_fnf.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                WHelper.getInstance(mContext).DialingCode("*121*4*1#");
            }
        });
        holder.btn_remove_fnf.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                WHelper.getInstance(mContext).DialingCode("*121*4*2#");
            }
        });
        holder.btn_fnf_service_menu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                WHelper.getInstance(mContext).DialingCode("*121*4#");
            }
        });
        if (AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng")) {
            convertEnglish(holder);
        } else {
            convertBangla(holder);
        }

    }

    private void convertEnglish(AirtelViewHolder holder) {
        holder.fnf_list.setText(""+mContext.getResources().getString(R.string.fnf_list));
        holder.add_fnf.setText(""+mContext.getResources().getString(R.string.add_fnf));
        holder.delete_fnf.setText(""+mContext.getResources().getString(R.string.delete_fnf));
        holder.fnf_service.setText(""+mContext.getResources().getString(R.string.fnf_service_menu));
        // buttons
        holder.btn_fnf_list.setText(""+mContext.getResources().getString(R.string.btn_check));
        holder.gp_btn_add_fnf.setText(""+mContext.getResources().getString(R.string.btn_add));
        holder.btn_remove_fnf.setText(""+mContext.getResources().getString(R.string.btn_remove));
        holder.btn_fnf_service_menu.setText(""+mContext.getResources().getString(R.string.menu));
    }

    private void convertBangla(AirtelViewHolder holder) {
        holder.fnf_list.setText(""+mContext.getResources().getString(R.string.fnf_list_bn));
        holder.add_fnf.setText(""+mContext.getResources().getString(R.string.add_fnf_bn));
        holder.delete_fnf.setText(""+mContext.getResources().getString(R.string.delete_fnf_bn));
        holder.fnf_service.setText(""+mContext.getResources().getString(R.string.fnf_service_menu_bn));
        // buttons
        holder.btn_fnf_list.setText(""+mContext.getResources().getString(R.string.btn_check_bn));
        holder.gp_btn_add_fnf.setText(""+mContext.getResources().getString(R.string.btn_add_bn));
        holder.btn_remove_fnf.setText(""+mContext.getResources().getString(R.string.btn_remove_bn));
        holder.btn_fnf_service_menu.setText(""+mContext.getResources().getString(R.string.menu_bn));
    }


    public static class AirtelViewHolder extends RecyclerView.ViewHolder {
        public CardView cv;
        public Button btn_fnf_list, gp_btn_add_fnf, btn_remove_fnf, btn_fnf_service_menu;
        public TextView fnf_list, add_fnf, delete_fnf, fnf_service;

        public AirtelViewHolder(View itemView) {
            super(itemView);
            showNativeAd(itemView);
            sshowNativeAd(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            fnf_list = (TextView) itemView.findViewById(R.id.fnf_list);
            add_fnf = (TextView) itemView.findViewById(R.id.add_fnf);
            delete_fnf = (TextView) itemView.findViewById(R.id.delete_fnf);
            fnf_service = (TextView) itemView.findViewById(R.id.fnf_service);
            btn_fnf_list = (Button) itemView.findViewById(R.id.btn_fnf_list);
            gp_btn_add_fnf = (Button) itemView.findViewById(R.id.gp_btn_add_fnf);
            btn_remove_fnf = (Button) itemView.findViewById(R.id.btn_remove_fnf);
            btn_fnf_service_menu = (Button) itemView.findViewById(R.id.btn_fnf_service_menu);

        }
    }
    private static void showNativeAd(final View itemView) {
        nativeAd = new NativeAd(mmContext, "1453443504734328_1469350829810262");
        nativeAd.setAdListener(new AdListener() {

            @Override
            public void onError(Ad ad, AdError error) {
                // Ad error callback
                Log.e("onError","onError::"+error.toString());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Add the Ad view into the ad container.
                nativeAdContainer = (LinearLayout) itemView.findViewById(R.id.native_ad_container);
                LayoutInflater inflater = LayoutInflater.from(mmContext);
                adView = (LinearLayout) inflater.inflate(R.layout.robi_native_ad_layout, nativeAdContainer, false);
                nativeAdContainer.addView(adView);

                // Create native UI using the ad metadata.
                ImageView nativeAdIcon = (ImageView) adView.findViewById(R.id.native_ad_icon);
                TextView nativeAdTitle = (TextView) adView.findViewById(R.id.native_ad_title);
                // MediaView nativeAdMedia = (MediaView) adView.findViewById(R.id.native_ad_media);
                //  TextView nativeAdSocialContext = (TextView) adView.findViewById(R.id.native_ad_social_context);
                // TextView nativeAdBody = (TextView) adView.findViewById(R.id.native_ad_body);
                Button nativeAdCallToAction = (Button) adView.findViewById(R.id.native_ad_call_to_action);

                // Set the Text.
                nativeAdTitle.setText(nativeAd.getAdTitle());
                // nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
                //  nativeAdBody.setText(nativeAd.getAdBody());
                nativeAdCallToAction.setText(nativeAd.getAdCallToAction());

                // Download and display the ad icon.
                NativeAd.Image adIcon = nativeAd.getAdIcon();
                NativeAd.downloadAndDisplayImage(adIcon, nativeAdIcon);

                // Download and display the cover image.
                //   nativeAdMedia.setNativeAd(nativeAd);

                // Add the AdChoices icon
                LinearLayout adChoicesContainer = (LinearLayout) itemView.findViewById(R.id.ad_choices_container);
                AdChoicesView adChoicesView = new AdChoicesView(mmContext, nativeAd, true);
                adChoicesContainer.addView(adChoicesView);

                // Register the Title and CTA button to listen for clicks.
                List<View> clickableViews = new ArrayList<>();
                clickableViews.add(nativeAdTitle);
                clickableViews.add(nativeAdCallToAction);
                nativeAd.registerViewForInteraction(nativeAdContainer, clickableViews);
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
        nativeAd.loadAd();
    }

    private static void sshowNativeAd(final View itemView) {
        _nativeAd = new NativeAd(mmContext, "1453443504734328_1469350829810262");
        _nativeAd.setAdListener(new AdListener() {

            @Override
            public void onError(Ad ad, AdError error) {
                // Ad error callback
                Log.e("onError","onError::"+error.toString());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Add the Ad view into the ad container.
                _nativeAdContainer = (LinearLayout) itemView.findViewById(R.id.native_ad_container1);
                LayoutInflater inflater = LayoutInflater.from(mmContext);
                _adView = (LinearLayout) inflater.inflate(R.layout.shhow_airtel_native_ad_layout, _nativeAdContainer, false);
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
                LinearLayout adChoicesContainer = (LinearLayout) itemView.findViewById(R.id.ad_choices_container1);
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



}


