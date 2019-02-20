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
import com.walton.internetdataplan.models.Migration;
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
public class RobiMigrationAdapter extends RecyclerView.Adapter<RobiMigrationAdapter.GPMigrationViewHolder> {
    private ArrayList<Migration> mList;
    public Context mContext;
    public static boolean mGPProSearch=false;

    public static boolean isShift=false;
    public static Context mmContext;
    public static NativeAd _nativeAd;
    public static LinearLayout _nativeAdContainer;
    public static LinearLayout _adView;

    public RobiMigrationAdapter(Context context, ArrayList<Migration> list, boolean mStatus) {
        mList = list;
        mContext = context;
        mGPProSearch=mStatus;
        mmContext=context;
        isShift=true;

    }

    @Override
    public GPMigrationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.robi_migration_layout, parent, false);
        GPMigrationViewHolder gpViewHolder = new GPMigrationViewHolder(view);
        return gpViewHolder;
    }
    @Override
    public void onBindViewHolder(final GPMigrationViewHolder holder, int position) {
        if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
        {
            convertEnglish(holder);
        }
        else
        {
            convertBangla(holder);
        }
        final Migration migration=mList.get(position);
        holder.packageName.setText(""+migration.getOpPacakge());
        holder.packageName.setTextSize(12);
        holder.gptogp.setText(""+migration.getDetailsGpToGp());
        holder.gptoOthers.setText(""+migration.getDetailsGpToOthers());
        holder.fnfService.setText(""+migration.getDetailsFnF());
        holder.pulse.setText(""+migration.getDetailsPulse());
        holder.sms.setText(""+migration.getDetailsSMS());
        holder.operatorType.setText(""+migration.getOperatorType());
        holder.migrateT.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (migration.getProcessType().equals("dial")) {
                    WHelper.getInstance(mContext).DialingCode(migration.getDialCode());
                } else {
                    WHelper.showMsgDialogForTeletalk(mContext, "" + migration.getOpPacakge(), migration.getSmsBody(), migration.getSmsCode());
                }
            }
        });
    }

    private void convertBangla(GPMigrationViewHolder holder) {
        holder.package_type.setText(""+mContext.getResources().getString(R.string.package_type_bn));
        holder.robi_to_robi.setText(""+mContext.getResources().getString(R.string.robi_to_robi_bn));
        holder.robi_to_others.setText(""+mContext.getResources().getString(R.string.robi_to_others_bn));
        holder.fnf_service.setText(""+mContext.getResources().getString(R.string.fnf_service_bn));
        holder.pulseT.setText(""+mContext.getResources().getString(R.string.pulse_bn));
        holder.smsT.setText(""+mContext.getResources().getString(R.string.sms_bn));
        holder.migrate.setText(""+mContext.getResources().getString(R.string.migrate_bn));
    }

    private void convertEnglish(GPMigrationViewHolder holder) {
        holder.package_type.setText(""+mContext.getResources().getString(R.string.package_type));
        holder.robi_to_robi.setText(""+mContext.getResources().getString(R.string.robi_to_robi));
        holder.robi_to_others.setText(""+mContext.getResources().getString(R.string.robi_to_others));
        holder.fnf_service.setText(""+mContext.getResources().getString(R.string.fnf_service));
        holder.pulseT.setText(""+mContext.getResources().getString(R.string.pulse));
        holder.smsT.setText(""+mContext.getResources().getString(R.string.sms));
        holder.migrate.setText(""+mContext.getResources().getString(R.string.migrate));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public static class GPMigrationViewHolder extends RecyclerView.ViewHolder {
        public ImageView migrateT;
        public TextView gptogp;
        public TextView gptoOthers;
        public TextView fnfService;
        public TextView pulse;
        public TextView sms;
        public TextView packageName;
        public TextView operatorType;
        public TextView package_type,robi_to_robi,robi_to_others,fnf_service,pulseT,smsT,migrate;

        public GPMigrationViewHolder(View itemView) {
            super(itemView);
            if(isShift) {
                try {
                    sshowNativeAd(itemView);
                    isShift=false;
                }
                catch (Exception e)
                {

                }

            }
            migrateT = (ImageView) itemView.findViewById(R.id.migrateT);
            gptogp = (TextView) itemView.findViewById(R.id.gptogp);
            gptoOthers = (TextView) itemView.findViewById(R.id.gptoOthers);
            fnfService = (TextView) itemView.findViewById(R.id.fnfService);
            pulse = (TextView) itemView.findViewById(R.id.pulse);
            sms = (TextView) itemView.findViewById(R.id.sms);
            packageName = (TextView) itemView.findViewById(R.id.packageName);
            operatorType = (TextView) itemView.findViewById(R.id.operatorType);
            //
            package_type = (TextView) itemView.findViewById(R.id.package_type);
            robi_to_robi = (TextView) itemView.findViewById(R.id.robi_to_robi);
            robi_to_others = (TextView) itemView.findViewById(R.id.robi_to_others);
            fnf_service = (TextView) itemView.findViewById(R.id.fnf_service);
            pulseT = (TextView) itemView.findViewById(R.id.pulseT);
            smsT = (TextView) itemView.findViewById(R.id.smsT);
            migrate = (TextView) itemView.findViewById(R.id.migrate);
        }
    }
    private static void sshowNativeAd(final View itemView) {
        _nativeAd = new NativeAd(mmContext, "1453443504734328_1469349873143691");
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
                _adView = (LinearLayout) inflater.inflate(R.layout.shhow_robi_native_ad_layout, _nativeAdContainer, false);
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
