package com.walton.internetdataplan.services;

/**
 * Created by Faruq on 12/21/2016.
 */


import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.TrafficStats;
import android.os.Binder;
import android.os.Build.VERSION_CODES;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.walton.internetdataplan.AppManager;
import com.walton.internetdataplan.R;
import com.walton.internetdataplan.activities.MainActivity;
import com.walton.internetdataplan.models.InternetData;
import com.walton.internetdataplan.utitls.AppsSettings;
import com.walton.internetdataplan.utitls.CheckInternet;
import com.walton.internetdataplan.utitls.DataPlan;
import com.walton.internetdataplan.utitls.InternetDetect;
import com.walton.internetdataplan.utitls.WHelper;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DailyConsumedService extends Service {
    public static float mJRtRx = 0;
    public static float mJRtTx = 0;
    public static float mJRtRTx = 0;
    public static float mJRmRx = 0;
    public static float mJRmTx = 0;
    public static float mJRmRTx = 0;
    public static float mJRwRx = 0;
    public static float mJRwTx = 0;
    public static float mJRwRTx = 0;

    public Context mContext;
    /**
     * indicates how to behave if the service is killed
     */
    int mStartMode;

    /**
     * interface for clients that bind
     */

    /**
     * indicates whether onRebind should be used
     */

    /**
     * Called when the service is being created.
     */
    @Override
    public void onCreate() {
        mContext = this;

    }

    /**
     * The service is starting, due to a call to startService()
     */
    @Override
    public int onStartCommand(Intent intent1, int flags, int startId) {
        final ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate
                (new Runnable() {
                    @RequiresApi(api = VERSION_CODES.LOLLIPOP)
                    public void run() {
                        Log.v("_nahid", "Omor :O:scheduleAtFixedRate");
                        if (CheckInternet.checkConn(mContext)) {
                            Log.e("14_01", ":internet yes");
                            if (InternetDetect.netDetect(mContext).equals("mobile")) {
                                Log.e("14_01", ":Mobile:");
                                // check set or not data plan check dataSizeType GB or MB
                                ArrayList<DataPlan> mDataPlan = new ArrayList<DataPlan>();
                                if (mDataPlan != null && mDataPlan.size() > 0) {
                                    mDataPlan.clear();
                                }
                                mDataPlan = AppManager.getInstance(mContext).retrieveDataPlan(mContext);
                                Log.e("14_01", ":mDataPlan:size:" + mDataPlan.size());
                                // check data exist or not in database //
                                if (mDataPlan != null && mDataPlan.size() > 0) {

                                    String mDataPlanType = mDataPlan.get(0).getDataSizeType();
                                    String mDataPlanEndDate = mDataPlan.get(0).getEndDate();
                                    String mDataPlanSize = mDataPlan.get(0).getDataSize();
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
                                        Date modifyFormat = format1.parse(mDataPlanEndDate);
                                        mMofifyEndDate = format2.format(modifyFormat);
                                        // end of re format end of date
                                        Log.e("14_01", ":DataPlan:mDSType:" + mDataPlanType + ", mDSize:" + mDataPlanSize + ", mDEndDate:" + mDataPlanEndDate + ", mMofifyEndDate:" + mMofifyEndDate);

                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                        Log.e("14_01", ":DataPlan:e" + e.toString());
                                    }
                                    // end of database end date
                                    // compare 2 dates
                                    boolean isYes = WHelper.getInstance(mContext).compare2Dates(mCurrentDate, mMofifyEndDate);
                                    if (isYes) {
                                        insertOrUpdateData();
                                        // check parcentage alert
                                        if (!AppsSettings.getAppsSettings(mContext).getInternetConsumeLimiParcentigetOverCome()) {
                                            // @@@@@@@@@@@@@@@@@ check set or not parcentage date limit @@@@@@@@@@@@@@@@@@@@@@@@@@//
                                            if (AppsSettings.getAppsSettings(mContext).getInternetConsumeParcentageLimit() != 0) {

                                                if (isTargetPercentis(mDataPlanType, mDataPlanSize)) {
                                                    Log.e("14_01", ":parcentage data:true:");
                                                } else {
                                                    Log.e("14_01", ":parcentage data:false:");
                                                }
                                            }
                                        }
                                        // @@@@@@@@@@@@@@@@@ end of check set or not parcentage date limit @@@@@@@@@@@@@@@@@@@@@@@@@@//


                                        // check daily limit data //
                                        if (!AppsSettings.getAppsSettings(mContext).getDailyInternetConsumeLimitOverCome()) {
                                            if (AppsSettings.getAppsSettings(mContext).getDailyInternetConsumeLimit() != 0) {
                                                if (isTarget(mDataPlanType)) {
                                                    Log.e("14_01", ":limit data:trueeeeeeeeeee");

                                                } else {
                                                    Log.e("14_01", ":limit data:falsessssssssssssssss");
                                                }
                                            }

                                        }
                                        // end of check daily limit data //


                                        // end of check parcentage alert //
                                    } else {
                                        Log.e("14_01", "::Already expair date");
                                    }
                                    // end of compare 2 dates
                                    // end of check Enddate is ok or not //

                                    // end of check set or not data plan check dataSizeType GB or MB
                                }
                            } else {
                                // just ignore
                                insertDataForOffline();
                                Log.e("14_01", ":Wi-Fi:");
                                Log.e("14_01", ":Wi-Fi:");
                            }

                        } else {
                            Log.e("14_01", ":internet no");
                            insertDataForOffline();
                        }


                    }
                }, 0, 30, TimeUnit.SECONDS);


        return START_STICKY;
    }

    private void insertOrUpdateData() {

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
                Log.e("_nahid", ":insertOrUpdateData:new insert:mUsage:"+mUsage+":mCurrent:"+mCurrent+":mPrevious:"+mPrevious+":mDate:"+mDate+":mMobileRTx:"+mMobileRTx);
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
                    Log.e("_nahid", ":insertOrUpdateData:update:0.0:mUsageAA:" + mUsageAA + ":mCurrentAA:" + mCurrentAA + ":mPreviousAA:" + mPreviousAA + ":mDate:" + mDate + ":mPreUsage:" + mPreUsage + ":mPreCurrent:" + mPreCurrent + ":mPrePrevious:" + mPrePrevious);





            } else {
                    // end of check date change status //
                    if (mPreUsage.equals("0.0") && mPreCurrent.equals("0.0") && mPrePrevious.equals("0.0")) {
                        // get current traffic states
                        String mUsageT = "0.0";
                        String mCurrentT = String.valueOf(mMobileRTx);
                        String mPreviousT = String.valueOf(mMobileRTx);
                        if (Float.parseFloat(mUsage) >= 0) {
                            AppManager.getInstance(mContext).updateInternetData(mContext, mUsageT, mCurrentT, mPreviousT, mDate, mPreUsage, mPreCurrent, mPrePrevious);
                            Log.e("_nahid", ":insertOrUpdateData:update:0.0:mUsageT:" + mUsageT + ":mCurrentT:" + mCurrentT + ":mPreviousT:" + mPreviousT + ":mDate:" + mDate + ":mPreUsage:" + mPreUsage + ":mPreCurrent:" + mPreCurrent + ":mPrePrevious:" + mPrePrevious);
                        }
                    } else {
                        {
                            if (xy > 0) {
                                AppManager.getInstance(mContext).updateInternetData(mContext, mUsage, mCurrent, mPrevious, mDate, mPreUsage, mPreCurrent, mPrePrevious);
                                Log.e("_nahid", ":insertOrUpdateData:update:mUsage:" + mUsage + ":mCurrent:" + mCurrent + ":mPrevious:" + mPrevious + ":mDate:" + mDate + ":mPreUsage:" + mPreUsage + ":mPreCurrent:" + mPreCurrent + ":mPrePrevious:" + mPrePrevious);
                            }
                        }
                    }
                }
                }catch(Exception e){
                    Log.e("_nahid", ":DailyConsumedService:Exception:uUsage:" + e.toString());
                }


        }
    }
    private void insertDataForOffline() {

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
        String customDateString2=year+"-"+month+"-"+day;
        // changing end


        mDate = customDateString2;
     //   mDate = formater.format(date);

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
                Log.e("16_01", ":insertDataForOffline:new insert:mUsage:"+mUsage+":mCurrent:"+mCurrent+":mPrevious:"+mPrevious+":mDate:"+mDate+":mMobileRTx:"+mMobileRTx);
            }


        }
    }


    @RequiresApi(api = VERSION_CODES.LOLLIPOP)
    public boolean isTargetPercentis(String mDataPlanType, String mDataPlanSize) {
        boolean isCorrect = false;
        float mGetValue = AppsSettings.getAppsSettings(mContext).getInternetConsumeParcentageLimit();
        if (mGetValue != 0) {
            // ***************** retrieve data from database ******************************** //
            // getting current date with format : yyyy-MM-dd
            Date date = Calendar.getInstance().getTime();
            DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
            String mDate = formatter1.format(date);
            // check internet data
            ArrayList<InternetData> mIntDataList = new ArrayList<InternetData>();
            if (mIntDataList != null && mIntDataList.size() > 0) {
                mIntDataList.clear();
            }

            mIntDataList = AppManager.getInstance(mContext).retrieveInternetDataPlanAll(mContext);
            float mDataBaseTotalRTx = 0;
            if (mIntDataList.size() == 0) {
                mDataBaseTotalRTx = 0;
            } else {
                for (int i = 0; i < mIntDataList.size(); i++) {
                    InternetData idm = mIntDataList.get(i);
                    mDataBaseTotalRTx = mDataBaseTotalRTx + Float.parseFloat(idm.getUsegeData());
                    Log.e("14_01", ":inner loop:mDataBaseTotalRTx:" + mDataBaseTotalRTx);
                }

            }
            // ***************** End of retrieve data from database ******************************** //

            // check dataSizeType MB or GB
            float mTotalRTx = 0;
            float mDSize = Float.parseFloat(mDataPlanSize);
            if (mDataPlanType.equals("MB")) {
                mTotalRTx = mDataBaseTotalRTx / (1024 * 1024);
                Log.e("14_01", ":mTotalRTx:" + mTotalRTx + ", mDSize:" + mDSize);
            } else if (mDataPlanType.equals("GB")) {
                mTotalRTx = mDataBaseTotalRTx / (1024 * 1024 * 1024);
                Log.e("14_01", ":mTotalRTx:" + mTotalRTx + ", mDSize:" + mDSize);
            } else {

            }
            // end of check dataSizeType MB or GB
            // formula of parcentage
            try {
                float mParchentage = (mTotalRTx * 100) / mDSize;
                Log.e("14_01", ":mDataBaseTotalRTx:" + mDataBaseTotalRTx + ":mParchentage:" + mParchentage + ":mTotalRTx:" + mTotalRTx + ":mDSize:" + mDSize);
                Log.e("14_01", ":mParchentage:" + mParchentage + ", AppsSettings.getAppsSettings(mContext).getInternetConsumeParcentageLimit():" + AppsSettings.getAppsSettings(mContext).getInternetConsumeParcentageLimit());
                if (AppsSettings.getAppsSettings(mContext).getInternetConsumeParcentageLimit() <= mParchentage) {
                    AppsSettings.getAppsSettings(mContext).setInternetConsumeLimiParcentigetOverCome(true);
                    //
                    DecimalFormat df = new DecimalFormat();
                    df.setMaximumFractionDigits(2);
                    //
                    if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
                    {
                        if (100 <= mParchentage) {
                            createNofiAlertForParcentage("Your internet usage 100 %");
                        } else {
                            createNofiAlertForParcentage("Your internet usage " + df.format(mParchentage) + " %");

                        }
                    }
                    else
                    {
                        if (100 <= mParchentage) {
                            createNofiAlertForParcentage("আপনার ইন্টারনেট ব্যবহার  ১০০ %");
                        } else {
                            createNofiAlertForParcentage("আপনার ইন্টারনেট ব্যবহার " + df.format(mParchentage) + " %");

                        }
                    }



                    isCorrect = true;
                } else {
                    isCorrect = false;
                }
            } catch (Exception e) {
                isCorrect = false;
            }
        } else {
            isCorrect = false;
        }
        return isCorrect;
    }

    public boolean isTarget(String mDSType) {
        boolean isCorrect = false;
        float mGetValue = AppsSettings.getAppsSettings(mContext).getDailyInternetConsumeLimit();
        // getting current date with format : yyyy-MM-dd
        Date date = Calendar.getInstance().getTime();
        DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");


        // changing here
        Calendar calendar = Calendar.getInstance();
        String year=calendar.get(Calendar.YEAR)+"";
        String month=(calendar.get(Calendar.MONTH)+1)+"";
        String day=calendar.get(Calendar.DAY_OF_MONTH)+"";
        String customDateString3=year+"-"+month+"-"+day;
        // changing end



        //String mDate = formatter1.format(date);
        String mDate = customDateString3;
        // check internet data
        ArrayList<InternetData> mIntDataList = new ArrayList<InternetData>();
        if (mIntDataList != null && mIntDataList.size() > 0) {
            mIntDataList.clear();
        }

        mIntDataList = AppManager.getInstance(mContext).retrieveInternetDataPlan(mContext, mDate);
        float mDataBaseTotalRTx = 0;
        if (mIntDataList.size() == 0) {
            mDataBaseTotalRTx = 0;
            Log.e("14_01", "if:mGetValue:" + mGetValue + ", mDataBaseTotalRTx:" + mDataBaseTotalRTx);
        } else {
            InternetData idm = mIntDataList.get(0);
            mDataBaseTotalRTx = Float.parseFloat(idm.getUsegeData());
            Log.e("14_01", "else:mGetValue:" + mGetValue + ", mDataBaseTotalRTx:" + mDataBaseTotalRTx);
        }
        //
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        //
        if (mDSType.equals("MB")) {

            float mMBDataBaseTotalRTx = 0;
            mMBDataBaseTotalRTx = mDataBaseTotalRTx / (1024 * 1024);
            if (mGetValue <= mMBDataBaseTotalRTx) {
                AppsSettings.getAppsSettings(mContext).setDailyInternetConsumeLimitOverCome(true);
                //
                if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
                {
                    createNofiAlertForDailyLimit("Your daily usage " + df.format(mMBDataBaseTotalRTx) + " MB");
                }
                else
                {
                    createNofiAlertForDailyLimit("আপনার দৈনিক ব্যবহার " + df.format(mMBDataBaseTotalRTx) + " মেগাবাইট");
                }

                isCorrect = true;
            } else {
                isCorrect = false;
            }
        } else if (mDSType.equals("GB")) {
            float mGBDataBaseTotalRTx = 0;
            mGBDataBaseTotalRTx = mDataBaseTotalRTx / (1024 * 1024 * 1024);
            if (mGetValue <= mGBDataBaseTotalRTx) {

                if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
                {
                    createNofiAlertForDailyLimit("Your daily usage " + df.format(mGBDataBaseTotalRTx) + " GB");
                }
                else
                {
                    createNofiAlertForDailyLimit("আপনার দৈনিক ব্যবহার " + df.format(mGBDataBaseTotalRTx) + " গিগাবাইট");
                }




                isCorrect = true;
            } else {
                isCorrect = false;
            }
        } else {
            //
        }

        return isCorrect;


    }

    @TargetApi(VERSION_CODES.LOLLIPOP)
    private void createNofiAlertForDailyLimit(String mValue) {
        /*********** Create notification ***********/
        Notification.Builder builder = new Notification.Builder(mContext);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // This pending intent will open after notification click
        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0,
                new Intent(getBaseContext(), MainActivity.class),
                0);
        if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
        {
            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.auncher);
            builder.setSmallIcon(R.drawable.ownload)
                    .setLargeIcon(largeIcon)
                    .setContentTitle("Daily Data Limit Over!")
                    .setContentText(mValue)
                    .setContentIntent(pendingIntent)
                    .setColor(getResources().getColor(R.color.gp_button_color_default)); // ColorPrime is red.
