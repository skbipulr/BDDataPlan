package com.walton.internetdataplan.nvai;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.TrafficStats;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.viewpagerindicator.CirclePageIndicator;
import com.walton.internetdataplan.AppManager;
import com.walton.internetdataplan.R;
import com.walton.internetdataplan.models.InternetData;
import com.walton.internetdataplan.services.DailyConsumedService;
import com.walton.internetdataplan.utitls.AppsSettings;
import com.walton.internetdataplan.utitls.DataPlan;
import com.walton.internetdataplan.utitls.MyServiceRunning;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by DELL on 12/5/2016.
 */

public class NetUsageFragmentViewpagerAdapter extends PagerAdapter implements OnChartValueSelectedListener {
    private static final String WIFI_MOBILE_CHART_PREFERENCE = "chart_size_pref";
    public TextView add_data_plan_text, tvInfoDeviceModel, current_data_plan, usage_limit_notification, daily_consumed_alert, set_daily_limit, usage_limit_alert, set_limit_percentage;
    public RadioGroup radioGroup;
    public String radioButtonSelected = "";
    Context context;
    public TextView mMbOrGb;
    ArrayList<NetUsageAppModel> netUsageAppModelArrayList;
    ListView netUsageListView;
    AppLocalDatabaseHelper appLocalDatabaseHelper;
    Dialog dialog;
    ImageView addDataPlanImageView;
    CardView addNewPlanCardView;
    LinearLayout dataPlanInfoContainer;
    String planStartDate;
    String planEndDate;
    String durationType;
    String planSizeType;
    LineChart dataPlanLineChart;
    BarChart dataPlanBarChart;
    CheckBox dailyLimitAlertCheckBox;
    CheckBox usageLimitCheckBox;
    EditText dailyUsageEditText;
    EditText usagePercentageEditText;
    TextView currentDataPlanSize;
    TextView currentDataPlanDuration;
    TextView currentDataPlanPeriod;
    PieChart dataPlanPieChart;
    Typeface mTf;
    public RectF mOnValueSelectedRectF = new RectF();
    public Typeface mTfLight;
    ImageView deletePlanImageView;
    public int seekBarPosition = -1;
    RelativeLayout wifiMobileBarChartContainer;
    SharedPreferences sharedPreferences;


    public NetUsageFragmentViewpagerAdapter(Context context) {
        this.context = context;
        //  this.netUsageAppModelArrayList=netUsageAppModelArrayList;
        appLocalDatabaseHelper = new AppLocalDatabaseHelper(context);
        sharedPreferences = context.getSharedPreferences(WIFI_MOBILE_CHART_PREFERENCE, MODE_PRIVATE);



    }

