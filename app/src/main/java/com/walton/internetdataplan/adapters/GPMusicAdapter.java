package com.walton.internetdataplan.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.NativeAd;
import com.walton.internetdataplan.R;
import com.walton.internetdataplan.models.GPMusic;
import com.walton.internetdataplan.utitls.AppsSettings;
import com.walton.internetdataplan.utitls.Call3rdPartyApps;
import com.walton.internetdataplan.utitls.WHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faruq on 10/25/2016.
 */

/**
 * Created by Faruq on 10/25/2016.
 */
public class GPMusicAdapter extends RecyclerView.Adapter<GPMusicAdapter.GPMusicViewHolder> {
    public static boolean isShift=false;
    public static Context mmContext;
    public static NativeAd _nativeAd;
    public static LinearLayout _nativeAdContainer;
    public static LinearLayout _adView;
    private ArrayList<GPMusic> mList;
    public Context mContext;
    public String[] mChargeList_bn={" ফ্রি"," ২.৩৯ টাকা","১৪.৬১ টাকা","২৪.৩৫ টাকা","৪২.৬১ টাকা"};
    public String[] mChargeList={"Free","BDT 2.39","BDT 14.61","BDT 24.35","BDT 42.61"};
    public String[] mStopList={"STOP T","STOP T","STOP W","STOP F","STOP M"};
    public String[] mStartList={"START T","START T","START W","START F","START M"};
    public String[] mSentToList={"7728","7728","7728","7728","7728"};
    public String[] mDialList={"*7728*1*1#","*7728*2*1#","*7728*3*1#","*7728*4*1#","*7728*5*1#"};
    public String[] mMusicTitle={"Trial Plan (30 Days)","Daily Plan (1 Day) ","Weekly Plan (7 Days)","Bi-weekly Plan (15 Days)","Monthly Plan (30 Days)"};
    public String[] mMusicTitle_bn={"ট্রায়াল প্ল্যান (৩০ দিন)","দৈনিক প্ল্যান (১ দিন)","সাপ্তাহিক প্ল্যান (৭ দিন)","দ্বিসাপ্তাহিক প্ল্যান (১৫ দিন)","মাসিক প্ল্যান (৩০ দিন)"};

    public GPMusicAdapter(Context context) {
        mmContext=context;
        isShift=true;
        mList=new ArrayList<GPMusic>();
        if(mList!=null && mList.size()>4)
        {
            mList.clear();
        }
       for(int i=0;i<5;i++)
       {
           if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
           {
               String mTitle=mMusicTitle[i];
               String mDialCode=mDialList[i];
               String mDialForStart=mStartList[i];
               String mDialForStop=mStopList[i];
               String mSentCode=mSentToList[i];
               String mCharge=mChargeList[i];
               GPMusic gm=new GPMusic(mTitle,mDialCode,mDialForStart,mDialForStop,mSentCode,mCharge);
               mList.add(gm);
           }
           else
           {
               String mTitle=mMusicTitle_bn[i];
               String mDialCode=mDialList[i];
               String mDialForStart=mStartList[i];
               String mDialForStop=mStopList[i];
               String mSentCode=mSentToList[i];
               String mCharge=mChargeList_bn[i];
               GPMusic gm=new GPMusic(mTitle,mDialCode,mDialForStart,mDialForStop,mSentCode,mCharge);
               mList.add(gm);
           }

       }
        mContext = context;

    }

