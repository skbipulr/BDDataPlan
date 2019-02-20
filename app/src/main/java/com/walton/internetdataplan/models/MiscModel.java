package com.walton.internetdataplan.models;

/**
 * Created by Faruq on 11/26/2016.
 */

public class MiscModel {
    public int mID;
    public String mOperator;
    public String mTitle;
    public String mDialingCode;
    public String mSmsBody;
    public String mSMSCode;
    public String mDetails;

    public MiscModel(int mID, String mOperator, String mTitle, String mDialingCode, String mSmsBody, String mSMSCode, String mDetails) {
        this.mID = mID;
        this.mOperator = mOperator;
        this.mTitle = mTitle;
        this.mDialingCode = mDialingCode;
        this.mSmsBody = mSmsBody;
        this.mSMSCode = mSMSCode;
        this.mDetails = mDetails;
    }

    public MiscModel(String mOperator, String mTitle, String mDialingCode, String mSmsBody, String mSMSCode, String mDetails) {
        this.mOperator = mOperator;
        this.mTitle = mTitle;
        this.mDialingCode = mDialingCode;
        this.mSmsBody = mSmsBody;
        this.mSMSCode = mSMSCode;
        this.mDetails = mDetails;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmOperator() {
        return mOperator;
    }

    public void setmOperator(String mOperator) {
        this.mOperator = mOperator;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDialingCode() {
        return mDialingCode;
    }

    public void setmDialingCode(String mDialingCode) {
        this.mDialingCode = mDialingCode;
    }

    public String getmSmsBody() {
        return mSmsBody;
    }

    public void setmSmsBody(String mSmsBody) {
        this.mSmsBody = mSmsBody;
    }

    public String getmSMSCode() {
        return mSMSCode;
    }

    public void setmSMSCode(String mSMSCode) {
        this.mSMSCode = mSMSCode;
    }

    public String getmDetails() {
        return mDetails;
    }

    public void setmDetails(String mDetails) {
        this.mDetails = mDetails;
    }
}