    private void deleteDataPlanSetup_bn(String mTitle) {
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        adb.setIcon(R.drawable.attention_100);
        if (mTitle.equals("")) {
            adb.setTitle("" + context.getResources().getString(R.string.delete_data_plan_bn));
        } else {
            adb.setTitle(mTitle);
        }

//                    adb.setMessage("Your data plan setup has been reset if will you agree?");
        adb.setMessage("" + context.getResources().getString(R.string.data_plan_delete_bn));
        adb.setNegativeButton("" + context.getResources().getString(R.string.no_bn), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        adb.setPositiveButton("" + context.getResources().getString(R.string.agree_bn), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dataPlanInfoContainer.setVisibility(View.INVISIBLE);
                addNewPlanCardView.setVisibility(View.VISIBLE);
                //  reset all flag
                // delete data plan
                AppManager.getInstance(context).DeleteDataPlanInfo(context);
                // end of delete data plan
                // delete Internet Data Consume table //
//                AppManager.getInstance(context).DeleteInternetDataConsumeInfo(context);
                AppManager.getInstance(context).DeleteInternetData(context);
                // end of delete Internet Data Consume table //
                AppsSettings.getAppsSettings(context).setInternetConsumeLimiParcentigetOverCome(false);
                AppsSettings.getAppsSettings(context).setInternetConsumeParcentageLimit(0);
                AppsSettings.getAppsSettings(context).setDailyInternetConsumeLimitOverCome(false);
                AppsSettings.getAppsSettings(context).setDailyInternetConsumeLimit(0);
                AppsSettings.getAppsSettings(context).setDailyCheckBox(false);
                AppsSettings.getAppsSettings(context).setUsageCheckBox(false);
                dailyLimitAlertCheckBox.setChecked(false);
                usageLimitCheckBox.setChecked(false);
                dailyUsageEditText.setText("");
                usagePercentageEditText.setText("");
                // reset enddate
                planEndDate = null;
                // end of reset all flag
                dialogInterface.dismiss();
            }
        });
        adb.setCancelable(false);
        adb.create();
        adb.show();

    }

    private void deleteDataPlanSetup(String mTitle) {
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        adb.setIcon(R.drawable.attention_100);
        if (mTitle.equals("")) {
            adb.setTitle("Delete Data Plan");
        } else {
            adb.setTitle(mTitle);
        }

//                    adb.setMessage("Your data plan setup has been reset if will you agree?");
        adb.setMessage("" + context.getResources().getString(R.string.data_plan_delete));
        adb.setNegativeButton("No", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        adb.setPositiveButton("Agree", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dataPlanInfoContainer.setVisibility(View.INVISIBLE);
                addNewPlanCardView.setVisibility(View.VISIBLE);
                //  reset all flag
                // delete data plan
                AppManager.getInstance(context).DeleteDataPlanInfo(context);
                // end of delete data plan
                // delete Internet Data Consume table //
//                AppManager.getInstance(context).DeleteInternetDataConsumeInfo(context);
                AppManager.getInstance(context).DeleteInternetData(context);
                // end of delete Internet Data Consume table //
                AppsSettings.getAppsSettings(context).setInternetConsumeLimiParcentigetOverCome(false);
                AppsSettings.getAppsSettings(context).setInternetConsumeParcentageLimit(0);
                AppsSettings.getAppsSettings(context).setDailyInternetConsumeLimitOverCome(false);
                AppsSettings.getAppsSettings(context).setDailyInternetConsumeLimit(0);
                AppsSettings.getAppsSettings(context).setDailyCheckBox(false);
                AppsSettings.getAppsSettings(context).setUsageCheckBox(false);
                dailyLimitAlertCheckBox.setChecked(false);
                usageLimitCheckBox.setChecked(false);
                dailyUsageEditText.setText("");
                usagePercentageEditText.setText("");
                // reset enddate
                planEndDate = null;
                // end of reset all flag
                dialogInterface.dismiss();
            }
        });
        adb.setCancelable(false);
        adb.create();
        adb.show();

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {


        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);
        View viewpagerLayout;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (position == 1) {

            viewpagerLayout = inflater.inflate(R.layout.nvai_viewpager_net_usage_layout, container, false);
            final ViewPager viewPager = (ViewPager) viewpagerLayout.findViewById(R.id.pager);

            wifiMobileBarChartContainer= (RelativeLayout) viewpagerLayout.findViewById(R.id.rlWifiMobileBarChartContainer);

            CirclePageIndicator circlePageIndicator = (CirclePageIndicator) viewpagerLayout.findViewById(R.id.indicator);
            NetUsagesPagerAdapter netUsagesPagerAdapter = new NetUsagesPagerAdapter(context);
            viewPager.setAdapter(netUsagesPagerAdapter);
            circlePageIndicator.setViewPager(viewPager);
            final float density = context.getResources().getDisplayMetrics().density;
            //Set circle indicator radius
            circlePageIndicator.setRadius(5 * density);

            TextView netUsageFromDate = (TextView) viewpagerLayout.findViewById(R.id.tvNetUsageSince);
            Log.e("hello_world", ":netUsageFromDate:" + netUsageFromDate);
            netUsageListView = (ListView) viewpagerLayout.findViewById(R.id.lvAppNetUsage);
            if (AppsSettings.getAppsSettings(context).getLanguage().equals("eng")) {
                netUsageFromDate.setText("" + context.getResources().getString(R.string.app_data_usage));
            } else {
                netUsageFromDate.setText("" + context.getResources().getString(R.string.app_data_usage_bn));
            }


            new appNetUsageTask().execute();

            container.addView(viewpagerLayout);

            wifiMobileBarChartContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    int availableHeight = wifiMobileBarChartContainer.getMeasuredHeight();
                    if(availableHeight>0) {
                        wifiMobileBarChartContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        //save height here and do whatever you want with it
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("chart_height",availableHeight);
                        editor.commit();
                        Log.v("_chart_height_",availableHeight+"");
                        // AppConstants.wifiMobileBarChartContainerHeight=availableHeight;
                    }
                }
            });





        } else {
            // check local database for a plan
            // if exists then show data plan details layout
            // otherwise show add new layout


            viewpagerLayout = inflater.inflate(R.layout.nvai_viewpager_data_plan_add_layout, container, false);
            addDataPlanImageView = (ImageView) viewpagerLayout.findViewById(R.id.ivAddDataPlan);
            add_data_plan_text = (TextView) viewpagerLayout.findViewById(R.id.add_data_plan_text);
            tvInfoDeviceModel = (TextView) viewpagerLayout.findViewById(R.id.tvInfoDeviceModel);
            current_data_plan = (TextView) viewpagerLayout.findViewById(R.id.current_data_plan);
            usage_limit_alert = (TextView) viewpagerLayout.findViewById(R.id.usage_limit_alert);
            set_daily_limit = (TextView) viewpagerLayout.findViewById(R.id.set_daily_limit);
            daily_consumed_alert = (TextView) viewpagerLayout.findViewById(R.id.daily_consumed_alert);
            set_limit_percentage = (TextView) viewpagerLayout.findViewById(R.id.set_limit_percentage);
            usage_limit_notification = (TextView) viewpagerLayout.findViewById(R.id.usage_limit_notification);
            addNewPlanCardView = (CardView) viewpagerLayout.findViewById(R.id.addPlanCard_view);
            dataPlanInfoContainer = (LinearLayout) viewpagerLayout.findViewById(R.id.llDataPlanInfo);
            dataPlanLineChart = (LineChart) viewpagerLayout.findViewById(R.id.chart);
            //  dataPlanBarChart= (BarChart) viewpagerLayout.findViewById(R.id.dataPlanBarchart);
            dataPlanPieChart = (PieChart) viewpagerLayout.findViewById(R.id.pcNetPlanRemaining);
            dailyLimitAlertCheckBox = (CheckBox) viewpagerLayout.findViewById(R.id.checkboxDailyUsages);

            if (AppsSettings.getAppsSettings(context).getLanguage().equals("eng")) {
                add_data_plan_text.setText("" + context.getResources().getString(R.string.add_data_plan_text));
                tvInfoDeviceModel.setText("" + context.getResources().getString(R.string.add_data_plan));
                current_data_plan.setText("" + context.getResources().getString(R.string.current_data_plan));
                usage_limit_alert.setText("" + context.getResources().getString(R.string.usage_limit_alert));
                set_daily_limit.setText("" + context.getResources().getString(R.string.set_daily_limit));
                daily_consumed_alert.setText("" + context.getResources().getString(R.string.daily_consumed_alert));
                set_limit_percentage.setText("" + context.getResources().getString(R.string.set_limit_percentage));
                usage_limit_notification.setText("" + context.getResources().getString(R.string.usage_limit_notification));
            } else {
                add_data_plan_text.setText("" + context.getResources().getString(R.string.add_data_plan_text_bn));
                tvInfoDeviceModel.setText("" + context.getResources().getString(R.string.add_data_plan_bn));
                current_data_plan.setText("" + context.getResources().getString(R.string.current_data_plan_bn));
                usage_limit_alert.setText("" + context.getResources().getString(R.string.usage_limit_alert_bn));
                set_daily_limit.setText("" + context.getResources().getString(R.string.set_daily_limit_bn));
                daily_consumed_alert.setText("" + context.getResources().getString(R.string.daily_consumed_alert_bn));
                set_limit_percentage.setText("" + context.getResources().getString(R.string.set_limit_percentage_bn));
                usage_limit_notification.setText("" + context.getResources().getString(R.string.usage_limit_notification_bn));
            }


            if (AppsSettings.getAppsSettings(context).getDailyCheckBox()) {
                if (AppsSettings.getAppsSettings(context).getDailyInternetConsumeLimit() == 0.0 || AppsSettings.getAppsSettings(context).getDailyInternetConsumeLimit() == 0) {
                    dailyLimitAlertCheckBox.setChecked(false);
                } else {
                    dailyLimitAlertCheckBox.setChecked(true);
                }
            } else {
                dailyLimitAlertCheckBox.setChecked(false);
            }
            usageLimitCheckBox = (CheckBox) viewpagerLayout.findViewById(R.id.checkboxUsesLimit);
            if (AppsSettings.getAppsSettings(context).getUsageCheckBox()) {
                if (AppsSettings.getAppsSettings(context).getInternetConsumeParcentageLimit() == 0.0 || AppsSettings.getAppsSettings(context).getInternetConsumeParcentageLimit() == 0) {
                    usageLimitCheckBox.setChecked(false);
                } else {
                    usageLimitCheckBox.setChecked(true);
                }
            } else {
                usageLimitCheckBox.setChecked(false);
            }
            mMbOrGb = (TextView) viewpagerLayout.findViewById(R.id.mbOrGb);
            if (AppsSettings.getAppsSettings(context).getDataPlanType().equals("MB")) {
                mMbOrGb.setText("MB");
            } else if (AppsSettings.getAppsSettings(context).getDataPlanType().equals("GB")) {
                Log.e("04_16", "if,if,if");
                mMbOrGb.setText("GB");
            } else if (AppsSettings.getAppsSettings(context).getDataPlanType().equalsIgnoreCase("unlimited")) {
                mMbOrGb.setText("GB");
            } else {
                mMbOrGb.setText("MB");
            }

            dailyUsageEditText = (EditText) viewpagerLayout.findViewById(R.id.editTextDailyLimit);
            if (AppsSettings.getAppsSettings(context).getDailyInternetConsumeLimit() != 0) {
                dailyUsageEditText.setText("" + AppsSettings.getAppsSettings(context).getDailyInternetConsumeLimit());
            }
            dailyUsageEditText.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                    float mValue = 0;
                    if (!dailyUsageEditText.getText().toString().equals("")) {
                        if (AppsSettings.getAppsSettings(context).getDailyCheckBox()) {
                            // check given value over or not //
                            ArrayList<DataPlan> mDataPlan = new ArrayList<DataPlan>();
                            if (mDataPlan != null && mDataPlan.size() > 0) {
                                mDataPlan.clear();
                            }
                            mDataPlan = AppManager.getInstance(context).retrieveDataPlan(context);
                            Log.e("14_01", ":mDataPlan:size:" + mDataPlan.size());
                            // check data exist or not in database //
                            if (mDataPlan != null && mDataPlan.size() > 0) {
                                String val = mDataPlan.get(0).getDataSize();
                                if (val.equals("")) {
                                    dailyUsageEditText.setText("");
                                    if (AppsSettings.getAppsSettings(context).getLanguage().equals("eng")) {
                                        Toast.makeText(context, "Delete your 'data plan' setup and create 'data plan' setup again.", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(context, "আপনার 'ডেটা প্ল্যান' সেটআপ মুছে ফেলা হইয়েছে, 'ডেটা প্ল্যান' সেটআপ আবার তৈরি তৈরি করুন |", Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    float mDataBaseDataLimit = Float.parseFloat(val);
                                    String value = dailyUsageEditText.getText().toString();
                                    if (value.equals("0") || value.equals(".") || value.equals("0.") || value.equals("0.0") || value.equals("0.00") || value.equals(".0") || value.equals(".00")) {
                                    } else {
                                        mValue = Float.parseFloat(value);
                                        if ((mValue > 0) && (mValue <= mDataBaseDataLimit)) {
                                            AppsSettings.getAppsSettings(context).setDailyInternetConsumeLimit(mValue);
                                            AppsSettings.getAppsSettings(context).setDailyInternetConsumeLimitOverCome(false);
                                        } else {
                                            dailyUsageEditText.setText("");
                                            if (AppsSettings.getAppsSettings(context).getLanguage().equals("eng")) {
                                                Toast.makeText(context, "Please enter value between 0.01 -" + mDataBaseDataLimit, Toast.LENGTH_SHORT).show();

                                            } else {
                                                Toast.makeText(context, "0.01 - "+ mDataBaseDataLimit +" এর  মধ্যে ইনপুট মান লিখুন", Toast.LENGTH_SHORT).show();
                                            }



                                        }
                                    }
                                }
                            }
                            // end of check given value over or not //

                        }
                    }
                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                }

                @Override
                public void afterTextChanged(Editable arg0) {
                }

            });
            usagePercentageEditText = (EditText) viewpagerLayout.findViewById(R.id.editTextPercentageAlert);
            currentDataPlanSize = (TextView) viewpagerLayout.findViewById(R.id.tvCurrentDataPlanSize);
            currentDataPlanDuration = (TextView) viewpagerLayout.findViewById(R.id.tvCurrentDataPlanDuration);
            currentDataPlanPeriod = (TextView) viewpagerLayout.findViewById(R.id.tvCurrentDataPlanPeriod);
            deletePlanImageView = (ImageView) viewpagerLayout.findViewById(R.id.ivDeletePlan);

            deletePlanImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (AppsSettings.getAppsSettings(context).getLanguage().equals("eng")) {
                        deleteDataPlanSetup("");
                    } else {
                        deleteDataPlanSetup_bn("");
                    }

                }
            });


            // pie chart
            dataPlanPieChart.setDrawHoleEnabled(false);
            dataPlanPieChart.setRotationAngle(180);
            // enable rotation of the chart by touch
            dataPlanPieChart.setRotationEnabled(true);
            dataPlanPieChart.setHighlightPerTapEnabled(true);
            dataPlanPieChart.setDrawEntryLabels(false);
            dataPlanPieChart.setDrawSliceText(false);
            dataPlanPieChart.getDescription().setEnabled(false);

            Legend l = dataPlanPieChart.getLegend();
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            l.setHorizontalAlignment(LegendHorizontalAlignment.CENTER);
            l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            l.setDrawInside(false);

            l.setXEntrySpace(15f);
            l.setYEntrySpace(0f);
            l.setYOffset(8f);
            l.setXOffset(4f);
            l.setTextSize(15f);


            l.setTextColor(Color.BLACK);


            // entry label styling
            dataPlanPieChart.setEntryLabelColor(Color.BLACK);
            //   mChart.setEntryLabelTypeface(mTfRegular);
            dataPlanPieChart.setEntryLabelTextSize(12f);

            // ***********************************set the value of each part of pie chart

