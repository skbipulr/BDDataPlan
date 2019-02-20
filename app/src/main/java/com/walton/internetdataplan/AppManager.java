package com.walton.internetdataplan;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.walton.internetdataplan.db.DBHelper;
import com.walton.internetdataplan.models.AirtelModel;
import com.walton.internetdataplan.models.BLModel;
import com.walton.internetdataplan.models.GPModel;
import com.walton.internetdataplan.models.InternetData;
import com.walton.internetdataplan.models.Migration;
import com.walton.internetdataplan.models.RobiModel;
import com.walton.internetdataplan.models.TeletalkModel;
import com.walton.internetdataplan.utitls.AppConstants;
import com.walton.internetdataplan.utitls.AppContantsExtra;
import com.walton.internetdataplan.utitls.DataPlan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * Created by omor faruq on 10/09/14.
 */
public class AppManager {
    public static ArrayList<AirtelModel> mAirtelPrepaidDataPackList;
    public static ArrayList<AirtelModel> mAirtelPostpaidDataPackList;
    public static ArrayList<TeletalkModel> mTeletalkPrepaidDataPackList;
    public static ArrayList<TeletalkModel> mTeletalkPostpaidDataPackList;
    public static ArrayList<BLModel> mBLPrepaidDataPackList;
    public static ArrayList<BLModel> mBLPostpaidDataPackList;
    public static ArrayList<RobiModel> mRobiPrepaidDataPackList;
    public static ArrayList<RobiModel> mRobiPostpaidDataPackList;
    public static ArrayList<GPModel> mGPPrepaidDataPackList;
    public static ArrayList<GPModel> mGPPostpaidDataPackList;
    public static ArrayList<Migration> mMigrationList;
    private static final String TAG = "AppManager";
    private static AppManager mAppManager = null;
    public Context context = null;

    private AppManager(Context _mContext) {
        this.context = _mContext;
    }

    public static AppManager getInstance(Context _mContext) {
        if (mAppManager == null) {
            mAppManager = new AppManager(_mContext);
            mAirtelPrepaidDataPackList = new ArrayList<AirtelModel>();
            mAirtelPostpaidDataPackList = new ArrayList<AirtelModel>();
            mTeletalkPrepaidDataPackList = new ArrayList<TeletalkModel>();
            mTeletalkPostpaidDataPackList = new ArrayList<TeletalkModel>();
            mBLPostpaidDataPackList = new ArrayList<BLModel>();
            mBLPrepaidDataPackList = new ArrayList<BLModel>();
            mGPPrepaidDataPackList = new ArrayList<GPModel>();
            mGPPostpaidDataPackList = new ArrayList<GPModel>();
            mRobiPrepaidDataPackList = new ArrayList<RobiModel>();
            mRobiPostpaidDataPackList = new ArrayList<RobiModel>();
            mMigrationList = new ArrayList<Migration>();
        }
        return mAppManager;
    }

    //	/**    //	/**  TABLE Internet DATA
    // 	 * // id,usage,current,previous
// 	 * Retrieve internet data consume info
//	 *
//	 * @param mDeptName
//	 */
    public ArrayList<InternetData> retrieveInternetDataPlanAll(Context _mContext) {
        ArrayList<InternetData> mInternetData = new ArrayList<InternetData>();
        if (mInternetData != null && mInternetData.size() > 0) {
            mInternetData.clear();
        }
        String retrieveDatas;

        retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_INTERNET_DATA;

        Cursor myCursor = null;
        try {
            myCursor = DBHelper.getInstance(_mContext).executeQuery(
                    retrieveDatas);

            if (myCursor.moveToFirst()) {
                do {

                    String mUsage = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_INTERNET_DATA_USAGE)));
                    String mCurrent = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_INTERNET_DATA_CURRENT)));
                    String mPrevious = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_INTERNET_DATA_PREVIOUS)));

                    InternetData internetData = new InternetData(mUsage, mCurrent, mPrevious);
                    mInternetData.add(internetData);

                } while (myCursor.moveToNext());
                Collections.sort(mInternetData, new DataInternet());

            }
        } catch (Exception e) {
            e.printStackTrace();
            // be sure to check if any exception is
            // thrown and find out what is the
            // reason
        } finally {
            // be sure to close the cursor or else, might get some
            // unexpected behavior
            // see if cursor really got something when requesting for
            // something and is not closed already, just for precaution
            if (myCursor != null && !myCursor.isClosed()) {
                myCursor.close();
            }

        }
        return mInternetData;

    }

    //	/**    //	/**  TABLE Internet DATA
    // 	 * // id,usage,current,previous
// 	 * Retrieve internet data consume info
//	 *
//	 * @param mDeptName
//	 */
    public ArrayList<InternetData> retrieveInternetDataPlan(Context _mContext, String mDate) {
        ArrayList<InternetData> mInternetData = new ArrayList<InternetData>();
        if (mInternetData != null && mInternetData.size() > 0) {
            mInternetData.clear();
        }
        String retrieveDatas;

        retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_INTERNET_DATA
                + " WHERE " + AppConstants.TABLE_INTERNET_DATA_DATE + " LIKE " + "'%" + mDate + "%'";

        Cursor myCursor = null;
        try {
            myCursor = DBHelper.getInstance(_mContext).executeQuery(
                    retrieveDatas);

            if (myCursor.moveToFirst()) {
                do {

                    String mUsage = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_INTERNET_DATA_USAGE)));
                    String mCurrent = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_INTERNET_DATA_CURRENT)));
                    String mPrevious = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_INTERNET_DATA_PREVIOUS)));

                    InternetData internetData = new InternetData(mUsage, mCurrent, mPrevious);
                    mInternetData.add(internetData);

                } while (myCursor.moveToNext());
                Collections.sort(mInternetData, new DataInternet());

            }
        } catch (Exception e) {
            e.printStackTrace();
            // be sure to check if any exception is
            // thrown and find out what is the
            // reason
        } finally {
            // be sure to close the cursor or else, might get some
            // unexpected behavior
            // see if cursor really got something when requesting for
            // something and is not closed already, just for precaution
            if (myCursor != null && !myCursor.isClosed()) {
                myCursor.close();
            }

        }
        return mInternetData;

    }


    //	/**  TABLE Internet DATA delete
//	 * // id,startDate,endDate,duration,dataSize,dataSizeType
//	 *
//	 * @param id
//	 *
//	 * @param
//	 */
    public Boolean DeleteInternetData(Context _mContext) {
        String sql = "";


        sql = "DELETE FROM " + AppConstants.TABLE_INTERNET_DATA;


        Log.e("Delete sql", "" + sql);
        if (DBHelper.getInstance(_mContext).executeDMLQuery(sql)) {
            Log.e("hes", "inserted");
            return true;
        } else {
            Log.e("hes", "DELETE FROM AppConstants.TABLE_INTERNET_DATA");
            return false;
        }

    }


    //	/**
//	 * Add new number in Internet data table.
//	 *
//	 * @param id
//	 *
//	 * @param
//	 */
    public Boolean addInternetData(Context _mContext, String mUsage, String mCurrent, String mPrevious, String mDateTime) {
        String sql = "";


        sql = "INSERT INTO " + AppConstants.TABLE_INTERNET_DATA + "("
                + AppConstants.TABLE_INTERNET_DATA_USAGE + ","
                + AppConstants.TABLE_INTERNET_DATA_CURRENT + ","
                + AppConstants.TABLE_INTERNET_DATA_PREVIOUS + ","
                + AppConstants.TABLE_INTERNET_DATA_DATE + ")" + "VALUES('"
                + mUsage
                + "','" + mCurrent + "','"
                + mPrevious + "','"
                + mDateTime + "')";


        Log.e("Dept info insert sql", "" + sql);
        if (DBHelper.getInstance(_mContext).executeDMLQuery(sql)) {
            Log.e("hes", "inserted");
            return true;
        } else {
            Log.e("hes", "INSERT INTO " + AppConstants.TABLE_INTERNET_DATA + "("
                    + AppConstants.TABLE_INTERNET_DATA_USAGE + ","
                    + AppConstants.TABLE_INTERNET_DATA_CURRENT + ","
                    + AppConstants.TABLE_INTERNET_DATA_PREVIOUS + ","
                    + AppConstants.TABLE_INTERNET_DATA_DATE + ")" + "VALUES('"
                    + mUsage
                    + "','" + mCurrent + "','"
                    + mPrevious + "','"
                    + mDateTime + "')"
                    + " insertion failed");
            return false;
        }

    }


    //	/**
//	 * update data in Internet data.
//	 *
//	 * @param id
//	 *
//	 * @param
//	 */
    public Boolean updateInternetData(Context _mContext, String mUsage, String mCurrent, String mPrevious, String mDate, String mPREUsage, String mPRECurrent, String mPREPrevious) {
        String sql = "";

        sql = "UPDATE " + AppConstants.TABLE_INTERNET_DATA
                + " SET " + AppConstants.TABLE_INTERNET_DATA_USAGE + " = " + "'" + mUsage + "',"
                + AppConstants.TABLE_INTERNET_DATA_CURRENT + " = " + "'" + mCurrent + "',"
                + AppConstants.TABLE_INTERNET_DATA_PREVIOUS + " = " + "'" + mPrevious + "'"
                + " WHERE " + AppConstants.TABLE_INTERNET_DATA_DATE + " LIKE " + "'%" + mDate + "%'"
                + " AND " + AppConstants.TABLE_INTERNET_DATA_USAGE + " LIKE " + "'%" + mPREUsage + "%'"
                + " AND " + AppConstants.TABLE_INTERNET_DATA_CURRENT + " LIKE " + "'%" + mPRECurrent + "%'"
                + " AND " + AppConstants.TABLE_INTERNET_DATA_PREVIOUS + " LIKE " + "'%" + mPREPrevious + "%'";


        Log.e("Dept info insert sql", "" + sql);
        if (DBHelper.getInstance(_mContext).executeDMLQuery(sql)) {
            Log.e("05_16_omor", ":sql:" + sql);
            return true;
        } else {
            Log.e("05_16_omor", ":wroooooooooooooooooooooong:UPDATE " + AppConstants.TABLE_INTERNET_DATA
                    + " SET " + AppConstants.TABLE_INTERNET_DATA_USAGE + " = " + "'" + mUsage + "',"
                    + AppConstants.TABLE_INTERNET_DATA_CURRENT + " = " + "'" + mCurrent + "',"
                    + AppConstants.TABLE_INTERNET_DATA_PREVIOUS + " = " + "'" + mPrevious + "'"
                    + " WHERE " + AppConstants.TABLE_INTERNET_DATA_DATE + " LIKE " + "'%" + mDate + "%'"
                    + " AND " + AppConstants.TABLE_INTERNET_DATA_USAGE + " LIKE " + "'%" + mPREUsage + "%'"
                    + " AND " + AppConstants.TABLE_INTERNET_DATA_CURRENT + " LIKE " + "'%" + mPRECurrent + "%'"
                    + " AND " + AppConstants.TABLE_INTERNET_DATA_PREVIOUS + " LIKE " + "'%" + mPREPrevious + "%'"
//
                    + " insertion failed");


//            + " WHERE " + AppConstants.TABLE_INTERNET_DATA_CONSUME_TOTAL_RTX + " = " + "'" + mPreviousValue + "'"
////                    + " AND " + AppConstants.TABLE_INTERNET_DATA_CONSUME_DATE + " = " + "'%" + mDate + "%'"

            return false;
        }

    }


    //	/**
