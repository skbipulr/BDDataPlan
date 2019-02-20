package com.walton.internetdataplan.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.walton.internetdataplan.nvai.AppLocalDatabaseHelper;
import com.walton.internetdataplan.services.DailyConsumedService;
import com.walton.internetdataplan.services.MobileWiFiDataUpdateService1;
import com.walton.internetdataplan.utitls.MyServiceRunning;

/**
 * Created by Faruq on 1/10/2017.
 */

public class NetConnectionChangeReceiver extends BroadcastReceiver {
    AppLocalDatabaseHelper appLocalDatabaseHelper;




    @Override
    public void onReceive(Context context, Intent intent) {

        appLocalDatabaseHelper = new AppLocalDatabaseHelper(context);


        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //  NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        NetworkInfo networkInfo = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);

        if (networkInfo != null && networkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED) {

            Log.d("_m_data", "net connected");


           // context.startService(new Intent(context, MobileWiFiDataUpdateService1.class));
            if (!MyServiceRunning.getInstance(context).isMyServiceRunning(MobileWiFiDataUpdateService1.class)) {
                // start service
                context.startService(new Intent(context, MobileWiFiDataUpdateService1.class));
                // end of start service
            }

            if (!MyServiceRunning.getInstance(context).isMyServiceRunning(DailyConsumedService.class)) {
                // start service
                context.startService(new Intent(context, DailyConsumedService.class));
                // end of start service
            }
        } else if (networkInfo != null && networkInfo.getDetailedState() == NetworkInfo.DetailedState.DISCONNECTED) {
            Log.d("_m_data", "net disconnected");
           // context.stopService(new Intent(context, MobileWiFiDataUpdateService1.class));

        }


    }
}
