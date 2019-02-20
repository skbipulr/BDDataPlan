package com.walton.internetdataplan.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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
public class GPMiscAdapter extends RecyclerView.Adapter<GPMiscAdapter.GPViewHolder> {
    public Context mContext;
    public static Context mmContext;
    public static NativeAd nativeAd;
    public static LinearLayout nativeAdContainer;
    private static LinearLayout adView;


    public static NativeAd _nativeAd;
    public static LinearLayout _nativeAdContainer;
    public static LinearLayout _adView;

    public GPMiscAdapter(Context context) {
        mContext = context;
        mmContext=context;

    }

    @Override
    public GPViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gp_miscellaneous_layout, parent, false);
        GPViewHolder mViewHolder = new GPViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final GPViewHolder holder, int position) {

        holder.btn_balance.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                WHelper.getInstance(mContext).DialingCode("*566#");
            }
        });
        holder.gp_btn_emergency_balance.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                WHelper.getInstance(mContext).DialingCode("*1010*1#");
            }
        });
        holder.btn_internet_balance_check.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                WHelper.getInstance(mContext).DialingCode("*121*1*4#");
            }
        });
        holder.btn_internet_balance_check_payg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                WHelper.getInstance(mContext).DialingCode("*567#");
            }
        });
        holder.gp_btn_your_no.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                WHelper.getInstance(mContext).DialingCode("*2#");
            }
        });

        holder.btn_call_block_start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                WHelper.getInstance(mContext).DialingCode("*111*1*1#");
            }
        });
        holder.btn_call_block_stop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                WHelper.getInstance(mContext).DialingCode("*111*1*4#");
            }
        });
        holder.btn_missed_call_alert_start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
                {
                    AlertDialog.Builder ab=new AlertDialog.Builder(mContext);
                    ab.setTitle("Missed Call Alert");
                    ab.setMessage("Missed Call Alert service provides the facility to the subscribers to get notified about the calls that they missed due to keeping the phone switched off or being out of network. Subscribers will be notified for Missed Call Alert through SMS. Do you want?");
                    ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
//                        WHelper.getInstance(mContext).createAlertin3("Missed Call Alert","START MCA","6222");
                            WHelper.getInstance(mContext).showMsgDialogForGP(mContext,"Missed Call Alert","START MCA","6222");
                        }
                    });
                    ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    ab.setCancelable(true);
                    ab.create();
                    ab.show();
                }
                else
                {
                    AlertDialog.Builder ab=new AlertDialog.Builder(mContext);
                    ab.setTitle("মিসড কল অ্যালার্ট");
                    ab.setMessage("আপনি যদি ফোন সুইচ অফ থাকায় বা ফোন নেটওয়ার্কের বাইরে থাকায় কোন ফোনকল মিস করে থাকেন, তাহলে মিসড কল অ্যালার্ট সার্ভিস আপনাকে এসএমএস-এর মাধ্যমে জানিয়ে দেবে মিস হয়ে যাওয়া কলগুলোর তথ্য।  আপনি কি রাজি?");
                    ab.setPositiveButton("হাঁ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
//                        WHelper.getInstance(mContext).createAlertin3("Missed Call Alert","START MCA","6222");
                            WHelper.getInstance(mContext).showMsgDialogForGP(mContext,"মিসড কল অ্যালার্ট","START MCA","6222");
                        }
                    });
                    ab.setNegativeButton("না", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    ab.setCancelable(true);
                    ab.create();
                    ab.show();
                }


            }
        });
        holder.btn_missed_call_alert_stop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                WHelper.getInstance(mContext).createAlertin3("Missed Call Alert","STOP MCA","6222");
                if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
                {
                    WHelper.getInstance(mContext).showMsgDialogForGP(mContext,"Missed Call Alert","STOP MCA","6222");
                }
                else
                {
                    WHelper.getInstance(mContext).showMsgDialogForGP(mContext,"মিসড কল অ্যালার্ট","STOP MCA","6222");
                }

            }
        });

        if (AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng")) {
            convertEnglish(holder);
        } else {
            convertBangla(holder);
        }


    }


    private void convertEnglish(GPViewHolder holder) {
        holder.balance_inquiry_prepaid.setText("" + mContext.getResources().getString(R.string.balance_inquiry_prepaid));
        holder.emergency_balance.setText("" + mContext.getResources().getString(R.string.emergency_balance));
        holder.internet_balance.setText("" + mContext.getResources().getString(R.string.internet_balance));
        holder.int_balance_for_payg.setText("" + mContext.getResources().getString(R.string.int_balance_for_payg));
        holder.own_number.setText("" + mContext.getResources().getString(R.string.own_number));
        holder.call_block_service.setText("" + mContext.getResources().getString(R.string.call_block_service));
        holder.call_block_service_1.setText("" + mContext.getResources().getString(R.string.call_block_service));
        holder.missed_call_alert.setText("" + mContext.getResources().getString(R.string.missed_call_alert));
        holder.missed_call_alert_1.setText("" + mContext.getResources().getString(R.string.missed_call_alert));

        holder.btn_balance.setText("" + mContext.getResources().getString(R.string.btn_check));
        holder.gp_btn_emergency_balance.setText("" + mContext.getResources().getString(R.string.btn_text_get));
        holder.btn_internet_balance_check.setText("" + mContext.getResources().getString(R.string.btn_check));
        holder.btn_internet_balance_check_payg.setText("" + mContext.getResources().getString(R.string.btn_check));
        holder.gp_btn_your_no.setText("" + mContext.getResources().getString(R.string.btn_text_get));
        holder.btn_call_block_start.setText("" + mContext.getResources().getString(R.string.btn_start));
        holder.btn_call_block_stop.setText("" + mContext.getResources().getString(R.string.btn_stop));
        holder.btn_missed_call_alert_start.setText("" + mContext.getResources().getString(R.string.btn_start));
        holder.btn_missed_call_alert_stop.setText("" + mContext.getResources().getString(R.string.btn_stop));
    }

    private void convertBangla(GPViewHolder holder) {
        holder.balance_inquiry_prepaid.setText("" + mContext.getResources().getString(R.string.balance_inquiry_prepaid_bn));
        holder.emergency_balance.setText("" + mContext.getResources().getString(R.string.emergency_balance_bn));
        holder.internet_balance.setText("" + mContext.getResources().getString(R.string.internet_balance_bn));
        holder.int_balance_for_payg.setText("" + mContext.getResources().getString(R.string.int_balance_for_payg_bn));
        holder.own_number.setText("" + mContext.getResources().getString(R.string.own_number_bn));
        holder.call_block_service.setText("" + mContext.getResources().getString(R.string.call_block_service_bn));
        holder.call_block_service_1.setText("" + mContext.getResources().getString(R.string.call_block_service_bn));
        holder.missed_call_alert.setText("" + mContext.getResources().getString(R.string.missed_call_alert_bn));
        holder.missed_call_alert_1.setText("" + mContext.getResources().getString(R.string.missed_call_alert_bn));

        holder.btn_balance.setText("" + mContext.getResources().getString(R.string.btn_check_bn));
        holder.gp_btn_emergency_balance.setText("" + mContext.getResources().getString(R.string.btn_text_get_bn));
        holder.btn_internet_balance_check.setText("" + mContext.getResources().getString(R.string.btn_check_bn));
        holder.btn_internet_balance_check_payg.setText("" + mContext.getResources().getString(R.string.btn_check_bn));
        holder.gp_btn_your_no.setText("" + mContext.getResources().getString(R.string.btn_text_get_bn));
        holder.btn_call_block_start.setText("" + mContext.getResources().getString(R.string.btn_start_bn));
        holder.btn_call_block_stop.setText("" + mContext.getResources().getString(R.string.btn_stop_bn));
        holder.btn_missed_call_alert_start.setText("" + mContext.getResources().getString(R.string.btn_start_bn));
        holder.btn_missed_call_alert_stop.setText("" + mContext.getResources().getString(R.string.btn_stop_bn));
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class GPViewHolder extends RecyclerView.ViewHolder {
        public CardView cv;
        public Button btn_balance, gp_btn_emergency_balance,btn_internet_balance_check,btn_internet_balance_check_payg, gp_btn_your_no,btn_call_block_start,btn_call_block_stop,btn_missed_call_alert_start,btn_missed_call_alert_stop;
        public TextView balance_inquiry_prepaid,emergency_balance,internet_balance,int_balance_for_payg,own_number,call_block_service,call_block_service_1,missed_call_alert,missed_call_alert_1;
        public GPViewHolder(View itemView) {
            super(itemView);
            showNativeAd(itemView);
            sshowNativeAd(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            btn_balance = (Button) itemView.findViewById(R.id.btn_balance);
            gp_btn_emergency_balance = (Button) itemView.findViewById(R.id.gp_btn_emergency_balance);
            btn_internet_balance_check = (Button) itemView.findViewById(R.id.btn_internet_balance_check);
            btn_internet_balance_check_payg = (Button) itemView.findViewById(R.id.btn_internet_balance_check_payg);
            gp_btn_your_no = (Button) itemView.findViewById(R.id.gp_btn_your_no);
            btn_call_block_start = (Button) itemView.findViewById(R.id.btn_call_block_start);
            btn_call_block_stop = (Button) itemView.findViewById(R.id.btn_call_block_stop);
            btn_missed_call_alert_start = (Button) itemView.findViewById(R.id.btn_missed_call_alert_start);
            btn_missed_call_alert_stop = (Button) itemView.findViewById(R.id.btn_missed_call_alert_stop);
            balance_inquiry_prepaid = (TextView) itemView.findViewById(R.id.balance_inquiry_prepaid);
            emergency_balance = (TextView) itemView.findViewById(R.id.emergency_balance);
            internet_balance = (TextView) itemView.findViewById(R.id.internet_balance);
            int_balance_for_payg = (TextView) itemView.findViewById(R.id.int_balance_for_payg);
            own_number = (TextView) itemView.findViewById(R.id.own_number);
            call_block_service = (TextView) itemView.findViewById(R.id.call_block_service);
            call_block_service_1 = (TextView) itemView.findViewById(R.id.call_block_service_1);
            missed_call_alert = (TextView) itemView.findViewById(R.id.missed_call_alert);
            missed_call_alert_1 = (TextView) itemView.findViewById(R.id.missed_call_alert_1);

        }
    }
    private static void showNativeAd(final View itemView) {
        nativeAd = new NativeAd(mmContext, "1453443504734328_1469349223143756");
        nativeAd.setAdListener(new AdListener() {

            @Override
            public void onError(Ad ad, AdError error) {
                // Ad error callback
                Log.e("facebook_ads","GPMiscAdapter:native ads:error:"+error.toString());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Add the Ad view into the ad container.
                nativeAdContainer = (LinearLayout) itemView.findViewById(R.id.native_ad_container);
                LayoutInflater inflater = LayoutInflater.from(mmContext);
                adView = (LinearLayout) inflater.inflate(R.layout.gp_native_ad_layout, nativeAdContainer, false);
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
        _nativeAd = new NativeAd(mmContext, "1453443504734328_1469349223143756");
        _nativeAd.setAdListener(new AdListener() {

            @Override
            public void onError(Ad ad, AdError error) {
                // Ad error callback
                Log.e("facebook_ads","GPMiscAdapter:native ads:error:"+error.toString());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Add the Ad view into the ad container.
                _nativeAdContainer = (LinearLayout) itemView.findViewById(R.id.native_ad_container1);
                LayoutInflater inflater = LayoutInflater.from(mmContext);
                _adView = (LinearLayout) inflater.inflate(R.layout.shhow_gp_native_ad_layout, _nativeAdContainer, false);
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