//	 * Add new number in Teletalk.
//	 *
//	 * @param id
//	 *            ,number,name,sms
//	 * @param
//	 */
    public Boolean addTeletalkDataFullPackInfo(Context _mContext, String mPackage, String mVolume, String mPrice, String mValidity, String mDialingCode, String mAutosmsBodyForPrepaid, String mAutosmsBodyForPostpaid, String mAutosmsCode, String mProcess12, String mActivateProcess, String mDescription) {
        String sql = "";


        sql = "INSERT INTO " + AppConstants.TABLE_TELETALK + "("
                + AppConstants.TABLE_TELETALK_PACKAGE_NAME + ","
                + AppConstants.TABLE_TELETALK_VOLUME + ","
                + AppConstants.TABLE_TELETALK_PRICE + ","
                + AppConstants.TABLE_TELETALK_VALIDITY + ","
                + AppConstants.TABLE_TELETALK_DIRECT_DIALING_CODE + ","
                + AppConstants.TABLE_TELETALK_AUTO_RENEW_SMS_BODY_FOR_PREPAID + ","
                + AppConstants.TABLE_TELETALK_AUTO_RENEW_SMS_BODY_FOR_POSTPAID + ","
                + AppConstants.TABLE_TELETALK_AUTO_RENEW_SMS_CODE + ","
                + AppConstants.TABLE_TELETALK_ACTIVATION_PROCESS_12 + ","
                + AppConstants.TABLE_TELETALK_DETAILSE_ACTIVATION_PROCESS + ","
                + AppConstants.TABLE_TELETALK_DESCRIPTION + ")" + "VALUES('"
                + mPackage
                + "','" + mVolume + "','"
                + mPrice + "','"
                + mValidity + "','"
                + mDialingCode + "','"
                + mAutosmsBodyForPrepaid + "','"
                + mAutosmsBodyForPostpaid + "','"
                + mAutosmsCode + "','"
                + mProcess12 + "','"
                + mActivateProcess + "','"
                + mDescription + "')";


        Log.e("Dept info insert sql", "" + sql);
        if (DBHelper.getInstance(_mContext).executeDMLQuery(sql)) {
            Log.e("SUCCESS", "inserted");
            return true;
        } else {
            Log.e("", "table name :" + AppConstants.TABLE_TELETALK
                    + " data :package=> " + AppConstants.TABLE_TELETALK_PACKAGE_NAME

                    + "\n validity=> " + AppConstants.TABLE_TELETALK_VALIDITY
                    + "\n direct_dialing_code=> " + AppConstants.TABLE_TELETALK_DIRECT_DIALING_CODE + ","
                    + "\n price=> " + AppConstants.TABLE_TELETALK_PRICE
                    + "\n sms_body_for_prepaid=> " + AppConstants.TABLE_TELETALK_AUTO_RENEW_SMS_BODY_FOR_PREPAID + ","
                    + "\n sms_body_for_prepost=> " + AppConstants.TABLE_TELETALK_AUTO_RENEW_SMS_BODY_FOR_POSTPAID + ","
                    + "\n sms_code=> " + AppConstants.TABLE_TELETALK_AUTO_RENEW_SMS_CODE + ","
                    + "\n process_12=> " + AppConstants.TABLE_TELETALK_ACTIVATION_PROCESS_12 + ","
                    + "\n details_process=> " + AppConstants.TABLE_TELETALK_DETAILSE_ACTIVATION_PROCESS + ","
                    + "\n operator_type=> " + AppConstants.TABLE_TELETALK_DESCRIPTION + ","
                    + " insertion failed");
            return false;
        }

    }


    //	/**  TABLE DATA PLAN
//	 * // id,startDate,endDate,duration,dataSize,dataSizeType
//	 *
//	 * @param id
//	 *
//	 * @param
//	 */
    public Boolean addDataPlanInfo(Context _mContext, String mStartDate, String mEndDate, String mDuration, String mDataSize, String mDataSizeType) {
        String sql = "";


        sql = "INSERT INTO " + AppContantsExtra.TABLE_DATA_PLAN + "("
                + AppContantsExtra.TABLE_DATA_PLAN_START_DATE + ","
                + AppContantsExtra.TABLE_DATA_PLAN_END_DATE + ","
                + AppContantsExtra.TABLE_DATA_PLAN_DURATION + ","
                + AppContantsExtra.TABLE_DATA_PLAN_DATA_SIZE + ","
                + AppContantsExtra.TABLE_DATA_PLAN_DATA_SIZE_TYPE + ")" + "VALUES('"
                + mStartDate
                + "','" + mEndDate + "','"
                + mDuration + "','"
                + mDataSize + "','"
                + mDataSizeType + "')";


        Log.e("Dept info insert sql", "" + sql);
        if (DBHelper.getInstance(_mContext).executeDMLQuery(sql)) {
            Log.e("hes", " data plan inserted");
            Log.e("abcd", ":sql:" + sql);
            return true;
        } else {
            Log.e("abcd", "INSERT INTO " + AppContantsExtra.TABLE_DATA_PLAN + "("
                    + AppContantsExtra.TABLE_DATA_PLAN_START_DATE + ","
                    + AppContantsExtra.TABLE_DATA_PLAN_END_DATE + ","
                    + AppContantsExtra.TABLE_DATA_PLAN_DURATION + ","
                    + AppContantsExtra.TABLE_DATA_PLAN_DATA_SIZE + ","
                    + AppContantsExtra.TABLE_DATA_PLAN_DATA_SIZE_TYPE + ")" + "VALUES('"
                    + mStartDate
                    + "','" + mEndDate + "','"
                    + mDuration + "','"
                    + mDataSize + "','"
                    + mDataSizeType + "') failed");
            return false;
        }

    }


    //	/**    //	/**  TABLE DATA PLAN
//	 * // id,startDate,endDate,duration,dataSize,dataSizeType
// 	 * Retrieve internet data consume info
//	 *
//	 * @param mDeptName
//	 */
    public ArrayList<DataPlan> retrieveDataPlan(Context _mContext) {
        ArrayList<DataPlan> mDataPlanList = new ArrayList<DataPlan>();
        if (mDataPlanList != null && mDataPlanList.size() > 0) {
            mDataPlanList.clear();
        }
        String retrieveDatas;

        retrieveDatas = "SELECT * FROM " + AppContantsExtra.TABLE_DATA_PLAN;

        Cursor myCursor = null;
        try {
            myCursor = DBHelper.getInstance(_mContext).executeQuery(
                    retrieveDatas);

            if (myCursor.moveToFirst()) {
                do {

                    String mStartDate = myCursor.getString((myCursor
                            .getColumnIndex(AppContantsExtra.TABLE_DATA_PLAN_START_DATE)));
                    String mEndDate = myCursor.getString((myCursor
                            .getColumnIndex(AppContantsExtra.TABLE_DATA_PLAN_END_DATE)));
                    String mDuration = myCursor.getString((myCursor
                            .getColumnIndex(AppContantsExtra.TABLE_DATA_PLAN_DURATION)));
                    String mDataSize = myCursor.getString((myCursor
                            .getColumnIndex(AppContantsExtra.TABLE_DATA_PLAN_DATA_SIZE)));

                    String mDataSizeType = myCursor.getString((myCursor
                            .getColumnIndex(AppContantsExtra.TABLE_DATA_PLAN_DATA_SIZE_TYPE)));
                    DataPlan dataPlan = new DataPlan(mStartDate, mEndDate, mDuration, mDataSize, mDataSizeType);
                    mDataPlanList.add(dataPlan);

                } while (myCursor.moveToNext());
                Collections.sort(mDataPlanList, new DataPlanName());

            }
        } catch (Exception e) {
            e.printStackTrace();
            // be sure to check if any exception is
            // thrown and find out what is the
            // reason
        } finally {
            // be sure to close the cursor or else, might get some
            // unexpected behavior
            // see if cursor really got something when requesting for
            // something and is not closed already, just for precaution
            if (myCursor != null && !myCursor.isClosed()) {
                myCursor.close();
            }

        }
        return mDataPlanList;

    }

    //	/**  TABLE DATA PLAN delete
//	 * // id,startDate,endDate,duration,dataSize,dataSizeType
//	 *
//	 * @param id
//	 *
//	 * @param
//	 */
    public Boolean DeleteDataPlanInfo(Context _mContext) {
        String sql = "";


        sql = "DELETE FROM " + AppContantsExtra.TABLE_DATA_PLAN;


        Log.e("Dept info insert sql", "" + sql);
        if (DBHelper.getInstance(_mContext).executeDMLQuery(sql)) {
            Log.e("hes", "inserted");
            return true;
        } else {
            Log.e("hes", "DELETE FROM AppConstants.TABLE_DATA_PLAN");
            return false;
        }

    }

    //	/**
//	 * Retrieve internet data consume info
//	 *
//	 * @param mDeptName
//	 */
    public ArrayList<InternetData> retrieveInternetForPieChartT(Context _mContext) {
        ArrayList<InternetData> mInternetData = new ArrayList<InternetData>();
        if (mInternetData != null && mInternetData.size() > 0) {
            mInternetData.clear();
        }
        String retrieveDatas;

        retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_INTERNET_DATA;

        Log.e("TodayRT", "" + retrieveDatas);
        Cursor myCursor = null;
        try {
            myCursor = DBHelper.getInstance(_mContext).executeQuery(
                    retrieveDatas);
            if (myCursor.moveToFirst()) {
                do {

                    String mUsage = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_INTERNET_DATA_USAGE)));
                    String mCurrent = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_INTERNET_DATA_CURRENT)));
                    String mPrevious = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_INTERNET_DATA_PREVIOUS)));
                    String mDate = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_INTERNET_DATA_DATE)));

                    InternetData internetData = new InternetData(mUsage, mCurrent, mPrevious, mDate);
                    mInternetData.add(internetData);

                } while (myCursor.moveToNext());
                Collections.sort(mInternetData, new DataInternet());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TodayRT", "" + e.toString());
            // be sure to check if any exception is
            // thrown and find out what is the
            // reason
        } finally {
            // be sure to close the cursor or else, might get some
            // unexpected behavior
            // see if cursor really got something when requesting for
            // something and is not closed already, just for precaution
            if (myCursor != null && !myCursor.isClosed()) {
                myCursor.close();
            }

        }
        return mInternetData;

    }

    //	/**
