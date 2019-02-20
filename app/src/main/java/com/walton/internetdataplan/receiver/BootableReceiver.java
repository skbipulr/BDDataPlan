package com.walton.internetdataplan.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.TrafficStats;
import android.util.Log;

import com.walton.internetdataplan.AppManager;
import com.walton.internetdataplan.models.InternetData;
import com.walton.internetdataplan.nvai.AppLocalDatabaseHelper;
import com.walton.internetdataplan.services.DailyConsumedService;
import com.walton.internetdataplan.services.MobileWiFiDataUpdateService1;
import com.walton.internetdataplan.utitls.DataPlan;
import com.walton.internetdataplan.utitls.MyServiceRunning;
import com.walton.internetdataplan.utitls.WHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Faruq on 12/22/2016.
 */

public class BootableReceiver extends BroadcastReceiver {


    AppLocalDatabaseHelper appLocalDatabaseHelper;
    ArrayList<HashMap<String, String>> networkDataList;
    @Override
    public void onReceive(Context mContext, Intent intent) {
        try {
            Log.e("05_16_omor",":Botable:: :onReceive: e:");
            Thread.sleep(3000);


            //************************ adding code for barchart for starting service if not started


            if (!MyServiceRunning.getInstance(mContext).isMyServiceRunning(MobileWiFiDataUpdateService1.class)) {
                // start service
                mContext.startService(new Intent(mContext, MobileWiFiDataUpdateService1.class));
                // end of start service
            }



            // if current date has no row in the device data usage and device mobile data
            // then insert a new row in the table
            appLocalDatabaseHelper=new AppLocalDatabaseHelper(mContext);
            Calendar calendar = Calendar.getInstance();


            String year=calendar.get(Calendar.YEAR)+"";
            String month=(calendar.get(Calendar.MONTH)+1)+"";
            String day=calendar.get(Calendar.DAY_OF_MONTH)+"";
            String todayDateString=year+"-"+month+"-"+day;
            networkDataList = new ArrayList<>();
            networkDataList = appLocalDatabaseHelper.getDeviceDataUsage();
            boolean found=false;
            for (int x=0;x<networkDataList.size();x++){
                if (networkDataList.get(x).get("date").equalsIgnoreCase(todayDateString)){
                    found=true;

                }
            }

            long totalTraffic= TrafficStats.getTotalRxBytes()+TrafficStats.getTotalTxBytes();
            long totalMobileTraffic=TrafficStats.getMobileTxBytes()+TrafficStats.getMobileRxBytes();

            if (found==false){
                appLocalDatabaseHelper.insertDeviceDataUsage(0+"",totalTraffic+"",0+"",todayDateString);
                appLocalDatabaseHelper.insertDeviceMobileDataUsage(0+"",totalMobileTraffic+"",todayDateString);

            }



            // end




            //**************************** end of adding code for barchart for starting service if not started




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
                    Log.e("Today", ":isTargetPercentis:e" + e.toString());
                }
                // end of database end date
                // compare 2 dates
                boolean isYes = WHelper.getInstance(mContext).compare2Dates(mCurrentDate, mMofifyEndDate);
                if (isYes) {
                    // check internet data
                    ArrayList<InternetData> mInternetData = new ArrayList<InternetData>();
                    if (mInternetData != null && mInternetData.size() > 0) {
                        mInternetData.clear();
                    }
                    // getting current date with format : yyyy-MM-dd
                    Date date = Calendar.getInstance().getTime();
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String mDate = formatter.format(date);
                    mInternetData = AppManager.getInstance(mContext).retrieveInternetDataPlan(mContext, mDate);


                    if (mInternetData.size() == 0) {

                    }
                    else
                    {
                        try {
                            String mPreUsage = mInternetData.get(0).getUsegeData();
                            String mPreCurrent = mInternetData.get(0).getCurrentData();
                            String mPrePrevious = mInternetData.get(0).getPreviousData();
                            String mmDate = mDate;
                            String mCurrent = 0 + "";
                            String mPrevious = "-"+mPreUsage;
                            String mUsage = mPreUsage;
                            Log.e("05_16_omor", ":BootableReceiver:uUsage:" + mUsage + ", :mCurrent:" + mCurrent + ", mPrevious:" + mPrevious);
                            if(Float.parseFloat(mUsage)>=0) {
                                AppManager.getInstance(mContext).updateInternetData(mContext, mUsage, mCurrent, mPrevious, mDate, mPreUsage, mPreCurrent, mPrePrevious);
                            }
                        } catch (Exception e) {
                            Log.e("05_16_omor", ":BootableReceiver:Exception:uUsage:" + e.toString());
                        }
                    }



                    // check running service or not
                    if (!MyServiceRunning.getInstance(mContext).isMyServiceRunning(DailyConsumedService.class)) {
                        // start service
                        mContext.startService(new Intent(mContext, DailyConsumedService.class));
                        // end of start service
                    }
                    Log.e("05_16_omor", "::BootableReceiver:onReceive");
                }
                else
                {
                    Log.e("05_16_omor",":Botable else YES:");
                }
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("05_16_omor",":Botable:: :InterruptedException: e:"+e.toString());
        }


    }
}
