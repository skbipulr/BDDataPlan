package com.walton.internetdataplan.db;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.walton.internetdataplan.utitls.AppConstants;
import com.walton.internetdataplan.utitls.AppContantsExtra;


public class DBTable {
    // Database creation SQL statement
    // Modify this class based on the need of the app's database

    String operator, opPacakge, processType, dialCode, smsBody, smsCode, details;

    // CREATE TABLE TABLE_INTERNET_DATA
    // id, usage, current, previous, date
    private static final String CREATE_TABLE_INTERNET_DATA = "create table "
            + AppConstants.TABLE_INTERNET_DATA + "(" + AppConstants.TABLE_INTERNET_DATA_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + AppConstants.TABLE_INTERNET_DATA_USAGE + " text null,"
            + AppConstants.TABLE_INTERNET_DATA_CURRENT + " text null,"
            + AppConstants.TABLE_INTERNET_DATA_PREVIOUS + " text null,"
            + AppConstants.TABLE_INTERNET_DATA_DATE + " text null)";




    // CREATE TABLE Migration
    private static final String CREATE_TABLE_MIGRATION = "create table "
            + AppConstants.TABLE_MIGRATION + "(" + AppConstants.TABLE_MIGRATION_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + AppConstants.TABLE_MIGRATION_OPERATOR + " text null,"
            + AppConstants.TABLE_MIGRATION_PACKAGE + " text null,"
            + AppConstants.TABLE_MIGRATION_PROCESS_TYPE + " text null,"
            + AppConstants.TABLE_MIGRATION_DIAL_CODE + " text null,"
            + AppConstants.TABLE_MIGRATION_SMS_BODY + " text null,"
            + AppConstants.TABLE_MIGRATION_SMS_CODE + " text null,"
            + AppConstants.TABLE_MIGRATION_DETAILS_GP_TO_GP + " text null,"
            + AppConstants.TABLE_MIGRATION_DETAILS_GP_TO_OTHERS + " text null,"
            + AppConstants.TABLE_MIGRATION_DETAILS_FNF + " text null,"
            + AppConstants.TABLE_MIGRATION_DETAILS_PULSE + " text null,"
            + AppConstants.TABLE_MIGRATION_DETAILS_SMS + " text null,"
            + AppConstants.TABLE_MIGRATION_OPERATOR_TYPE + " text null)";

    // CREATE TABLE GP
    private static final String CREATE_TABLE_GP = "create table "
            + AppConstants.TABLE_GP + "(" + AppConstants.TABLE_GP_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + AppConstants.TABLE_GP_PACKAGE_NAME + " text null,"
            + AppConstants.TABLE_GP_PRICE + " text null,"
            + AppConstants.TABLE_GP_VALIDITY + " text null,"
            + AppConstants.TABLE_GP_DIRECT_DIALING_CODE + " text null,"
            + AppConstants.TABLE_GP_AUTO_RENEW_SMS_BODY + " text null,"
            + AppConstants.TABLE_GP_AUTO_RENEW_SMS_CODE + " text null,"

            + AppConstants.TABLE_GP_ACTIVATION_PROCESS_12 + " text null,"


            + AppConstants.TABLE_GP_DETAILSE_ACTIVATION_PROCESS + " text null,"
            + AppConstants.TABLE_GP_DESCRIPTION + " text null,"
            + AppConstants.TABLE_GP_OPERATOR_TYPE + " text null)";

    // CREATE TABLE Airtel
    private static final String CREATE_TABLE_Airtel = "create table "
            + AppConstants.TABLE_Airtel + "(" + AppConstants.TABLE_Airtel_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + AppConstants.TABLE_Airtel_PACKAGE_NAME + " text null,"
            + AppConstants.TABLE_Airtel_VALIDITY + " text null,"
            + AppConstants.TABLE_Airtel_DIRECT_DIALING_CODE + " text null,"
            + AppConstants.TABLE_Airtel_PRICE + " text null,"
            + AppConstants.TABLE_Airtel_AUTO_RENEW_SMS_BODY + " text null,"
            + AppConstants.TABLE_Airtel_AUTO_RENEW_SMS_CODE + " text null,"

            + AppConstants.TABLE_Airtel_ACTIVATION_PROCESS_12 + " text null,"


            + AppConstants.TABLE_Airtel_DETAILSE_ACTIVATION_PROCESS + " text null,"
            + AppConstants.TABLE_Airtel_DESCRIPTION + " text null,"
            + AppConstants.TABLE_Airtel_OPERATOR_TYPE + " text null)";