//	 * Retrieve internet data consume info
//	 *
//	 * @param mDeptName
//	 */
    public ArrayList<InternetData> retrieveInternetForPieChart(Context _mContext) {
        ArrayList<InternetData> mInternetData = new ArrayList<InternetData>();
        if (mInternetData != null && mInternetData.size() > 0) {
            mInternetData.clear();
        }
        String retrieveDatas;

        retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_INTERNET_DATA;

        Log.e("TodayRT", "" + retrieveDatas);
        Cursor myCursor = null;
        try {
            myCursor = DBHelper.getInstance(_mContext).executeQuery(
                    retrieveDatas);
            if (myCursor.moveToFirst()) {
                do {

                    String mUsage = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_INTERNET_DATA_USAGE)));
                    String mCurrent = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_INTERNET_DATA_CURRENT)));
                    String mPrevious = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_INTERNET_DATA_PREVIOUS)));

                    InternetData internetData = new InternetData(mUsage, mCurrent, mPrevious);
                    mInternetData.add(internetData);

                } while (myCursor.moveToNext());
                Collections.sort(mInternetData, new DataInternet());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TodayRT", "" + e.toString());
            // be sure to check if any exception is
            // thrown and find out what is the
            // reason
        } finally {
            // be sure to close the cursor or else, might get some
            // unexpected behavior
            // see if cursor really got something when requesting for
            // something and is not closed already, just for precaution
            if (myCursor != null && !myCursor.isClosed()) {
                myCursor.close();
            }

        }
        return mInternetData;

    }


    //	/**
//	 * Add String operator, opPacakge, processType, dialCode, smsBody, smsCode, details
// String operator,String opPacakge,String processType,String dialCode,String smsBody,String smsCode,String details;
//	 */
    public Boolean addMigrationInfo(Context _mContext, String operator, String opPacakge, String processType, String dialCode, String smsBody, String smsCode, String detailsGpToGp, String detailsGpToOthers, String detailsFnF, String detailsPulse, String detailsSMS, String operatorType) {
        String sql = "";


        sql = "INSERT INTO " + AppConstants.TABLE_MIGRATION + "("
                + AppConstants.TABLE_MIGRATION_OPERATOR + ","
                + AppConstants.TABLE_MIGRATION_PACKAGE + ","
                + AppConstants.TABLE_MIGRATION_PROCESS_TYPE + ","
                + AppConstants.TABLE_MIGRATION_DIAL_CODE + ","
                + AppConstants.TABLE_MIGRATION_SMS_BODY + ","
                + AppConstants.TABLE_MIGRATION_SMS_CODE + ","
                + AppConstants.TABLE_MIGRATION_DETAILS_GP_TO_GP + ","
                + AppConstants.TABLE_MIGRATION_DETAILS_GP_TO_OTHERS + ","
                + AppConstants.TABLE_MIGRATION_DETAILS_FNF + ","
                + AppConstants.TABLE_MIGRATION_DETAILS_PULSE + ","
                + AppConstants.TABLE_MIGRATION_DETAILS_SMS + ","
                + AppConstants.TABLE_MIGRATION_OPERATOR_TYPE + ")" + "VALUES('"
                + operator
                + "','" + opPacakge + "','"
                + processType + "','"
                + dialCode + "','"
                + smsBody + "','"
                + smsCode + "','"
                + detailsGpToGp + "','"
                + detailsGpToOthers + "','"
                + detailsFnF + "','"
                + detailsPulse + "','"
                + detailsSMS + "','"
                + operatorType + "')";


        Log.e("Dept info insert sql", "" + sql);
        if (DBHelper.getInstance(_mContext).executeDMLQuery(sql)) {
            Log.e("helloworld", "inserted");
            return true;
        } else {
            Log.e("helloworld", "table name :" + AppConstants.TABLE_MIGRATION
                    + " data :operator=> " + AppConstants.TABLE_MIGRATION_OPERATOR
                    + "\n package=> " + AppConstants.TABLE_MIGRATION_PACKAGE
                    + "\n process=> " + AppConstants.TABLE_MIGRATION_PROCESS_TYPE + ","
                    + "\n dial=> " + AppConstants.TABLE_MIGRATION_DIAL_CODE
                    + "\n sms_body=> " + AppConstants.TABLE_MIGRATION_SMS_BODY + ","
                    + "\n sms_code=> " + AppConstants.TABLE_MIGRATION_SMS_CODE + ","
                    + "\n DETAILS=> " + AppConstants.TABLE_MIGRATION_DETAILS_GP_TO_GP + ","
                    + " insertion failed");
            return false;
        }

    }


    //	/**
//	 * Add new number in Miscellance.
//	 *
//	 * @param id
//	 *            ,number,name,sms
//	 * @param
//	 */
    public Boolean addMiscellaneousInfo(Context _mContext, String mOperator, String mTitle, String mDialingCode, String mSMSBody, String mSMSCode, String mDetails) {
        String sql = "";


        sql = "INSERT INTO " + AppConstants.TABLE_MISCELLANCEOUS + "("
                + AppConstants.TABLE_MISCELLANCEOUS_OPERATOR + ","
                + AppConstants.TABLE_MISCELLANCEOUS_TITLE + ","
                + AppConstants.TABLE_MISCELLANCEOUS_DIALING_CODE + ","
                + AppConstants.TABLE_MISCELLANCEOUS_SMS_BODY + ","
                + AppConstants.TABLE_MISCELLANCEOUS_SMS_CODE + ","

                + AppConstants.TABLE_MISCELLANCEOUS_DETAILS + ")" + "VALUES('"
                + mOperator
                + "','" + mTitle + "','"
                + mDialingCode + "','"
                + mSMSBody + "','"
                + mSMSCode + "','"
                + mDetails + "')";

        Log.e("helloworld", "" + sql);
        if (DBHelper.getInstance(_mContext).executeDMLQuery(sql)) {
            Log.e("SUCCESS", "inserted");
            return true;
        } else {
            Log.e("", "table name :" + AppConstants.TABLE_MISCELLANCEOUS
                    + " data :Operator=> " + AppConstants.TABLE_MISCELLANCEOUS_OPERATOR
                    + "\n Title=> " + AppConstants.TABLE_MISCELLANCEOUS_TITLE
                    + "\n dialingCode=> " + AppConstants.TABLE_MISCELLANCEOUS_DIALING_CODE
                    + "\n sms_body=> " + AppConstants.TABLE_MISCELLANCEOUS_SMS_BODY + ","
                    + "\n sms_code=> " + AppConstants.TABLE_MISCELLANCEOUS_SMS_CODE + ","
                    + "\n details=> " + AppConstants.TABLE_MISCELLANCEOUS_DETAILS + ","
                    + " insertion failed");
            return false;
        }


    }


    //	/**
//	 * Add new number in Robi.
//	 *
//	 * @param id
//	 *            ,number,name,sms
//	 * @param
//	 */
    public Boolean addRobiDataFullPackInfo(Context _mContext, String mPackage, String mPrice, String mValidity, String mAutoRenew, String mDialingCode, String mProcess12, String mActivateProcess, String mDescription, String mOperatorType) {
        String sql = "";

        Log.e("mvalue", "mPackage:" + mPackage
                + "','" + "mPrice:" + mPrice + "','"
                + "mValidity:" + mValidity + "','"
                + "mAutoRenew:" + mAutoRenew + "','"
                + "mDialingCode:" + mDialingCode + "','"
                + "mProcess12:" + mProcess12 + "','"
                + "mActivateProcess:" + mActivateProcess + "','"
                + "mDescription:" + mDescription + "','"
                + "mOperatorType:" + mOperatorType + "','");
        Log.e("hdsfsdf", "\n\n");
        sql = "INSERT INTO " + AppConstants.TABLE_ROBI + "("
                + AppConstants.TABLE_ROBI_PACKAGE_NAME + ","
                + AppConstants.TABLE_ROBI_PRICE + ","
                + AppConstants.TABLE_ROBI_VALIDITY + ","
                + AppConstants.TABLE_ROBI_AUTO_RENEW + ","
                + AppConstants.TABLE_ROBI_DIRECT_DIALING_CODE + ","
                + AppConstants.TABLE_ROBI_ACTIVATION_PROCESS_12 + ","
                + AppConstants.TABLE_ROBI_DETAILSE_ACTIVATION_PROCESS + ","
                + AppConstants.TABLE_ROBI_DESCRIPTION + ","
                + AppConstants.TABLE_ROBI_OPERATOR_TYPE + ")" + "VALUES('"
                + mPackage
                + "','" + mPrice + "','"
                + mValidity + "','"
                + mAutoRenew + "','"
                + mDialingCode + "','"
                + mProcess12 + "','"
                + mActivateProcess + "','"
                + mDescription + "','"
                + mOperatorType + "')";

        Log.e("Dept info insert sql", "" + sql);
        if (DBHelper.getInstance(_mContext).executeDMLQuery(sql)) {
            Log.e("SUCCESS", "inserted");
            return true;
        } else {
            Log.e("", "table name :" + AppConstants.TABLE_ROBI
                    + " data :package=> " + AppConstants.TABLE_ROBI_PACKAGE_NAME
                    + "\n price=> " + AppConstants.TABLE_ROBI_PRICE
                    + "\n validity=> " + AppConstants.TABLE_ROBI_VALIDITY
                    + "\n auto_renew=> " + AppConstants.TABLE_ROBI_AUTO_RENEW + ","
                    + "\n direct_dialing_code=> " + AppConstants.TABLE_ROBI_DIRECT_DIALING_CODE + ","
                    + "\n process_12=> " + AppConstants.TABLE_ROBI_ACTIVATION_PROCESS_12 + ","
                    + "\n details_process=> " + AppConstants.TABLE_ROBI_DETAILSE_ACTIVATION_PROCESS + ","
                    + "\n description=> " + AppConstants.TABLE_ROBI_DESCRIPTION + ","
                    + "\n operator_type=> " + AppConstants.TABLE_ROBI_OPERATOR_TYPE + ","
                    + " insertion failed");
            return false;
        }


    }


    //	/**
