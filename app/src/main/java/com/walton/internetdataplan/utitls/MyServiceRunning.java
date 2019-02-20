package com.walton.internetdataplan.utitls;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;

public class MyServiceRunning {

	private Context mContext=null;
	private static MyServiceRunning myServiceRunningSingleton = null;

	/*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private MyServiceRunning(Context _mContext) {
		this.mContext = _mContext;
	}

	/* Static 'instance' method */
	public static MyServiceRunning getInstance(Context _mContext) {
		return (myServiceRunningSingleton == null ? myServiceRunningSingleton = new MyServiceRunning(
				_mContext) : myServiceRunningSingleton);
	}

	public boolean isMyServiceRunning(Class<?> serviceClass) {
		ActivityManager manager = (ActivityManager) mContext
				.getSystemService(Context.ACTIVITY_SERVICE);
		for (RunningServiceInfo service : manager
				.getRunningServices(Integer.MAX_VALUE)) {
			if (serviceClass.getName().equals(service.service.getClassName())) {
				return true;
			}
		}
		return false;
	}

}
