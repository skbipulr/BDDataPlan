package com.walton.internetdataplan.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.walton.internetdataplan.R;
import com.walton.internetdataplan.models.TeletalkModel;
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
public class TeletalkPostpaidAdapter extends RecyclerView.Adapter<TeletalkPostpaidAdapter.TeletalkViewHolder> {
    private ArrayList<TeletalkModel> mList;
    public Context mContext;
    public static boolean mTeletalkPostpaidSearch = false;

    public TeletalkPostpaidAdapter(Context context, ArrayList<TeletalkModel> list, boolean mSearch) {
        mList = list;
        mContext = context;
        mTeletalkPostpaidSearch = mSearch;

    }

    @Override
    public TeletalkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teletalk_postpaid_layout, parent, false);
        TeletalkViewHolder gpViewHolder = new TeletalkViewHolder(view);
        return gpViewHolder;
    }

    //custom ArrayAdapter
    class propertyArrayAdapter extends ArrayAdapter<TeletalkModel> {

        private Context context;
        private List<TeletalkModel> rentalProperties;

        //constructor, call on creation
        public propertyArrayAdapter(Context context, int resource, ArrayList<TeletalkModel> objects) {
            super(context, resource, objects);
            this.context = context;
            this.rentalProperties = objects;
        }

        //called when rendering the list
        public View getView(int position, final View convertView, ViewGroup parent) {


            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.teletalk_postpaid_item_layout, null);


            TeletalkModel gp = mList.get(position);
            final String mPackage = gp.getmPackageName();
            final String mVolume = gp.getmVolume();
            final String mPrice = gp.getmPrice();
            final String mDuration = gp.getmValidity();
            final String mDialingCode = gp.getmDirectDialingCode();
            final String mAutoRenCode = gp.getmSMSCode();
            final String mAutoInsBodyForPostpaid = gp.getmSmsBodyForPost();
            final String mAutoInsBodyForPostPaid = gp.getmSmsBodyForPost();

            final TableLayout tableLayout1 = (TableLayout) view.findViewById(R.id.tableLayout1);
            tableLayout1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(tableLayout1, mDialingCode, mAutoRenCode, mAutoInsBodyForPostpaid, mAutoInsBodyForPostPaid, mVolume);
                }
            });
            TextView packageTime = (TextView) view.findViewById(R.id.packageTime);