    // CREATE TABLE Banglalink Prepaid
    private static final String CREATE_TABLE_BanglalinkPrepaid = "create table "
            + AppConstants.TABLE_BanglalinkPrepaid + "(" + AppConstants.TABLE_BanglalinkPrepaid_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + AppConstants.TABLE_BanglalinkPrepaid_PACKAGE_NAME + " text null,"
            + AppConstants.TABLE_BanglalinkPrepaid_VALIDITY + " text null,"
            + AppConstants.TABLE_BanglalinkPrepaid_PRICE + " text null,"
            + AppConstants.TABLE_BanglalinkPrepaid_DIRECT_DIALING_CODE_WITH + " text null,"
            + AppConstants.TABLE_BanglalinkPrepaid_DIRECT_DIALING_CODE_WITHOUT + " text null,"

            + AppConstants.TABLE_BanglalinkPrepaid_AUTO_RENEW_SMS_BODY + " text null,"
            + AppConstants.TABLE_BanglalinkPrepaid_AUTO_RENEW_SMS_CODE + " text null,"

            + AppConstants.TABLE_BanglalinkPrepaid_ACTIVATION_PROCESS_12 + " text null,"


            + AppConstants.TABLE_BanglalinkPrepaid_DETAILSE_ACTIVATION_PROCESS + " text null,"
            + AppConstants.TABLE_BanglalinkPrepaid_DESCRIPTION + " text null,"
            + AppConstants.TABLE_BanglalinkPrepaid_OPERATOR_TYPE + " text null)";

    // CREATE TABLE Banglalink PostPaid
    private static final String CREATE_TABLE_BanglalinkPostPaid = "create table "
            + AppConstants.TABLE_BanglalinkPostPaid + "(" + AppConstants.TABLE_BanglalinkPostPaid_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + AppConstants.TABLE_BanglalinkPostPaid_PACKAGE_NAME + " text null,"
            + AppConstants.TABLE_BanglalinkPostPaid_VALIDITY + " text null,"
            + AppConstants.TABLE_BanglalinkPostPaid_PRICE + " text null,"
            + AppConstants.TABLE_BanglalinkPostPaid_DIRECT_DIALING_CODE_WITH + " text null,"
            + AppConstants.TABLE_BanglalinkPostPaid_DIRECT_DIALING_CODE_WITHOUT + " text null,"

            + AppConstants.TABLE_BanglalinkPostPaid_AUTO_RENEW_SMS_BODY + " text null,"
            + AppConstants.TABLE_BanglalinkPostPaid_AUTO_RENEW_SMS_CODE + " text null,"

            + AppConstants.TABLE_BanglalinkPostPaid_ACTIVATION_PROCESS_12 + " text null,"


            + AppConstants.TABLE_BanglalinkPostPaid_DETAILSE_ACTIVATION_PROCESS + " text null,"
            + AppConstants.TABLE_BanglalinkPostPaid_DESCRIPTION + " text null,"
            + AppConstants.TABLE_BanglalinkPostPaid_OPERATOR_TYPE + " text null)";


    // CREATE TABLE Robi Prepaid
    private static final String CREATE_TABLE_ROBI = "create table "
            + AppConstants.TABLE_ROBI + "(" + AppConstants.TABLE_ROBI_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + AppConstants.TABLE_ROBI_PACKAGE_NAME + " text null,"
            + AppConstants.TABLE_ROBI_PRICE + " text null,"
            + AppConstants.TABLE_ROBI_VALIDITY + " text null,"

            + AppConstants.TABLE_ROBI_AUTO_RENEW + " text null,"
            + AppConstants.TABLE_ROBI_DIRECT_DIALING_CODE + " text null,"

            + AppConstants.TABLE_ROBI_ACTIVATION_PROCESS_12 + " text null,"


            + AppConstants.TABLE_ROBI_DETAILSE_ACTIVATION_PROCESS + " text null,"
            + AppConstants.TABLE_ROBI_DESCRIPTION + " text null,"
            + AppConstants.TABLE_ROBI_OPERATOR_TYPE + " text null)";

