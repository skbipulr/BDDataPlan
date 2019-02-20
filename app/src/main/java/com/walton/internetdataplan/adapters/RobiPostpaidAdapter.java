package com.walton.internetdataplan.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import android.widget.TableLayout;
import android.widget.TextView;

import com.walton.internetdataplan.R;
import com.walton.internetdataplan.models.RobiModel;
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
public class RobiPostpaidAdapter extends RecyclerView.Adapter<RobiPostpaidAdapter.RobiPostpaidViewHolder> {
    private ArrayList<RobiModel> mList;
    public Context mContext;
    public static boolean mRobiPostSearch=false;

    public RobiPostpaidAdapter(Context context, ArrayList<RobiModel> list,boolean mSearch) {
        mList = list;
        mContext = context;
        mRobiPostSearch=mSearch;

    }

    @Override
    public RobiPostpaidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.robi_postpaid_layout, parent, false);
        RobiPostpaidViewHolder mViewHolder = new RobiPostpaidViewHolder(view);
        return mViewHolder;
    }



    @Override
    public void onBindViewHolder(final RobiPostpaidViewHolder holder, int position) {
        holder.official_web_link.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Call3rdPartyApps
                        .openBrowser(mContext,"https://www.robi.com.bd/internet/internet-and-wifi-packages/internet-packages/?lang=eng");
            }
        });
        if (mRobiPostSearch)
        {
            holder.justStaticItemForRobiPost.setVisibility(View.GONE);
        }
        else
        {
            holder.justStaticItemForRobiPost.setVisibility(View.VISIBLE);
        }
        //create our new array adapter


        // Standard media packs
        String mStdName = "Standard Volume Packs";
        ArrayAdapter<RobiModel> mStdAdapter = new propertyArrayAdapter(mContext, 0, mList);
        holder.list_item.setAdapter(mStdAdapter);
        // height based according to children
        setListViewHeightBasedOnChildren(holder.list_item);
        if (AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng")) {
            convertEnglish(holder);
        } else {
            convertBangla(holder);
        }
    }
    private void convertEnglish(RobiPostpaidViewHolder holder) {
        holder.robi_postpaid_condition_1.setText(""+mContext.getResources().getString(R.string.robi_postpaid_condition_1));
        holder.robi_postpaid_condition_2.setText(""+mContext.getResources().getString(R.string.robi_postpaid_condition_2));
        holder.robi_postpaid_condition_3.setText(""+mContext.getResources().getString(R.string.robi_postpaid_condition_3));
        holder.robi_postpaid_condition_4.setText(""+mContext.getResources().getString(R.string.robi_postpaid_condition_4));
        holder.terms_and_conditions.setText(""+mContext.getResources().getString(R.string.terms_and_conditions));
        holder.check_on_web.setText(""+mContext.getResources().getString(R.string.check_on_web));
        holder.official_web_link.setText(""+mContext.getResources().getString(R.string.g_internet_plan));


    }

    private void convertBangla(RobiPostpaidViewHolder holder) {

        holder.robi_postpaid_condition_1.setText(""+mContext.getResources().getString(R.string.robi_postpaid_condition_1_bn));
        holder.robi_postpaid_condition_2.setText(""+mContext.getResources().getString(R.string.robi_postpaid_condition_2_bn));
        holder.robi_postpaid_condition_3.setText(""+mContext.getResources().getString(R.string.robi_postpaid_condition_3_bn));
        holder.robi_postpaid_condition_4.setText(""+mContext.getResources().getString(R.string.robi_postpaid_condition_4_bn));
        holder.terms_and_conditions.setText(""+mContext.getResources().getString(R.string.terms_and_conditions_bn));
        holder.check_on_web.setText(""+mContext.getResources().getString(R.string.check_on_web_bn));
        holder.official_web_link.setText(""+mContext.getResources().getString(R.string.g_internet_plan_bn));

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class RobiPostpaidViewHolder extends RecyclerView.ViewHolder {
        public ListView list_item;
        public static CardView justStaticItemForRobiPost;
        public TextView official_web_link,robi_postpaid_condition_1,robi_postpaid_condition_2,robi_postpaid_condition_3,robi_postpaid_condition_4,terms_and_conditions,check_on_web;

        public RobiPostpaidViewHolder(View itemView) {
            super(itemView);
            list_item = (ListView) itemView.findViewById(R.id.list_item);
            justStaticItemForRobiPost = (CardView) itemView.findViewById(R.id.justStaticItemForRobiPro);
            official_web_link = (TextView) itemView.findViewById(R.id.official_web_link);
            robi_postpaid_condition_1 = (TextView) itemView.findViewById(R.id.robi_postpaid_condition_1);
            robi_postpaid_condition_2 = (TextView) itemView.findViewById(R.id.robi_postpaid_condition_2);
            robi_postpaid_condition_3 = (TextView) itemView.findViewById(R.id.robi_postpaid_condition_3);
            robi_postpaid_condition_4 = (TextView) itemView.findViewById(R.id.robi_postpaid_condition_4);
            terms_and_conditions = (TextView) itemView.findViewById(R.id.terms_and_conditions);
            check_on_web = (TextView) itemView.findViewById(R.id.check_on_web);
        }
    }

    //custom ArrayAdapter
    class propertyArrayAdapter extends ArrayAdapter<RobiModel> {

        private Context context;
        private List<RobiModel> rentalProperties;

        //constructor, call on creation
        public propertyArrayAdapter(Context context, int resource, ArrayList<RobiModel> objects) {
            super(context, resource, objects);
            this.context = context;
            this.rentalProperties = objects;
        }

        //called when rendering the list
        public View getView(int position, final View convertView, ViewGroup parent) {


            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.robi_postpaid_item_layout, null);


            RobiModel gp = mList.get(position);
            final String mPackage = gp.getmPackageName();

            final String mDialCode = gp.getmDirectDialingCode();
            final String mDescription = gp.getmDescription();


            TextView packageTime = (TextView) view.findViewById(R.id.packageTime);
            TextView duration = (TextView) view.findViewById(R.id.duration);
            TextView mAutoRenew = (TextView) view.findViewById(R.id.autoRenew);
//            TextView active = (TextView) view.findViewById(R.id.active);
            TextView balance = (TextView) view.findViewById(R.id.balance);

            final TableLayout tableLayout1=(TableLayout)view.findViewById(R.id.tableLayout1);
            tableLayout1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(tableLayout1, mPackage, mDialCode, mDescription);
                }
            });
            Log.e("hellosd", "mPackage:" + gp.getmPackageName() + "\n dialCode:" + gp.mDirectDialingCode + "\n price:" + gp.getmPrice() + "\n description:" + gp.getmDescription() + "\n process:" + gp.getmProcess12() + "\n details:" + gp.getmDetailsActivateProcess());
            if (AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng")) {
                if (gp.getmValidity().equals("1")) {
                    duration.setText("duration: " + gp.getmValidity() + " day");
                } else {
                    duration.setText("duration: " + gp.getmValidity() + " days");
                }
                mAutoRenew.setText("Auto Renew: " + gp.getmAutoRenew());
                packageTime.setText("Package: " + gp.getmPackageName());
            }
            else
            {
                packageTime.setText("" + mContext.getResources().getString(R.string.packge_bn) + " : " + gp.getmPackageName());

                duration.setText("" + mContext.getResources().getString(R.string.duration_bn) + " : " + gp.getmValidity() + " " + mContext.getResources().getString(R.string.day_bn));

                if (gp.getmAutoRenew().equals("Yes"))
                {
                    mAutoRenew.setText(""+mContext.getResources().getString(R.string.auto_renew_bn) + " : " + " হাঁ ");
                }
                else
                {
                    mAutoRenew.setText(""+mContext.getResources().getString(R.string.auto_renew_bn) + " : " + " না ");
                }

            }
            balance.setText(gp.getmPrice() + " /-");


            return view;
        }
    }
    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view, String mPackage, String mDialCode, String mDescription) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view, Gravity.RIGHT);
        MenuInflater inflater = popup.getMenuInflater();
        if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
        {
            inflater.inflate(R.menu.robimenu, popup.getMenu());
        }
        else
        {
            inflater.inflate(R.menu.robimenu_bn, popup.getMenu());
        }

        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(mPackage, mDialCode, mDescription));
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        String mPackage, mDialCode, mDescription = "";

        public MyMenuItemClickListener(String mPackage, String mDialCode, String mDescription) {
            this.mPackage = mPackage;
            this.mDialCode = mDialCode;
            this.mDescription = mDescription;
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
                        if (!mDescription.equals("")) {
                            AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                            ab.setTitle("Package: " + mPackage);
                            ab.setMessage("Used for: " + mDescription);
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
                    }
                    else
                    {
                        if (!mDescription.equals("")) {
                            AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                            ab.setTitle("প্যাকেজ: " + mPackage);
                            ab.setMessage("ব্যবহারের জন্য: " + mDescription);
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
