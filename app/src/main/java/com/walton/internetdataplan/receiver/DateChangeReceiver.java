package com.walton.internetdataplan.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.TrafficStats;
import android.util.Log;

import com.walton.internetdataplan.AppManager;
import com.walton.internetdataplan.models.InternetData;
import com.walton.internetdataplan.nvai.AppLocalDatabaseHelper;
import com.walton.internetdataplan.utitls.AppConstants;
import com.walton.internetdataplan.utitls.AppsSettings;
import com.walton.internetdataplan.utitls.DataPlan;
import com.walton.internetdataplan.utitls.WHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Faruq on 12/24/2016.
 */

public class DateChangeReceiver extends BroadcastReceiver {


    // for wifi and mobile barchart data

    AppLocalDatabaseHelper appLocalDatabaseHelper;
    // String wifiData, mobileData;
    String previousDateString;
    HashMap<String, String> previousDataHashMap;
    HashMap<String, String> previousDailyNetworkDataHashMap;
    float preMobileTx = 0;
    float preMobileRx = 0;
    float preTotalTx = 0;
    float preTotalRx = 0;

    ArrayList<HashMap<String, String>> networkDataList;
    float mobileDataFromDatabase = 0;
    float wifiDataFromDatabase = 0;

    // end
    long prevTotal = 0;
    long mobileData = 0;

    // end
    long usedMobile = 0;
    long prevMobile = 0;


