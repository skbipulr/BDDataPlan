package com.walton.internetdataplan.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.walton.internetdataplan.R;
import com.walton.internetdataplan.models.AirtelModel;
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
public class AirtelPrepaidAdapter extends RecyclerView.Adapter<AirtelPrepaidAdapter.AirtelViewHolder> {
    private ArrayList<AirtelModel> mList;
    public Context mContext;
    public static boolean mAirtelPreSearch=false;

    public AirtelPrepaidAdapter(Context context, ArrayList<AirtelModel> list,boolean mSearch) {
        mList = list;
        mContext = context;
        mAirtelPreSearch=mSearch;

    }

    @Override
    public AirtelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.airtel_prepaid_layout, parent, false);
        AirtelViewHolder gpViewHolder = new AirtelViewHolder(view);
        return gpViewHolder;
    }

    //custom ArrayAdapter
    class propertyArrayAdapter extends ArrayAdapter<AirtelModel> {

        private Context context;
        private List<AirtelModel> rentalProperties;

        //constructor, call on creation
        public propertyArrayAdapter(Context context, int resource, ArrayList<AirtelModel> objects) {
            super(context, resource, objects);
            this.context = context;
            this.rentalProperties = objects;
        }

        //called when rendering the list
        public View getView(int position, final View convertView, ViewGroup parent) {


            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.airtel_prepaid_item_layout, null);


            AirtelModel gp = rentalProperties.get(position);

            final String mPackage = gp.getmPackageName();
            final String mDialCode = gp.getmDirectDialingCode();

            final TableLayout tableLayout1=(TableLayout)view.findViewById(R.id.tableLayout1);
            tableLayout1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("mDialCode",":recharge:"+mDialCode);
                    if(mDialCode.contains("Recharge"))
                    {
                        if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
                        {
                            AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                            ab.setTitle("Package: " + mPackage);
                            ab.setMessage("Used for: " + mDialCode+" TK");
                            ab.setNegativeButton("OK", new DialogInterface.OnClickListener() {
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
                            ab.setTitle("প্যাকেজ: " + mPackage);
                            ab.setMessage("ব্যবহারের জন্য: " + mDialCode+" টাকা");
                            ab.setNegativeButton("ঠিক আছে", new DialogInterface.OnClickListener() {
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
                    else {
                        WHelper.getInstance(mContext).DialingCode(mDialCode);
                    }
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

                        packageTime.setText("Package: " + mPackage);



                } else {
                    packageTime.setText("" + mContext.getResources().getString(R.string.packge_bn) + " : " + gp.getmPackageName());

                    duration.setText("" + mContext.getResources().getString(R.string.duration_bn) + " : " + gp.getmValidity() + " " + mContext.getResources().getString(R.string.day_bn));

                        packageTime.setText("" + mContext.getResources().getString(R.string.packge_bn) + " : " + gp.getmPackageName());


                }

            }

            TextView balance = (TextView) view.findViewById(R.id.balance);
            balance.setText(gp.getmPrice() + " /-");


            return view;
        }
    }

    @Override
    public void onBindViewHolder(final AirtelViewHolder holder, int position) {
        holder.official_web_link.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Call3rdPartyApps
                        .openBrowser(mContext,"http://www.bd.airtel.com/personal/3g/internet-package/selfcare-packages");
            }
        });
        if(mAirtelPreSearch) {
            holder.justStaticItemForAirtelPre.setVisibility(View.GONE);
        }
        else
        {
            holder.justStaticItemForAirtelPre.setVisibility(View.VISIBLE);
        }
        //create our new array adapter


        // Standard media packs
        String mStdName = "Standard Volume Packs";
        ArrayAdapter<AirtelModel> mStdAdapter = new propertyArrayAdapter(mContext, 0, mList);
        holder.list_item.setAdapter(mStdAdapter);
        // height based according to children
        setListViewHeightBasedOnChildren(holder.list_item);
        if (AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng")) {
            convertEnglish(holder);
        } else {
            convertBangla(holder);
        }

    }

    private void convertEnglish(AirtelViewHolder holder) {


        holder.official_web_link.setText(""+mContext.getResources().getString(R.string.g_internet_plan));
        holder.terms_and_conditions.setText(""+mContext.getResources().getString(R.string.terms_and_conditions));
        holder.airtel_prepaid_balance_check.setText(""+mContext.getResources().getString(R.string.airtel_prepaid_balance_check));
        holder.check_on_web.setText(""+mContext.getResources().getString(R.string.check_on_web));
        holder.vat.setText(""+mContext.getResources().getString(R.string.vat));
    }

    private void convertBangla(AirtelViewHolder holder) {
        holder.official_web_link.setText(""+mContext.getResources().getString(R.string.g_internet_plan_bn));
        holder.terms_and_conditions.setText(""+mContext.getResources().getString(R.string.terms_and_conditions_bn));
        holder.airtel_prepaid_balance_check.setText(""+mContext.getResources().getString(R.string.airtel_prepaid_balance_check_bn));
        holder.check_on_web.setText(""+mContext.getResources().getString(R.string.check_on_web_bn));
        holder.vat.setText(""+mContext.getResources().getString(R.string.vat_bn));
    }



    @Override
    public int getItemCount() {
        return 1;
    }

    public static class AirtelViewHolder extends RecyclerView.ViewHolder {
        public ListView list_item;
        public static CardView justStaticItemForAirtelPre;
        public TextView official_web_link,terms_and_conditions,airtel_prepaid_balance_check,check_on_web,vat;

        public AirtelViewHolder(View itemView) {
            super(itemView);
            list_item = (ListView) itemView.findViewById(R.id.list_item);
            justStaticItemForAirtelPre = (CardView) itemView.findViewById(R.id.justStaticItemForAirtelPre);
            official_web_link = (TextView) itemView.findViewById(R.id.official_web_link);
            terms_and_conditions = (TextView) itemView.findViewById(R.id.terms_and_conditions);
            airtel_prepaid_balance_check = (TextView) itemView.findViewById(R.id.airtel_prepaid_balance_check);
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