    @Override
    public GPMusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gp_music_layout, parent, false);
        GPMusicViewHolder gpViewHolder = new GPMusicViewHolder(view);
        return gpViewHolder;
    }

    //custom ArrayAdapter
    class propertyArrayAdapter extends ArrayAdapter<GPMusic> {

        private Context context;
        private List<GPMusic> rentalProperties;

        //constructor, call on creation
        public propertyArrayAdapter(Context context, int resource, ArrayList<GPMusic> objects) {
            super(context, resource, objects);
            this.context = context;
            this.rentalProperties = objects;
        }

        //called when rendering the list
        public View getView(int position, final View convertView, ViewGroup parent) {


            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.gp_music_item_layout, null);


            GPMusic gp = rentalProperties.get(position);

            final String mTitle = gp.getmTitle();
            final String mDialCode = gp.getmDialCode();
            final String mDialForStart = gp.getmDialForStart();
            final String mDialForStop = gp.getmDialForStop();
            final String mSentCode = gp.getmSentCode();
            final String mCharge=gp.getmCharge();

            TextView musicTitle=(TextView)view.findViewById(R.id.musicTitle);
            musicTitle.setText(mTitle);
            TextView charge = (TextView) view.findViewById(R.id.charge);
            if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
            {
                charge.setText(" "+mContext.getResources().getString(R.string.apply_charge)+mCharge);

            }
            else
            {
                charge.setText(" "+mContext.getResources().getString(R.string.apply_charge_bn)+mCharge);
            }


           Button dial = (Button) view.findViewById(R.id.dial);

            dial.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    WHelper.getInstance(mContext).DialingCode(mDialCode);
                }
            });
            Button sms = (Button) view.findViewById(R.id.sms);

            sms.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
//                WHelper.getInstance(mContext).createAlertin3(mMusicTitle[position],mStartList[position],mSentToList[position]);
                    WHelper.getInstance(mContext).showMsgDialogForGP(mContext,mTitle,mDialForStart,mSentCode);
                }
            });
            Button stop = (Button) view.findViewById(R.id.stop);
            stop.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
