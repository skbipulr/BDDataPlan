package com.walton.internetdataplan.utitls;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckInternet 
{
	public static boolean checkConn(Context ctx)
    {
        ConnectivityManager conMgr =  (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conMgr.getActiveNetworkInfo();
        if (info == null)
            return false;
          if (!info.isConnected())
            return false;
          if (!info.isAvailable())
            return false;
          return true;


    }


}