//	 * Add new number in BlackList.
//	 *
//	 * @param id
//	 *            ,number,name,sms
//	 * @param
//	 */
    public Boolean addGPDataFullPackInfo(Context _mContext, String mPackage, String mPrice, String mValidity, String mDialingCode, String mAutosmsBody, String mAutosmsCode, String mProcess12, String mActivateProcess, String mDescription, String mOperatorType) {
        String sql = "";


        sql = "INSERT INTO " + AppConstants.TABLE_GP + "("
                + AppConstants.TABLE_GP_PACKAGE_NAME + ","
                + AppConstants.TABLE_GP_PRICE + ","
                + AppConstants.TABLE_GP_VALIDITY + ","
                + AppConstants.TABLE_GP_DIRECT_DIALING_CODE + ","
                + AppConstants.TABLE_GP_AUTO_RENEW_SMS_BODY + ","
                + AppConstants.TABLE_GP_AUTO_RENEW_SMS_CODE + ","
                + AppConstants.TABLE_GP_ACTIVATION_PROCESS_12 + ","
                + AppConstants.TABLE_GP_DETAILSE_ACTIVATION_PROCESS + ","
                + AppConstants.TABLE_GP_DESCRIPTION + ","
                + AppConstants.TABLE_GP_OPERATOR_TYPE + ")" + "VALUES('"
                + mPackage
                + "','" + mPrice + "','"
                + mValidity + "','"
                + mDialingCode + "','"
                + mAutosmsBody + "','"
                + mAutosmsCode + "','"
                + mProcess12 + "','"
                + mActivateProcess + "','"
                + mDescription + "','"
                + mOperatorType + "')";


        Log.e("Dept info insert sql", "" + sql);
        if (DBHelper.getInstance(_mContext).executeDMLQuery(sql)) {
            Log.e("SUCCESS", "inserted");
            return true;
        } else {
            Log.e("", "table name :" + AppConstants.TABLE_GP
                    + " data :package=> " + AppConstants.TABLE_GP_PACKAGE_NAME
                    + "\n price=> " + AppConstants.TABLE_GP_PRICE
                    + "\n validity=> " + AppConstants.TABLE_GP_VALIDITY
                    + "\n direct_dialing_code=> " + AppConstants.TABLE_GP_DIRECT_DIALING_CODE + ","
                    + "\n sms_body=> " + AppConstants.TABLE_GP_AUTO_RENEW_SMS_BODY + ","
                    + "\n sms_code=> " + AppConstants.TABLE_GP_AUTO_RENEW_SMS_CODE + ","
                    + "\n process_12=> " + AppConstants.TABLE_GP_ACTIVATION_PROCESS_12 + ","
                    + "\n details_process=> " + AppConstants.TABLE_GP_DETAILSE_ACTIVATION_PROCESS + ","
                    + "\n description=> " + AppConstants.TABLE_GP_DESCRIPTION + ","
                    + "\n operator_type=> " + AppConstants.TABLE_GP_OPERATOR_TYPE + ","
                    + " insertion failed");
            return false;
        }

    }


    //	/**
//	 * Add new number in Banglalink Prepaid.
//	 *
//	 * @param id
//	 *            ,number,name,sms
//	 * @param
//	 */
    public Boolean addBLPostPaidDataFullPackInfo(Context _mContext, String mPackage, String mValidity, String mPrice, String mDialingCodeWith, String mDialingCodeWithout, String mAutosmsBody, String mAutosmsCode, String mProcess12, String mActivateProcess, String mDescription, String mOperatorType) {
        String sql = "";


        sql = "INSERT INTO " + AppConstants.TABLE_BanglalinkPostPaid + "("
                + AppConstants.TABLE_BanglalinkPostPaid_PACKAGE_NAME + ","
                + AppConstants.TABLE_BanglalinkPostPaid_VALIDITY + ","
                + AppConstants.TABLE_BanglalinkPostPaid_PRICE + ","
                + AppConstants.TABLE_BanglalinkPostPaid_DIRECT_DIALING_CODE_WITH + ","
                + AppConstants.TABLE_BanglalinkPostPaid_DIRECT_DIALING_CODE_WITHOUT + ","
                + AppConstants.TABLE_BanglalinkPostPaid_AUTO_RENEW_SMS_BODY + ","
                + AppConstants.TABLE_BanglalinkPostPaid_AUTO_RENEW_SMS_CODE + ","
                + AppConstants.TABLE_BanglalinkPostPaid_ACTIVATION_PROCESS_12 + ","
                + AppConstants.TABLE_BanglalinkPostPaid_DETAILSE_ACTIVATION_PROCESS + ","
                + AppConstants.TABLE_BanglalinkPostPaid_DESCRIPTION + ","
                + AppConstants.TABLE_BanglalinkPostPaid_OPERATOR_TYPE + ")" + "VALUES('"
                + mPackage
                + "','" + mValidity + "','"
                + mPrice + "','"
                + mDialingCodeWith + "','"
                + mDialingCodeWithout + "','"
                + mAutosmsBody + "','"
                + mAutosmsCode + "','"
                + mProcess12 + "','"
                + mActivateProcess + "','"
                + mDescription + "','"
                + mOperatorType + "')";


        Log.e("Dept info insert sql", "" + sql);
        if (DBHelper.getInstance(_mContext).executeDMLQuery(sql)) {
            Log.e("SUCCESS", "inserted");
            return true;
        } else {
            Log.e("", "table name :" + AppConstants.TABLE_BanglalinkPostPaid
                    + " data :package=> " + AppConstants.TABLE_BanglalinkPostPaid_PACKAGE_NAME
                    + "\n price=> " + AppConstants.TABLE_BanglalinkPostPaid_PRICE
                    + "\n validity=> " + AppConstants.TABLE_BanglalinkPostPaid_VALIDITY
                    + "\n direct_dialing_code_with=> " + AppConstants.TABLE_BanglalinkPostPaid_DIRECT_DIALING_CODE_WITH + ","
                    + "\n direct_dialing_code_without=> " + AppConstants.TABLE_BanglalinkPostPaid_DIRECT_DIALING_CODE_WITHOUT + ","
                    + "\n sms_body=> " + AppConstants.TABLE_BanglalinkPostPaid_AUTO_RENEW_SMS_BODY + ","
                    + "\n sms_code=> " + AppConstants.TABLE_BanglalinkPostPaid_AUTO_RENEW_SMS_CODE + ","
                    + "\n process_12=> " + AppConstants.TABLE_BanglalinkPostPaid_ACTIVATION_PROCESS_12 + ","
                    + "\n details_process=> " + AppConstants.TABLE_BanglalinkPostPaid_DETAILSE_ACTIVATION_PROCESS + ","
                    + "\n description=> " + AppConstants.TABLE_BanglalinkPostPaid_DESCRIPTION + ","
                    + "\n operator_type=> " + AppConstants.TABLE_BanglalinkPostPaid_OPERATOR_TYPE + ","
                    + " insertion failed");
            return false;
        }

    }


    //	/**
//	 * Add new number in Banglalink Prepaid.
//	 *
//	 * @param id
//	 *            ,number,name,sms
//	 * @param
//	 */
    public Boolean addBLPrePaidDataFullPackInfo(Context _mContext, String mPackage, String mValidity, String mPrice, String mDialingCodeWith, String mDialingCodeWithout, String mAutosmsBody, String mAutosmsCode, String mProcess12, String mActivateProcess, String mDescription, String mOperatorType) {
        String sql = "";


        sql = "INSERT INTO " + AppConstants.TABLE_BanglalinkPrepaid + "("
                + AppConstants.TABLE_BanglalinkPrepaid_PACKAGE_NAME + ","
                + AppConstants.TABLE_BanglalinkPrepaid_VALIDITY + ","
                + AppConstants.TABLE_BanglalinkPrepaid_PRICE + ","
                + AppConstants.TABLE_BanglalinkPrepaid_DIRECT_DIALING_CODE_WITH + ","
                + AppConstants.TABLE_BanglalinkPrepaid_DIRECT_DIALING_CODE_WITHOUT + ","
                + AppConstants.TABLE_BanglalinkPrepaid_AUTO_RENEW_SMS_BODY + ","
                + AppConstants.TABLE_BanglalinkPrepaid_AUTO_RENEW_SMS_CODE + ","
                + AppConstants.TABLE_BanglalinkPrepaid_ACTIVATION_PROCESS_12 + ","
                + AppConstants.TABLE_BanglalinkPrepaid_DETAILSE_ACTIVATION_PROCESS + ","
                + AppConstants.TABLE_BanglalinkPrepaid_DESCRIPTION + ","
                + AppConstants.TABLE_BanglalinkPrepaid_OPERATOR_TYPE + ")" + "VALUES('"
                + mPackage
                + "','" + mValidity + "','"
                + mPrice + "','"
                + mDialingCodeWith + "','"
                + mDialingCodeWithout + "','"
                + mAutosmsBody + "','"
                + mAutosmsCode + "','"
                + mProcess12 + "','"
                + mActivateProcess + "','"
                + mDescription + "','"
                + mOperatorType + "')";


        Log.e("Dept info insert sql", "" + sql);
        if (DBHelper.getInstance(_mContext).executeDMLQuery(sql)) {
            Log.e("SUCCESS", "inserted");
            return true;
        } else {
            Log.e("", "table name :" + AppConstants.TABLE_BanglalinkPrepaid
                    + " data :package=> " + AppConstants.TABLE_BanglalinkPrepaid_PACKAGE_NAME
                    + "\n price=> " + AppConstants.TABLE_BanglalinkPrepaid_PRICE
                    + "\n validity=> " + AppConstants.TABLE_BanglalinkPrepaid_VALIDITY
                    + "\n direct_dialing_code_with=> " + AppConstants.TABLE_BanglalinkPrepaid_DIRECT_DIALING_CODE_WITH + ","
                    + "\n direct_dialing_code_without=> " + AppConstants.TABLE_BanglalinkPrepaid_DIRECT_DIALING_CODE_WITHOUT + ","
                    + "\n sms_body=> " + AppConstants.TABLE_BanglalinkPrepaid_AUTO_RENEW_SMS_BODY + ","
                    + "\n sms_code=> " + AppConstants.TABLE_BanglalinkPrepaid_AUTO_RENEW_SMS_CODE + ","
                    + "\n process_12=> " + AppConstants.TABLE_BanglalinkPrepaid_ACTIVATION_PROCESS_12 + ","
                    + "\n details_process=> " + AppConstants.TABLE_BanglalinkPrepaid_DETAILSE_ACTIVATION_PROCESS + ","
                    + "\n description=> " + AppConstants.TABLE_BanglalinkPrepaid_DESCRIPTION + ","
                    + "\n operator_type=> " + AppConstants.TABLE_BanglalinkPrepaid_OPERATOR_TYPE + ","
                    + " insertion failed");
            return false;
        }

    }


    //	/**
