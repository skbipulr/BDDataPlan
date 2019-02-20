package com.walton.internetdataplan.nvai;

import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.TrafficStats;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.walton.internetdataplan.R;
import com.walton.internetdataplan.utitls.AppsSettings;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by DELL on 12/3/2016.
 */

public class NetUsagesPagerAdapter extends PagerAdapter implements OnChartValueSelectedListener {


    private LayoutInflater inflater;
    private Context context;
    public Typeface mTfRegular;
    public Typeface mTfLight;
    BarChart barChart;
    public RectF mOnValueSelectedRectF = new RectF();
    AppLocalDatabaseHelper appLocalDatabaseHelper;
    ArrayList<HashMap<String, String>> netDataList;
    ArrayList<HashMap<String, String>> netDataListMobile;
    long mobileDataFromDatabase = 0;
    long wifiDataFromDatabase = 0;
    float netUsageMbInFloat[] = new float[]{0};
    long preMobileTx = 0;
    long preMobileRx = 0;
    long preTotalTx = 0;
    long preTotalRx = 0;
    long mobileDataTx = 0;
    long mobileDataRx = 0;
    long totalMobileData = 0;
    long totalData = 0;
    long totalWifiData = 0;

    public NetUsagesPagerAdapter(Context context) {

        this.context = context;
        //  inflater = LayoutInflater.from(context);
        appLocalDatabaseHelper = new AppLocalDatabaseHelper(context);
        netDataList = new ArrayList<>();
        netDataListMobile = new ArrayList<>();

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
    public Object instantiateItem(ViewGroup container, int position) {


        Log.d("_nahid", "##############instantiateItem###");


        float[] values = new float[]{0};

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View graphLayout = inflater.inflate(R.layout.nvai_viewpager_net_usages_item, container, false);


        //   updateNetworkData();

        netDataList = appLocalDatabaseHelper.getDeviceDataUsage();
        netDataListMobile = appLocalDatabaseHelper.getDeviceMobileDataUsage();

        assert graphLayout != null;
        barChart = (BarChart) graphLayout
                .findViewById(R.id.chart1);

        barChart.setOnChartValueSelectedListener(this);
        mTfRegular = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(context.getAssets(), "OpenSans-Light.ttf");

        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        barChart.setMaxVisibleValueCount(7);

        // scaling can now only be done on x- and y-axis separately
        barChart.setPinchZoom(false);

        barChart.setDrawBarShadow(false);
        barChart.setDrawGridBackground(false);


        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(barChart);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        xAxis.setValueFormatter(xAxisFormatter);


        IAxisValueFormatter custom = new MbAxisValueFormatter();

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(30f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setEnabled(false);

        // add a nice and smooth animation
        barChart.animateY(2000);

        //  barChart.getLegend().setEnabled(false);

        Legend l = barChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.EMPTY);

        l.setFormSize(9f);
        l.setTextSize(18f);
        l.setXEntrySpace(4f);


        // add code to visualize graph in viewpager

        XYMarkerView mv = new XYMarkerView(context, xAxisFormatter);
        mv.setChartView(barChart); // For bounds control
        barChart.setMarker(mv); // Set the marker to the chart

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

//        if (position==0){
//            values=new float[]{135,228,450,180,322,200,110};
//        }else if (position==1){
//
//            values=new float[]{35,28,150,180,222,20,110};
//        }


//        for (int i = 0; i < values.length; i++) {
//         //   float mult = (80 + 1);
//         //   float val = (float) (Math.random() * mult) + mult / 3;
//
//
//            yVals1.add(new BarEntry(i, values[i]));
//        }

        if (position == 0) {


            if (netDataList.size() == 0) {

                for (int i = 0; i < 5; i++) {
                    //   float mult = (80 + 1);
                    //   float val = (float) (Math.random() * mult) + mult / 3;

//                    float val= 30*i+10;
//                    yVals1.add(new BarEntry(i,val ));
//                    long  firstValue= TrafficStats.getTotalTxBytes()+TrafficStats.getTotalRxBytes();
//                    Float a=Float.parseFloat(firstValue+"");
//                    Float b=a/(1024*1024*1024);
                    yVals1.add(new BarEntry(i, 0));

                }

            } else {
                for (int i = 0; i < netDataList.size(); i++) {
                    //   float mult = (80 + 1);
                    //   float val = (float) (Math.random() * mult) + mult / 3;

                    long totalWifiData = Long.parseLong(netDataList.get(i).get("used_wifi"));
                    //  float val = Float.parseFloat(netDataList.get(i).get("wifi_data"));
                    float wifiDataInMb = (totalWifiData / (1024.00f * 1024.00f));
                    Log.v("_nahid", "wifi bar chart: " + (totalWifiData / (1024.0 * 1024.0)) + "");
                  //  DecimalFormat decimalFormat = new DecimalFormat("#.#");
//                    try {
                    Log.v("26_02:::", "Wifi bar chart in format: " + wifiDataInMb + "");
                    float oneDecimalPointWifiInMb = Float.parseFloat(wifiDataInMb + "");

                    if (oneDecimalPointWifiInMb<0){
                        oneDecimalPointWifiInMb=0;
                    }
                    yVals1.add(new BarEntry(i, oneDecimalPointWifiInMb));
                    Log.v("_nahid", "Wifi bar chart in format: " + oneDecimalPointWifiInMb + "");
//                    }
//                    catch (Exception e)
//                    {
////                        String val="123";
//
//
//                        String val=String.valueOf(wifiDataInMb);
//                        char[] arabicChars = {'০','১','২','৩','৪','৫','৬','৭','৮','৯'};
//                        StringBuilder builder = new StringBuilder();
//                        for(int j =0;j<val.length();j++){
//                            if(Character.isDigit(val.charAt(j))){
//                                Log.e("26_02",":bbb:val.charAt(j):"+val.charAt(j));
//                                builder.append(arabicChars[(int)(val.charAt(j))-48]);
//                            }
//                            else{
////                                builder.append(val.charAt(i));
//                                builder.append(".");
//                                Log.e("26_02",":bbbbb:val.charAt(j):"+val.charAt(j));
//                            }
//                        }
//                        System.out.println(""+builder.toString());
//                        Log.e("26_02",":a:b:c"+builder.toString());
//                        if(builder.toString().equals("০.০"))
//                        {
//
//                        }
//                        else
//                        {
//                            float oneDecimalPointWifiInMb = Float.parseFloat(decimalFormat.format(builder.toString()));
//                            yVals1.add(new BarEntry(i, oneDecimalPointWifiInMb));
//                        }
//
//                    }


                }
            }
            //     Log.v("1dateChangeReceiver","wifiDatalist  size"+netDataList.size());

        } else if (position == 1) {

            if (netDataListMobile.size() == 0) {

                for (int i = 0; i < 5; i++) {
                    //   float mult = (80 + 1);
                    //   float val = (float) (Math.random() * mult) + mult / 3;

                    float val = 10 * i + 10;
                    yVals1.add(new BarEntry(i, 90));

                }
            } else {
                for (int i = 0; i < netDataListMobile.size(); i++) {
                    //   float mult = (80 + 1);
                    //   float val = (float) (Math.random() * mult) + mult / 3;

                    long totalMobileData = Long.parseLong(netDataListMobile.get(i).get("used_mobile"));
                    float mobileDataInMb = (float) (totalMobileData / (1024.00 * 1024.00));
                    Log.v("_nahid", "mobile bar chart: " + (totalMobileData / (1024.0 * 1024.0)) + "");

                    //  DecimalFormat decimalFormat = new DecimalFormat("#.#");


//                    try {
                    Log.v("26_02:::", "mobile bar chart in format: " + mobileDataInMb + "");
                    // float oneDecimalPointMobileInMb = Float.parseFloat(decimalFormat.format(mobileDataInMb));
                    float oneDecimalPointMobileInMb = Float.parseFloat(mobileDataInMb + "");
                    yVals1.add(new BarEntry(i, oneDecimalPointMobileInMb));
//                    }
//                    catch (Exception e)
//                    {
//                        Log.v("26_02:::", "mobile bar chart in format: "+mobileDataInMb+"");
//                      //  float oneDecimalPointMobileInMb = Float.parseFloat(decimalFormat.format(mobileDataInMb));
////                      String val="123";
//                        String val=String.valueOf(mobileDataInMb);
//
//                            float flt=Float.parseFloat(val);
////                            //Log.e("26_02",":str:"+str+", flt:"+flt);
////                            float oneDecimalPointMobileInMb = Float.parseFloat(decimalFormat.format(flt));
////                            yVals1.add(new BarEntry(i, oneDecimalPointMobileInMb));
///*
//                        char[] arabicChars = {'০','১','২','৩','৪','৫','৬','৭','৮','৯','৯'};
//                        StringBuilder builder = new StringBuilder();
//                        for(int j =0;j<val.length();j++){
//                            if(Character.isDigit(val.charAt(j))){
//                                Log.e("26_02",":aaa:val.charAt(j):"+val.charAt(j));
//                                builder.append(arabicChars[(int)(val.charAt(j))-48]);
//                            }
//                            else{
////                                builder.append(val.charAt(i));
//                                builder.append(".");
//                                Log.e("26_02",":aaaaa:val.charAt(j):"+val.charAt(j));
//                            }
//                        }
//                        System.out.println(""+builder.toString());
//                        Log.e("26_02",":a:b:"+builder.toString());
//                        if(builder.toString().equals("০.০"))
//                        {
//
//                        }
//                        else {
//                            String str=builder.toString();
//                            Log.e("26_02",":str:"+str);
////                            float flt=Float.parseFloat(str);
////                            Log.e("26_02",":str:"+str+", flt:"+flt);
////                            float oneDecimalPointMobileInMb = Float.parseFloat(decimalFormat.format(flt));
////                            yVals1.add(new BarEntry(i, oneDecimalPointMobileInMb));
//                        }*/
//                    }


                }
            }
            //  Log.v("1dateChangeReceiver","mobile Datalist  size"+netDataList.size());

        }


        BarDataSet set1;

        if (barChart.getData() != null &&
                barChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {

            String title = "";
            if(AppsSettings.getAppsSettings(context).getLanguage().equals("eng"))
            {
                if (position == 0) {
                    title = "WIFI DATA";
                } else if (position == 1) {
                    title = "MOBILE DATA";
                }
            }
            else
            {
                if (position == 0) {
                    title = "ওয়াইফাই ডেটা";
                } else if (position == 1) {
                    title = "মোবাইল ডাটা";
                }
            }

            set1 = new BarDataSet(yVals1, title);
            //   ColorTemplate colorTemplate=new ColorTemplate();

            ArrayList<Integer> colors = new ArrayList<Integer>();

            colors.add(Color.parseColor("#57A157"));
            colors.add(Color.parseColor("#76448A"));
            colors.add(Color.parseColor("#6087EA"));
            colors.add(Color.parseColor("#EAC90C"));
            colors.add(Color.parseColor("#B9770E"));
            colors.add(Color.parseColor("#8cd20a"));
            colors.add(Color.parseColor("#a30858"));
            set1.setColors(colors);


            //set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
            set1.setDrawValues(false);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            barChart.setData(data);
            barChart.setFitBars(true);
        }

        barChart.invalidate();


        // upto this

        container.addView(graphLayout, 0);

        return graphLayout;
    }

    public String getdateInEnglish(String string) {
        Character bangla_number[] = {'০', '১', '২', '৩', '৪', '৫', '৬', '৭', '৮', '৯'};
        Character eng_number[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String values = "";
        char[] character = string.toCharArray();
        for (int i = 0; i < character.length; i++) {
            Character c = ' ';
            for (int j = 0; j < eng_number.length; j++) {
                if (character[i] == eng_number[j]) {
                    c = eng_number[j];
                    break;
                } else {
                    c = character[i];
                }
            }
            values = values + c;
        }
        return values;
    }

    private void updateNetworkData() {


        // get previous traffic stat values
        HashMap<String, String> previousDailyNetworkDataHashMap = new HashMap<>();
        previousDailyNetworkDataHashMap = appLocalDatabaseHelper.getPreviousNetworkData();
        if (previousDailyNetworkDataHashMap.size() > 0) {

            // previous traffic stat value
            preMobileTx = Long.parseLong(previousDailyNetworkDataHashMap.get("previous_mobile_tx_data"));
            preMobileRx = Long.parseLong(previousDailyNetworkDataHashMap.get("previous_mobile_rx_data"));
            preTotalTx = Long.parseLong(previousDailyNetworkDataHashMap.get("previous_total_tx_data"));
            preTotalRx = Long.parseLong(previousDailyNetworkDataHashMap.get("previous_total_rx_data"));

            Log.d("_m_data", " update method pre Total data: " + (preTotalTx + preTotalRx) + "");
            Log.d("_m_data", " update method pre mobile data: " + (preMobileTx + preMobileRx) + "");

        }


        // current traffic stat values
        mobileDataTx = TrafficStats.getMobileTxBytes();
        mobileDataRx = TrafficStats.getMobileRxBytes();
        totalMobileData = mobileDataRx + mobileDataTx;
        totalData = TrafficStats.getTotalRxBytes() + TrafficStats.getTotalTxBytes();
        totalWifiData = totalData - totalMobileData;

        Log.d("_m_data", " update method currentts mobile data: " + totalMobileData + "");
        Log.d("_m_data", " update method currentts wifi data: " + totalWifiData + "");
        ArrayList<HashMap<String, String>> networkDataList = new ArrayList<>();
        networkDataList = appLocalDatabaseHelper.getNetworkData();

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter1.format(date);


        // changing here
        String year=calendar.get(Calendar.YEAR)+"";
        String month=(calendar.get(Calendar.MONTH)+1)+"";
        String day=calendar.get(Calendar.DAY_OF_MONTH)+"";
        String customDateString=year+"-"+month+"-"+day;
        // changing end


        if (networkDataList.size() > 0) {

            // current date  network data from database value
            for (int z = 0; z < networkDataList.size(); z++) {

                if (networkDataList.get(z).get("date").equalsIgnoreCase(customDateString)) {
                    mobileDataFromDatabase = Long.parseLong(networkDataList.get(z).get("mobile_data"));
                    wifiDataFromDatabase = Long.parseLong(networkDataList.get(z).get("wifi_data"));
                    Log.d("_m_data", " update method database mobile data: " + mobileDataFromDatabase + "");
                    Log.d("_m_data", " update method database wifi data: " + wifiDataFromDatabase + "");


                }
            }
        }

        // update data
        long finalMobileData = mobileDataFromDatabase + (totalMobileData - (preMobileTx + preMobileRx));
        long prevTotalWifi = (preTotalRx + preTotalTx) - (preMobileRx + preMobileTx);
        long finalWifiData = wifiDataFromDatabase + (totalWifiData - (prevTotalWifi));
        Log.d("_m_data", "final mobile data: " + finalMobileData + "");
        Log.d("_m_data", "final  wifi data: " + finalWifiData + "");
        // update current date row when device shut down
        appLocalDatabaseHelper.updateDailyNetUsageData(finalMobileData + "", finalWifiData + "", customDateString);

        // update previous data  to current traffic stat
        appLocalDatabaseHelper.insertPreviousNetworkData(mobileDataTx + "", mobileDataRx + "", TrafficStats.getTotalTxBytes() + "", TrafficStats.getTotalRxBytes() + "");

        Log.d("_m_data", "insert prev total data: " + (TrafficStats.getTotalTxBytes() + TrafficStats.getTotalRxBytes()) + "");
        Log.d("_m_data", "insert  prev mobile data: " + (mobileDataTx + mobileDataRx) + "");


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
        barChart.getBarBounds((BarEntry) e, bounds);
        MPPointF position = barChart.getPosition(e, YAxis.AxisDependency.LEFT);

        Log.i("bounds", bounds.toString());
        Log.i("position", position.toString());

        Log.i("x-index",
                "low: " + barChart.getLowestVisibleX() + ", high: "
                        + barChart.getHighestVisibleX());

        MPPointF.recycleInstance(position);
    }

    @Override
    public void onNothingSelected() {

    }
}
