package com.walton.internetdataplan.utitls;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * Created by Faruq on 12/22/2016.
 */

public class AppsSettings {
    private static AppsSettings appsSettings = null;
    private Context mContext;
    private SharedPreferences sharedPreferences;

    private AppsSettings(Context context) {
        mContext = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public static AppsSettings getAppsSettings(Context mContext) {
        if (appsSettings == null) {
            appsSettings = new AppsSettings(mContext);
        }
        return appsSettings;
    }

    public void setDailyInternetConsumeLimitOverCome(boolean mValue) {
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(AppContantsExtra.DAILY_INERTER_CONSUME_LIMIT_OVERCOME, mValue);
        editor.commit();
    }

    public boolean getDailyInternetConsumeLimitOverCome() {
        return sharedPreferences.getBoolean(AppContantsExtra.DAILY_INERTER_CONSUME_LIMIT_OVERCOME, false);
    }


    public void setInternetConsumeLimiParcentigetOverCome(boolean mValue) {
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(AppContantsExtra.INERTER_CONSUME_PARCENTIGE_LIMIT_OVERCOME, mValue);
        editor.commit();
    }

    public boolean getInternetConsumeLimiParcentigetOverCome() {
        return sharedPreferences.getBoolean(AppContantsExtra.INERTER_CONSUME_PARCENTIGE_LIMIT_OVERCOME, false);
    }


    public void setInternetConsumeParcentageLimit(float mValue) {
        Editor editor = sharedPreferences.edit();
        editor.putFloat(AppContantsExtra.INERTER_CONSUME_PARCENTIGE_LIMIT, mValue);
        editor.commit();
    }

    public float getInternetConsumeParcentageLimit() {
        return sharedPreferences.getFloat(AppContantsExtra.INERTER_CONSUME_PARCENTIGE_LIMIT, 0);
    }


    public void setDailyInternetConsumeLimit(float mValue) {
        Editor editor = sharedPreferences.edit();
        editor.putFloat(AppContantsExtra.DAILY_INERTER_CONSUME_LIMIT, mValue);
        editor.commit();
    }

    public float getDailyInternetConsumeLimit() {
        return sharedPreferences.getFloat(AppContantsExtra.DAILY_INERTER_CONSUME_LIMIT, 0);
    }

    public void setDataPlanType(String mValue) {
        Editor editor = sharedPreferences.edit();
        editor.putString(AppContantsExtra.DATA_PLAN_TYPE, mValue);
        editor.commit();
    }

    public String getDataPlanType() {
        return sharedPreferences.getString(AppContantsExtra.DATA_PLAN_TYPE, "");
    }

    public void setDailyCheckBox(boolean mValue) {
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(AppConstants.DAILY_CHECK_BOX, mValue);
        editor.commit();
    }

    public boolean getDailyCheckBox() {
        return sharedPreferences.getBoolean(AppConstants.DAILY_CHECK_BOX, false);
    }

    public void setUsageCheckBox(boolean mValue) {
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(AppConstants.USAGE_CHECK_BOX, mValue);
        editor.commit();
    }

    public boolean getUsageCheckBox() {
        return sharedPreferences.getBoolean(AppConstants.USAGE_CHECK_BOX, false);
    }



    public void setDateChangeStatus(boolean mValue) {
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(AppConstants.DATE_CHANGE_STATUS, mValue);
        editor.commit();
    }

    public boolean getDateChangeStatus() {
        return sharedPreferences.getBoolean(AppConstants.DATE_CHANGE_STATUS, false);
    }

    public void setLanguage(String mValue) {
        Editor editor = sharedPreferences.edit();
        editor.putString(AppConstants.LANGUAGE, mValue);
        editor.commit();
    }

    public String getLanguage() {
        return sharedPreferences.getString(AppConstants.LANGUAGE, "");
    }


    public void setAirtelPrepaidLoad(boolean mValue) {
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(AppConstants.AIRTEL_PREPAID_LOAD, mValue);
        editor.commit();
    }

    public boolean getAirtelPrepaidLoad() {
        return sharedPreferences.getBoolean(AppConstants.AIRTEL_PREPAID_LOAD, false);
    }

    public void setAirtelPostpaidLoad(boolean mValue) {
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(AppConstants.AIRTEL_POSTPAID_LOAD, mValue);
        editor.commit();
    }

    public boolean getAirtelPostpaidLoad() {
        return sharedPreferences.getBoolean(AppConstants.AIRTEL_POSTPAID_LOAD, false);
    }
}