//	 * Add new number in Airtel.
//	 *
//	 * @param id
//	 *            ,number,name,sms
//	 * @param
//	 */
    public Boolean addAirtelDataFullPackInfo(Context _mContext, String mPackage, String mValidity, String mDialingCode, String mPrice, String mAutosmsBody, String mAutosmsCode, String mProcess12, String mActivateProcess, String mDescription, String mOperatorType) {
        String sql = "";


        sql = "INSERT INTO " + AppConstants.TABLE_Airtel + "("
                + AppConstants.TABLE_Airtel_PACKAGE_NAME + ","
                + AppConstants.TABLE_Airtel_VALIDITY + ","
                + AppConstants.TABLE_Airtel_DIRECT_DIALING_CODE + ","
                + AppConstants.TABLE_Airtel_PRICE + ","
                + AppConstants.TABLE_Airtel_AUTO_RENEW_SMS_BODY + ","
                + AppConstants.TABLE_Airtel_AUTO_RENEW_SMS_CODE + ","
                + AppConstants.TABLE_Airtel_ACTIVATION_PROCESS_12 + ","
                + AppConstants.TABLE_Airtel_DETAILSE_ACTIVATION_PROCESS + ","
                + AppConstants.TABLE_Airtel_DESCRIPTION + ","
                + AppConstants.TABLE_Airtel_OPERATOR_TYPE + ")" + "VALUES('"
                + mPackage
                + "','" + mValidity + "','"
                + mDialingCode + "','"
                + mPrice + "','"
                + mAutosmsBody + "','"
                + mAutosmsCode + "','"
                + mProcess12 + "','"
                + mActivateProcess + "','"
                + mDescription + "','"
                + mOperatorType + "')";


        Log.e("Dept info insert sql", "" + sql);
        if (DBHelper.getInstance(_mContext).executeDMLQuery(sql)) {
            Log.e("SUCCESS", "inserted");
            return true;
        } else {
            Log.e("", "table name :" + AppConstants.TABLE_Airtel
                    + " data :package=> " + AppConstants.TABLE_Airtel_PACKAGE_NAME

                    + "\n validity=> " + AppConstants.TABLE_Airtel_VALIDITY
                    + "\n direct_dialing_code=> " + AppConstants.TABLE_Airtel_DIRECT_DIALING_CODE + ","
                    + "\n price=> " + AppConstants.TABLE_Airtel_PRICE
                    + "\n sms_body=> " + AppConstants.TABLE_Airtel_AUTO_RENEW_SMS_BODY + ","
                    + "\n sms_code=> " + AppConstants.TABLE_Airtel_AUTO_RENEW_SMS_CODE + ","
                    + "\n process_12=> " + AppConstants.TABLE_Airtel_ACTIVATION_PROCESS_12 + ","
                    + "\n details_process=> " + AppConstants.TABLE_Airtel_DETAILSE_ACTIVATION_PROCESS + ","
                    + "\n description=> " + AppConstants.TABLE_Airtel_DESCRIPTION + ","
                    + "\n operator_type=> " + AppConstants.TABLE_Airtel_OPERATOR_TYPE + ","
                    + " insertion failed");
            return false;
        }

    }

    //	/**
//	 * Retrieve Teletalk
//	 *
//	  String operator, opPacakge, processType, dialCode, smsBody, smsCode, details
//	 */
    public void retrieveMigrationList(Context _mContext, String operator, String opPackage) {
        if (mMigrationList != null && mMigrationList.size() > 0) {
            mMigrationList.clear();
        }
        String retrieveDatas;
        if (operator.equals("")) {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_MIGRATION;
        } else if (opPackage.equals("")) {
//            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_MIGRATION
//                    + " WHERE " + AppConstants.TABLE_MIGRATION_OPERATOR + " LIKE " + "'%" + operator + "%'";
 retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_MIGRATION
                    + " WHERE " + AppConstants.TABLE_MIGRATION_OPERATOR + " = " + "'" + operator + "'";
        } else {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_MIGRATION
                    + " WHERE " + AppConstants.TABLE_MIGRATION_OPERATOR + " LIKE " + "'%" + operator + "%' OR "
                    + AppConstants.TABLE_MIGRATION_PACKAGE + " LIKE " + "'%" + opPackage + "%'";
        }

        Log.e("19_03", "" + retrieveDatas);
        Cursor myCursor = null;
        try {
            myCursor = DBHelper.getInstance(_mContext).executeQuery(
                    retrieveDatas);

            if (myCursor.moveToFirst()) {
                do {
                    String mOperator = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_MIGRATION_OPERATOR)));
                    String mPackage = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_MIGRATION_PACKAGE)));
                    String mProcessType = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_MIGRATION_PROCESS_TYPE)));
                    String mDialCode = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_MIGRATION_DIAL_CODE)));
                    String mSMSBody = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_MIGRATION_SMS_BODY)));
                    String mSMSCode = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_MIGRATION_SMS_CODE)));
                    String mDetailsGptoGp = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_MIGRATION_DETAILS_GP_TO_GP)));

                    String mDetailsGptoOthers = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_MIGRATION_DETAILS_GP_TO_OTHERS)));

                    String mDetailsFnF = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_MIGRATION_DETAILS_FNF)));

                    String mDetailsPulse = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_MIGRATION_DETAILS_PULSE)));
                    String mDetailsSMS = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_MIGRATION_DETAILS_SMS)));
                    String mOperatorType = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_MIGRATION_OPERATOR_TYPE)));

                    Migration migration = new Migration(mOperator, mPackage, mProcessType, mDialCode, mSMSBody, mSMSCode, mDetailsGptoGp, mDetailsGptoOthers, mDetailsFnF, mDetailsPulse, mDetailsSMS, mOperatorType);
                    mMigrationList.add(migration);

                } while (myCursor.moveToNext());
                Collections.sort(mMigrationList, new MigrationName());

            }
        } catch (Exception e) {
            e.printStackTrace();

            // be sure to check if any exception is
            // thrown and find out what is the
            // reason
        } finally {
            // be sure to close the cursor or else, might get some
            // unexpected behavior
            // see if cursor really got something when requesting for
            // something and is not closed already, just for precaution
            if (myCursor != null && !myCursor.isClosed()) {
                myCursor.close();
            }

        }

    }


    //	/**
//	 * Retrieve Teletalk
//	 *
//	 * @param mDeptName
//	 */
    public void retrieveTeletalkPrepaidDataPackList(Context _mContext, String mValue) {
        if (mTeletalkPrepaidDataPackList != null && mTeletalkPrepaidDataPackList.size() > 0) {
            mTeletalkPrepaidDataPackList.clear();
        }
        String retrieveDatas;


        if (mValue.equals("")) {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_TELETALK
                    + " WHERE " + AppConstants.TABLE_TELETALK_DESCRIPTION + " IN ('both','prepaid')";
        } else {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_TELETALK
                    + " WHERE " + AppConstants.TABLE_TELETALK_DESCRIPTION + " IN ('both','prepaid') AND "
                    + AppConstants.TABLE_TELETALK_PACKAGE_NAME + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_TELETALK_PRICE + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_TELETALK_VALIDITY + " LIKE " + "'%" + mValue + "%'";
        }


        Log.e(AppManager.class.getName(), "" + retrieveDatas);
        Cursor myCursor = null;
        try {
            myCursor = DBHelper.getInstance(_mContext).executeQuery(
                    retrieveDatas);

            if (myCursor.moveToFirst()) {
                do {

                    String mPackage = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_PACKAGE_NAME)));
                    String mVolume = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_VOLUME)));
                    String mPrice = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_PRICE)));
                    String mValidity = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_VALIDITY)));
                    String mDialingCode = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_DIRECT_DIALING_CODE)));
                    String mBodyForPrepaid = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_AUTO_RENEW_SMS_BODY_FOR_PREPAID)));

                    String mBodyForPostpaid = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_AUTO_RENEW_SMS_BODY_FOR_POSTPAID)));

                    String mSMSCode = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_AUTO_RENEW_SMS_CODE)));


                    TeletalkModel dm = new TeletalkModel(mPackage, mVolume, mPrice, mValidity, mDialingCode, mBodyForPrepaid, mBodyForPostpaid, mSMSCode);
                    mTeletalkPrepaidDataPackList.add(dm);

                } while (myCursor.moveToNext());
                Collections.sort(mTeletalkPrepaidDataPackList, new TeletalkComparatorName());

            }
        } catch (Exception e) {
            e.printStackTrace(); // be sure to check if any exception is
            // thrown and find out what is the
            // reason
        } finally {
            // be sure to close the cursor or else, might get some
            // unexpected behavior
            // see if cursor really got something when requesting for
            // something and is not closed already, just for precaution
            if (myCursor != null && !myCursor.isClosed()) {
                myCursor.close();
            }

        }

    }
    //	/**
//	 * Retrieve Teletalk
//	 *
//	 * @param mDeptName
//	 */
    public void retrieveTeletalkPrepaidDataPackListFull(Context _mContext, String mValue) {
        if (mTeletalkPrepaidDataPackList != null && mTeletalkPrepaidDataPackList.size() > 0) {
            mTeletalkPrepaidDataPackList.clear();
        }
        String retrieveDatas;


        if (mValue.equals("")) {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_TELETALK
                    + " WHERE " + AppConstants.TABLE_TELETALK_DESCRIPTION + " IN ('both','prepaid')";
        } else {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_TELETALK
                    + " WHERE " + AppConstants.TABLE_TELETALK_DESCRIPTION + " IN ('both','prepaid') AND "
                    + AppConstants.TABLE_TELETALK_PACKAGE_NAME + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_TELETALK_PRICE + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_TELETALK_VALIDITY + " LIKE " + "'%" + mValue + "%'";
        }


        Log.e(AppManager.class.getName(), "" + retrieveDatas);
        Cursor myCursor = null;
        try {
            myCursor = DBHelper.getInstance(_mContext).executeQuery(
                    retrieveDatas);

            if (myCursor.moveToFirst()) {
                do {

                    String mPackage = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_PACKAGE_NAME)));
                    String mVolume = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_VOLUME)));
                    String mPrice = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_PRICE)));
                    String mValidity = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_VALIDITY)));
                    String mDialingCode = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_DIRECT_DIALING_CODE)));
                    String mBodyForPrepaid = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_AUTO_RENEW_SMS_BODY_FOR_PREPAID)));

                    String mBodyForPostpaid = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_AUTO_RENEW_SMS_BODY_FOR_POSTPAID)));

                    String mSMSCode = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_AUTO_RENEW_SMS_CODE)));


                    TeletalkModel dm = new TeletalkModel(mPackage, mVolume, mPrice, mValidity, mDialingCode, mBodyForPrepaid, mBodyForPostpaid, mSMSCode);
                    mTeletalkPrepaidDataPackList.add(dm);

                } while (myCursor.moveToNext());
                Collections.sort(mTeletalkPrepaidDataPackList, new TeletalkComparatorName());

            }
        } catch (Exception e) {
            e.printStackTrace(); // be sure to check if any exception is
            // thrown and find out what is the
            // reason
        } finally {
            // be sure to close the cursor or else, might get some
            // unexpected behavior
            // see if cursor really got something when requesting for
            // something and is not closed already, just for precaution
            if (myCursor != null && !myCursor.isClosed()) {
                myCursor.close();
            }

        }

    }

    //	/**