//            HashMap<String, String> dataPlanInfo = appLocalDatabaseHelper.getDataPlanInfo();
            ArrayList<DataPlan> mDataPlanList = new ArrayList<DataPlan>();
            if (mDataPlanList != null && mDataPlanList.size() > 0) {
                mDataPlanList.clear();
            }
            mDataPlanList = AppManager.getInstance(context).retrieveDataPlan(context);

            if (mDataPlanList.size() > 0) {
                if (mDataPlanList.get(0).getDataSizeType().equalsIgnoreCase("unlimited")) {

                    if(AppsSettings.getAppsSettings(context).getLanguage().equals("eng"))
                    {
                        currentDataPlanSize.setText("Data Size: Unlimit.");
                    }
                    else
                    {
                        currentDataPlanSize.setText(""+context.getResources().getString(R.string.data_size_unlimit_bn));
                    }
                } else if (mDataPlanList.get(0).getDataSizeType().equalsIgnoreCase("GB")) {
                    if(AppsSettings.getAppsSettings(context).getLanguage().equals("eng"))
                    {
                        currentDataPlanSize.setText("Data Size : " + mDataPlanList.get(0).getDataSize() + " GB");
                    }
                    else
                    {
                        currentDataPlanSize.setText(""+context.getResources().getString(R.string.data_size_bn) + mDataPlanList.get(0).getDataSize() + " "+context.getResources().getString(R.string.gb_bn));
                    }

                } else if (mDataPlanList.get(0).getDataSizeType().equalsIgnoreCase("MB")) {


                    if(AppsSettings.getAppsSettings(context).getLanguage().equals("eng"))
                    {
                        currentDataPlanSize.setText("Data Size : " + mDataPlanList.get(0).getDataSize() + " MB");
                    }
                    else
                    {
                        currentDataPlanSize.setText(""+context.getResources().getString(R.string.data_size_bn) + mDataPlanList.get(0).getDataSize() + " "+context.getResources().getString(R.string.mb_bn));
                    }







                } else {

                }

                if(AppsSettings.getAppsSettings(context).getLanguage().equals("eng"))
                {
                    currentDataPlanDuration.setText("Usage Limit : " + mDataPlanList.get(0).getDuration() + " days");
                    currentDataPlanPeriod.setText("Usage Period " + mDataPlanList.get(0).getStartDate() + " to " + mDataPlanList.get(0).getEndDate());
                }
                else
                {
                    currentDataPlanDuration.setText("ব্যবহারের সীমা : " + mDataPlanList.get(0).getDuration() + " দিন");
                    currentDataPlanPeriod.setText("ব্যবহারের সময়কাল : " + mDataPlanList.get(0).getStartDate() + " to " + mDataPlanList.get(0).getEndDate());
                }









                float planSize = 0;
                // OMOR//
                ArrayList<InternetData> mIDM = new ArrayList<InternetData>();
                if (mIDM != null && mIDM.size() > 0) {
                    mIDM.clear();
                }
                mIDM = AppManager.getInstance(context).retrieveInternetForPieChart(context);
                float mTotalRTX = 0;
                planSize = Float.parseFloat(mDataPlanList.get(0).getDataSize());
                //
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
//                    df.format(mNowTotalRTX);
                //
                if (mIDM.size() > 0) {


                    for (int a = 0; a < mIDM.size(); a++) {
                        mTotalRTX += Float.parseFloat(mIDM.get(a).getUsegeData());
                    }
                    if (mDataPlanList.get(0).getDataSizeType().equalsIgnoreCase("MB")) {
                        float mNowTotalRTX = 0;
                        mNowTotalRTX = mTotalRTX / (1024 * 1024);

                        float usedDataInMb = mNowTotalRTX;
                        float usedPercentage = (usedDataInMb / planSize) * 100;
                        Log.e("TodayRTY", usedPercentage + " used data " + usedDataInMb + " plansize :" + planSize);
                        float remainingPercentage = 100 - usedPercentage;
                        // float usedPercentage, float remainingPercentage, String usedData, String remainingData
                        if (remainingPercentage == 0.00) {
                            // it's a positive
//                            setPieChartData(usedPercentage, (100 - usedPercentage), mNowTotalRTX + " Mb", (planSize - mNowTotalRTX) + " Mb");
                            setPieChartData(usedPercentage, (100 - usedPercentage), df.format(mNowTotalRTX) + " Mb", df.format(planSize - mNowTotalRTX) + " Mb");
                            Log.e("TodayRTY", ":if:usedPercentage :" + usedPercentage + " remainingPercentage" + remainingPercentage);
                        } else if (remainingPercentage < 0.00) {
                            // negative
                            // set data size
                            ArrayList<DataPlan> mDP = new ArrayList<DataPlan>();
                            if (mDP != null && mDP.size() > 0) {
                                mDP.clear();
                            }
                            mDP = AppManager.getInstance(context).retrieveDataPlan(context);


                            setPieChartData(100, 0, mDP.get(0).getDataSize() + " MB", 0 + " MB");
                            Log.e("TodayRTY", ":else if:usedPercentage :" + usedPercentage + " remainingPercentage" + remainingPercentage);
                            if(AppsSettings.getAppsSettings(context).getLanguage().equals("eng"))
                            {
                                deleteDataPlanSetup("Current Data Plan Limit Over!");
                            }
                            else
                            {
                                deleteDataPlanSetup_bn("বর্তমান ডাটা প্ল্যান সীমা ওভার");
                            }


                        } else {
                            // it's a positive
//                            setPieChartData(usedPercentage, (100 - usedPercentage), mNowTotalRTX + " MB", (planSize - mNowTotalRTX) + " MB");
                            setPieChartData(usedPercentage, (100 - usedPercentage), df.format(mNowTotalRTX) + " Mb", df.format(planSize - mNowTotalRTX) + " Mb");
                            Log.e("TodayRTY", ":else:usedPercentage :" + usedPercentage + " remainingPercentage" + remainingPercentage);
                        }


                    } else if (mDataPlanList.get(0).getDataSizeType().equalsIgnoreCase("GB")) {
                        float mNowTotalRTX = 0;
                        mNowTotalRTX = mTotalRTX / (1024 * 1024 * 1024);
                        float usedDataInMb = mNowTotalRTX;
                        float usedPercentage = (usedDataInMb / planSize) * 100;
                        Log.e("TodayRT", usedPercentage + " used data " + usedDataInMb + " plansize :" + planSize);
                        float remainingPercentage = 100 - usedPercentage;
                        // float usedPercentage, float remainingPercentage, String usedData, String remainingData
                        // float usedPercentage, float remainingPercentage, String usedData, String remainingData
                        if (remainingPercentage == 0.00) {
                            // it's a positive
//                            setPieChartData(usedPercentage, (100 - usedPercentage), mNowTotalRTX + " GB", (planSize - mNowTotalRTX) + " GB");
                            setPieChartData(usedPercentage, (100 - usedPercentage), df.format(mNowTotalRTX) + " GB", df.format(planSize - mNowTotalRTX) + " GB");
                            Log.e("TodayRTY", ":if:usedPercentage :" + usedPercentage + " remainingPercentage" + remainingPercentage);
                        } else if (remainingPercentage < 0.00) {
                            // negative
                            // set data size
                            ArrayList<DataPlan> mDP = new ArrayList<DataPlan>();
                            if (mDP != null && mDP.size() > 0) {
                                mDP.clear();
                            }
                            mDP = AppManager.getInstance(context).retrieveDataPlan(context);

                            setPieChartData(100, 0, mDP.get(0).getDataSize() + " GB", 0 + " GB");
                            Log.e("TodayRTY", ":else if:usedPercentage :" + usedPercentage + " remainingPercentage" + remainingPercentage);
                            if(AppsSettings.getAppsSettings(context).getLanguage().equals("eng"))
                            {
                                deleteDataPlanSetup("Current Data Plan Limit Over!");
                            }
                            else
                            {
                                deleteDataPlanSetup_bn("বর্তমান ডাটা প্ল্যান সীমা ওভার");
                            }


                        } else {
                            // it's a positive
//                            setPieChartData(usedPercentage, (100 - usedPercentage), mNowTotalRTX + " GB", (planSize - mNowTotalRTX) + " GB");
                            setPieChartData(usedPercentage, (100 - usedPercentage), df.format(mNowTotalRTX) + " GB", df.format(planSize - mNowTotalRTX) + " GB");
                            Log.e("TodayRTY", ":else:usedPercentage :" + usedPercentage + " remainingPercentage" + remainingPercentage);
                        }

                    } else if (mDataPlanList.get(0).getDataSizeType().equalsIgnoreCase("unlimited")) {
                        // start code for unlimited
                        float mNowTotalRTX = 0;
                        mNowTotalRTX = mTotalRTX / (1024 * 1024 * 1024);
                        float usedDataInMb = mNowTotalRTX;


                        float usedPercentage = (usedDataInMb / planSize) * 100;
                        Log.e("TodayRT", usedPercentage + " used data " + usedDataInMb + " plansize :" + planSize);
                        float remainingPercentage = 100 - usedPercentage;
                        // float usedPercentage, float remainingPercentage, String usedData, String remainingData
                        // float usedPercentage, float remainingPercentage, String usedData, String remainingData
                        if (remainingPercentage == 0.00) {
                            // it's a positive
                            String.format("%.2f", usedDataInMb);
//                            setPieChartData(usedPercentage, (100 - usedPercentage), mNowTotalRTX + " GB", (planSize - mNowTotalRTX) + " GB");
                            setPieChartData(usedPercentage, (100 - usedPercentage), df.format(mNowTotalRTX) + " GB", df.format(planSize - mNowTotalRTX) + " GB");
                            Log.e("TodayRTY", ":if:usedPercentage :" + usedPercentage + " remainingPercentage" + remainingPercentage);
                        } else if (remainingPercentage < 0.00) {
                            // negative
                            // set data size
                            ArrayList<DataPlan> mDP = new ArrayList<DataPlan>();
                            if (mDP != null && mDP.size() > 0) {
                                mDP.clear();
                            }
                            mDP = AppManager.getInstance(context).retrieveDataPlan(context);

                            setPieChartData(100, 0, mDP.get(0).getDataSize() + " GB", 0 + " GB");
                            Log.e("TodayRTY", ":else if:usedPercentage :" + usedPercentage + " remainingPercentage" + remainingPercentage);
                            if(AppsSettings.getAppsSettings(context).getLanguage().equals("eng"))
                            {
                                deleteDataPlanSetup("Current Data Plan Limit Over!");
                            }
                            else
                            {
                                deleteDataPlanSetup_bn("বর্তমান ডাটা প্ল্যান সীমা ওভার");
                            }


                        } else {
                            // it's a positive
//                            setPieChartData(usedPercentage, (100 - usedPercentage), mNowTotalRTX + " GB", (planSize - mNowTotalRTX) + " GB");
                            setPieChartData(usedPercentage, (100 - usedPercentage), df.format(mNowTotalRTX) + " GB", df.format(planSize - mNowTotalRTX) + " GB");
                            Log.e("TodayRTY", ":else:usedPercentage :" + usedPercentage + " remainingPercentage" + remainingPercentage);
                        }
                        // end code for unlimited
                    }


                } else {
                    setPieChartData(0, 100, "0 Mb", mDataPlanList.get(0).getDataSize() + " Mb");
//                    setPieChartData(0, 100,"0 Mb", df.format(mDataPlanList.get(0).getDataSize()) + " MB");
                    Log.e("TodayR", " used data " + " plansize :" + planSize);
                }
                // END OF OMOR //

            }