    @Override
    public void onReceive(Context mContext, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_DATE_CHANGED)) {
          AppsSettings.getAppsSettings(mContext).setDateChangeStatus(true);
            AppConstants.adjustData=0;

            Log.d("_nahid", "###############date Receiver called");

            appLocalDatabaseHelper = new AppLocalDatabaseHelper(mContext);



            // *********************************start of task to wifi and mobile barchart**************************


            // update previous date data of device_data_usage_table
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1);
            Date date = calendar.getTime();
            DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
         //   String previousDateString = formatter1.format(date);


            // changing here
            String year=calendar.get(Calendar.YEAR)+"";
            String month=(calendar.get(Calendar.MONTH)+1)+"";
            String day=calendar.get(Calendar.DAY_OF_MONTH)+"";
            String customDateString=year+"-"+month+"-"+day;
            String previousDateString = customDateString;


            // current traffic stat values
            long currentTotalTraffic=TrafficStats.getTotalRxBytes()+TrafficStats.getTotalTxBytes();

            // getting previous date values from database
            ArrayList<HashMap<String, String>> networkDataList=new ArrayList<HashMap<String, String>>();
            networkDataList=appLocalDatabaseHelper.getDeviceDataUsage();
            if (networkDataList.size()>0){

                for (int x=0;x<networkDataList.size();x++){
                    if (networkDataList.get(x).get("date").equalsIgnoreCase(previousDateString)){
                        prevTotal=Long.parseLong(networkDataList.get(x).get("previous_total"));
                        mobileData=Long.parseLong(networkDataList.get(x).get("mobile_data"));

                        Log.v("_nahid","**************************************************");
                        Log.v("_nahid","Date changed: get wifi table data from current date");
                        Log.v("_nahid","prevTotal :"+(prevTotal/(1024*1024))+" mobile data :"+(mobileData/(1024*1024)));

                    }
                }
            }else{
                Log.v("_m_data","inside date change : database table is empty");
            }

            long usedWifi=(currentTotalTraffic-prevTotal-mobileData);
            // update current date data in data_usage_table


            // stopped the date change update
                appLocalDatabaseHelper.updateDeviceDataUsage(usedWifi+"",prevTotal+"",mobileData+"",previousDateString);
            Log.v("_nahid","Date changed: data updated for wifi table :date :"+previousDateString);
            Log.v("_nahid","used wifi:"+(usedWifi/(1024*1024))+"prevTotal :"+(prevTotal/(1024*1024))+" mobile data :"+(mobileData/(1024*1024)));

            // insert new row with present date in device_data_usage

            Calendar currentCalender = Calendar.getInstance();
            Date currentDate = currentCalender.getTime();
            // changing here
            String year2=currentCalender.get(Calendar.YEAR)+"";
            String month2=(currentCalender.get(Calendar.MONTH)+1)+"";
            String day2=currentCalender.get(Calendar.DAY_OF_MONTH)+"";
            String customDateString2=year2+"-"+month2+"-"+day2;
          //  String currentDateString = formatter1.format(currentDate);
            String currentDateString = customDateString2;

            appLocalDatabaseHelper.insertDeviceDataUsage(0+"",(currentTotalTraffic-mobileData)+"",mobileData+"",currentDateString);

            //  appLocalDatabaseHelper.insertDeviceDataUsage(0+"",currentTotalTraffic+"",mobileData+"",currentDateString);
            //  appLocalDatabaseHelper.insertDeviceDataUsage(0+"",usedWifi+"",mobileData+"",currentDateString);

            Log.v("_nahid","Date changed: new row inserted  for wifi table date : "+currentDateString);
            Log.v("_nahid","used wifi:"+"0"+"prevTotal :"+(currentTotalTraffic/(1024*1024))+" mobile data :"+(mobileData/(1024*1024)));


            // update device_mobile_data_usage

            long currentTotalMobileTraffic = TrafficStats.getMobileRxBytes() + TrafficStats.getMobileTxBytes();
            ArrayList<HashMap<String, String>> networkMobileDataList = new ArrayList<HashMap<String, String>>();
            networkMobileDataList = appLocalDatabaseHelper.getDeviceMobileDataUsage();
            if (networkMobileDataList.size() > 0) {

                for (int x = 0; x < networkMobileDataList.size(); x++) {
                    if (networkMobileDataList.get(x).get("date").equalsIgnoreCase(previousDateString)) {


                        usedMobile = Long.parseLong(networkMobileDataList.get(x).get("used_mobile"));
                        prevMobile = Long.parseLong(networkMobileDataList.get(x).get("previous_mobile"));
                        Log.v("_nahid","Date changed: get mobile table data from current date");
                        Log.v("_nahid","prevMobile :"+(prevMobile/(1024*1024))+" used mobile data :"+(usedMobile/(1024*1024)));

                    }
                }

            } else {
                Log.v("_m_data", "inside service : database table is empty");
            }


            long usedMobile=currentTotalMobileTraffic-prevMobile;

            // update mobile used data in device_mobile_data_usage table

            // stopping update for previous date

             appLocalDatabaseHelper.updateDeviceMobileDataUsage(usedMobile+"",prevMobile+"",previousDateString);
            Log.v("_nahid","Date changed: data updated for mobile table date : "+previousDateString);
            Log.v("_nahid","used mobile:"+(usedMobile/(1024*1024))+"prev mobile :"+(prevMobile/(1024*1024)));

            appLocalDatabaseHelper.insertDeviceMobileDataUsage(0+"",currentTotalMobileTraffic+"",currentDateString);
            Log.v("_nahid","Date changed: data inserted for mobile table : date "+currentDateString);
            Log.v("_nahid","used mobile:"+"0"+"prev mobile :"+(currentTotalMobileTraffic/(1024*1024)));


            //         end of task to wifi and mobile bar chart




            ArrayList<DataPlan> mDataPlan = new ArrayList<DataPlan>();
            if (mDataPlan != null && mDataPlan.size() > 0) {
                mDataPlan.clear();
            }
            mDataPlan = AppManager.getInstance(mContext).retrieveDataPlan(mContext);
            // check data exist or not in database //
            if (mDataPlan != null && mDataPlan.size() > 0) {

                String mDSType = mDataPlan.get(0).getDataSizeType();
                String mDEndDate = mDataPlan.get(0).getEndDate();
                String mDSize = mDataPlan.get(0).getDataSize();
                // check Enddate is ok or not //
                // get current date
                Date date12 = new Date();
                String mCurrentDate = new SimpleDateFormat("yyyy-MM-dd").format(date12);
                // end of get current date
                String mMofifyEndDate = null;
                try {
                    // re format end of date
                    SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
                    Date modifyFormat = format1.parse(mDEndDate);
                    mMofifyEndDate = format2.format(modifyFormat);
                    // end of re format end of date
                    Log.e("Today", ":isTargetPercentis:mDSType:" + mDSType + ", mDSize:" + mDSize + ", mDEndDate:" + mDEndDate + ", mMofifyEndDate:" + mMofifyEndDate);

                } catch (ParseException e) {
                    e.printStackTrace();
                    Log.e("05_16_omor", ":isTargetPercentis:e" + e.toString());
                }
                // end of database end date
                // compare 2 dates
                boolean isYes = WHelper.getInstance(mContext).compare2Dates(currentDateString, mMofifyEndDate);
                if (isYes) {

                    // reset all flag
                    AppsSettings.getAppsSettings(mContext).setInternetConsumeLimiParcentigetOverCome(false);
                    AppsSettings.getAppsSettings(mContext).setDailyInternetConsumeLimitOverCome(false);
                    insertOrUpdateData(mContext);
//                    insertOrUpdateData(mContext);
                    Log.e("05_16_omor", "" + "ACTION_DATE_CHANGED received");


                } else {
                    Log.e("05_16_omor", ":DateChangeReceiver:DateChangeReceiver:Already expair date");
                }
                // end of compare 2 dates
                // end of check Enddate is ok or not //


            } else {
                Log.e("05_16_omor", ":DateChangeReceiver:Not Initialized data plan yet:");
            }


        }
    }



    private void insertOrUpdateData(Context mContext) {

        // getting current date with format : yyyy-MM-dd
        Date date;
        String mDate;
        date = Calendar.getInstance().getTime();
        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

        // changing here
        Calendar calendar = Calendar.getInstance();
        String year=calendar.get(Calendar.YEAR)+"";
        String month=(calendar.get(Calendar.MONTH)+1)+"";
        String day=calendar.get(Calendar.DAY_OF_MONTH)+"";
        String customDateString=year+"-"+month+"-"+day;
        // changing end



        //mDate = formater.format(date);
        mDate = customDateString;

        // just calculate data
        float mMobileRx = 0;
        float mMobileTx = 0;
        float mMobileRTx = 0;
        mMobileRx=TrafficStats.getMobileRxBytes();
        mMobileTx=TrafficStats.getMobileTxBytes();
        mMobileRTx = mMobileRx + mMobileTx;
        // check internet data
        ArrayList<InternetData> mInternetData = new ArrayList<InternetData>();
        if (mInternetData != null && mInternetData.size() > 0) {
            mInternetData.clear();
        }
        mInternetData = AppManager.getInstance(mContext).retrieveInternetDataPlan(mContext, mDate);


        if (mInternetData.size() == 0) {
            // get current traffic states
            String mUsage = "0.0";
            String mCurrent = String.valueOf(mMobileRTx);
            String mPrevious = String.valueOf(mMobileRTx);
            if (Float.parseFloat(mUsage) >= 0) {
                AppManager.getInstance(mContext).addInternetData(mContext, mUsage, mCurrent, mPrevious, mDate);
                Log.e("16_01", ":insertOrUpdateData:new insert:mUsage:"+mUsage+":mCurrent:"+mCurrent+":mPrevious:"+mPrevious+":mDate:"+mDate+":mMobileRTx:"+mMobileRTx);
            }


        } else {
            try {


                String mPreUsage = mInternetData.get(0).getUsegeData();
                String mPreCurrent = mInternetData.get(0).getCurrentData();
                String mPrePrevious = mInternetData.get(0).getPreviousData();
                String mmDate = mDate;
                String mCurrent = mMobileRTx + "";
                String mPrevious = mPrePrevious;
                float xy;

                xy = mMobileRTx - Float.parseFloat(mPrevious);

                String mUsage = xy + "";
                // check date change status //
                if (AppsSettings.getAppsSettings(mContext).getDateChangeStatus()) {
                    AppsSettings.getAppsSettings(mContext).setDateChangeStatus(false);
                    String mUsageAA = mPreUsage;
                    float mRx=TrafficStats.getMobileRxBytes();
                    float mTx=TrafficStats.getMobileTxBytes();
                    float mRTx = mRx + mTx;
                    String mCurrentAA = String.valueOf(mRTx);
                    String mPreviousAA = String.valueOf(mRTx);

                    AppManager.getInstance(mContext).updateInternetData(mContext, mUsageAA, mCurrentAA, mPreviousAA, mDate, mPreUsage, mPreCurrent, mPrePrevious);
                    Log.e("16_01", ":insertOrUpdateData:update:0.0:mUsageAA:" + mUsageAA + ":mCurrentAA:" + mCurrentAA + ":mPreviousAA:" + mPreviousAA + ":mDate:" + mDate + ":mPreUsage:" + mPreUsage + ":mPreCurrent:" + mPreCurrent + ":mPrePrevious:" + mPrePrevious);





                } else {
                    // end of check date change status //
                    if (mPreUsage.equals("0.0") && mPreCurrent.equals("0.0") && mPrePrevious.equals("0.0")) {
                        // get current traffic states
                        String mUsageT = "0.0";
                        String mCurrentT = String.valueOf(mMobileRTx);
                        String mPreviousT = String.valueOf(mMobileRTx);
                        if (Float.parseFloat(mUsage) >= 0) {
                            AppManager.getInstance(mContext).updateInternetData(mContext, mUsageT, mCurrentT, mPreviousT, mDate, mPreUsage, mPreCurrent, mPrePrevious);
                            Log.e("16_01", ":insertOrUpdateData:update:0.0:mUsageT:" + mUsageT + ":mCurrentT:" + mCurrentT + ":mPreviousT:" + mPreviousT + ":mDate:" + mDate + ":mPreUsage:" + mPreUsage + ":mPreCurrent:" + mPreCurrent + ":mPrePrevious:" + mPrePrevious);
                        }
                    } else {
                        {
                            if (xy > 0) {
                                AppManager.getInstance(mContext).updateInternetData(mContext, mUsage, mCurrent, mPrevious, mDate, mPreUsage, mPreCurrent, mPrePrevious);
                                Log.e("16_01", ":insertOrUpdateData:update:mUsage:" + mUsage + ":mCurrent:" + mCurrent + ":mPrevious:" + mPrevious + ":mDate:" + mDate + ":mPreUsage:" + mPreUsage + ":mPreCurrent:" + mPreCurrent + ":mPrePrevious:" + mPrePrevious);
                            }
                        }
                    }
                }
            }catch(Exception e){
                Log.e("16_01", ":DailyConsumedService:Exception:uUsage:" + e.toString());
            }


        }
    }
}
