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
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.walton.internetdataplan.R;
import com.walton.internetdataplan.models.RobiFnF;
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
public class RobiFnFAdapter extends RecyclerView.Adapter<RobiFnFAdapter.RobiFnFViewHolder> {
    public static boolean isShift=false;
    public static Context mmContext;
    public static NativeAd _nativeAd;
    public static LinearLayout _nativeAdContainer;
    public static LinearLayout _adView;
    private ArrayList<RobiFnF> mList;
    public Context mContext;
    public String[] mBody = {"8363", "8363", "8363", "8363", "8363",};
    public String[] mCode = {"H", "F", "A 018xxxxxxxx 017xxxxxxxx 019xxxxxxxx", "P 018xxxxxxxx", "D 018xxxxxxxx"};
    public String[] mTitleforButton = {"Explore", "Check", "Add", "Add", "Remove"};
    public String[] mTitleforButton_bn = {"জানুন", "চেক করুন", "যোগ করুন", "যোগ করুন", "মুছে ফেলুন"};
    public String[] mTitle = {" About FnF", "FnF List", "FnF", "Parner Number", "FnF"};
    public String[] mTitle_bn = {"এফএনএফ সম্বন্ধে", "এফএনএফ লিস্ট", "এফএনএফ", "পার্টনার নম্বর", "এফএনএফ"};

    public RobiFnFAdapter(Context context) {
        mmContext=context;
        isShift=true;
        mList = new ArrayList<RobiFnF>();
        if (mList != null && mList.size() > 4) {
            mList.clear();
        }
        if (AppsSettings.getAppsSettings(context).getLanguage().equals("eng")) {
            for (int i = 0; i < 5; i++) {

                String title = mTitle[i];
                String titleForBtn = mTitleforButton[i];
                String body = mBody[i];
                String code = mCode[i];
                RobiFnF gm = new RobiFnF(title, titleForBtn, body, code);
                mList.add(gm);
            }
        } else {
            for (int i = 0; i < 5; i++) {

                String title_bn = mTitle_bn[i];
                String titleForBtn_bn = mTitleforButton_bn[i];
                String body = mBody[i];
                String code = mCode[i];
                RobiFnF gm = new RobiFnF(title_bn, titleForBtn_bn, body, code);
                mList.add(gm);
            }
        }

        mContext = context;

    }

    @Override
    public RobiFnFViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


//        if (viewType==100){
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.robi_fnf_layout, parent, false);
//            RobiFnFViewHolder gpViewHolder = new RobiFnFViewHolder(view);
//            return gpViewHolder;
//
//        }else{
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.robi_fnf_layout, parent, false);
//            RobiFnFViewHolder gpViewHolder = new RobiFnFViewHolder(view);
//            return gpViewHolder;
//        }


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.robi_fnf_layout, parent, false);
        RobiFnFViewHolder gpViewHolder = new RobiFnFViewHolder(view);
        return gpViewHolder;
    }


    //custom ArrayAdapter
    class propertyArrayAdapter extends ArrayAdapter<RobiFnF> {

        private Context context;
        private List<RobiFnF> rentalProperties;

        //constructor, call on creation
        public propertyArrayAdapter(Context context, int resource, ArrayList<RobiFnF> objects) {
            super(context, resource, objects);
            this.context = context;
            this.rentalProperties = objects;
        }

        //called when rendering the list
        public View getView(int position, final View convertView, ViewGroup parent) {


            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.robi_fnf_item_layout, null);


            RobiFnF gp = rentalProperties.get(position);

            final String mTitle = gp.getmTitle();
            final String mTitleForBtn = gp.getmTitleForBtn();
            final String mBody = gp.getmBody();
            final String mCode = gp.getmCode();


            TextView title = (TextView) view.findViewById(R.id.title);
            title.setText(mTitle);


            Button dial = (Button) view.findViewById(R.id.btnAction);
            dial.setText("" + mTitleForBtn);
            dial.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    WHelper.showMsgDialogForRobi(mContext, mTitle, mBody, mCode);
                }
            });
            return view;
        }
    }

    @Override
    public void onBindViewHolder(final RobiFnFViewHolder holder, int position) {
        ArrayAdapter<RobiFnF> mStdAdapter = new propertyArrayAdapter(mContext, 0, mList);
        holder.list_item.setAdapter(mStdAdapter);
        // height based according to children
        setListViewHeightBasedOnChildren(holder.list_item);
    }


    @Override
    public int getItemCount() {
        return 1;
    }

    public static class RobiFnFViewHolder extends RecyclerView.ViewHolder {
        public ListView list_item;

        public RobiFnFViewHolder(View itemView) {
            super(itemView);
            if(isShift) {
                sshowNativeAd(itemView);
                isShift=false;
            }
            list_item = (ListView) itemView.findViewById(R.id.list_item);
        }
    }

//
//    public static class AdViewHolder extends RecyclerView.ViewHolder {
//        LinearLayout linearLayout;
//
//        public AdViewHolder(View view) {
//            super(view);
//            linearLayout= (LinearLayout) view.findViewById(R.id.);
//        }
//
//    }


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
//
//    @Override
//    public int getItemViewType(int position) {
//
//        int itemType;
//        if (position==4){
//            itemType=200;
//
//
//        }else{
//            itemType=100;
//        }
//
//        return itemType;
//
//
//
//    }
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
