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
public class TeletalkMiscAdapter extends RecyclerView.Adapter<TeletalkMiscAdapter.TeletalkiewHolder> {
    public Context mContext;
    public static Context mmContext;
    public static NativeAd _nativeAd;
    public static LinearLayout _nativeAdContainer;
    public static LinearLayout _adView;


    public static NativeAd nativeAd;
    public static LinearLayout nativeAdContainer;
    private static LinearLayout adView;

    public TeletalkMiscAdapter(Context context) {
        mContext = context;
        mmContext=context;

    }

    @Override
    public TeletalkiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teletalk_misc_layout, parent, false);
        TeletalkiewHolder mViewHolder = new TeletalkiewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final TeletalkiewHolder holder, int position) {

        holder.btn_balance.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                WHelper.getInstance(mContext).DialingCode("*152#");
            }
        });
        holder.btn_internet_balance_check.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
                {
                    WHelper.showMsgDialogForTeletalk(mContext,"Internet Balance ", "u", "111");
                }
                else
                {
                    WHelper.showMsgDialogForTeletalk(mContext,"ইন্টারনেট ব্যালেন্স ", "u", "111");
                }

            }
        });

        holder.gp_btn_your_no.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                WHelper.getInstance(mContext).DialingCode("*551#");
            }
        });

        holder.btn_missed_call_alert_start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
                {
                    AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                    ab.setTitle("Missed Call Alert");
                    ab.setMessage("Missed Call Alert service provides the facility to get notified about the calls that are missed due to keeping the phone switched off or being out of network. You will be notified through SMS when your phone is switch ON. The missed call alert SMS will contain information of calling mobile numbers, time and date when the call was made and the number of calls. For this Service a fix amount of charge will be deducted in every month..Tk. 10.00/month. Do you want?");
                    ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
//                        WHelper.getInstance(mContext).createAlertin3("Missed Call Alert", "TT<>START", "2455");
                            WHelper.showMsgDialogForTeletalk(mContext,"Missed Call Alert", "TT START", "2455");
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
                    AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                    ab.setTitle("মিসড কল এলার্ট");
                    ab.setMessage("এখন থেকে আর একটি কলও মিস হবে না আপনার – যদি আপনি ব্যবহার করেন টেলিটকের মিসড কল এলার্ট সার্ভিস। এ ভ্যালু অ্যাডেড সার্ভিসটি চালু থাকলে কোনো কারণে আপনার মোবাইল ফোনটি বন্ধ বা ব্যস্ত থাকলেও তাৎক্ষনিক আপনি পেয়ে যাবেন এসএমএস নোটিফিকেশন। সেবাটি নিতে START লিখে এসএমএস পাঠাতে হবে ২৪৫৫ নম্বরে। আপনি কি রাজি?");
                    ab.setPositiveButton("হাঁ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
//                        WHelper.getInstance(mContext).createAlertin3("Missed Call Alert", "TT<>START", "2455");
                            WHelper.showMsgDialogForTeletalk(mContext,"মিসড কল অ্যালার্ট", "TT START", "2455");
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
//                WHelper.getInstance(mContext).createAlertin3("Missed Call Alert", "TT<>STOP", "2455");
                if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
                {
                    WHelper.showMsgDialogForTeletalk(mContext,"Missed Call Alert", "TT STOP", "2455");
                }
                else
                {
                    WHelper.showMsgDialogForTeletalk(mContext,"মিসড কল অ্যালার্ট", "TT STOP", "2455");
                }

            }
        });
        holder.btn_call_block.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
                {
                    AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                    ab.setTitle("Call block");
                    ab.setMessage("If you're having trouble with unwanted or prank calls and want to avoid those calls & block them permanently then just dial 1515 from your mobile number and follow the IVR instruction.");
                    ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
                    AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                    ab.setTitle("কল ব্লক");
                    ab.setMessage("টেলিটক আপনাকে দিচ্ছে বিরক্তিকর কলারদের ব্লক করার সুযোগও। ১৫১৫ নম্বরে ডায়াল করে আপনি চালু করতে পারেন এবং অনুসরণ করুন পরবর্তী নির্দেশনা।");
                    ab.setPositiveButton("ঠিক আছে", new DialogInterface.OnClickListener() {
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
        if (AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng")) {
            convertEnglish(holder);
        } else {
            convertBangla(holder);
        }


    }
    private void convertEnglish(TeletalkiewHolder holder) {
        holder.btn_balance.setText(""+mContext.getResources().getString(R.string.btn_check));
        holder.btn_internet_balance_check.setText(""+mContext.getResources().getString(R.string.btn_check));
        holder.gp_btn_your_no.setText(""+mContext.getResources().getString(R.string.btn_text_get));
        holder.btn_missed_call_alert_start.setText(""+mContext.getResources().getString(R.string.btn_start));
        holder.btn_missed_call_alert_stop.setText(""+mContext.getResources().getString(R.string.btn_stop));
        holder.btn_call_block.setText(""+mContext.getResources().getString(R.string.btn_about));
        //
        holder.balance_check.setText(""+mContext.getResources().getString(R.string.balance_inquiry_prepaid));
        holder.int_balance.setText(""+mContext.getResources().getString(R.string.internet_balance));
        holder.own_number.setText(""+mContext.getResources().getString(R.string.own_number));
        holder.missed_called_alert.setText(""+mContext.getResources().getString(R.string.missed_call_alert));
        holder.missed_called_alert_a.setText(""+mContext.getResources().getString(R.string.missed_call_alert));
        holder.call_block_text.setText(""+mContext.getResources().getString(R.string.call_block_service));

    }

    private void convertBangla(TeletalkiewHolder holder) {
        holder.btn_balance.setText(""+mContext.getResources().getString(R.string.btn_check_bn));
        holder.btn_internet_balance_check.setText(""+mContext.getResources().getString(R.string.btn_check_bn));
        holder.gp_btn_your_no.setText(""+mContext.getResources().getString(R.string.btn_text_get_bn));
        holder.btn_missed_call_alert_start.setText(""+mContext.getResources().getString(R.string.btn_start_bn));
        holder.btn_missed_call_alert_stop.setText(""+mContext.getResources().getString(R.string.btn_stop_bn));
        holder.btn_call_block.setText(""+mContext.getResources().getString(R.string.btn_about_bn));
        //
        holder.balance_check.setText(""+mContext.getResources().getString(R.string.balance_inquiry_prepaid_bn));
        holder.int_balance.setText(""+mContext.getResources().getString(R.string.internet_balance_bn));
        holder.own_number.setText(""+mContext.getResources().getString(R.string.own_number_bn));
        holder.missed_called_alert.setText(""+mContext.getResources().getString(R.string.missed_call_alert_bn));
        holder.missed_called_alert_a.setText(""+mContext.getResources().getString(R.string.missed_call_alert_bn));
        holder.call_block_text.setText(""+mContext.getResources().getString(R.string.call_block_service_bn));

    }
    @Override
    public int getItemCount() {
        return 1;
    }

    public static class TeletalkiewHolder extends RecyclerView.ViewHolder {
        public CardView cv;
        public Button btn_balance, btn_internet_balance_check, gp_btn_your_no, btn_missed_call_alert_start, btn_missed_call_alert_stop, btn_call_block;
        public TextView balance_check, int_balance,own_number,missed_called_alert,missed_called_alert_a,call_block_text;

        public TeletalkiewHolder(View itemView) {
            super(itemView);
            showNativeAd(itemView);
            sshowNativeAd(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            btn_balance = (Button) itemView.findViewById(R.id.btn_balance);
            btn_internet_balance_check = (Button) itemView.findViewById(R.id.btn_internet_balance_check);
            gp_btn_your_no = (Button) itemView.findViewById(R.id.gp_btn_your_no);
            btn_missed_call_alert_start = (Button) itemView.findViewById(R.id.btn_missed_call_alert_start);
            btn_missed_call_alert_stop = (Button) itemView.findViewById(R.id.btn_missed_call_alert_stop);
            btn_call_block = (Button) itemView.findViewById(R.id.btn_call_block);

            //
            balance_check=(TextView)itemView.findViewById(R.id.balance_check);
            int_balance=(TextView)itemView.findViewById(R.id.int_balance);
            own_number=(TextView)itemView.findViewById(R.id.own_number);
            missed_called_alert=(TextView)itemView.findViewById(R.id.missed_called_alert);
            missed_called_alert_a=(TextView)itemView.findViewById(R.id.missed_called_alert_a);
            call_block_text=(TextView)itemView.findViewById(R.id.call_block_text);


        }
    }
    private static void sshowNativeAd(final View itemView) {
        _nativeAd = new NativeAd(mmContext, "1453443504734328_1469351066476905");
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
                _adView = (LinearLayout) inflater.inflate(R.layout.shhow_teletalk_native_ad_layout, _nativeAdContainer, false);
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
    private static void showNativeAd(final View itemView) {
        nativeAd = new NativeAd(mmContext, "1453443504734328_1469351066476905");
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
                adView = (LinearLayout) inflater.inflate(R.layout.teletalk_native_ad_layout, nativeAdContainer, false);
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


}
