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
import com.walton.internetdataplan.models.GPFnF;
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
public class GPFnFServiceAdapter extends RecyclerView.Adapter<GPFnFServiceAdapter.GPFnFViewHolder> {

    public static boolean isShift=false;
    public static Context mmContext;
    public static NativeAd _nativeAd;
    public static LinearLayout _nativeAdContainer;
    public static LinearLayout _adView;
    private ArrayList<GPFnF> mList;
    public Context mContext;
    public String[] mDialCodeA={"*121*1*5*5#","*121*1*5*1#","*121*1*5*2#","*121*1*5*3#","*121#"};
    public String[] mTitleforButton={"Check","Add","Add","Remove","Menu"};
    public String[] mTitleforButton_bn={"চেক করুন","যোগ করুন","যোগ করুন","মুছে ফেলুন","মেনু"};
    public String[] mTitleA={"FnF List","FnF","Super FnF","FnF/Super FnF","FnF"};
    public String[] mTitleA_bn={"এফএনএফ লিস্ট","এফএনএফ","সুপার এফএনএফ","এফএনএফ","এফএনএফ"};

    public GPFnFServiceAdapter(Context context) {
        isShift=true;
        mmContext=context;
        mList=new ArrayList<GPFnF>();
        if(mList!=null && mList.size()>4)
        {
            mList.clear();
        }
        for(int i=0;i<5;i++)
        {
            if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
            {
                String mTitle=mTitleA[i];
                String mTitleForBtn=mTitleforButton[i];
                String mDialCode=mDialCodeA[i];
                GPFnF gm=new GPFnF(mTitle,mTitleForBtn,mDialCode);
                mList.add(gm);
            }
            else
            {
                String mTitle=mTitleA_bn[i];
                String mTitleForBtn=mTitleforButton_bn[i];
                String mDialCode=mDialCodeA[i];
                GPFnF gm=new GPFnF(mTitle,mTitleForBtn,mDialCode);
                mList.add(gm);
            }

        }
        mContext = context;

    }

    @Override
    public GPFnFViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gp_fnf_layout, parent, false);
        GPFnFViewHolder gpViewHolder = new GPFnFViewHolder(view);
        return gpViewHolder;
    }

    //custom ArrayAdapter
    class propertyArrayAdapter extends ArrayAdapter<GPFnF> {

        private Context context;
        private List<GPFnF> rentalProperties;

        //constructor, call on creation
        public propertyArrayAdapter(Context context, int resource, ArrayList<GPFnF> objects) {
            super(context, resource, objects);
            this.context = context;
            this.rentalProperties = objects;
        }

        //called when rendering the list
        public View getView(int position, final View convertView, ViewGroup parent) {


            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.gp_fnf_item_layout, null);


            GPFnF gp = rentalProperties.get(position);

            final String mTitle = gp.getmTitle();
            final String mTitleForBtn = gp.getmTitleForBtn();
            final String mDialCode = gp.getmDialCode();


            TextView title=(TextView)view.findViewById(R.id.title);
            title.setText(mTitle);


            Button dial = (Button) view.findViewById(R.id.btnAction);
            dial.setText(""+mTitleForBtn);
            dial.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    WHelper.getInstance(mContext).DialingCode(mDialCode);
                }
            });
            return view;
        }
    }

    @Override
    public void onBindViewHolder(final GPFnFViewHolder holder, int position) {
        ArrayAdapter<GPFnF> mStdAdapter = new propertyArrayAdapter(mContext, 0, mList);
        holder.list_item.setAdapter(mStdAdapter);
        // height based according to children
        setListViewHeightBasedOnChildren(holder.list_item);
        if (AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng")) {
            convertEnglish(holder);
        } else {
            convertBangla(holder);
        }
    }
    private void convertEnglish(GPFnFViewHolder holder) {
        holder.terms_and_conditions.setText(""+mContext.getResources().getString(R.string.terms_and_conditions));
        holder.gp_fnf_condition_1.setText(""+mContext.getResources().getString(R.string.gp_fnf_condition_1));
        holder.gp_fnf_condition_2.setText(""+mContext.getResources().getString(R.string.gp_fnf_condition_2));
        holder.gp_fnf_condition_3.setText(""+mContext.getResources().getString(R.string.gp_fnf_condition_3));
        holder.gp_fnf_condition_4.setText(""+mContext.getResources().getString(R.string.gp_fnf_condition_4));
        holder.gp_fnf_condition_5.setText(""+mContext.getResources().getString(R.string.gp_fnf_condition_5));

    }

    private void convertBangla(GPFnFViewHolder holder) {
        holder.terms_and_conditions.setText(""+mContext.getResources().getString(R.string.terms_and_conditions_bn));
        holder.gp_fnf_condition_1.setText(""+mContext.getResources().getString(R.string.gp_fnf_condition_1_bn));
        holder.gp_fnf_condition_2.setText(""+mContext.getResources().getString(R.string.gp_fnf_condition_2_bn));
        holder.gp_fnf_condition_3.setText(""+mContext.getResources().getString(R.string.gp_fnf_condition_3_bn));
        holder.gp_fnf_condition_4.setText(""+mContext.getResources().getString(R.string.gp_fnf_condition_4_bn));
        holder.gp_fnf_condition_5.setText(""+mContext.getResources().getString(R.string.gp_fnf_condition_5_bn));
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class GPFnFViewHolder extends RecyclerView.ViewHolder {
        public ListView list_item;
        public TextView terms_and_conditions,gp_fnf_condition_1,gp_fnf_condition_2,gp_fnf_condition_3,gp_fnf_condition_4,gp_fnf_condition_5;

        public GPFnFViewHolder(View itemView) {
            super(itemView);
            if(isShift) {
                sshowNativeAd(itemView);
                isShift=false;
            }
            list_item = (ListView) itemView.findViewById(R.id.list_item);
            terms_and_conditions=(TextView)itemView.findViewById(R.id.terms_and_conditions);
            gp_fnf_condition_1=(TextView)itemView.findViewById(R.id.gp_fnf_condition_1);
            gp_fnf_condition_2=(TextView)itemView.findViewById(R.id.gp_fnf_condition_2);
            gp_fnf_condition_3=(TextView)itemView.findViewById(R.id.gp_fnf_condition_3);
            gp_fnf_condition_4=(TextView)itemView.findViewById(R.id.gp_fnf_condition_4);
            gp_fnf_condition_5=(TextView)itemView.findViewById(R.id.gp_fnf_condition_5);
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
                Log.e("facebook_ads","GPFnFServiceAdapter:native ads:error:"+error.toString());
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