//	 * Retrieve Teletalk
//	 *
//	 * @param mDeptName
//	 */
    public void retrieveTeletalkPostpaidDataPackList(Context _mContext, String mValue) {
        if (mTeletalkPostpaidDataPackList != null && mTeletalkPostpaidDataPackList.size() > 0) {
            mTeletalkPostpaidDataPackList.clear();
        }
        String retrieveDatas;


        if (mValue.equals("")) {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_TELETALK
                    + " WHERE " + AppConstants.TABLE_TELETALK_DESCRIPTION + " IN ('both','postpaid')";
        } else {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_TELETALK
                    + " WHERE " + AppConstants.TABLE_TELETALK_DESCRIPTION + " IN ('both','postpaid') AND "
                    + AppConstants.TABLE_TELETALK_PACKAGE_NAME + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_TELETALK_PRICE + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_TELETALK_VALIDITY + " LIKE " + "'%" + mValue + "%'";
        }


        Log.e(AppManager.class.getName(), "" + retrieveDatas);
        Cursor myCursor = null;
        try {
            myCursor = DBHelper.getInstance(_mContext).executeQuery(
                    retrieveDatas);

            if (myCursor.moveToFirst()) {
                do {

                    String mPackage = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_PACKAGE_NAME)));
                    String mVolume = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_VOLUME)));
                    String mPrice = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_PRICE)));
                    String mValidity = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_VALIDITY)));
                    String mDialingCode = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_DIRECT_DIALING_CODE)));
                    String mBodyForPrepaid = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_AUTO_RENEW_SMS_BODY_FOR_PREPAID)));

                    String mBodyForPostpaid = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_AUTO_RENEW_SMS_BODY_FOR_POSTPAID)));

                    String mSMSCode = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_AUTO_RENEW_SMS_CODE)));


                    TeletalkModel dm = new TeletalkModel(mPackage, mVolume, mPrice, mValidity, mDialingCode, mBodyForPrepaid, mBodyForPostpaid, mSMSCode);
                    mTeletalkPostpaidDataPackList.add(dm);

                } while (myCursor.moveToNext());
                Collections.sort(mTeletalkPostpaidDataPackList, new TeletalkComparatorName());

            }
        } catch (Exception e) {
            e.printStackTrace(); // be sure to check if any exception is
            // thrown and find out what is the
            // reason
        } finally {
            // be sure to close the cursor or else, might get some
            // unexpected behavior
            // see if cursor really got something when requesting for
            // something and is not closed already, just for precaution
            if (myCursor != null && !myCursor.isClosed()) {
                myCursor.close();
            }

        }

    }
    //	/**
//	 * Retrieve Teletalk
//	 *
//	 * @param mDeptName
//	 */
    public void retrieveTeletalkPostpaidDataPackListFull(Context _mContext, String mValue) {
        if (mTeletalkPostpaidDataPackList != null && mTeletalkPostpaidDataPackList.size() > 0) {
            mTeletalkPostpaidDataPackList.clear();
        }
        String retrieveDatas;


        if (mValue.equals("")) {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_TELETALK
                    + " WHERE " + AppConstants.TABLE_TELETALK_DESCRIPTION + " IN ('both','postpaid')";
        } else {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_TELETALK
                    + " WHERE " + AppConstants.TABLE_TELETALK_DESCRIPTION + " IN ('both','postpaid') AND "
                    + AppConstants.TABLE_TELETALK_PACKAGE_NAME + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_TELETALK_PRICE + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_TELETALK_VALIDITY + " LIKE " + "'%" + mValue + "%'";
        }


        Log.e(AppManager.class.getName(), "" + retrieveDatas);
        Cursor myCursor = null;
        try {
            myCursor = DBHelper.getInstance(_mContext).executeQuery(
                    retrieveDatas);

            if (myCursor.moveToFirst()) {
                do {

                    String mPackage = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_PACKAGE_NAME)));
                    String mVolume = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_VOLUME)));
                    String mPrice = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_PRICE)));
                    String mValidity = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_VALIDITY)));
                    String mDialingCode = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_DIRECT_DIALING_CODE)));
                    String mBodyForPrepaid = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_AUTO_RENEW_SMS_BODY_FOR_PREPAID)));

                    String mBodyForPostpaid = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_AUTO_RENEW_SMS_BODY_FOR_POSTPAID)));

                    String mSMSCode = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_TELETALK_AUTO_RENEW_SMS_CODE)));


                    TeletalkModel dm = new TeletalkModel(mPackage, mVolume, mPrice, mValidity, mDialingCode, mBodyForPrepaid, mBodyForPostpaid, mSMSCode);
                    mTeletalkPostpaidDataPackList.add(dm);

                } while (myCursor.moveToNext());
                Collections.sort(mTeletalkPostpaidDataPackList, new TeletalkComparatorName());

            }
        } catch (Exception e) {
            e.printStackTrace(); // be sure to check if any exception is
            // thrown and find out what is the
            // reason
        } finally {
            // be sure to close the cursor or else, might get some
            // unexpected behavior
            // see if cursor really got something when requesting for
            // something and is not closed already, just for precaution
            if (myCursor != null && !myCursor.isClosed()) {
                myCursor.close();
            }

        }

    }


    //	/**
//	 * Retrieve Teletalk
//	 *
//	 * @param mDeptName
//	 */
    public void retrieveRobiPostpaidDataPackList(Context _mContext, String mValue) {
        if (mRobiPostpaidDataPackList != null && mRobiPostpaidDataPackList.size() > 0) {
            mRobiPostpaidDataPackList.clear();
        }
        String retrieveDatas;
        if (mValue.equals("")) {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_ROBI
                    + " WHERE " + AppConstants.TABLE_ROBI_OPERATOR_TYPE + " IN ('both','postpaid')";
        } else {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_ROBI
                    + " WHERE " + AppConstants.TABLE_ROBI_OPERATOR_TYPE + " IN ('both','postpaid') AND "
                    + AppConstants.TABLE_ROBI_PACKAGE_NAME + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_ROBI_PRICE + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_ROBI_VALIDITY + " LIKE " + "'%" + mValue + "%'";
            Log.e("hellotesting", "" + retrieveDatas);
        }
        Log.e(AppManager.class.getName(), "" + retrieveDatas);
        Cursor myCursor = null;
        try {
            myCursor = DBHelper.getInstance(_mContext).executeQuery(
                    retrieveDatas);

            if (myCursor.moveToFirst()) {
                do {

                    String mPackage = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_ROBI_PACKAGE_NAME)));
                    String mPrice = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_ROBI_PRICE)));

                    String mValidity = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_ROBI_VALIDITY)));
                    String mAutoRenew = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_ROBI_AUTO_RENEW)));
                    String mDialingCode = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_ROBI_DIRECT_DIALING_CODE)));
                    String mProcess12 = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_ROBI_ACTIVATION_PROCESS_12)));
                    String mActProcess = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_ROBI_DETAILSE_ACTIVATION_PROCESS)));
                    String mDescription = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_ROBI_DESCRIPTION)));
                    String mOperatorType = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_ROBI_OPERATOR_TYPE)));


                    RobiModel dm = new RobiModel(mPackage, mPrice, mValidity, mAutoRenew, mDialingCode, mProcess12, mActProcess, mDescription, mOperatorType);
                    mRobiPostpaidDataPackList.add(dm);

                } while (myCursor.moveToNext());
                Collections.sort(mRobiPostpaidDataPackList, new RobiComparatorName());

            }
        } catch (Exception e) {
            e.printStackTrace(); // be sure to check if any exception is
            // thrown and find out what is the
            // reason
        } finally {
            // be sure to close the cursor or else, might get some
            // unexpected behavior
            // see if cursor really got something when requesting for
            // something and is not closed already, just for precaution
            if (myCursor != null && !myCursor.isClosed()) {
                myCursor.close();
            }

        }

    }

    //	/**
//	 * Retrieve Teletalk
//	 *
//	 * @param mDeptName
//	 */
    public void retrieveRobiPrepaidDataPackList(Context _mContext, String mValue) {
        if (mRobiPrepaidDataPackList != null && mRobiPrepaidDataPackList.size() > 0) {
            mRobiPrepaidDataPackList.clear();
        }
        String retrieveDatas;
        if (mValue.equals("")) {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_ROBI
                    + " WHERE " + AppConstants.TABLE_ROBI_OPERATOR_TYPE + " IN ('both','prepaid')";
        } else {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_ROBI
                    + " WHERE " + AppConstants.TABLE_ROBI_OPERATOR_TYPE + " IN ('both','prepaid') AND "
                    + AppConstants.TABLE_ROBI_PACKAGE_NAME + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_ROBI_PRICE + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_ROBI_VALIDITY + " LIKE " + "'%" + mValue + "%'";
            Log.e("hellotesting", "" + retrieveDatas);
        }

        Log.e(AppManager.class.getName(), "" + retrieveDatas);
        Cursor myCursor = null;
        try {
            myCursor = DBHelper.getInstance(_mContext).executeQuery(
                    retrieveDatas);

            if (myCursor.moveToFirst()) {
                do {

                    String mPackage = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_ROBI_PACKAGE_NAME)));
                    String mPrice = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_ROBI_PRICE)));

                    String mValidity = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_ROBI_VALIDITY)));
                    String mAutoRenew = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_ROBI_AUTO_RENEW)));
                    String mDialingCode = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_ROBI_DIRECT_DIALING_CODE)));
                    String mProcess12 = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_ROBI_ACTIVATION_PROCESS_12)));
                    String mActProcess = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_ROBI_DETAILSE_ACTIVATION_PROCESS)));
                    String mDescription = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_ROBI_DESCRIPTION)));
                    String mOperatorType = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_ROBI_OPERATOR_TYPE)));


                    RobiModel dm = new RobiModel(mPackage, mPrice, mValidity, mAutoRenew, mDialingCode, mProcess12, mActProcess, mDescription, mOperatorType);
                    mRobiPrepaidDataPackList.add(dm);

                } while (myCursor.moveToNext());
                Collections.sort(mRobiPrepaidDataPackList, new RobiComparatorName());

            }
        } catch (Exception e) {
            e.printStackTrace(); // be sure to check if any exception is
            // thrown and find out what is the
            // reason
        } finally {
            // be sure to close the cursor or else, might get some
            // unexpected behavior
            // see if cursor really got something when requesting for
            // something and is not closed already, just for precaution
            if (myCursor != null && !myCursor.isClosed()) {
                myCursor.close();
            }

        }

    }

    //	/**
