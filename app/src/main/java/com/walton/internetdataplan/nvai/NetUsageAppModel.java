package com.walton.internetdataplan.nvai;

import android.graphics.drawable.Drawable;

/**
 * Created by Faruq on 12/19/2016.
 */


public class NetUsageAppModel {

    String appPackage;
    String appName;
    Drawable appIcon;
    String txData;
    String rxData;
    String rtxData;



    public NetUsageAppModel(String appPackage, String appName, Drawable appIcon, String txData, String rxData, String rtxData) {
        this.appPackage = appPackage;
        this.appName = appName;
        this.appIcon = appIcon;
        this.txData = txData;
        this.rxData = rxData;
        this.rtxData = rtxData;
    }
    public String getRtxData() {
        return rtxData;
    }

    public void setRtxData(String rtxData) {
        this.rtxData = rtxData;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getTxData() {
        return txData;
    }

    public void setTxData(String txData) {
        this.txData = txData;
    }

    public String getRxData() {
        return rxData;
    }

    public void setRxData(String rxData) {
        this.rxData = rxData;
    }
}
