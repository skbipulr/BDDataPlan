package com.walton.internetdataplan.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.TrafficStats;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.walton.internetdataplan.nvai.AppLocalDatabaseHelper;
import com.walton.internetdataplan.utitls.AppConstants;
import com.walton.internetdataplan.utitls.InternetDetect;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Faruq on 1/10/2017.
 */

public class MobileWiFiDataUpdateService1 extends Service {
    Context context;
    AppLocalDatabaseHelper appLocalDatabaseHelper;

    long prevTotal = 0;
    long mobileData = 0;
    long prevMobile = 0;
    long usedMobile = 0;

    static long previousMinus=0;
    static long currentMinus=0;
    boolean firstEntered=false;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Log.v("_m_data", " ***************** Service started ************");

        appLocalDatabaseHelper = new AppLocalDatabaseHelper(context);

        final ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate
                (new Runnable() {
//                    @RequiresApi(api = VERSION_CODES.LOLLIPOP)
                    public void run() {

                        if (InternetDetect.netDetect(context).equals("mobile")) {

                            //  connected data is mobile

                            Log.d("_nahid", " mobile data connected");
                            Calendar calendar = Calendar.getInstance();
                            Date date = calendar.getTime();
                            DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                          //  String dateString = formatter1.format(date);


                            // changing here
                            String year=calendar.get(Calendar.YEAR)+"";
                            String month=(calendar.get(Calendar.MONTH)+1)+"";
                            String day=calendar.get(Calendar.DAY_OF_MONTH)+"";
                            String customDateString=year+"-"+month+"-"+day;
                            String dateString = customDateString;
                            // changing end

                            long currentTotalMobileTraffic = TrafficStats.getMobileRxBytes() + TrafficStats.getMobileTxBytes();
                            ArrayList<HashMap<String, String>> networkMobileDataList = new ArrayList<HashMap<String, String>>();
                            networkMobileDataList = appLocalDatabaseHelper.getDeviceMobileDataUsage();
                            if (networkMobileDataList.size() > 0) {

                                for (int x = 0; x < networkMobileDataList.size(); x++) {
                                    if (networkMobileDataList.get(x).get("date").equalsIgnoreCase(dateString)) {


                                        usedMobile = Long.parseLong(networkMobileDataList.get(x).get("used_mobile"));
                                        prevMobile = Long.parseLong(networkMobileDataList.get(x).get("previous_mobile"));
                                        Log.d("_nahid", "**Mobile database used_mobile:" + (usedMobile / (1024 * 1024)) + " previous_mobile :" + (prevMobile / (1024 * 1024)));
                                    }
                                }

                            } else {
                                Log.v("_nahid", "inside service : database table is empty");
                            }

                            Log.d("_nahid", "**Mobile current traffic total :" + ((TrafficStats.getTotalTxBytes() + TrafficStats.getTotalRxBytes()) / (1024 * 1024)) + " mobile :" + (currentTotalMobileTraffic / (1024 * 1024)));
                            long newUsedMobile = currentTotalMobileTraffic - prevMobile;

                            // update mobile used data in device_mobile_data_usage table

                            appLocalDatabaseHelper.updateDeviceMobileDataUsage(newUsedMobile + "", prevMobile + "", dateString);
                            // update mobile data in device_data_usage table for wifi
                            Log.d("_nahid", "**Mobile updated mobile database used_mobile:" + (newUsedMobile / (1024 * 1024)) + " previous_mobile :" + (prevMobile / (1024 * 1024)) + " date:" + dateString);


                            appLocalDatabaseHelper.updateDeviceDataUsageWifi(currentTotalMobileTraffic, dateString);
                            Log.d("_nahid", "**Mobile updated wifi table mobile_data:" + (currentTotalMobileTraffic / (1024 * 1024)) + " date:" + dateString);


                        } else {
                            Log.d("_m_data", " wifi data connected");

                            Calendar calendar = Calendar.getInstance();
                            Date date = calendar.getTime();
                            DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                          //  String dateString = formatter1.format(date);


                            // changing here
                            String year=calendar.get(Calendar.YEAR)+"";
                            String month=(calendar.get(Calendar.MONTH)+1)+"";
                            String day=calendar.get(Calendar.DAY_OF_MONTH)+"";
                            String customDateString=year+"-"+month+"-"+day;
                            String dateString = customDateString;



                            long currentTotalTraffic = TrafficStats.getTotalRxBytes() + TrafficStats.getTotalTxBytes();

                            // get data from today date database row in device_data_usage table

                            ArrayList<HashMap<String, String>> networkDataList = new ArrayList<HashMap<String, String>>();
                            networkDataList = appLocalDatabaseHelper.getDeviceDataUsage();
                            if (networkDataList.size() > 0) {

                                for (int x = 0; x < networkDataList.size(); x++) {
                                    if (networkDataList.get(x).get("date").equalsIgnoreCase(dateString)) {
                                        prevTotal = Long.parseLong(networkDataList.get(x).get("previous_total"));
                                        mobileData = Long.parseLong(networkDataList.get(x).get("mobile_data"));
                                        Log.d("_nahid", "**wifi database prev_total:" + (prevTotal / (1024 * 1024)) + " mobile_data :" + (mobileData / (1024 * 1024)));

                                    }
                                }

                            } else {
                                Log.v("_m_data", "inside service : database table is empty");
                            }

                            // ****** changing now
                            long usedWifi=0;
                            if ((currentTotalTraffic - prevTotal - mobileData)<0){


                                usedWifi=0;

                                // sghshsh
                                if (firstEntered==false){
                                    previousMinus=currentTotalTraffic - prevTotal - mobileData;
                                    firstEntered=true;
                                  //  usedWifi=0;

                                }else {
                                    currentMinus=currentTotalTraffic - prevTotal - mobileData;
                                    AppConstants.adjustData=currentMinus-(previousMinus);
                                   // usedWifi = AppConstants.adjustData;

                                }

                                //fgsdfhshfgh

                            }else{
                                usedWifi = (currentTotalTraffic - prevTotal - mobileData);

                            }

                            // update current date data in data_usage_table
                            appLocalDatabaseHelper.updateDeviceDataUsage((usedWifi)+AppConstants.adjustData + "", prevTotal + "", mobileData + "", dateString);
                            Log.d("_nahid", "**wifi current traffic total :" + (currentTotalTraffic / (1024 * 1024)) + " mobile total traffic :" + ((TrafficStats.getMobileTxBytes() + TrafficStats.getMobileRxBytes()) / (1024 * 1024)));
                            Log.d("_nahid", "**wifi updated wifi database used_wifi:" + (usedWifi / (1024 * 1024)) + " previous_total :" + (prevTotal / (1024 * 1024)) + "mobile data: " + (mobileData / (1024 * 1024)) + " date:" + dateString);


                        }

                    }
                }, 0, 30, TimeUnit.SECONDS);


        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }



    public class MobileWifiDataServiceBinder extends Binder {

        public MobileWiFiDataUpdateService1 getService() {
            return MobileWiFiDataUpdateService1.this;
        }
    }
    private MobileWifiDataServiceBinder mBinder = new MobileWifiDataServiceBinder();
}