//	 * Retrieve Airtel
//	 *
//	 * @param mDeptName
//	 */
    public void retrieveAirtelPrepaidDataPackList(Context _mContext, String mValue) {
        if (mAirtelPrepaidDataPackList != null && mAirtelPrepaidDataPackList.size() > 0) {
            mAirtelPrepaidDataPackList.clear();
        }
        String retrieveDatas;
        if (mValue.equals("")) {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_Airtel
                    + " WHERE " + AppConstants.TABLE_Airtel_OPERATOR_TYPE + " IN ('both','prepaid')";
        } else {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_Airtel
                    + " WHERE " + AppConstants.TABLE_Airtel_OPERATOR_TYPE + " IN ('both','prepaid') AND "
                    + AppConstants.TABLE_Airtel_PACKAGE_NAME + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_Airtel_PRICE + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_Airtel_VALIDITY + " LIKE " + "'%" + mValue + "%'";
        }

        Log.e(AppManager.class.getName(), "" + retrieveDatas);
        Cursor myCursor = null;
        try {
            myCursor = DBHelper.getInstance(_mContext).executeQuery(
                    retrieveDatas);

            if (myCursor.moveToFirst()) {
                do {

                    String mPackage = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_Airtel_PACKAGE_NAME)));

                    String mValidity = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_Airtel_VALIDITY)));
                    String mDialingCode = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_Airtel_DIRECT_DIALING_CODE)));
                    String mPrice = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_Airtel_PRICE)));

                    AirtelModel dm = new AirtelModel(mPackage, mValidity, mDialingCode, mPrice);
                    mAirtelPrepaidDataPackList.add(dm);

                } while (myCursor.moveToNext());
                Collections.sort(mAirtelPrepaidDataPackList, new AirtelComparatorName());

            }
        } catch (Exception e) {
            e.printStackTrace(); // be sure to check if any exception is
            // thrown and find out what is the
            // reason
        } finally {
            // be sure to close the cursor or else, might get some
            // unexpected behavior
            // see if cursor really got something when requesting for
            // something and is not closed already, just for precaution
            if (myCursor != null && !myCursor.isClosed()) {
                myCursor.close();
            }

        }

    }

    //	/**
//	 * Retrieve Airtel
//	 *
//	 * @param mDeptName
//	 */
    public void retrieveAirtelPostDataPackList(Context _mContext, String mValue) {
        if (mAirtelPostpaidDataPackList != null && mAirtelPostpaidDataPackList.size() > 0) {
            mAirtelPostpaidDataPackList.clear();
        }
        String retrieveDatas;
        if (mValue.equals("")) {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_Airtel
                    + " WHERE " + AppConstants.TABLE_Airtel_OPERATOR_TYPE + " IN ('both','postpaid')";
        } else {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_Airtel
                    + " WHERE " + AppConstants.TABLE_Airtel_OPERATOR_TYPE + " IN ('both','postpaid') AND "
                    + AppConstants.TABLE_Airtel_PACKAGE_NAME + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_Airtel_PRICE + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_Airtel_VALIDITY + " LIKE " + "'%" + mValue + "%'";
        }

        Log.e(AppManager.class.getName(), "" + retrieveDatas);
        Cursor myCursor = null;
        try {
            myCursor = DBHelper.getInstance(_mContext).executeQuery(
                    retrieveDatas);

            if (myCursor.moveToFirst()) {
                do {

                    String mPackage = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_Airtel_PACKAGE_NAME)));

                    String mValidity = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_Airtel_VALIDITY)));
                    String mDialingCode = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_Airtel_DIRECT_DIALING_CODE)));
                    String mPrice = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_Airtel_PRICE)));

                    AirtelModel dm = new AirtelModel(mPackage, mValidity, mDialingCode, mPrice);
                    mAirtelPostpaidDataPackList.add(dm);

                } while (myCursor.moveToNext());
                Collections.sort(mAirtelPostpaidDataPackList, new AirtelComparatorName());

            }
        } catch (Exception e) {
            e.printStackTrace(); // be sure to check if any exception is
            // thrown and find out what is the
            // reason
        } finally {
            // be sure to close the cursor or else, might get some
            // unexpected behavior
            // see if cursor really got something when requesting for
            // something and is not closed already, just for precaution
            if (myCursor != null && !myCursor.isClosed()) {
                myCursor.close();
            }

        }

    }

    //	/**
//	 * Retrieve match
//	 *
//	 * @param mDeptName
//	 */
    public void retrieveGPPrepaidDataPackList(Context _mContext, String mValue) {
        if (mGPPrepaidDataPackList != null && mGPPrepaidDataPackList.size() > 0) {
            mGPPrepaidDataPackList.clear();
        }
        String retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_GP
                + " WHERE " + AppConstants.TABLE_GP_PACKAGE_NAME + " LIKE " + "'%" + mValue + "%' OR "
                + AppConstants.TABLE_GP_PRICE + " LIKE " + "'%" + mValue + "%' OR "
                + AppConstants.TABLE_GP_VALIDITY + " LIKE " + "'%" + mValue + "%'";
        Log.e("hellotesting", "" + retrieveDatas);
        Cursor myCursor = null;
        try {
            myCursor = DBHelper.getInstance(_mContext).executeQuery(
                    retrieveDatas);

            if (myCursor.moveToFirst()) {
                do {

                    String mPackage = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_GP_PACKAGE_NAME)));
                    String mPrice = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_GP_PRICE)));
                    String mValidity = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_GP_VALIDITY)));
                    String mDialingCode = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_GP_DIRECT_DIALING_CODE)));
                    String mAutoRenewedSMSBody = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_GP_AUTO_RENEW_SMS_BODY)));
                    String mAutoRenewedSMSCode = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_GP_AUTO_RENEW_SMS_CODE)));

                    GPModel dm = new GPModel(mPackage, mPrice, mValidity, mDialingCode, mAutoRenewedSMSBody, mAutoRenewedSMSCode);
                    mGPPrepaidDataPackList.add(dm);

                } while (myCursor.moveToNext());
                Collections.sort(mGPPrepaidDataPackList, new CustomComparatorName());

            }
        } catch (Exception e) {
            e.printStackTrace(); // be sure to check if any exception is
            // thrown and find out what is the
            // reason
        } finally {
            // be sure to close the cursor or else, might get some
            // unexpected behavior
            // see if cursor really got something when requesting for
            // something and is not closed already, just for precaution
            if (myCursor != null && !myCursor.isClosed()) {
                myCursor.close();
            }

        }

    }


    //	/**
//	 * Retrieve match
//	 *
//	 * @param mDeptName
//	 */
    public void retrieveGPPostpaidDataPackList(Context _mContext, String mValue) {
        if (mGPPostpaidDataPackList != null && mGPPostpaidDataPackList.size() > 0) {
            mGPPostpaidDataPackList.clear();
        }
        String retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_GP
                + " WHERE " + AppConstants.TABLE_GP_PACKAGE_NAME + " LIKE " + "'%" + mValue + "%' OR "
                + AppConstants.TABLE_GP_PRICE + " LIKE " + "'%" + mValue + "%' OR "
                + AppConstants.TABLE_GP_VALIDITY + " LIKE " + "'%" + mValue + "%'";
        Log.e("hellotesting", "" + retrieveDatas);
        Cursor myCursor = null;
        try {
            myCursor = DBHelper.getInstance(_mContext).executeQuery(
                    retrieveDatas);

            if (myCursor.moveToFirst()) {
                do {

                    String mPackage = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_GP_PACKAGE_NAME)));
                    String mPrice = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_GP_PRICE)));
                    String mValidity = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_GP_VALIDITY)));
                    String mDialingCode = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_GP_DIRECT_DIALING_CODE)));
                    String mAutoRenewedSMSBody = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_GP_AUTO_RENEW_SMS_BODY)));
                    String mAutoRenewedSMSCode = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_GP_AUTO_RENEW_SMS_CODE)));

                    GPModel dm = new GPModel(mPackage, mPrice, mValidity, mDialingCode, mAutoRenewedSMSBody, mAutoRenewedSMSCode);
                    mGPPostpaidDataPackList.add(dm);

                } while (myCursor.moveToNext());
                Collections.sort(mGPPostpaidDataPackList, new CustomComparatorName());

            }
        } catch (Exception e) {
            e.printStackTrace(); // be sure to check if any exception is
            // thrown and find out what is the
            // reason
        } finally {
            // be sure to close the cursor or else, might get some
            // unexpected behavior
            // see if cursor really got something when requesting for
            // something and is not closed already, just for precaution
            if (myCursor != null && !myCursor.isClosed()) {
                myCursor.close();
            }

        }

    }




    //	/**
//	 * Retrieve BL PostPaid
//	 *
//	 * @param mDeptName
//	 */
    public void retrieveBLPostpaidDataPackList(Context _mContext, String mValue) {
        if (mBLPostpaidDataPackList != null && mBLPostpaidDataPackList.size() > 0) {
            mBLPostpaidDataPackList.clear();
        }
        String retrieveDatas;
        if (mValue.equals("")) {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_BanglalinkPostPaid;
        } else {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_BanglalinkPostPaid
                    + " WHERE " + AppConstants.TABLE_BanglalinkPostPaid_PACKAGE_NAME + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_BanglalinkPostPaid_PRICE + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_BanglalinkPostPaid_VALIDITY + " LIKE " + "'%" + mValue + "%'";
        }

        Log.e(AppManager.class.getName(), "" + retrieveDatas);
        Cursor myCursor = null;
        try {
            myCursor = DBHelper.getInstance(_mContext).executeQuery(
                    retrieveDatas);

            if (myCursor.moveToFirst()) {
                do {

                    String mPackage = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPostPaid_PACKAGE_NAME)));

                    String mValidity = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPostPaid_VALIDITY)));
                    String mPrice = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPostPaid_PRICE)));
                    String mDialingCodeWith = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPostPaid_DIRECT_DIALING_CODE_WITH)));
                    String mDialingCodeWithout = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPostPaid_DIRECT_DIALING_CODE_WITHOUT)));
                    String mSMSBody = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPostPaid_AUTO_RENEW_SMS_BODY)));
                    String mSMSCode = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPostPaid_AUTO_RENEW_SMS_CODE)));


                    BLModel dm = new BLModel(mPackage, mValidity, mPrice, mDialingCodeWith, mDialingCodeWithout, mSMSBody, mSMSCode);
                    mBLPostpaidDataPackList.add(dm);

                } while (myCursor.moveToNext());
                Collections.sort(mBLPostpaidDataPackList, new BLPrepaidComparatorName());

            }
        } catch (Exception e) {
            e.printStackTrace(); // be sure to check if any exception is
            // thrown and find out what is the
            // reason
        } finally {
            // be sure to close the cursor or else, might get some
            // unexpected behavior
            // see if cursor really got something when requesting for
            // something and is not closed already, just for precaution
            if (myCursor != null && !myCursor.isClosed()) {
                myCursor.close();
            }

        }

    }


    //	/**
