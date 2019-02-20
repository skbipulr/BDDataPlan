package com.walton.internetdataplan.adapters;

import android.content.Context;
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
import com.walton.internetdataplan.utitls.Call3rdPartyApps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faruq on 10/25/2016.
 */

/**
 * Created by Faruq on 10/25/2016.
 */
public class AirtelMigrationAdapter extends RecyclerView.Adapter<AirtelMigrationAdapter.AirtelMigrationViewHolder> {
    public static Context mmContext;
    public static NativeAd _nativeAd;
    public static LinearLayout _nativeAdContainer;
    public static LinearLayout _adView;
    public Context mContext;
    public static boolean mGPProSearch=false;

    public AirtelMigrationAdapter(Context context, boolean mStatus) {
        mContext = context;
        mmContext = context;
        mGPProSearch=mStatus;

    }

    @Override
    public AirtelMigrationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.airtel_package_introduce_layout, parent, false);
        AirtelMigrationViewHolder gpViewHolder = new AirtelMigrationViewHolder(view);
        return gpViewHolder;
    }
    @Override
    public void onBindViewHolder(final AirtelMigrationViewHolder holder, int position) {
        if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
        {
           convertEnglish(holder);
        }
        else
        {
            convertBangla(holder);
        }
        holder.official_web_link.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Call3rdPartyApps
                        .openBrowser(mContext,"http://www.bd.airtel.com/other-packages");
            }
        });

    }
    private void convertBangla(AirtelMigrationViewHolder holder) {
        holder.official_web_link.setText(""+mContext.getResources().getString(R.string.recharge_info_bn));
        holder.tvPackageGolpo.setText(""+mContext.getResources().getString(R.string.tvPackageGolpo_bn));
        holder.tvPackageAdda.setText(""+mContext.getResources().getString(R.string.tvPackageAdda_bn));
        holder.tvPackageSuperAdda.setText(""+mContext.getResources().getString(R.string.tvPackageSuperAdda_bn));
        holder.tvPackageDosti.setText(""+mContext.getResources().getString(R.string.tvPackageDosti_bn));
        holder.tvPackageHoichoi.setText(""+mContext.getResources().getString(R.string.tvPackageHoichoi_bn));
        holder.tvPackageGangtalk.setText(""+mContext.getResources().getString(R.string.tvPackageGangtalk_bn));
        holder.tvPackageDolbol.setText(""+mContext.getResources().getString(R.string.tvPackageDolbol_bn));
        holder.tvPackageFoorti.setText(""+mContext.getResources().getString(R.string.tvPackageFoorti_bn));
        holder.tvPackageKotha.setText(""+mContext.getResources().getString(R.string.tvPackageKotha_bn));
        holder.terms_and_conditions.setText(""+mContext.getResources().getString(R.string.terms_and_conditions_bn));
        holder.on_net_applies_for_airtel.setText(""+mContext.getResources().getString(R.string.on_net_applies_for_airtel_bn));
        holder.airtel_conditon_2.setText(""+mContext.getResources().getString(R.string.airtel_conditon_2_bn));
        holder.airtel_conditon_3.setText(""+mContext.getResources().getString(R.string.airtel_conditon_3_bn));
    }

    private void convertEnglish(AirtelMigrationViewHolder holder) {

        holder.check_on_web.setText(""+mContext.getResources().getString(R.string.check_on_web));
        holder.official_web_link.setText(""+mContext.getResources().getString(R.string.recharge_info));
        holder.tvPackageGolpo.setText(""+mContext.getResources().getString(R.string.tvPackageGolpo));
        holder.tvPackageAdda.setText(""+mContext.getResources().getString(R.string.tvPackageAdda));
        holder.tvPackageSuperAdda.setText(""+mContext.getResources().getString(R.string.tvPackageSuperAdda));
        holder.tvPackageDosti.setText(""+mContext.getResources().getString(R.string.tvPackageDosti));
        holder.tvPackageHoichoi.setText(""+mContext.getResources().getString(R.string.tvPackageHoichoi));
        holder.tvPackageGangtalk.setText(""+mContext.getResources().getString(R.string.tvPackageGangtalk));
        holder.tvPackageDolbol.setText(""+mContext.getResources().getString(R.string.tvPackageDolbol));
        holder.tvPackageFoorti.setText(""+mContext.getResources().getString(R.string.tvPackageFoorti));
        holder.tvPackageKotha.setText(""+mContext.getResources().getString(R.string.tvPackageKotha));
        holder.terms_and_conditions.setText(""+mContext.getResources().getString(R.string.terms_and_conditions));
        holder.on_net_applies_for_airtel.setText(""+mContext.getResources().getString(R.string.on_net_applies_for_airtel));
        holder.airtel_conditon_2.setText(""+mContext.getResources().getString(R.string.airtel_conditon_2));
        holder.airtel_conditon_3.setText(""+mContext.getResources().getString(R.string.airtel_conditon_3));

    }
    @Override
    public int getItemCount() {
        return 1;
    }
    public static class AirtelMigrationViewHolder extends RecyclerView.ViewHolder {
       public TextView official_web_link,check_on_web;
       public TextView terms_and_conditions,on_net_applies_for_airtel,airtel_conditon_2,airtel_conditon_3;
       public TextView tvPackageGolpo,tvPackageAdda,tvPackageSuperAdda,tvPackageDosti,tvPackageHoichoi,tvPackageGangtalk,tvPackageDolbol,tvPackageFoorti,tvPackageKotha;


        public AirtelMigrationViewHolder(View itemView) {
            super(itemView);
            sshowNativeAd(itemView);
            official_web_link = (TextView) itemView.findViewById(R.id.official_web_link);
            check_on_web = (TextView) itemView.findViewById(R.id.check_on_web);
            tvPackageGolpo = (TextView) itemView.findViewById(R.id.tvPackageGolpo);
            tvPackageAdda = (TextView) itemView.findViewById(R.id.tvPackageAdda);
            tvPackageSuperAdda = (TextView) itemView.findViewById(R.id.tvPackageSuperAdda);
            tvPackageDosti = (TextView) itemView.findViewById(R.id.tvPackageDosti);
            tvPackageHoichoi = (TextView) itemView.findViewById(R.id.tvPackageHoichoi);
            tvPackageGangtalk = (TextView) itemView.findViewById(R.id.tvPackageGangtalk);
            tvPackageDolbol = (TextView) itemView.findViewById(R.id.tvPackageDolbol);
            tvPackageFoorti = (TextView) itemView.findViewById(R.id.tvPackageFoorti);
            tvPackageKotha = (TextView) itemView.findViewById(R.id.tvPackageKotha);
            terms_and_conditions = (TextView) itemView.findViewById(R.id.terms_and_conditions);
            on_net_applies_for_airtel = (TextView) itemView.findViewById(R.id.on_net_applies_for_airtel);
            airtel_conditon_2 = (TextView) itemView.findViewById(R.id.airtel_conditon_2);
            airtel_conditon_3 = (TextView) itemView.findViewById(R.id.airtel_conditon_3);
        }
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
