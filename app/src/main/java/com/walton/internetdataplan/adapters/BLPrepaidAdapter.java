package com.walton.internetdataplan.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.walton.internetdataplan.AppManager;
import com.walton.internetdataplan.R;
import com.walton.internetdataplan.models.BLModel;
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
public class BLPrepaidAdapter extends RecyclerView.Adapter<BLPrepaidAdapter.BLPrepaidViewHolder> {
    private ArrayList<BLModel> mBLPrepaidList;
    public Context mContext;
    public static boolean mBLPrepaidSearch = false;

    public BLPrepaidAdapter(Context context, ArrayList<BLModel> list, boolean mSearch) {
        mBLPrepaidList = list;
        mContext = context;
        mBLPrepaidSearch = mSearch;

    }

    @Override
    public BLPrepaidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bl_prepaid_layout, parent, false);
        BLPrepaidViewHolder gpViewHolder = new BLPrepaidViewHolder(view);
        return gpViewHolder;
    }

    //custom ArrayAdapter
    class propertyArrayAdapter extends ArrayAdapter<BLModel> {

        private Context context;
        private List<BLModel> rentalProperties;

        //constructor, call on creation
        public propertyArrayAdapter(Context context, int resource, ArrayList<BLModel> objects) {
            super(context, resource, objects);
            this.context = context;
            this.rentalProperties = objects;
        }

        //called when rendering the list
        public View getView(int position, final View convertView, ViewGroup parent) {
            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.bl_prepaid_item_layout, null);


            BLModel gp = rentalProperties.get(position);
            final String mAutoRenCode = gp.getmSMSCode();
            final String mAutoInsBody = gp.getmSmsBody();
            final String mPackage = gp.getmPackageName();
            final String mDialCodeWith = gp.getmDirectDialingCodeWith();
            final String mDialCodeWithout = gp.getmDirectDialingCodeWithout();


            final TableLayout tableLayout1 = (TableLayout) view.findViewById(R.id.tableLayout1);
            tableLayout1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    WHelper.getInstance(mContext).DialingCode(mAutoRenCode);
//                    showPopupMenu(tableLayout1, mAutoRenCode, mAutoInsBody, mPackage, mDialCodeWith, mDialCodeWithout);
                }
            });

            Log.e("faultu", "::" + "package:" + gp.getmPackageName() + "\n price:" + gp.getmPrice() + "\n validity:" + gp.getmValidity());
            TextView packageTime = (TextView) view.findViewById(R.id.packageTime);
            TextView duration = (TextView) view.findViewById(R.id.duration);
            if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
            {
                packageTime.setText(""+mContext.getResources().getString(R.string.packge)+" : " + gp.getmPackageName());
                if(gp.getmValidity().equals("1"))
                {
                    duration.setText("Duration: " + gp.getmValidity()+ " day");
                }
                else
                {
                    duration.setText("Duration: " + gp.getmValidity()+ " days");
                }
            }
            else
            {
                packageTime.setText(""+mContext.getResources().getString(R.string.packge_bn)+" : " + gp.getmPackageName());

                duration.setText(""+mContext.getResources().getString(R.string.duration_bn) +" : " + gp.getmValidity()+ " "+mContext.getResources().getString(R.string.day_bn));

            }





            TextView balance = (TextView) view.findViewById(R.id.balance);
            balance.setText(gp.getmPrice() + " /-");