//	 * Retrieve BL Prepaid
//	 *
//	 * @param mDeptName
//	 */
    public void retrieveBLPrepaidDataPackList(Context _mContext, String mValue) {
        if (mBLPrepaidDataPackList != null && mBLPrepaidDataPackList.size() > 0) {
            mBLPrepaidDataPackList.clear();
        }
        String retrieveDatas;
        if (mValue.equals("")) {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_BanglalinkPrepaid;
        } else {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_BanglalinkPrepaid
                    + " WHERE " + AppConstants.TABLE_BanglalinkPrepaid_PACKAGE_NAME + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_BanglalinkPrepaid_PRICE + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_BanglalinkPrepaid_VALIDITY + " LIKE " + "'%" + mValue + "%'";
        }

        Log.e(AppManager.class.getName(), "" + retrieveDatas);
        Cursor myCursor = null;
        try {
            myCursor = DBHelper.getInstance(_mContext).executeQuery(
                    retrieveDatas);

            if (myCursor.moveToFirst()) {
                do {

                    String mPackage = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_PACKAGE_NAME)));

                    String mValidity = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_VALIDITY)));
                    String mPrice = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_PRICE)));
                    String mDialingCodeWith = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_DIRECT_DIALING_CODE_WITH)));
                    String mDialingCodeWithout = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_DIRECT_DIALING_CODE_WITHOUT)));
                    String mSMSBody = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_AUTO_RENEW_SMS_BODY)));
                    String mSMSCode = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_AUTO_RENEW_SMS_CODE)));

                    String mProcess12 = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_ACTIVATION_PROCESS_12)));

                    String mProcess = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_DETAILSE_ACTIVATION_PROCESS)));

                    String mDescription = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_DESCRIPTION)));

                    String mType = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_OPERATOR_TYPE)));


                    Log.e("testont", ":mPackage:" + mPackage + "\n ,mValidity:" + mValidity + ",\n mPrice:" + mPrice + ":mDialingCodeWith:" + mDialingCodeWith + "','"
                            + "\n :mDialingCodeWithout:" + mDialingCodeWithout + "','"
                            + "\n :mSMSBody:" + mSMSBody + "','"
                            + "\n :mSMSCode:" + mSMSCode + "','"
                            + "\n :mProcess12:" + mProcess12 + "','"
                            + "\n :mProcess:" + mProcess + "','"
                            + "\n :mDescription:" + mDescription + "','"
                            + "\n :mType:" + mType + "','");


                    BLModel dm = new BLModel(mPackage, mValidity, mPrice, mDialingCodeWith, mDialingCodeWithout, mSMSBody, mSMSCode, mProcess12, mProcess, mDescription, mType);
                    mBLPrepaidDataPackList.add(dm);

                } while (myCursor.moveToNext());
                Collections.sort(mBLPrepaidDataPackList, new BLPrepaidComparatorName());

            }
        } catch (Exception e) {
            e.printStackTrace(); // be sure to check if any exception is
            // thrown and find out what is the
            // reason
        } finally {
            // be sure to close the cursor or else, might get some
            // unexpected behavior
            // see if cursor really got something when requesting for
            // something and is not closed already, just for precaution
            if (myCursor != null && !myCursor.isClosed()) {
                myCursor.close();
            }

        }

    }


    //	/**
//	 * Retrieve BL Prepaid
//	 *
//	 * @param mDeptName
//	 */
    public ArrayList<BLModel> retrieveBLPrepaidDataPackList(Context _mContext, String mDesc, String mValue) {
        ArrayList<BLModel> mBLPrepaidDataPackList = new ArrayList<BLModel>();
        if (mBLPrepaidDataPackList != null && mBLPrepaidDataPackList.size() > 0) {
            mBLPrepaidDataPackList.clear();
        }
        String retrieveDatas;
        if (mValue.equals("")) {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_BanglalinkPrepaid
                    + " WHERE " + AppConstants.TABLE_BanglalinkPrepaid_DESCRIPTION
                    + " = " + "'" + mDesc + "'";
        } else {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_BanglalinkPrepaid
                    + " WHERE " + AppConstants.TABLE_BanglalinkPrepaid_DESCRIPTION
                    + " = " + "'" + mDesc + "'"
                    + " AND "
                    + AppConstants.TABLE_BanglalinkPrepaid_PACKAGE_NAME + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_BanglalinkPrepaid_PRICE + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_BanglalinkPrepaid_VALIDITY + " LIKE " + "'%" + mValue + "%'";

        }

        Log.e(AppManager.class.getName(), "" + retrieveDatas);
        Cursor myCursor = null;
        try {
            myCursor = DBHelper.getInstance(_mContext).executeQuery(
                    retrieveDatas);

            if (myCursor.moveToFirst()) {
                do {

                    String mPackage = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_PACKAGE_NAME)));

                    String mValidity = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_VALIDITY)));
                    String mPrice = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_PRICE)));
                    String mDialingCodeWith = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_DIRECT_DIALING_CODE_WITH)));
                    String mDialingCodeWithout = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_DIRECT_DIALING_CODE_WITHOUT)));
                    String mSMSBody = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_AUTO_RENEW_SMS_BODY)));
                    String mSMSCode = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_AUTO_RENEW_SMS_CODE)));

                    String mProcess12 = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_ACTIVATION_PROCESS_12)));

                    String mProcess = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_DETAILSE_ACTIVATION_PROCESS)));

                    String mDescription = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_DESCRIPTION)));

                    String mType = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_OPERATOR_TYPE)));
                    BLModel dm = new BLModel(mPackage, mValidity, mPrice, mDialingCodeWith, mDialingCodeWithout, mSMSBody, mSMSCode, mProcess12, mProcess, mDescription, mType);
                    mBLPrepaidDataPackList.add(dm);

                } while (myCursor.moveToNext());
                Collections.sort(mBLPrepaidDataPackList, new BLPrepaidComparatorName());

            }
        } catch (Exception e) {
            e.printStackTrace(); // be sure to check if any exception is
            // thrown and find out what is the
            // reason
        } finally {
            // be sure to close the cursor or else, might get some
            // unexpected behavior
            // see if cursor really got something when requesting for
            // something and is not closed already, just for precaution
            if (myCursor != null && !myCursor.isClosed()) {
                myCursor.close();
            }

        }
        return mBLPrepaidDataPackList;

    }

    //	/**
//	 * Retrieve BL Prepaid
//	 *
//	 * @param mDeptName
//	 */
    public ArrayList<BLModel> retrieveBLPostpaidDataPackList(Context _mContext, String mDesc, String mValue) {
        ArrayList<BLModel> mBLPostpaidDataPackList = new ArrayList<BLModel>();
        if (mBLPostpaidDataPackList != null && mBLPostpaidDataPackList.size() > 0) {
            mBLPostpaidDataPackList.clear();
        }
        String retrieveDatas;
        if (mValue.equals("")) {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_BanglalinkPostPaid
                    + " WHERE " + AppConstants.TABLE_BanglalinkPostPaid_OPERATOR_TYPE + " IN ('both','Postpaid') AND "
                    + AppConstants.TABLE_BanglalinkPostPaid_DESCRIPTION
                    + " = " + "'" + mDesc + "'";
        } else {
            retrieveDatas = "SELECT * FROM " + AppConstants.TABLE_BanglalinkPostPaid
                    + " WHERE " + AppConstants.TABLE_BanglalinkPostPaid_OPERATOR_TYPE + " IN ('both','Postpaid') AND "
                    + AppConstants.TABLE_BanglalinkPostPaid_DESCRIPTION
                    + " = " + "'" + mDesc + "'"
                    + " AND "
                    + AppConstants.TABLE_BanglalinkPostPaid_PACKAGE_NAME + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_BanglalinkPostPaid_PRICE + " LIKE " + "'%" + mValue + "%' OR "
                    + AppConstants.TABLE_BanglalinkPostPaid_VALIDITY + " LIKE " + "'%" + mValue + "%'";

        }

        Log.e(AppManager.class.getName(), "" + retrieveDatas);
        Cursor myCursor = null;
        try {
            myCursor = DBHelper.getInstance(_mContext).executeQuery(
                    retrieveDatas);

            if (myCursor.moveToFirst()) {
                do {

                    String mPackage = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_PACKAGE_NAME)));

                    String mValidity = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_VALIDITY)));
                    String mPrice = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_PRICE)));
                    String mDialingCodeWith = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_DIRECT_DIALING_CODE_WITH)));
                    String mDialingCodeWithout = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_DIRECT_DIALING_CODE_WITHOUT)));
                    String mSMSBody = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_AUTO_RENEW_SMS_BODY)));
                    String mSMSCode = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_AUTO_RENEW_SMS_CODE)));

                    String mProcess12 = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_ACTIVATION_PROCESS_12)));

                    String mProcess = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_DETAILSE_ACTIVATION_PROCESS)));

                    String mDescription = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_DESCRIPTION)));

                    String mType = myCursor.getString((myCursor
                            .getColumnIndex(AppConstants.TABLE_BanglalinkPrepaid_OPERATOR_TYPE)));
                    BLModel dm = new BLModel(mPackage, mValidity, mPrice, mDialingCodeWith, mDialingCodeWithout, mSMSBody, mSMSCode, mProcess12, mProcess, mDescription, mType);
                    mBLPostpaidDataPackList.add(dm);

                } while (myCursor.moveToNext());
                Collections.sort(mBLPostpaidDataPackList, new BLPrepaidComparatorName());

            }
        } catch (Exception e) {
            e.printStackTrace(); // be sure to check if any exception is
            // thrown and find out what is the
            // reason
        } finally {
            // be sure to close the cursor or else, might get some
            // unexpected behavior
            // see if cursor really got something when requesting for
            // something and is not closed already, just for precaution
            if (myCursor != null && !myCursor.isClosed()) {
                myCursor.close();
            }

        }
        return mBLPostpaidDataPackList;

    }


    class CustomComparatorName implements Comparator<GPModel> {
        @Override
        public int compare(GPModel o1, GPModel o2) {
            return ((String) o1.getmValidity().toUpperCase()).compareTo(o2.getmValidity().toUpperCase());
        }
    }

    class AirtelComparatorName implements Comparator<AirtelModel> {
        @Override
        public int compare(AirtelModel o1, AirtelModel o2) {
            return ((String) o1.getmValidity().toUpperCase()).compareTo(o2.getmValidity().toUpperCase());
        }
    }

    class BLPrepaidComparatorName implements Comparator<BLModel> {
        @Override
        public int compare(BLModel o1, BLModel o2) {
            return ((String) o1.getmValidity().toUpperCase()).compareTo(o2.getmValidity().toUpperCase());
        }
    }


    class MigrationName implements Comparator<Migration> {
        @Override
        public int compare(Migration o1, Migration o2) {
            return ((String) o1.getOpPacakge().toUpperCase()).compareTo(o2.getOpPacakge().toUpperCase());
        }
    }


    class TeletalkComparatorName implements Comparator<TeletalkModel> {
        @Override
        public int compare(TeletalkModel o1, TeletalkModel o2) {
            return ((String) o1.getmPackageName().toUpperCase()).compareTo(o2.getmPackageName().toUpperCase());
        }
    }

    class RobiComparatorName implements Comparator<RobiModel> {
        @Override
        public int compare(RobiModel o1, RobiModel o2) {
            return ((String) o1.getmValidity().toUpperCase()).compareTo(o2.getmValidity().toUpperCase());
        }
    }


    class DataPlanName implements Comparator<DataPlan> {
        @Override
        public int compare(DataPlan o1, DataPlan o2) {
            return ((String) o1.getDataSize().toUpperCase()).compareTo(o2.getDataSize().toUpperCase());
        }
    }

    class DataInternet implements Comparator<InternetData> {
        @Override
        public int compare(InternetData o1, InternetData o2) {
            return ((String) o1.getDate().toUpperCase()).compareTo(o2.getDate().toUpperCase());
        }
    }

}
