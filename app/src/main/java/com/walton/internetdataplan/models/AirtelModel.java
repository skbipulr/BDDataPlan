package com.walton.internetdataplan.models;

/**
 * Created by Faruq on 10/24/2016.
 */

public class AirtelModel {
    public int mID;
    public String mPackageName;
    public String mValidity;
    public String mDirectDialingCode;
    public String mPrice;
    public String mSmsBody;
    public String mSMSCode;
    public String mProcess12;
    public String mDetailsActivateProcess;
    public String mDescription;
    public String mOperatorType;

    public AirtelModel(int mID, String mOperatorType, String mDescription, String mDetailsActivateProcess, String mProcess12, String mSMSCode, String mSmsBody, String mPrice, String mDirectDialingCode, String mValidity, String mPackageName) {
        this.mID = mID;
        this.mOperatorType = mOperatorType;
        this.mDescription = mDescription;
        this.mDetailsActivateProcess = mDetailsActivateProcess;
        this.mProcess12 = mProcess12;
        this.mSMSCode = mSMSCode;
        this.mSmsBody = mSmsBody;
        this.mPrice = mPrice;
        this.mDirectDialingCode = mDirectDialingCode;
        this.mValidity = mValidity;
        this.mPackageName = mPackageName;
    }

    public AirtelModel(String mOperatorType, String mDescription, String mDetailsActivateProcess, String mProcess12, String mSMSCode, String mSmsBody, String mPrice, String mDirectDialingCode, String mValidity, String mPackageName) {
        this.mOperatorType = mOperatorType;
        this.mDescription = mDescription;
        this.mDetailsActivateProcess = mDetailsActivateProcess;
        this.mProcess12 = mProcess12;
        this.mSMSCode = mSMSCode;
        this.mSmsBody = mSmsBody;
        this.mPrice = mPrice;
        this.mDirectDialingCode = mDirectDialingCode;
        this.mValidity = mValidity;
        this.mPackageName = mPackageName;
    }

    public AirtelModel(int mID, String mPackageName, String mValidity, String mDirectDialingCode, String mPrice) {
        this.mID = mID;
        this.mPackageName = mPackageName;
        this.mValidity = mValidity;
        this.mDirectDialingCode = mDirectDialingCode;
        this.mPrice = mPrice;
    }

    public AirtelModel(String mPackageName, String mValidity, String mDirectDialingCode, String mPrice) {
        this.mPackageName = mPackageName;
        this.mValidity = mValidity;
        this.mDirectDialingCode = mDirectDialingCode;
        this.mPrice = mPrice;
    }



    public AirtelModel() {
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmPackageName() {
        return mPackageName;
    }

    public void setmPackageName(String mPackageName) {
        this.mPackageName = mPackageName;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public String getmValidity() {
        return mValidity;
    }

    public void setmValidity(String mValidity) {
        this.mValidity = mValidity;
    }

    public String getmDirectDialingCode() {
        return mDirectDialingCode;
    }

    public void setmDirectDialingCode(String mDirectDialingCode) {
        this.mDirectDialingCode = mDirectDialingCode;
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


    public String getmProcess12() {
        return mProcess12;
    }

    public void setmProcess12(String mProcess12) {
        this.mProcess12 = mProcess12;
    }

    public String getmDetailsActivateProcess() {
        return mDetailsActivateProcess;
    }

    public void setmDetailsActivateProcess(String mDetailsActivateProcess) {
        this.mDetailsActivateProcess = mDetailsActivateProcess;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmOperatorType() {
        return mOperatorType;
    }

    public void setmOperatorType(String mOperatorType) {
        this.mOperatorType = mOperatorType;
    }
}