//            final ImageView submenuarrow = (ImageView) view.findViewById(R.id.submenuarrow);
//            submenuarrow.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    showPopupMenu(submenuarrow, mAutoRenCode, mAutoInsBody, mPackage, mDialCodeWith, mDialCodeWithout);
//                }
//            });


            return view;
        }
    }

    @Override
    public void onBindViewHolder(final BLPrepaidViewHolder holder, int position) {
        holder.official_web_link.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Call3rdPartyApps
                        .openBrowser(mContext,"http://www.banglalink.com.bd/en/personal/internet?shs_term_node_tid_depth=144&packagevaliditytype=8&price%255Bmin%255D=0&price%255Bmax%255D=2000&validity%255Bmin%255D=0&validity%255Bmax%255D=30");
            }
        });
        if (mBLPrepaidSearch) {
            holder.justStaticItemForbLPrepaid1.setVisibility(View.GONE);
            holder.justStaticItemForbLPrepaid2.setVisibility(View.GONE);
            holder.justStaticItemForbLPrepaid3.setVisibility(View.GONE);

            ArrayAdapter<BLModel> mSolAda = new propertyArrayAdapter(mContext, 0, mBLPrepaidList);
            holder.list_item_sol_mda_packs.setAdapter(mSolAda);
            // height based according to children
            setListViewHeightBasedOnChildren(holder.list_item_sol_mda_packs);
        } else {
            holder.justStaticItemForbLPrepaid1.setVisibility(View.VISIBLE);
            holder.justStaticItemForbLPrepaid2.setVisibility(View.VISIBLE);
            holder.justStaticItemForbLPrepaid3.setVisibility(View.VISIBLE);

            //create our new array adapter


            // Standard media packs
            String mStdName = "Standard Volume Packs";
            ArrayList<BLModel> mStdList = new ArrayList<BLModel>();
            if(mStdList!=null && mStdList.size()>0)
            {
                mStdList.clear();
            }
            mStdList = AppManager.getInstance(mContext).retrieveBLPrepaidDataPackList(mContext, mStdName, "");
            ArrayAdapter<BLModel> mStdAdapter = new propertyArrayAdapter(mContext, 0, mStdList);
            holder.list_item_std_vol_packs.setAdapter(mStdAdapter);
            // height based according to children
            setListViewHeightBasedOnChildren(holder.list_item_std_vol_packs);


            // Smart media packs
            String mSmtName = "Recharge Packs";
            ArrayList<BLModel> mSmtList = new ArrayList<BLModel>();
            if(mSmtList!=null && mSmtList.size()>0)
            {
                mSmtList.clear();
            }
            mSmtList = AppManager.getInstance(mContext).retrieveBLPrepaidDataPackList(mContext, mSmtName, "");
            ArrayAdapter<BLModel> mSmtAdapter = new propertyArrayAdapter(mContext, 0, mSmtList);
            holder.list_item_smt_vol_packs.setAdapter(mSmtAdapter);
            // height based according to children
            setListViewHeightBasedOnChildren(holder.list_item_smt_vol_packs);


            // social media packs
            String mSolName = "Social Media Packs";
            ArrayList<BLModel> mSolList = new ArrayList<BLModel>();
            if(mSolList!=null && mSolList.size()>0)
            {
                mSolList.clear();
            }
            mSolList = AppManager.getInstance(mContext).retrieveBLPrepaidDataPackList(mContext, mSolName, "");
            ArrayAdapter<BLModel> mSolAdapter = new propertyArrayAdapter(mContext, 0, mSolList);
            holder.list_item_sol_mda_packs.setAdapter(mSolAdapter);
            // height based according to children
            setListViewHeightBasedOnChildren(holder.list_item_sol_mda_packs);
        }
        if (AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng")) {
            convertEnglish(holder);
        } else {
            convertBangla(holder);
        }



    }



    private void convertEnglish(BLPrepaidViewHolder holder) {
        holder.official_web_link.setText(""+mContext.getResources().getString(R.string.g_internet_plan));
        holder.standard_volume_packs.setText(""+mContext.getResources().getString(R.string.standard_volume_packs));
        holder.standard_volume_packs_info.setText(""+mContext.getResources().getString(R.string.standard_volume_packs_info));
        holder.auto_renew_for_bl.setText(""+mContext.getResources().getString(R.string.auto_renew_for_bl));
        holder.recharge_packs.setText(""+mContext.getResources().getString(R.string.recharge_packs));
        holder.balance_recharge_packs.setText(""+mContext.getResources().getString(R.string.balance_recharge_packs));
        holder.social_media_packs.setText(""+mContext.getResources().getString(R.string.social_media_packs));
        holder.social_media_packs_info.setText(""+mContext.getResources().getString(R.string.social_media_packs_info));
        holder.social_media_packs_sms_bundle.setText(""+mContext.getResources().getString(R.string.social_media_packs_sms_bundle));
        holder.check_on_web.setText(""+mContext.getResources().getString(R.string.check_on_web));




    }

    private void convertBangla(BLPrepaidViewHolder holder) {
        holder.official_web_link.setText(""+mContext.getResources().getString(R.string.g_internet_plan_bn));
        holder.standard_volume_packs.setText(""+mContext.getResources().getString(R.string.standard_volume_packs_bn));
        holder.standard_volume_packs_info.setText(""+mContext.getResources().getString(R.string.standard_volume_packs_info_bn));
        holder.auto_renew_for_bl.setText(""+mContext.getResources().getString(R.string.auto_renew_for_bl_bn));
        holder.recharge_packs.setText(""+mContext.getResources().getString(R.string.recharge_packs_bn));
        holder.balance_recharge_packs.setText(""+mContext.getResources().getString(R.string.balance_recharge_packs_bn));
        holder.social_media_packs.setText(""+mContext.getResources().getString(R.string.social_media_packs_bn));
        holder.social_media_packs_info.setText(""+mContext.getResources().getString(R.string.social_media_packs_info_bn));
        holder.social_media_packs_sms_bundle.setText(""+mContext.getResources().getString(R.string.social_media_packs_sms_bundle_bn));
        holder.check_on_web.setText(""+mContext.getResources().getString(R.string.check_on_web_bn));
    }
    @Override
    public int getItemCount() {
        return 1;
    }

    public static class BLPrepaidViewHolder extends RecyclerView.ViewHolder {
        public ListView list_item_std_vol_packs, list_item_smt_vol_packs, list_item_sol_mda_packs;
        public static CardView justStaticItemForbLPrepaid1, justStaticItemForbLPrepaid2, justStaticItemForbLPrepaid3;
        public TextView official_web_link,standard_volume_packs,standard_volume_packs_info,auto_renew_for_bl,recharge_packs,balance_recharge_packs,social_media_packs;
        public TextView social_media_packs_info,social_media_packs_sms_bundle,check_on_web;

        public BLPrepaidViewHolder(View itemView) {
            super(itemView);
            list_item_std_vol_packs = (ListView) itemView.findViewById(R.id.list_item_std_vol_packs);
            list_item_smt_vol_packs = (ListView) itemView.findViewById(R.id.list_item_smt_vol_packs);
            list_item_sol_mda_packs = (ListView) itemView.findViewById(R.id.list_item_sol_mda_packs);

            justStaticItemForbLPrepaid1 = (CardView) itemView.findViewById(R.id.justStaticItemForbLPrepaid1);
            justStaticItemForbLPrepaid2 = (CardView) itemView.findViewById(R.id.justStaticItemForbLPrepaid2);
            justStaticItemForbLPrepaid3 = (CardView) itemView.findViewById(R.id.justStaticItemForbLPrepaid3);

            official_web_link = (TextView) itemView.findViewById(R.id.official_web_link);
            standard_volume_packs = (TextView) itemView.findViewById(R.id.standard_volume_packs);
            standard_volume_packs_info = (TextView) itemView.findViewById(R.id.standard_volume_packs_info);
            auto_renew_for_bl = (TextView) itemView.findViewById(R.id.auto_renew_for_bl);
            recharge_packs = (TextView) itemView.findViewById(R.id.recharge_packs);
            balance_recharge_packs = (TextView) itemView.findViewById(R.id.balance_recharge_packs);
            social_media_packs = (TextView) itemView.findViewById(R.id.social_media_packs);
            social_media_packs_info = (TextView) itemView.findViewById(R.id.social_media_packs_info);
            social_media_packs_sms_bundle = (TextView) itemView.findViewById(R.id.social_media_packs_sms_bundle);
            check_on_web = (TextView) itemView.findViewById(R.id.check_on_web);
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
}