//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&


            // ********* omor ********************************//

            final ArrayList<Entry> e1 = new ArrayList<Entry>();

            ArrayList<InternetData> mInteIntDataModels = new ArrayList<InternetData>();
            if (mInteIntDataModels != null && mInteIntDataModels.size() > 0) {
                mInteIntDataModels.clear();
            }
            // add new item blank
            mInteIntDataModels.add(new InternetData("0", "0", "0"));
            // end add new item blank
            mInteIntDataModels = AppManager.getInstance(context).retrieveInternetForPieChart(context);
            if (mInteIntDataModels.size() > 0) {
                for (int i = 0; i < mInteIntDataModels.size(); i++) {
                    float mUsage = Float.parseFloat(mInteIntDataModels.get(i).getUsegeData());
                    float mCurrent = Float.parseFloat(mInteIntDataModels.get(i).getCurrentData());
                    float mPrevious = Float.parseFloat(mInteIntDataModels.get(i).getPreviousData());
                    float allTotalFloat = (float) mUsage / (1024 * 1024);

                    e1.add(new Entry(i, allTotalFloat));


                }
            }
            populateLineChart();

            // ********* end of omor ********************************//

            // line chart
            if (mDataPlanList.size() > 0) {
                addNewPlanCardView.setVisibility(View.GONE);
                //  setData(planDataInfoHashMapArrayList.size(), 100);
                //  setData(10,100);

            } else {
                //#########################
//                e1.clear();
//                e1.add(new Entry(0, 0));
                populateLineChart();
                dataPlanInfoContainer.setVisibility(View.INVISIBLE);


            }


            // add data ********************************************************************************************


            //  dataPlanBarChart.animateX(2500);
            //  dataPlanBarChart.setVisibleXRange(7,0);


            dailyLimitAlertCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked == true) {
                        dailyUsageEditText.setEnabled(true);
                        AppsSettings.getAppsSettings(context).setDailyCheckBox(true);

                    } else {
                        AppsSettings.getAppsSettings(context).setDailyCheckBox(false);
                        AppsSettings.getAppsSettings(context).setDailyInternetConsumeLimit(0);
                        dailyUsageEditText.setEnabled(false);

                    }
                }
            });

            usageLimitCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked == true) {
                        AppsSettings.getAppsSettings(context).setUsageCheckBox(true);
                        usagePercentageEditText.setEnabled(true);
                    } else {
                        AppsSettings.getAppsSettings(context).setUsageCheckBox(false);
                        AppsSettings.getAppsSettings(context).setInternetConsumeParcentageLimit(0);
                        usagePercentageEditText.setEnabled(false);
                    }
                }
            });


            if (AppsSettings.getAppsSettings(context).getInternetConsumeParcentageLimit() != 0) {
                usagePercentageEditText.setText("" + AppsSettings.getAppsSettings(context).getInternetConsumeParcentageLimit());
            }
            usagePercentageEditText.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {


//                    ArrayList<DataPlan> mDataPlan = new ArrayList<DataPlan>();
//                    if (mDataPlan != null && mDataPlan.size() > 0) {
//                        mDataPlan.clear();
//                    }
//                    mDataPlan = AppManager.getInstance(context).retrieveDataPlan(context);
//                    Log.e("14_01", ":mDataPlan:size:" + mDataPlan.size());
//                    // check data exist or not in database //
//                    if (mDataPlan != null && mDataPlan.size() > 0) {
//                        String val = mDataPlan.get(0).getDataSize();
//                        if (val.equals("")) {
//                            dailyUsageEditText.setText("");
//                        } else {
//
//                        }
//                    }


                    String value = usagePercentageEditText.getText().toString();
                    if (value.equals("0") || value.equals(".") || value.equals("0.") || value.equals("0.0") || value.equals("0.00") || value.equals(".0") || value.equals(".00")) {
                        usagePercentageEditText.setText("");

                        if(AppsSettings.getAppsSettings(context).getLanguage().equals("eng"))
                        {
                            Toast.makeText(context, "percentage should be 1-100", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Toast.makeText(context, "শতাংশ ১-১০০ হওয়া উচিত", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        if (usagePercentageEditText.getText().length() > 0) {

                            float percentage = Float.parseFloat(usagePercentageEditText.getText().toString());
                            if ((percentage > 0) && (percentage <= 100)) {
                                if (usageLimitCheckBox.isChecked()) {
                                    if (AppsSettings.getAppsSettings(context).getUsageCheckBox()) {
                                        AppsSettings.getAppsSettings(context).setInternetConsumeParcentageLimit(percentage);
                                        AppsSettings.getAppsSettings(context).setInternetConsumeLimiParcentigetOverCome(false);
                                    }

                                }

                            } else {
                                usagePercentageEditText.setText("");


                                if(AppsSettings.getAppsSettings(context).getLanguage().equals("eng"))
                                {
                                    Toast.makeText(context, "percentage should be 1-100", Toast.LENGTH_SHORT).show();

                                }
                                else
                                {
                                    Toast.makeText(context, "শতাংশ ১-১০০ হওয়া উচিত", Toast.LENGTH_SHORT).show();
                                }


                            }

                        } else {
//                        Log.e("Today", "else:usagePercentageEditText:0");
                        }
                    }


                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                }

                @Override
                public void afterTextChanged(Editable arg0) {
                }

            });

            addDataPlanImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.add_data_plan_dialog);
                    TextView tvAddPlanTitle = (TextView) dialog.findViewById(R.id.tvAddPlanTitle);
                    TextView tvDataPackageSize = (TextView) dialog.findViewById(R.id.tvDataPackageSize);
                    TextView tvDataTimeLength = (TextView) dialog.findViewById(R.id.tvDataTimeLength);
                    TextView set_daily_limit = (TextView) dialog.findViewById(R.id.set_daily_limit);
                    TextView usage_limit_alert = (TextView) dialog.findViewById(R.id.usage_limit_alert);
                    Button cancelButton = (Button) dialog.findViewById(R.id.btDialogCancelButton);
                    Button doneAddingPlan = (Button) dialog.findViewById(R.id.btDoneNetPlan);
                    final Button startDateButton = (Button) dialog.findViewById(R.id.btStartDatePicker);
                    final Button endDateButton = (Button) dialog.findViewById(R.id.btEndDatePicker);
                    if (AppsSettings.getAppsSettings(context).getLanguage().equals("eng")) {
                        tvAddPlanTitle.setText("" + context.getResources().getString(R.string.data_plan));
                        tvDataPackageSize.setText("" + context.getResources().getString(R.string.dataSize));
                        tvDataTimeLength.setText("" + context.getResources().getString(R.string.package_duration));
                        cancelButton.setText("" + context.getResources().getString(R.string.cancel));
                        doneAddingPlan.setText("" + context.getResources().getString(R.string.done));
                        endDateButton.setText("" + context.getResources().getString(R.string.end_date));
                    } else {
                        tvAddPlanTitle.setText("" + context.getResources().getString(R.string.data_plan_bn));
                        tvDataPackageSize.setText("" + context.getResources().getString(R.string.dataSize_bn));
                        tvDataTimeLength.setText("" + context.getResources().getString(R.string.package_duration_bn));
                        cancelButton.setText("" + context.getResources().getString(R.string.cancel_bn));
                        doneAddingPlan.setText("" + context.getResources().getString(R.string.done_bn));
                        endDateButton.setText("" + context.getResources().getString(R.string.end_date_bn));
                    }
                    final DiscreteSeekBar discreteSeekBar = (DiscreteSeekBar) dialog.findViewById(R.id.sbPlanSize);
                    discreteSeekBar.setProgress(5);
                    final EditText planSizeMB = (EditText) dialog.findViewById(R.id.etPlanSizeMB);
                    final TextView planDurationTextView = (TextView) dialog.findViewById(R.id.tvPlanDuration);

                    // setup radio group //
                    radioGroup = (RadioGroup) dialog.findViewById(R.id.radiogroup);


                    // set by default
                    radioButtonSelected = "cbMb";
                    planSizeType = "MB";
                    planSizeMB.setText("10");
                    discreteSeekBar.setEnabled(true);
                    planSizeMB.setEnabled(true);
                    discreteSeekBar.setMax(1023);
                    discreteSeekBar.setMin(1);
                    discreteSeekBar.setProgress(10);
                    planSizeMB.setText(discreteSeekBar.getProgress() + "");
                    // end of set by default
                    radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, int i) {
                            if (i == R.id.cbMb) {
                                radioButtonSelected = "cbMb";
                                planSizeType = "MB";
                                planSizeMB.setText("10");
                                discreteSeekBar.setEnabled(true);
                                planSizeMB.setEnabled(true);
                                discreteSeekBar.setMax(1023);
                                discreteSeekBar.setMin(1);
                                planSizeMB.setText(discreteSeekBar.getProgress() + "");
                            } else if (i == R.id.cbGb) {
                                radioButtonSelected = "cbGb";
                                planSizeType = "GB";
                                planSizeMB.setText("1");
                                discreteSeekBar.setEnabled(true);
                                planSizeMB.setEnabled(true);
                                discreteSeekBar.setMax(100);
                                discreteSeekBar.setMin(1);
                                planSizeMB.setText(discreteSeekBar.getMin() + "");
                            } else if (i == R.id.cbUnlimited) {
                                radioButtonSelected = "cbUnlimited";
                                planSizeType = "Unlimited";
                                planSizeMB.setText("200");
                                discreteSeekBar.setProgress(200);
                                discreteSeekBar.setMin(0);
                                discreteSeekBar.setEnabled(false);
                                planSizeMB.setEnabled(false);
                            } else {
                                radioButtonSelected = "cbMb";
                                planSizeType = "MB";
                                planSizeMB.setText("10");
                                discreteSeekBar.setEnabled(true);
                                planSizeMB.setEnabled(true);
                                discreteSeekBar.setMax(1023);
                                discreteSeekBar.setMin(1);
                                planSizeMB.setText(discreteSeekBar.getProgress() + "");
                            }
                        }
                    });
                    // end of setup radio group //
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = Calendar.getInstance().getTime();
                    planStartDate = sdf.format(date);
                    startDateButton.setText(planStartDate);
                    Log.e("hello_world", ":planStartDate:" + planStartDate + ", :planStartDate:" + planStartDate);
                    startDateButton.setEnabled(false);

                    planSizeMB.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            String value = planSizeMB.getText().toString();
                            if (value.equals("0") || value.equals(".") || value.equals("0.") || value.equals("0.0") || value.equals("0.00") || value.equals(".0") || value.equals(".00")) {
                                planSizeMB.setText("");
                                if(AppsSettings.getAppsSettings(context).getLanguage().equals("eng"))
                                {
                                    Toast.makeText(context, "Value should be between :1 - 1023 MB", Toast.LENGTH_SHORT).show();

                                }
                                else
                                {
                                    Toast.makeText(context, "মান ১-১০২৩ মেগাবাইট হত্তয়া উচিত", Toast.LENGTH_SHORT).show();
                                }
                            } else {


                                // end of check limit for input //
                                if (planSizeMB.getText().length() > 0) {

                                    float mFplanSize = Float.parseFloat(planSizeMB.getText().toString());
                                    int planSizeInt = Math.round(mFplanSize);
                                    // check limit for input //
                                    if (radioButtonSelected.equalsIgnoreCase("cbMb")) {

                                        if ((planSizeInt > 0) && (planSizeInt <= 1023)) {
                                            discreteSeekBar.setProgress(planSizeInt);
                                        } else {
                                            planSizeMB.setText("");
                                            if(AppsSettings.getAppsSettings(context).getLanguage().equals("eng"))
                                            {
                                                Toast.makeText(context, "Value should be between :1 - 1023 MB", Toast.LENGTH_SHORT).show();

                                            }
                                            else
                                            {
                                                Toast.makeText(context, "মান ১-১০২৩ মেগাবাইট হত্তয়া উচিত", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    } else if (radioButtonSelected.equalsIgnoreCase("cbGb")) {
                                        if ((planSizeInt > 0) && (planSizeInt <= 100)) {
                                            discreteSeekBar.setProgress(planSizeInt);
                                        } else {
                                            planSizeMB.setText("");

                                            if(AppsSettings.getAppsSettings(context).getLanguage().equals("eng"))
                                            {
                                                Toast.makeText(context, "Value should be between :1 - 100 GB", Toast.LENGTH_SHORT).show();

                                            }
                                            else
                                            {
                                                Toast.makeText(context, "মান ১-১০০ গিগাবাইট হত্তয়া উচিত", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    } else if (radioButtonSelected.equalsIgnoreCase("cbUnlimited")) {
                                        discreteSeekBar.setProgress(planSizeInt);
                                    } else {
                                        if ((planSizeInt > 0) && (planSizeInt <= 1023)) {
                                            discreteSeekBar.setProgress(planSizeInt);
                                        } else {
                                            planSizeMB.setText("");
                                            if(AppsSettings.getAppsSettings(context).getLanguage().equals("eng"))
                                            {
                                                Toast.makeText(context, "Value should be between :1 - 1023 MB", Toast.LENGTH_SHORT).show();

                                            }
                                            else
                                            {
                                                Toast.makeText(context, "মান ১-১০২৩ মেগাবাইট হত্তয়া উচিত", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }


                                }
                            }


                        }
                    });
                    doneAddingPlan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (planSizeMB.getText().toString().equals("")) {

                                if(AppsSettings.getAppsSettings(context).getLanguage().equals("eng"))
                                {
                                    Toast.makeText(context, "Please input your data size", Toast.LENGTH_LONG).show();

                                }
                                else
                                {
                                    Toast.makeText(context, "আপনার ডেটা আকার টাইপ করুন", Toast.LENGTH_SHORT).show();
                                }



                            } else {

                                if (planEndDate != null) {
                                    Calendar cal1 = null;
                                    Calendar cal2 = null;
                                    try {
                                        // insert plan info to database
                                        addNewPlanCardView.setVisibility(View.GONE);
                                        dataPlanInfoContainer.setVisibility(View.VISIBLE);

                                        cal1 = new GregorianCalendar();
                                        cal2 = new GregorianCalendar();

                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                                        Date date = null;


                                        date = sdf.parse(planStartDate);
                                        cal1.setTime(date);
                                        date = sdf.parse(planEndDate);
                                        cal2.setTime(date);

                                        Log.e("hello_world", ": cal2.setTime(date);:date:" + date);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                        Log.e("abcd", ":ParseException:" + e.toString());
                                    }

                                    if (AppUtil.daysBetween(cal1, cal2) > 29) {
                                        durationType = "days";
                                    } else {
                                        durationType = "month";
                                    }
                                    // check if have any data in data plan
                                    ArrayList<DataPlan> mDataPlan = new ArrayList<DataPlan>();
                                    if (mDataPlan != null && mDataPlan.size() > 0) {
                                        mDataPlan.clear();
                                    }
                                    mDataPlan = AppManager.getInstance(context).retrieveDataPlan(context);
//                                Log.e("Today",":NetUsageFragmentViewPagerAdapter:mDataPlan size:"+mDataPlan.size());
                                    if (mDataPlan != null && mDataPlan.size() > 0) {

                                        AppsSettings.getAppsSettings(context).setDataPlanType(planSizeType);
                                        Log.e("04_16", "value:" + AppsSettings.getAppsSettings(context).getDataPlanType());
                                        if (AppsSettings.getAppsSettings(context).getDataPlanType().equals("MB")) {
                                            mMbOrGb.setText("MB");
                                        } else if (AppsSettings.getAppsSettings(context).getDataPlanType().equals("GB")) {
                                            Log.e("04_16", "if,if,if");
                                            mMbOrGb.setText("GB");
                                        } else if (AppsSettings.getAppsSettings(context).getDataPlanType().equalsIgnoreCase("unlimited")) {
                                            mMbOrGb.setText("GB");
                                            planSizeMB.setText("200");
                                        } else {
                                            mMbOrGb.setText("MB");
                                        }
                                        Log.e("abcd", ":mDataPlan != null && mDataPlan.size() > 0:if:");
                                        boolean isDelete = AppManager.getInstance(context).DeleteDataPlanInfo(context);
                                        if (isDelete) {
                                            Log.e("abcd", ":mDataPlan != null && mDataPlan.size() > 0:if:delete");
                                            // insert data in data plan
                                            if (AppManager.getInstance(context).addDataPlanInfo(context, planStartDate, planEndDate, AppUtil.daysBetween(cal1, cal2) + "", planSizeMB.getText().toString(), planSizeType)) {
                                                // check running service or not
                                                if (!MyServiceRunning.getInstance(context).isMyServiceRunning(DailyConsumedService.class)) {
                                                    // start service
                                                    context.startService(new Intent(context, DailyConsumedService.class));
                                                    // end of start service
                                                }
                                            }

                                            // end of insert data in data plan


                                            // update line chart data


                                        }
                                    } else {
                                        AppsSettings.getAppsSettings(context).setDataPlanType(planSizeType);
                                        Log.e("04_16", "value:" + AppsSettings.getAppsSettings(context).getDataPlanType());
                                        if (AppsSettings.getAppsSettings(context).getDataPlanType().equals("MB")) {
                                            mMbOrGb.setText("MB");
                                        } else if (AppsSettings.getAppsSettings(context).getDataPlanType().equals("GB")) {
                                            Log.e("04_16", "if,if,if");
                                            mMbOrGb.setText("GB");
                                        } else if (AppsSettings.getAppsSettings(context).getDataPlanType().equalsIgnoreCase("unlimited")) {
                                            mMbOrGb.setText("GB");
                                        } else {
                                            mMbOrGb.setText("MB");
                                        }
                                        // insert data in data plan
                                        if (AppManager.getInstance(context).addDataPlanInfo(context, planStartDate, planEndDate, AppUtil.daysBetween(cal1, cal2) + "", planSizeMB.getText().toString(), planSizeType)) {
                                            // check running service or not
                                            if (!MyServiceRunning.getInstance(context).isMyServiceRunning(DailyConsumedService.class)) {
                                                // start service
                                                context.startService(new Intent(context, DailyConsumedService.class));
                                                // end of start service
                                            }
                                        }
                                        // end of insert data in data plan
                                    }
                                    // end of check if have any data in data plan
                                    // retrieve data plan info //
                                    ArrayList<DataPlan> mDPlan = new ArrayList<DataPlan>();
                                    if (mDPlan != null) {
                                        mDPlan.clear();
                                    }
                                    mDPlan = AppManager.getInstance(context).retrieveDataPlan(context);
                                    if (mDPlan != null && mDPlan.size() > 0) {
                                        DataPlan dataPlan = mDPlan.get(0);
                                        if (AppsSettings.getAppsSettings(context).getDataPlanType().equalsIgnoreCase("Unlimited")) {
                                            if (AppsSettings.getAppsSettings(context).getLanguage().equals("eng")) {
                                                currentDataPlanSize.setText("" + context.getResources().getString(R.string.data_size_unlimit));
                                            } else {
                                                currentDataPlanSize.setText("" + context.getResources().getString(R.string.data_size_unlimit_bn));
                                            }

                                        } else {
                                            if (AppsSettings.getAppsSettings(context).getLanguage().equals("eng")) {
                                                currentDataPlanSize.setText("" + context.getResources().getString(R.string.data_size) + dataPlan.getDataSize() + " " + dataPlan.getDataSizeType());
                                            } else {
                                                currentDataPlanSize.setText("" + context.getResources().getString(R.string.data_size_bn) + dataPlan.getDataSize() + " " + dataPlan.getDataSizeType());
                                            }
                                        }
                                        if (AppsSettings.getAppsSettings(context).getLanguage().equals("eng")) {
                                            currentDataPlanDuration.setText("" + context.getResources().getString(R.string.time_limit) + dataPlan.getDuration() + " days");
                                            currentDataPlanPeriod.setText("" + context.getResources().getString(R.string.usage_periodd) + dataPlan.getStartDate() + " to " + dataPlan.getEndDate());
                                        } else {
                                            currentDataPlanDuration.setText("" + context.getResources().getString(R.string.time_limit_bn) + dataPlan.getDuration() + " days");
                                            currentDataPlanPeriod.setText("" + context.getResources().getString(R.string.usage_periodd_bn) + dataPlan.getStartDate() + " to " + dataPlan.getEndDate());
                                        }


                                        if (dataPlan.getDataSizeType().equalsIgnoreCase("unlimited")) {
                                            setPieChartData(0, 100, "0 GB", dataPlan.getDataSize() + " GB");
                                        } else if (AppsSettings.getAppsSettings(context).getDataPlanType().equals("GB")) {
                                            setPieChartData(0, 100, "0 GB", dataPlan.getDataSize() + " GB");
                                        } else {
                                            setPieChartData(0, 100, "0 MB", dataPlan.getDataSize() + " MB");
                                        }


                                    }


                                    // ##############################################################################

                                    // end of retrieve data plan info //
                                    populateLineChart();


                                    dialog.dismiss();
                                } else {
                                    if (AppsSettings.getAppsSettings(context).getLanguage().equals("eng")) {
                                        Toast.makeText(context, "" + context.getResources().getString(R.string.setup_alert), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(context, "" + context.getResources().getString(R.string.setup_alert_bn), Toast.LENGTH_SHORT).show();
                                    }

                                }

                            }
                        }
                    });

                    discreteSeekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
                        @Override
                        public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                            seekBarPosition = value;
                        }

                        @Override
                        public void onStartTrackingTouch(DiscreteSeekBar seekBar) {


                            planSizeMB.setSelection(planSizeMB.getText().length());
                        }

                        @Override
                        public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
                            planSizeMB.setText("" + seekBarPosition);
//
//                            if (gbCheckBox.isChecked() == false && mbCheckBox.isChecked() == false && mbCheckBox.isChecked() == false) {
//                                discreteSeekBar.setProgress(discreteSeekBar.getMin());
//                            }

                        }
                    });


                    endDateButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                    String dateString = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                    planEndDate = dateString;
                                    Log.e("hello_world", ":planEndDate:" + planEndDate + ",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,fasfasfsa");
                                    endDateButton.setText(dateString);

                                    // set plan duration
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                    Date StartDate = null;
                                    try {
                                        StartDate = sdf.parse(planStartDate);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    Calendar startCalendar = new GregorianCalendar();
                                    startCalendar.setTime(StartDate);

                                    Date endDate = null;
                                    try {
                                        endDate = sdf.parse(planEndDate);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    Calendar endCalendar = new GregorianCalendar();
                                    startCalendar.setTime(endDate);
                                    String planDuration = AppUtil.daysBetween(startCalendar, endCalendar) + " days";
                                    planDurationTextView.setText(planDuration);

                                    //  cal2.setTime(date);

                                }

                            }, year, month, day);
                            Calendar tomorrowCalender = new GregorianCalendar();
                            tomorrowCalender.add(Calendar.DATE, 1);

                            datePickerDialog.getDatePicker().setMinDate(tomorrowCalender.getTimeInMillis());
                            //  datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                            datePickerDialog.show();

                        }
                    });

                    cancelButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }


            });


            container.addView(viewpagerLayout);

        }

        return viewpagerLayout;
    }

    private void populateLineChart() {


//        // omor //

        ArrayList<InternetData> mList = new ArrayList<InternetData>();


        if (mList != null && mList.size() > 0) {
            mList.clear();
        }


        mList = AppManager.getInstance(context).retrieveInternetForPieChartT(context);


        ArrayList<Entry> entriesT = new ArrayList<>();
        if (entriesT != null && entriesT.size() > 0) {
            entriesT.clear();
        }


        entriesT.add(new Entry(0, 0f));

        final HashMap<Integer, String> numMapT = new HashMap<>();
        if (numMapT != null && numMapT.size() > 0) {
            numMapT.clear();
        }

        numMapT.put(0, "start");


        if (mList.size() > 0) {

            for (int i = 1; i <= mList.size(); i++) {

                String strDate = "";
                try {
                    strDate = mList.get(i - 1).getDate();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date varDate = dateFormat.parse(strDate);
                    dateFormat = new SimpleDateFormat("dd MMM");


                    float justValue = Float.parseFloat(mList.get(i - 1).getUsegeData()) / (1024 * 1024);
                    entriesT.add(new Entry(i, justValue));

                    numMapT.put(i, "" + dateFormat.format(varDate));

                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                    Log.e("hellod", ":e:" + e.toString());
                }

            }

        } else {
            entriesT.add(new Entry(1, 0f));
            numMapT.put(1, "extra");
        }

        LineDataSet dataset = new LineDataSet(entriesT, "Data Usage Chart");
//        // end of omor //


//
//        ArrayList<Entry> entries = new ArrayList<>();
//
//        entries.add(new Entry(1, 8f));
//        entries.add(new Entry(2, 6f));
//        entries.add(new Entry(3, 2f));



        /*//LineDataSet dataset = new LineDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");*/

//        final HashMap<Integer, String> numMap = new HashMap<>();
//        numMap.put(1, "first");
//        numMap.put(2, "second");
//        numMap.put(3, "third");


//        LineDataSet dataset2 = new LineDataSet(entriesT, "test data");


        //LineData data = new LineData(labels, dataset);
        LineData data = new LineData(dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        // dataset.setDrawCubic(true);
        dataset.setDrawFilled(true);

        dataPlanLineChart.getDescription().setEnabled(false);

        YAxis rightAxis = dataPlanLineChart.getAxisRight();
        rightAxis.setEnabled(false);
        YAxis leftAxis = dataPlanLineChart.getAxisLeft();
        leftAxis.setAxisMinimum(0f);

        XAxis xAxis = dataPlanLineChart.getXAxis();
        xAxis.setAxisMinimum(0f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // nahid vai //

        if (entriesT.size() < 8) {
            xAxis.setLabelCount(entriesT.size(), true);
        } else {
            xAxis.setLabelCount(7, true);
        }
        // end of nahid vai //
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
//                // omor //
//                return numMapT.get((int) value);
//                // end omor //

                return numMapT.get((int) value);
            }

            //@Override
            /*public int getDecimalDigits() {
                return 0;
            }*/
        });

        dataPlanLineChart.setData(data);

        int chartHeight=sharedPreferences.getInt("chart_height",0);
        Log.v("_chart_height_","in populate  : "+chartHeight+"");
        if (chartHeight>0){
            dataPlanLineChart.setMinimumHeight(chartHeight);
            Log.v("_chart_height_","in populate greater than 0 :"+chartHeight+"");
        }

        dataPlanLineChart.animateY(5000);
        dataPlanLineChart.invalidate();


    }


    private void setPieChartData(float usedPercentage, float remainingPercentage, String usedData, String remainingData) {


        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

//
//        // *************************************************
//     //***************************************************************************************************************

        if (AppsSettings.getAppsSettings(context).getLanguage().equals("eng")) {
            entries.add(new PieEntry(usedPercentage, "Used " + usedData));
            entries.add(new PieEntry(remainingPercentage, "Remaining " + remainingData));
        } else {
            entries.add(new PieEntry(usedPercentage, "ব্যবহৃত  " + usedData));
            entries.add(new PieEntry(remainingPercentage, "অবশিষ্ট  " + remainingData));
        }


        // entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5), mParties[i % mParties.length]));


        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(2f);
        dataSet.setSelectionShift(5f);
        dataSet.setValueTextSize(15f);


        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();


        colors.add(Color.parseColor("#307d99"));
        colors.add(Color.parseColor("#0191D7"));
        colors.add(Color.parseColor("#0191D7"));

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(mTfLight);
        dataPlanPieChart.setData(data);

        // undo all highlights
        dataPlanPieChart.highlightValues(null);

        dataPlanPieChart.invalidate();


    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;

        RectF bounds = mOnValueSelectedRectF;
        dataPlanBarChart.getBarBounds((BarEntry) e, bounds);
        MPPointF position = dataPlanBarChart.getPosition(e, YAxis.AxisDependency.LEFT);

        Log.i("bounds", bounds.toString());
        Log.i("position", position.toString());

        Log.i("x-index",
                "low: " + dataPlanBarChart.getLowestVisibleX() + ", high: "
                        + dataPlanBarChart.getHighestVisibleX());

        MPPointF.recycleInstance(position);
    }

    @Override
    public void onNothingSelected() {

    }


    public class appNetUsageTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {
            netUsageAppModelArrayList = setUsageAppModelsArrayList();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            NetUsageListViewAdapter netUsageListViewAdapter = new NetUsageListViewAdapter(netUsageAppModelArrayList, context);
            netUsageListView.setAdapter(netUsageListViewAdapter);

        }


        public ArrayList<NetUsageAppModel> setUsageAppModelsArrayList() {

            ArrayList<NetUsageAppModel> usageAppModelsArrayList = new ArrayList<>();
            PackageManager pm = context.getPackageManager();
            //get a list of installed apps.
            List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
            for (ApplicationInfo packageInfo : packages) {
                //     if ((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 1) {
                int uid = packageInfo.uid;
                Log.d("abcdef", " appname:" + packageInfo.loadLabel(pm).toString());

//                    String tx = TrafficStats.getUidTxBytes(uid) / 1024 + "KB";
//                    String rx = TrafficStats.getUidRxBytes(uid) / 1024 + "KB";
                String tx = TrafficStats.getUidTxBytes(uid) / 1024 + "";
                String rx = TrafficStats.getUidRxBytes(uid) / 1024 + "";
                float r = TrafficStats.getUidRxBytes(uid) / 1024;
                float t = TrafficStats.getUidTxBytes(uid) / 1024;
                float rt = r + t;
                Log.e("04_16", "::r:" + r + ",t:" + t + ",rx:" + rt);
//                    String rtx = rt + "KB";
                String rtx = rt + "";
                NetUsageAppModel netUsageAppModel = new NetUsageAppModel(packageInfo.packageName,
                        packageInfo.loadLabel(pm).toString(), packageInfo.loadIcon(pm), tx, rx, rtx);

                if (TrafficStats.getUidTxBytes(uid) > 0 || TrafficStats.getUidRxBytes(uid) > 0) {
                    usageAppModelsArrayList.add(netUsageAppModel);
                }

                // }
            }

            return usageAppModelsArrayList;
        }
    }


}