    // CREATE TABLE TELETALK
    private static final String CREATE_TABLE_TELETALK = "create table "
            + AppConstants.TABLE_TELETALK + "(" + AppConstants.TABLE_TELETALK_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + AppConstants.TABLE_TELETALK_PACKAGE_NAME + " text null,"
            + AppConstants.TABLE_TELETALK_VOLUME + " text null,"
            + AppConstants.TABLE_TELETALK_PRICE + " text null,"
            + AppConstants.TABLE_TELETALK_VALIDITY + " text null,"
            + AppConstants.TABLE_TELETALK_DIRECT_DIALING_CODE + " text null,"
            + AppConstants.TABLE_TELETALK_AUTO_RENEW_SMS_BODY_FOR_PREPAID + " text null,"
            + AppConstants.TABLE_TELETALK_AUTO_RENEW_SMS_BODY_FOR_POSTPAID + " text null,"
            + AppConstants.TABLE_TELETALK_AUTO_RENEW_SMS_CODE + " text null,"
            + AppConstants.TABLE_TELETALK_ACTIVATION_PROCESS_12 + " text null,"
            + AppConstants.TABLE_TELETALK_DETAILSE_ACTIVATION_PROCESS + " text null,"
            + AppConstants.TABLE_TELETALK_DESCRIPTION + " text null)";


    // CREATE TABLE Miscellaneous
    private static final String CREATE_TABLE_MISCELLANCEOUS = "create table "
            + AppConstants.TABLE_MISCELLANCEOUS + "(" + AppConstants.TABLE_MISCELLANCEOUS_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + AppConstants.TABLE_MISCELLANCEOUS_OPERATOR + " text null,"
            + AppConstants.TABLE_MISCELLANCEOUS_TITLE + " text null,"
            + AppConstants.TABLE_MISCELLANCEOUS_DIALING_CODE + " text null,"
            + AppConstants.TABLE_MISCELLANCEOUS_SMS_BODY + " text null,"
            + AppConstants.TABLE_MISCELLANCEOUS_SMS_CODE + " text null,"
            + AppConstants.TABLE_MISCELLANCEOUS_DETAILS + " text null)";

    // CREATE TABLE dataPlan
    // id,startDate,endDate,duration,dataSize,dataSizeType
    private static final String CREATE_TABLE_DATA_PLAN = "create table "
            + AppContantsExtra.TABLE_DATA_PLAN + "(" + AppContantsExtra.TABLE_DATA_PLAN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + AppContantsExtra.TABLE_DATA_PLAN_START_DATE + " text null,"
            + AppContantsExtra.TABLE_DATA_PLAN_END_DATE + " text null,"
            + AppContantsExtra.TABLE_DATA_PLAN_DURATION + " text null,"
            + AppContantsExtra.TABLE_DATA_PLAN_DATA_SIZE + " text null,"
            + AppContantsExtra.TABLE_DATA_PLAN_DATA_SIZE_TYPE + " text null)";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_GP);
        database.execSQL(CREATE_TABLE_Airtel);
        database.execSQL(CREATE_TABLE_BanglalinkPrepaid);
        database.execSQL(CREATE_TABLE_TELETALK);
        database.execSQL(CREATE_TABLE_ROBI);
        database.execSQL(CREATE_TABLE_MISCELLANCEOUS);
        database.execSQL(CREATE_TABLE_BanglalinkPostPaid);
        database.execSQL(CREATE_TABLE_MIGRATION);
        database.execSQL(CREATE_TABLE_INTERNET_DATA);
        database.execSQL(CREATE_TABLE_DATA_PLAN);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.e(DBTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + AppConstants.TABLE_GP);
        database.execSQL("DROP TABLE IF EXISTS " + AppConstants.TABLE_Airtel);
        database.execSQL("DROP TABLE IF EXISTS " + AppConstants.TABLE_BanglalinkPrepaid);
        database.execSQL("DROP TABLE IF EXISTS " + AppConstants.TABLE_TELETALK);
        database.execSQL("DROP TABLE IF EXISTS " + AppConstants.TABLE_ROBI);
        database.execSQL("DROP TABLE IF EXISTS " + AppConstants.TABLE_MISCELLANCEOUS);
        database.execSQL("DROP TABLE IF EXISTS " + AppConstants.TABLE_BanglalinkPostPaid);
        database.execSQL("DROP TABLE IF EXISTS " + AppConstants.TABLE_MIGRATION);
        database.execSQL("DROP TABLE IF EXISTS " + AppConstants.TABLE_INTERNET_DATA);
        database.execSQL("DROP TABLE IF EXISTS " + AppContantsExtra.TABLE_DATA_PLAN);
        onCreate(database);
    }
}
