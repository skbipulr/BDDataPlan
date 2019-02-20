package com.walton.internetdataplan.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.walton.internetdataplan.nvai.AppLocalDatabaseHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Faruq on 1/10/2017.
 */

public class DeviceShutDownReceiver extends BroadcastReceiver {

    HashMap<String,String> previousDailyNetworkDataHashMap;
    long preMobileTx=0;
    long preMobileRx=0;
    long preTotalTx=0;
    long preTotalRx=0;
    ArrayList<HashMap<String, String>> networkDataList;
    long mobileDataFromDatabase = 0;
    long wifiDataFromDatabase = 0;


    AppLocalDatabaseHelper appLocalDatabaseHelper;
    ArrayList<HashMap<String,String>> deviceDataList;
    long usedTotal=0;
    long negativeUsedTotal=0;
    long mobileData=0;

    ArrayList<HashMap<String,String>> deviceMobileDataList;
    long usedMobile=0;
    long prevMobile=0;
    long negativeUsedMobile=0;


    @Override
    public void onReceive(Context context, Intent intent) {

        appLocalDatabaseHelper = new AppLocalDatabaseHelper(context);

        deviceDataList=new ArrayList<>();
        //  networkDataList = new ArrayList<>();
        deviceMobileDataList=new ArrayList<>();


        // *********************************start of task to wifi and mobile barchart**************************
// code for wifi and mobile bar chart


        deviceDataList=appLocalDatabaseHelper.getDeviceDataUsage();
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");

        // changing here

        Date date = calendar.getTime();

        // changing here
        String year=calendar.get(Calendar.YEAR)+"";
        String month=(calendar.get(Calendar.MONTH)+1)+"";
        String day=calendar.get(Calendar.DAY_OF_MONTH)+"";
        String customDateString=year+"-"+month+"-"+day;
        // changing end
        // end of change




      //  String currentDateString = formatter1.format(currentDate);
        String currentDateString = customDateString;

        if (deviceDataList.size()>0){

            for (int y=0;y<deviceDataList.size();y++){
                if (deviceDataList.get(y).get("date").equalsIgnoreCase(currentDateString)){
                    usedTotal=Long.parseLong(deviceDataList.get(y).get("used_wifi"));
                    mobileData=Long.parseLong(deviceDataList.get(y).get("mobile_data"));
                    negativeUsedTotal=usedTotal*(-1);

                    Log.v("_nahid","inside bootable negative used data is :"+negativeUsedTotal);
                }
            }
        }
        appLocalDatabaseHelper.updateDeviceDataUsage(usedTotal+"",negativeUsedTotal+"",0+"",currentDateString);


// update mobile data table


        deviceMobileDataList=appLocalDatabaseHelper.getDeviceMobileDataUsage();
        if (deviceMobileDataList.size()>0){
            for (int z=0;z<deviceMobileDataList.size();z++){
                if (deviceMobileDataList.get(z).get("date").equalsIgnoreCase(currentDateString)){


                    usedMobile=Long.parseLong(deviceMobileDataList.get(z).get("used_mobile"));
                    prevMobile=Long.parseLong(deviceMobileDataList.get(z).get("previous_mobile"));
                    negativeUsedMobile=usedMobile*(-1);



                }
            }
        }
        appLocalDatabaseHelper.updateDeviceMobileDataUsage(usedMobile+"",negativeUsedMobile+"",currentDateString);




//        if (!MyServiceRunning.getInstance(context).isMyServiceRunning(MobileWiFiDataUpdateService1.class)) {
//            // start service
//            context.startService(new Intent(context, MobileWiFiDataUpdateService1.class));
//            // end of start service
//        }


        // end of code of mobile and wifi bar chart



//
//
//        previousDailyNetworkDataHashMap=new HashMap<>();
//        previousDailyNetworkDataHashMap=appLocalDatabaseHelper.getPreviousNetworkData();
//
//        if (previousDailyNetworkDataHashMap.size()>0){
//
//            // previous traffic stat value
//            preMobileTx=Long.parseLong(previousDailyNetworkDataHashMap.get("previous_mobile_tx_data"));
//            preMobileRx=Long.parseLong(previousDailyNetworkDataHashMap.get("previous_mobile_rx_data"));
//            preTotalTx=Long.parseLong(previousDailyNetworkDataHashMap.get("previous_total_tx_data"));
//            preTotalRx=Long.parseLong(previousDailyNetworkDataHashMap.get("previous_total_rx_data"));
//
//        }
//
//        // current traffic stat values
//        long mobileDataTx = TrafficStats.getMobileTxBytes();
//        long mobileDataRx = TrafficStats.getMobileRxBytes();
//        long totalMobileData = mobileDataRx + mobileDataTx;
//        long totalData = TrafficStats.getTotalRxBytes() + TrafficStats.getTotalTxBytes();
//        long totalWifiData = totalData - totalMobileData;
//
//
//        Calendar calendar = Calendar.getInstance();
//        Date date = calendar.getTime();
//        DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
//        String dateString = formatter1.format(date);
//
//
//        if (networkDataList.size() > 0) {
//
//            // current date  network data from database value
//            for (int z = 0; z < networkDataList.size(); z++) {
//
//                if (networkDataList.get(z).get("date").equalsIgnoreCase(dateString)) {
//                    mobileDataFromDatabase = Long.parseLong(networkDataList.get(z).get("mobile_data"));
//                    wifiDataFromDatabase = Long.parseLong(networkDataList.get(z).get("wifi_data"));
//
//                }
//            }
//
//        }
//
//        long finalMobileData = mobileDataFromDatabase+(totalMobileData-(preMobileTx+preMobileRx));
//        long prevTotalWifi=(preTotalRx+preTotalTx)-(preMobileRx+preMobileTx);
//        long finalWifiData = wifiDataFromDatabase+(totalWifiData-(prevTotalWifi));
//
//
//        // update current date row when device shut down
//        appLocalDatabaseHelper.updateDailyNetUsageData(finalMobileData+"", finalWifiData+"", dateString);
//
//        // update previous data  to zero
//        appLocalDatabaseHelper.insertPreviousNetworkData("0","0","0","0");
    }
}