//                .setColor(Color.TRANSPARENT); // ColorPrime is red.
        }
        else
        {
            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.auncher);
            builder.setSmallIcon(R.drawable.ownload)
                    .setLargeIcon(largeIcon)
                    .setContentTitle("দৈনিক ডাটা  সীমা ওভার!")
                    .setContentText(mValue)
                    .setContentIntent(pendingIntent)
                    .setColor(getResources().getColor(R.color.gp_button_color_default)); // ColorPrime is red.
//                .setColor(Color.TRANSPARENT); // ColorPrime is red.
        }


        Notification notification = builder.getNotification();
        notificationManager.notify(R.mipmap.auncher, notification);


    }

    @RequiresApi(api = VERSION_CODES.LOLLIPOP)
    private void createNofiAlertForParcentage(String mValue) {
        /*********** Create notification ***********/
        Notification.Builder builder = new Notification.Builder(mContext);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // This pending intent will open after notification click
        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0,
                new Intent(getBaseContext(), MainActivity.class),
                0);
        if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
        {
            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//            builder.setSmallIcon(R.drawable.p_arrived_96)
            builder.setSmallIcon(R.drawable.ownload)
                    .setLargeIcon(largeIcon)
                    .setContentTitle("Data Usage Limit!")
                    .setContentText(mValue)
                    .setContentIntent(pendingIntent);
//                    .setColor(getResources().getColor(R.color.gp_button_color_default)); // ColorPrime is red.
        }
        else
        {
            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//            builder.setSmallIcon(R.drawable.p_arrived_96)
            builder.setSmallIcon(R.drawable.ownload)
                    .setLargeIcon(largeIcon)
                    .setContentTitle("ডেটা ব্যবহার সীমা!")
                    .setContentText(mValue)
                    .setContentIntent(pendingIntent);
        }


        Notification notification = builder.getNotification();
        notificationManager.notify(R.mipmap.ic_launcher, notification);
//        notificationManager.notify(R.drawable.p_arrived_96, notification);

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }



    public class DailyConsumedServiceBinder extends Binder {

        public DailyConsumedService getService() {
            return DailyConsumedService.this;
        }
    }
    private DailyConsumedServiceBinder mBinder = new DailyConsumedServiceBinder();
}
