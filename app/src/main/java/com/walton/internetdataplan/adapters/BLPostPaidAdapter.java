package com.walton.internetdataplan.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
public class BLPostPaidAdapter extends RecyclerView.Adapter<BLPostPaidAdapter.BLPrepaidViewHolder> {
    public ArrayList<BLModel> mGPList;
    public Context mContext;
    public static boolean mBLProSearch = false;

    public BLPostPaidAdapter(Context context, ArrayList<BLModel> gpList, boolean mSearch) {
        mGPList = gpList;
        mContext = context;
        mBLProSearch = mSearch;

    }

    @Override
    public BLPrepaidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bl_postpaid_layout, parent, false);
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
            View view = inflater.inflate(R.layout.bl_postpaid_item_layout, null);


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
            TextView packageTime = (TextView) view.findViewById(R.id.packageTime);
            TextView duration = (TextView) view.findViewById(R.id.duration);
            TextView balance = (TextView) view.findViewById(R.id.balance);
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
            balance.setText(gp.getmPrice() + " /-");
//            final ImageView submenuarrow = (ImageView) view.findViewById(R.id.submenuarrow);
//            submenuarrow.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    showPopupMenu(submenuarrow,mAutoRenCode, mAutoInsBody, mPackage, mDialCodeWith, mDialCodeWithout);
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
                        .openBrowser(mContext, "http://www.banglalink.com.bd/en/personal/internet?shs_term_node_tid_depth=145&packagevaliditytype=8&price%255Bmin%255D=0&price%255Bmax%255D=2000&validity%255Bmin%255D=0&validity%255Bmax%255D=30");
            }
        });
        if (mBLProSearch) {
            holder.justStaticItemForbLPost1.setVisibility(View.GONE);
            holder.justStaticItemForbLPost2.setVisibility(View.GONE);
            ArrayAdapter<BLModel> mSolAda = new propertyArrayAdapter(mContext, 0, mGPList);
            holder.list_item_bill_base.setAdapter(mSolAda);
            // height based according to children
            setListViewHeightBasedOnChildren(holder.list_item_bill_base);

        } else {
            holder.justStaticItemForbLPost1.setVisibility(View.VISIBLE);
            holder.justStaticItemForbLPost2.setVisibility(View.VISIBLE);
            // bill cycle based Packs
            String mBCBPName = "bill cycle based Packs";
            ArrayList<BLModel> mBCBPList = new ArrayList<BLModel>();
            if (mBCBPList != null && mBCBPList.size() > 0) {
                mBCBPList.clear();
            }
            mBCBPList = AppManager.getInstance(mContext).retrieveBLPostpaidDataPackList(mContext, mBCBPName, "");
            //create our new array adapter
            ArrayAdapter<BLModel> adapter = new propertyArrayAdapter(mContext, 0, mBCBPList);
            holder.list_item_bill_base.setAdapter(adapter);
            // height based according to children
            setListViewHeightBasedOnChildren(holder.list_item_bill_base);


            // bill cycle based Packs
            String mAddonsName = "add-ons Packs";
            ArrayList<BLModel> mAddonsList = new ArrayList<BLModel>();
            if (mAddonsList != null && mAddonsList.size() > 0) {
                mAddonsList.clear();
            }
            mAddonsList = AppManager.getInstance(mContext).retrieveBLPostpaidDataPackList(mContext, mAddonsName, "");
            //create our new array adapter
            ArrayAdapter<BLModel> mAddOnsadapter = new propertyArrayAdapter(mContext, 0, mAddonsList);
            holder.list_item_one_time.setAdapter(mAddOnsadapter);
            setListViewHeightBasedOnChildren(holder.list_item_one_time);
        }
        if (AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng")) {
            convertEnglish(holder);
        } else {
            convertBangla(holder);
        }



    }



    private void convertEnglish(BLPrepaidViewHolder holder) {


        holder.official_web_link.setText(""+mContext.getResources().getString(R.string.g_internet_plan));

        holder.check_on_web.setText(""+mContext.getResources().getString(R.string.check_on_web));
        holder.bill_cycle_based_packs.setText(""+mContext.getResources().getString(R.string.bill_cycle_based_packs));
        holder.bill_cycle_based_packs_info.setText(""+mContext.getResources().getString(R.string.bill_cycle_based_packs_info));
        holder.one_time_pack_add_ons.setText(""+mContext.getResources().getString(R.string.one_time_pack_add_ons));
        holder.one_time_pack_add_ons_info.setText(""+mContext.getResources().getString(R.string.one_time_pack_add_ons_info));
        holder.one_time_pack_add_ons_info.setText(""+mContext.getResources().getString(R.string.one_time_pack_add_ons_info));


    }

    private void convertBangla(BLPrepaidViewHolder holder) {
        holder.official_web_link.setText(""+mContext.getResources().getString(R.string.g_internet_plan_bn));

        holder.check_on_web.setText(""+mContext.getResources().getString(R.string.check_on_web_bn));

        holder.bill_cycle_based_packs.setText(""+mContext.getResources().getString(R.string.bill_cycle_based_packs_bn));
        holder.bill_cycle_based_packs_info.setText(""+mContext.getResources().getString(R.string.bill_cycle_based_packs_info_bn));
        holder.one_time_pack_add_ons.setText(""+mContext.getResources().getString(R.string.one_time_pack_add_ons_bn));
        holder.one_time_pack_add_ons_info.setText(""+mContext.getResources().getString(R.string.one_time_pack_add_ons_info_bn));
    }
    @Override
    public int getItemCount() {
        return 1;
    }

    public static class BLPrepaidViewHolder extends RecyclerView.ViewHolder {
        public ListView list_item_bill_base, list_item_one_time;
        public static CardView justStaticItemForbLPost1, justStaticItemForbLPost2;
        public TextView official_web_link,bill_cycle_based_packs,bill_cycle_based_packs_info,one_time_pack_add_ons,one_time_pack_add_ons_info,check_on_web;

        public BLPrepaidViewHolder(View itemView) {
            super(itemView);
            list_item_bill_base = (ListView) itemView.findViewById(R.id.list_item_bill_base);
            list_item_one_time = (ListView) itemView.findViewById(R.id.list_item_one_time);
            bill_cycle_based_packs = (TextView) itemView.findViewById(R.id.bill_cycle_based_packs);
            bill_cycle_based_packs_info = (TextView) itemView.findViewById(R.id.bill_cycle_based_packs_info);
            one_time_pack_add_ons = (TextView) itemView.findViewById(R.id.one_time_pack_add_ons);
            one_time_pack_add_ons_info = (TextView) itemView.findViewById(R.id.one_time_pack_add_ons_info);
            justStaticItemForbLPost1 = (CardView) itemView.findViewById(R.id.justStaticItemForbLPost1);
            justStaticItemForbLPost2 = (CardView) itemView.findViewById(R.id.justStaticItemForbLPost2);

            official_web_link = (TextView) itemView.findViewById(R.id.official_web_link);
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
