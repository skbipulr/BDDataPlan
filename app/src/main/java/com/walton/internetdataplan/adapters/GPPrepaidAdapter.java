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
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.walton.internetdataplan.R;
import com.walton.internetdataplan.models.GPModel;
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
public class GPPrepaidAdapter extends RecyclerView.Adapter<GPPrepaidAdapter.GPPreViewHolder> {
    private ArrayList<GPModel> mList;
    public Context mContext;
    public static boolean mGPPreSearch = false;

    public GPPrepaidAdapter(Context context, ArrayList<GPModel> list, boolean mStatus) {
        mList = list;
        mContext = context;
        mGPPreSearch = mStatus;

    }

    @Override
    public GPPreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gp_prepaid_layout, parent, false);
        GPPreViewHolder gpViewHolder = new GPPreViewHolder(view);
        return gpViewHolder;
    }

    @Override
    public void onBindViewHolder(final GPPreViewHolder holder, int position) {
        holder.official_web_link.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Call3rdPartyApps
                        .openBrowser(mContext, "https://www.grameenphone.com/personal/internet/packages#tab-phone-specification3");
            }
        });
        if (mGPPreSearch) {
            holder.justStaticItemForGPPRE.setVisibility(View.GONE);
        } else {
            holder.justStaticItemForGPPRE.setVisibility(View.VISIBLE);
        }
        //create our new array adapter


        // Standard media packs
        String mStdName = "Standard Volume Packs";
        ArrayAdapter<GPModel> mStdAdapter = new propertyArrayAdapter(mContext, 0, mList);
        holder.list_item.setAdapter(mStdAdapter);
        // height based according to children
        setListViewHeightBasedOnChildren(holder.list_item);
        if (AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng")) {
            convertEnglish(holder);
        } else {
            convertBangla(holder);
        }


    }


    private void convertEnglish(GPPreViewHolder holder) {

        holder.official_web_link.setText("" + mContext.getResources().getString(R.string.g_internet_plan));
        holder.check_on_web.setText("" + mContext.getResources().getString(R.string.check_on_web));
        holder.terms_and_conditions.setText("" + mContext.getResources().getString(R.string.terms_and_conditions));
        holder.application_charge_is_not_applicable.setText("" + mContext.getResources().getString(R.string.application_charge_is_not_applicable));
        holder.to_turn_sms_5000.setText("" + mContext.getResources().getString(R.string.to_turn_sms_5000));
        holder.to_stop_sms_5000.setText("" + mContext.getResources().getString(R.string.to_stop_sms_5000));
        holder.extra_charge_fee_for_gp.setText("" + mContext.getResources().getString(R.string.extra_charge_fee_for_gp));
        holder.vat.setText("" + mContext.getResources().getString(R.string.vat));


    }

    private void convertBangla(GPPreViewHolder holder) {

        holder.official_web_link.setText("" + mContext.getResources().getString(R.string.g_internet_plan_bn));
        holder.check_on_web.setText("" + mContext.getResources().getString(R.string.check_on_web_bn));
        holder.terms_and_conditions.setText("" + mContext.getResources().getString(R.string.terms_and_conditions_bn));
        holder.application_charge_is_not_applicable.setText("" + mContext.getResources().getString(R.string.application_charge_is_not_applicable_bn));
        holder.to_turn_sms_5000.setText("" + mContext.getResources().getString(R.string.to_turn_sms_5000_bn));
        holder.to_stop_sms_5000.setText("" + mContext.getResources().getString(R.string.to_stop_sms_5000_bn));
        holder.extra_charge_fee_for_gp.setText("" + mContext.getResources().getString(R.string.extra_charge_fee_for_gp_bn));
        holder.vat.setText("" + mContext.getResources().getString(R.string.vat_bn));
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class GPPreViewHolder extends RecyclerView.ViewHolder {
        public ListView list_item;
        public static CardView justStaticItemForGPPRE;
        public TextView official_web_link, terms_and_conditions, application_charge_is_not_applicable, to_turn_sms_5000, to_stop_sms_5000, extra_charge_fee_for_gp, check_on_web,vat;

        public GPPreViewHolder(View itemView) {
            super(itemView);
            list_item = (ListView) itemView.findViewById(R.id.list_item);
            justStaticItemForGPPRE = (CardView) itemView.findViewById(R.id.justStaticItemForGPPRE);
            terms_and_conditions = (TextView) itemView.findViewById(R.id.terms_and_conditions);
            application_charge_is_not_applicable = (TextView) itemView.findViewById(R.id.application_charge_is_not_applicable);
            to_turn_sms_5000 = (TextView) itemView.findViewById(R.id.to_turn_sms_5000);
            to_stop_sms_5000 = (TextView) itemView.findViewById(R.id.to_stop_sms_5000);
            extra_charge_fee_for_gp = (TextView) itemView.findViewById(R.id.extra_charge_fee_for_gp);
            check_on_web = (TextView) itemView.findViewById(R.id.check_on_web);
            official_web_link = (TextView) itemView.findViewById(R.id.official_web_link);
            vat = (TextView) itemView.findViewById(R.id.vat);
        }
    }

    //custom ArrayAdapter
    class propertyArrayAdapter extends ArrayAdapter<GPModel> {

        private Context context;
        private List<GPModel> rentalProperties;

        //constructor, call on creation
        public propertyArrayAdapter(Context context, int resource, ArrayList<GPModel> objects) {
            super(context, resource, objects);
            this.context = context;
            this.rentalProperties = objects;
        }

        //called when rendering the list
        public View getView(int position, final View convertView, ViewGroup parent) {


            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.gp_prepaid_item_layout, null);


            GPModel gp = rentalProperties.get(position);

            final String mAutoRenCode = gp.getmSMSCode();
            final String mAutoInsBody = gp.getmSmsBody();
            final String mPackage = gp.getmPackageName();
            final String mDialCode = gp.getmDirectDialingCode();

            final TableLayout tableLayout1 = (TableLayout) view.findViewById(R.id.tableLayout1);
            tableLayout1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(tableLayout1, mAutoRenCode, mAutoInsBody, mPackage, mDialCode);
                }
            });
            TextView packageTime = (TextView) view.findViewById(R.id.packageTime);

            TextView duration = (TextView) view.findViewById(R.id.duration);


            if (gp.getmValidity().equals("Per MB")) {
                duration.setText("Duration : " + gp.getmValidity() + "");
            } else {
                if (AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng")) {
                    packageTime.setText("" + mContext.getResources().getString(R.string.packge) + " : " + gp.getmPackageName());
                    if (gp.getmValidity().equals("1")) {
                        duration.setText("Duration: " + gp.getmValidity() + " day");
                    } else {
                        duration.setText("Duration: " + gp.getmValidity() + " days");
                    }


                    if (mPackage.equals("Pay As You Go")) {
                        packageTime.setText("Package: " + mPackage);
                        packageTime.setTextSize(12);
                    } else {
                        packageTime.setText("Package: " + mPackage);
                        packageTime.setTextSize(16);
                    }


                } else {
                    packageTime.setText("" + mContext.getResources().getString(R.string.packge_bn) + " : " + gp.getmPackageName());

                    duration.setText("" + mContext.getResources().getString(R.string.duration_bn) + " : " + gp.getmValidity() + " " + mContext.getResources().getString(R.string.day_bn));


                    if (mPackage.equals("Pay As You Go")) {
                        packageTime.setText("" + mContext.getResources().getString(R.string.packge_bn) + " : " + gp.getmPackageName());
                        packageTime.setTextSize(12);
                    } else {
                        packageTime.setText("" + mContext.getResources().getString(R.string.packge_bn) + " : " + gp.getmPackageName());
                        packageTime.setTextSize(16);
                    }

                }

            }


            TextView balance = (TextView) view.findViewById(R.id.balance);
            balance.setText(gp.getmPrice() + " /-");
            final ImageView submenuarrow = (ImageView) view.findViewById(R.id.submenuarrow);
            return view;
        }
    }


    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view, String mAutoRenCode, String mAutoInsBody, String mPackage, String mDialCode) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view, Gravity.RIGHT);
        MenuInflater inflater = popup.getMenuInflater();
        if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
        {
            inflater.inflate(R.menu.submenu, popup.getMenu());
        }
        else
        {
            inflater.inflate(R.menu.submenu_bn, popup.getMenu());
        }

        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(mAutoRenCode, mAutoInsBody, mPackage, mDialCode));
        popup.show();
    }


    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        String mAutoRenCode, mAutoInsBody, mPackage, mDialCode;

        public MyMenuItemClickListener(String mAutoRenCode, String mAutoInsBody, String mPackage, String mDialCode) {
            this.mAutoRenCode = mAutoRenCode;
            this.mAutoInsBody = mAutoInsBody;
            this.mPackage = mPackage;
            this.mDialCode = mDialCode;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.purchase:
                    WHelper.getInstance(mContext).DialingCode(mDialCode);

                    return true;
                case R.id.details:
                    if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
                    {
                        WHelper.getInstance(mContext).showMsgDialogForGP(mContext, "Package: " + mPackage, mAutoInsBody, mAutoRenCode);
                    }
                    else
                    {
                        WHelper.getInstance(mContext).showMsgDialogForGP(mContext, "প্যাকেজ: " + mPackage, mAutoInsBody, mAutoRenCode);
                    }

                    return true;
                default:
            }
            return false;
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