//                WHelper.getInstance(mContext).createAlertin3(mMusicTitle[position],mStopList[position],mSentToList[position]);
                    WHelper.getInstance(mContext).showMsgDialogForGP(mContext,mTitle,mDialForStop,mSentCode);
                }
            });
            if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
            {
                dial.setText(""+mContext.getResources().getString(R.string.btn_dial));
                sms.setText(""+mContext.getResources().getString(R.string.btn_sms));
                stop.setText(""+mContext.getResources().getString(R.string.btn_stop));
            }
            else
            {
                dial.setText(""+mContext.getResources().getString(R.string.btn_dial_bn));
                sms.setText(""+mContext.getResources().getString(R.string.btn_sms_bn));
                stop.setText(""+mContext.getResources().getString(R.string.btn_stop_bn));
            }


            return view;
        }
    }

    @Override
    public void onBindViewHolder(final GPMusicViewHolder holder, int position) {
        if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
        {
            holder.gp_official_music_link.setText(""+mContext.getResources().getString(R.string.gp_music));
            holder.check_on_web.setText(" "+mContext.getResources().getString(R.string.check_on_web));

            //

            holder.terms_and_conditions.setText(" "+mContext.getResources().getString(R.string.terms_and_conditions));
            holder.day_30_free_trial_subscription.setText(" "+mContext.getResources().getString(R.string.day_30_free_trial_subscription));
            holder.free_20mb_interner_for_music.setText(" "+mContext.getResources().getString(R.string.free_20mb_interner_for_music));
            holder.simple_subscription_packs.setText(" "+mContext.getResources().getString(R.string.simple_subscription_packs));
            holder.ad_free_for_music.setText(" "+mContext.getResources().getString(R.string.ad_free_for_music));
            holder.earn_points_by_streaming_for_music.setText(" "+mContext.getResources().getString(R.string.earn_points_by_streaming_for_music));
            holder.premium_sound_quality.setText(" "+mContext.getResources().getString(R.string.premium_sound_quality));
        }
        else
        {
            holder.gp_official_music_link.setText(""+mContext.getResources().getString(R.string.gp_music_bn));
            holder.check_on_web.setText(" "+mContext.getResources().getString(R.string.check_on_web_bn));
            //
            holder.terms_and_conditions.setText(" "+mContext.getResources().getString(R.string.terms_and_conditions_bn));
            holder.day_30_free_trial_subscription.setText(" "+mContext.getResources().getString(R.string.day_30_free_trial_subscription_bn));
            holder.free_20mb_interner_for_music.setText(" "+mContext.getResources().getString(R.string.free_20mb_interner_for_music_bn));
            holder.simple_subscription_packs.setText(" "+mContext.getResources().getString(R.string.simple_subscription_packs_bn));
            holder.ad_free_for_music.setText(" "+mContext.getResources().getString(R.string.ad_free_for_music_bn));
            holder.earn_points_by_streaming_for_music.setText(" "+mContext.getResources().getString(R.string.earn_points_by_streaming_for_music_bn));
            holder.premium_sound_quality.setText(" "+mContext.getResources().getString(R.string.premium_sound_quality_bn));
        }
        holder.gp_official_music_link.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Call3rdPartyApps
                        .openBrowser(mContext,"https://www.grameenphone.com/personal/digital-services/gp-music");
            }
        });
        ArrayAdapter<GPMusic> mStdAdapter = new propertyArrayAdapter(mContext, 0, mList);
        holder.list_item.setAdapter(mStdAdapter);
        // height based according to children
        setListViewHeightBasedOnChildren(holder.list_item);
    }


    @Override
    public int getItemCount() {
        return 1;
    }

    public static class GPMusicViewHolder extends RecyclerView.ViewHolder {
        public ListView list_item;
        public TextView gp_official_music_link,check_on_web;
        public TextView terms_and_conditions,day_30_free_trial_subscription,free_20mb_interner_for_music,simple_subscription_packs,ad_free_for_music,earn_points_by_streaming_for_music,premium_sound_quality;

        public GPMusicViewHolder(View itemView) {
            super(itemView);
            if(isShift) {
                sshowNativeAd(itemView);
                isShift=false;
            }
            list_item = (ListView) itemView.findViewById(R.id.list_item);
            gp_official_music_link = (TextView) itemView.findViewById(R.id.gp_official_music_link);
            check_on_web = (TextView) itemView.findViewById(R.id.check_on_web);
            terms_and_conditions = (TextView) itemView.findViewById(R.id.terms_and_conditions);
            day_30_free_trial_subscription = (TextView) itemView.findViewById(R.id.day_30_free_trial_subscription);
            free_20mb_interner_for_music = (TextView) itemView.findViewById(R.id.free_20mb_interner_for_music);
            simple_subscription_packs = (TextView) itemView.findViewById(R.id.simple_subscription_packs);
            ad_free_for_music = (TextView) itemView.findViewById(R.id.ad_free_for_music);
            earn_points_by_streaming_for_music = (TextView) itemView.findViewById(R.id.earn_points_by_streaming_for_music);
            premium_sound_quality = (TextView) itemView.findViewById(R.id.premium_sound_quality);
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
    private static void sshowNativeAd(final View itemView) {
        _nativeAd = new NativeAd(mmContext, "1453443504734328_1469349223143756");
        _nativeAd.setAdListener(new AdListener() {

            @Override
            public void onError(Ad ad, AdError error) {
                // Ad error callback
                Log.e("facebook_ads","GPMusicAdapter:native ads:error:"+error.toString());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Add the Ad view into the ad container.
                _nativeAdContainer = (LinearLayout) itemView.findViewById(R.id.native_ad_container1);
                LayoutInflater inflater = LayoutInflater.from(mmContext);
                _adView = (LinearLayout) inflater.inflate(R.layout.gp_music_native_ad_layout, _nativeAdContainer, false);
                _nativeAdContainer.addView(_adView);

                // Create native UI using the ad metadata.
                ImageView nativeAdIcon = (ImageView) _adView.findViewById(R.id.native_ad_icon1);
                TextView nativeAdTitle = (TextView) _adView.findViewById(R.id.native_ad_title1);
             //   MediaView nativeAdMedia = (MediaView) _adView.findViewById(R.id.native_ad_media1);
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
              //  nativeAdMedia.setNativeAd(_nativeAd);

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