//            packageTime.setTextSize(10);
            TextView duration = (TextView) view.findViewById(R.id.duration);
            TextView balance = (TextView) view.findViewById(R.id.balance);
            balance.setText(mPrice + " /-");

            TextView volume = (TextView) view.findViewById(R.id.volume);
            if (AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng")) {
                packageTime.setText("" + mContext.getResources().getString(R.string.packge) + " : " + mPackage);

                if (mDuration.equals("1")) {
                    duration.setText("duration: " + mDuration + " day");
                } else {
                    duration.setText("duration: " + mDuration + " days");
                }
                volume.setText("Volume: " + mVolume + "");
            }
            else
            {
                packageTime.setText("" + mContext.getResources().getString(R.string.packge_bn) + " : " + mPackage);

                duration.setText("" + mContext.getResources().getString(R.string.duration_bn) + " : " + mDuration + " " + mContext.getResources().getString(R.string.day_bn));

                volume.setText(" "+mContext.getResources().getString(R.string.volume_bn) + " : " + mVolume + "");
            }




            return view;
        }
    }
    private void convertEnglish(TeletalkViewHolder holder) {
        holder.teletalk_postpaid_condition_1.setText(""+mContext.getResources().getString(R.string.teletalk_postpaid_condition_1));
        holder.teletalk_postpaid_condition_2.setText(""+mContext.getResources().getString(R.string.teletalk_postpaid_condition_2));
        holder.terms_and_conditions.setText(""+mContext.getResources().getString(R.string.terms_and_conditions));
        holder.official_web_link.setText(""+mContext.getResources().getString(R.string.g_internet_plan));
        holder.check_on_web.setText(""+mContext.getResources().getString(R.string.check_on_web));
        holder.vat.setText(""+mContext.getResources().getString(R.string.vat));
    }

    private void convertBangla(TeletalkViewHolder holder) {
        holder.teletalk_postpaid_condition_1.setText(""+mContext.getResources().getString(R.string.teletalk_postpaid_condition_1_bn));
        holder.teletalk_postpaid_condition_2.setText(""+mContext.getResources().getString(R.string.teletalk_postpaid_condition_2_bn));
        holder.terms_and_conditions.setText(""+mContext.getResources().getString(R.string.terms_and_conditions_bn));
        holder.official_web_link.setText(""+mContext.getResources().getString(R.string.g_internet_plan_bn));
        holder.check_on_web.setText(""+mContext.getResources().getString(R.string.check_on_web_bn));
        holder.vat.setText(""+mContext.getResources().getString(R.string.vat_bn));
    }

    @Override
    public void onBindViewHolder(final TeletalkViewHolder holder, int position) {

        holder.official_web_link.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Call3rdPartyApps
                        .openBrowser(mContext, "http://www.teletalk.com.bd/packages/dataPackageDetails.jsp?packageID=22006");
            }
        });
        if (mTeletalkPostpaidSearch) {
            holder.justStaticItemForTeletalkPostpaid.setVisibility(View.GONE);
        } else {
            holder.justStaticItemForTeletalkPostpaid.setVisibility(View.VISIBLE);
//                holder.splash_progress.setVisibility(View.GONE);
        }
        //create our new array adapter


        // Standard media packs
        String mStdName = "Standard Volume Packs";
        ArrayAdapter<TeletalkModel> mStdAdapter = new propertyArrayAdapter(mContext, 0, mList);
        holder.list_item.setAdapter(mStdAdapter);
        // height based according to children
        setListViewHeightBasedOnChildren(holder.list_item);
        if (AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng")) {
            convertEnglish(holder);
        } else {
            convertBangla(holder);
        }
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */


    private void showPopupMenu(View view, String mDialingCode, String mAutoRenCode, String mAutoInsBodyForPrepaid, String mAutoInsBodyForPostPaid, String mVolume) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view, Gravity.RIGHT);
        MenuInflater inflater = popup.getMenuInflater();



        if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
        {
            inflater.inflate(R.menu.teletalkprepaidsubmenu, popup.getMenu());
        }
        else
        {
            inflater.inflate(R.menu.teletalkprepaidsubmenu_bn, popup.getMenu());
        }








        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(mDialingCode, mAutoRenCode, mAutoInsBodyForPrepaid, mAutoInsBodyForPostPaid, mVolume));
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        String mVolume, mDialingCode, mAutoRenCode, mAutoInsBodyForPrepaid, mAutoInsBodyForPostPaid;

        public MyMenuItemClickListener(String mDialingCode, String mAutoRenCode, String mAutoInsBodyForPrepaid, String mAutoInsBodyForPostPaid, String mVolume) {
            this.mVolume = mVolume;
            this.mDialingCode = mDialingCode;
            this.mAutoRenCode = mAutoRenCode;
            this.mAutoInsBodyForPrepaid = mAutoInsBodyForPrepaid;
            this.mAutoInsBodyForPostPaid = mAutoInsBodyForPostPaid;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.purchase:
                    if (mDialingCode.equals("")) {
                        if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
                        {
                            Toast.makeText(mContext,"Only purchase via sms",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(mContext,"কেবলমাত্র  এসএমএস এর মাধ্যমে ক্রয় করা যাবে|",Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        WHelper.getInstance(mContext).DialingCode(mDialingCode);
                    }

                    return true;
                case R.id.prepaid:
                    if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
                    {
                        WHelper.showMsgDialogForTeletalk(mContext, "Package: " + mVolume, mAutoInsBodyForPrepaid, mAutoRenCode);
                    }
                    else
                    {
                        WHelper.showMsgDialogForTeletalk(mContext, "প্যাকেজ: " + mVolume, mAutoInsBodyForPrepaid, mAutoRenCode);
                    }


                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class TeletalkViewHolder extends RecyclerView.ViewHolder {
        public ListView list_item;
        public static CardView justStaticItemForTeletalkPostpaid;
        public TextView official_web_link,teletalk_postpaid_condition_1,teletalk_postpaid_condition_2,terms_and_conditions,check_on_web,vat;
        public ProgressBar splash_progress;

        public TeletalkViewHolder(View itemView) {
            super(itemView);
            list_item = (ListView) itemView.findViewById(R.id.list_item);
            justStaticItemForTeletalkPostpaid = (CardView) itemView.findViewById(R.id.justStaticItemForTeletalkPostpaid);
            official_web_link = (TextView) itemView.findViewById(R.id.official_web_link);
            teletalk_postpaid_condition_1 = (TextView) itemView.findViewById(R.id.teletalk_postpaid_condition_1);
            teletalk_postpaid_condition_2 = (TextView) itemView.findViewById(R.id.teletalk_postpaid_condition_2);
            terms_and_conditions = (TextView) itemView.findViewById(R.id.terms_and_conditions);
            check_on_web = (TextView) itemView.findViewById(R.id.check_on_web);
            vat = (TextView) itemView.findViewById(R.id.vat);
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
