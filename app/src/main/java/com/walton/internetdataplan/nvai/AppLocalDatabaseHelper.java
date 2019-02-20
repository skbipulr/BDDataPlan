package com.walton.internetdataplan.nvai;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by DELL on 11/15/2016.
 */

public class AppLocalDatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "mobile_security_bd";


    public AppLocalDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        try {


            db.execSQL("create table if not exists sent_status " +
                    "( sent_date text, date_time text )");


            db.execSQL("create table if not exists network_data " +
                    "( net_usage_id INTEGER primary key AUTOINCREMENT , mobile_data text, wifi_data text, usage_date text)");


            db.execSQL("create table if not exists data_plan " +
                    "( data_plan_id INTEGER primary key AUTOINCREMENT , start_date text, end_date text, duration text, data_size text, duration_type text ,data_size_type text)");


            db.execSQL("create table if not exists plan_data_monitor " +
                    "( plan_data_monitor_id INTEGER primary key AUTOINCREMENT , mobile_tx_data text, mobile_rx_data text, plan_data_monitor_date text , total_rx_data text, total_tx_data text)");

            db.execSQL("create table if not exists boot_status " +
                    "( boot_status_id INTEGER primary key , booted text)");


            db.execSQL("create table if not exists block_numbers " +
                    "( block_number_id INTEGER primary key AUTOINCREMENT, contact_name text , contact_number text )");

            db.execSQL("create table if not exists block_log " +
                    "( block_log_id INTEGER primary key AUTOINCREMENT, contact_name text , contact_number text , date_time text , block_count text)");

            db.execSQL("create table if not exists previous_data " +
                    "( previous_data_id INTEGER primary key , previous_mobile_tx_data text, previous_total_rx_data text , previous_total_tx_data text, previous_mobile_rx_data text)");

            db.execSQL("create table if not exists previous_network_data " +
                    "( previous_data_id INTEGER primary key , previous_mobile_tx_data text, previous_total_rx_data text , previous_total_tx_data text, previous_mobile_rx_data text)");

            db.execSQL("create table if not exists temperature " +
                    "( cpu_temperature text, battery_temperature text )");


            // table for wifi mobile barchart wifi data
            db.execSQL("create table if not exists device_data_usage " +
                    "( usage_id INTEGER primary key AUTOINCREMENT , used_wifi text, previous_total text, mobile_data text, date text)");
            // end of table for wifi mobile barchart data


            // table for wifi mobile barchart mobile data
            db.execSQL("create table if not exists device_mobile_data_usage " +
                    "( mobile_usage_id INTEGER primary key AUTOINCREMENT , used_mobile text, previous_mobile text, date text)");
            // end of table for wifi mobile barchart data


        } catch (SQLiteException e) {
            e.printStackTrace();
            Log.e("Table creation error", "onCreate error");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS sent_status");
        db.execSQL("DROP TABLE IF EXISTS network_data");
        db.execSQL("DROP TABLE IF EXISTS data_plan");
        db.execSQL("DROP TABLE IF EXISTS plan_data_monitor");
        db.execSQL("DROP TABLE IF EXISTS boot_status");
        db.execSQL("DROP TABLE IF EXISTS previous_data");
        db.execSQL("DROP TABLE IF EXISTS block_numbers");
        db.execSQL("DROP TABLE IF EXISTS block_log");
        db.execSQL("DROP TABLE IF EXISTS temperature");
        db.execSQL("DROP TABLE IF EXISTS device_data_usage");
        db.execSQL("DROP TABLE IF EXISTS device_mobile_data_usage");


        onCreate(db);
    }



    // insert data to device_data_plan_monitor table ( mobile bar chart)
    public boolean insertDeviceMobileDataUsage(String usedMobile, String previousMobile, String date)

    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("used_mobile", usedMobile);
        contentValues.put("previous_mobile", previousMobile);
        contentValues.put("date", date);

        db.insert("device_mobile_data_usage", null, contentValues);
        return true;
    }

    // update device data usage(mobile)
    public boolean updateDeviceMobileDataUsage(String usedMobile, String previousMobile,  String date) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("used_mobile", usedMobile);
        contentValues.put("previous_mobile", previousMobile);
        db.update("device_mobile_data_usage", contentValues, "date = ? ", new String[]{date});

        return true;
    }


    // get device mobile data usage list

    public ArrayList<HashMap<String, String>> getDeviceMobileDataUsage() {


        ArrayList<HashMap<String, String>> deviceDataUsageList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from device_mobile_data_usage ORDER BY mobile_usage_id DESC", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {

            HashMap<String, String> map = new HashMap<String, String>();

            map.put("used_mobile", res.getString(res.getColumnIndex("used_mobile")));
            map.put("previous_mobile", res.getString(res.getColumnIndex("previous_mobile")));
            map.put("date", res.getString(res.getColumnIndex("date")));

            deviceDataUsageList.add(map);

            res.moveToNext();
        }
        Log.d("_abcdefgh", "get device data usage Array list size:" + res.toString());

        return deviceDataUsageList;
    }


    // insert data to device_data_plan_monitor table ( wifi bar chart)
    public boolean insertDeviceDataUsage(String usedWifi, String previousTotal, String mobileData, String date)

    {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("used_wifi", usedWifi);
        contentValues.put("previous_total", previousTotal);
        contentValues.put("mobile_data", mobileData);
        contentValues.put("date", date);

        db.insert("device_data_usage", null, contentValues);

        return true;
    }

    // update device data usage(wifi)
    public boolean updateDeviceDataUsage(String usedWifi, String previousTotal, String mobileData, String date) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("used_wifi", usedWifi);
        contentValues.put("previous_total", previousTotal);
        contentValues.put("mobile_data", mobileData);

        db.update("device_data_usage", contentValues, "date = ? ", new String[]{date});

        return true;
    }

    // update device data usage table mobile data to make mobile data up to date
    public void updateDeviceDataUsageWifi(long mobileData, String dateString) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mobile_data", mobileData);

        db.update("device_data_usage", contentValues, "date = ? ", new String[]{dateString});
    }
    // get device data usage list

    public ArrayList<HashMap<String, String>> getDeviceDataUsage() {


        ArrayList<HashMap<String, String>> deviceDataUsageList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from device_data_usage ORDER BY usage_id DESC", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {

            HashMap<String, String> map = new HashMap<String, String>();

            map.put("used_wifi", res.getString(res.getColumnIndex("used_wifi")));
            map.put("previous_total", res.getString(res.getColumnIndex("previous_total")));
            map.put("mobile_data", res.getString(res.getColumnIndex("mobile_data")));
            map.put("date", res.getString(res.getColumnIndex("date")));

            deviceDataUsageList.add(map);

            res.moveToNext();
        }
        Log.d("_abcdefgh", "get device data usage Array list size:" + res.toString());

        return deviceDataUsageList;
    }



    // insert boot status data

    public boolean insertBootStatusData(String isBooted)

    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("boot_status_id", 1);
        contentValues.put("booted", isBooted);
        db.execSQL("delete from " + "boot_status");
        db.insert("boot_status", null, contentValues);

        return true;
    }

   // insert previous network data

    public boolean insertPreviousNetworkData(String previousMobileDataTx, String previousMobileDataRx, String previousTotalTx, String previousTotalRx)

    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("previous_mobile_tx_data", previousMobileDataTx);
        contentValues.put("previous_total_rx_data", previousTotalRx);
        contentValues.put("previous_total_tx_data", previousTotalTx);
        contentValues.put("previous_mobile_rx_data", previousMobileDataRx);
        int id = 1;
        contentValues.put("previous_data_id", id);

        db.execSQL("delete from " + "previous_network_data");

        db.insert("previous_network_data", null, contentValues);

        return true;
    }
    // get previous data

    public HashMap<String, String> getPreviousNetworkData() {


        HashMap<String, String> map = new HashMap<String, String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from previous_network_data", null);
        Log.v("previous_network_data:", res.toString());
        res.moveToFirst();

        while (res.isAfterLast() == false) {

            map.put("previous_mobile_tx_data", res.getString(res.getColumnIndex("previous_mobile_tx_data")));
            map.put("previous_total_rx_data", res.getString(res.getColumnIndex("previous_total_rx_data")));
            map.put("previous_total_tx_data", res.getString(res.getColumnIndex("previous_total_tx_data")));
            map.put("previous_mobile_rx_data", res.getString(res.getColumnIndex("previous_mobile_rx_data")));


            res.moveToNext();
        }

        return map;
    }

    // update data plan monitor data
    public boolean updateDailyNetUsageData(String mobileData, String wifiData, String date) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mobile_data", mobileData);
        contentValues.put("wifi_data", wifiData);

        db.update("network_data", contentValues, "usage_date = ? ", new String[]{date});

        return true;
    }


    public ArrayList<HashMap<String, String>> getNetworkData() {

        ArrayList<HashMap<String, String>> netDataList = new ArrayList<HashMap<String, String>>();


        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor res = db.rawQuery("select * from network_data ORDER BY net_usage_id DESC LIMIT 7", null);


            res.moveToFirst();
            while (res.isAfterLast() == false) {

                HashMap<String, String> netUsageMap = new HashMap<String, String>();
                netUsageMap.put("net_usage_id", res.getString(res.getColumnIndex("net_usage_id")));
                netUsageMap.put("mobile_data", res.getString(res.getColumnIndex("mobile_data")));
                netUsageMap.put("wifi_data", res.getString(res.getColumnIndex("wifi_data")));
                netUsageMap.put("date", res.getString(res.getColumnIndex("usage_date")));
                netDataList.add(netUsageMap);

                res.moveToNext();
            }

        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        return netDataList;
    }


}