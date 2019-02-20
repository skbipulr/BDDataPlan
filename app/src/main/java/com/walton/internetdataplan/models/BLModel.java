package com.walton.internetdataplan.models;

/**
 * Created by Faruq on 10/24/2016.
 */

public class BLModel {


    public BLModel(int mID, String mOperatorType, String mPackageName, String mValidity, String mPrice, String mDirectDialingCodeWith, String mDirectDialingCodeWithout, String mSmsBody, String mSMSCode, String mProcess12, String mDetailsActivateProcess, String mDescription) {
        this.mID = mID;
        this.mOperatorType = mOperatorType;
        this.mPackageName = mPackageName;

        this.mValidity = mValidity;
        this.mPrice = mPrice;
        this.mDirectDialingCodeWith = mDirectDialingCodeWith;
        this.mDirectDialingCodeWithout = mDirectDialingCodeWithout;
        this.mSmsBody = mSmsBody;
        this.mSMSCode = mSMSCode;
        this.mProcess12 = mProcess12;
        this.mDetailsActivateProcess = mDetailsActivateProcess;
        this.mDescription = mDescription;
    }
    public BLModel(String mPackage, String mValidity, String mPrice, String mDialingCodeWith, String mDialingCodeWithout,String mSmsBody,String mSMSCode, String mProcess12, String mDetailsActivateProcess, String mDescription, String mOperatorType) {
        this.mSMSCode = mSMSCode;
        this.mPackageName = mPackage;
        this.mPrice = mPrice;
        this.mValidity = mValidity;
        this.mDirectDialingCodeWith = mDialingCodeWith;
        this.mDirectDialingCodeWithout = mDialingCodeWithout;
        this.mSmsBody = mSmsBody;
        this.mProcess12 = mProcess12;
        this.mDetailsActivateProcess = mDetailsActivateProcess;
        this.mDescription = mDescription;
        this.mOperatorType = mOperatorType;
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

    public String getmDirectDialingCodeWith() {
        return mDirectDialingCodeWith;
    }

    public void setmDirectDialingCodeWith(String mDirectDialingCodeWith) {
        this.mDirectDialingCodeWith = mDirectDialingCodeWith;
    }

    public String getmDirectDialingCodeWithout() {
        return mDirectDialingCodeWithout;
    }

    public void setmDirectDialingCodeWithout(String mDirectDialingCodeWithout) {
        this.mDirectDialingCodeWithout = mDirectDialingCodeWithout;
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

    public int mID;
    public String mPackageName;

    public String mValidity;
    public String mPrice;

    public BLModel(int mID, String mPackageName, String mValidity, String mPrice, String mDirectDialingCodeWith, String mDirectDialingCodeWithout, String mSmsBody, String mSMSCode) {
        this.mID = mID;
        this.mPackageName = mPackageName;
        this.mValidity = mValidity;
        this.mPrice = mPrice;
        this.mDirectDialingCodeWith = mDirectDialingCodeWith;
        this.mDirectDialingCodeWithout = mDirectDialingCodeWithout;
        this.mSmsBody = mSmsBody;
        this.mSMSCode = mSMSCode;
    }

    public BLModel(String mPackageName, String mValidity, String mPrice, String mDirectDialingCodeWith, String mDirectDialingCodeWithout, String mSmsBody, String mSMSCode) {
        this.mPackageName = mPackageName;
        this.mValidity = mValidity;
        this.mPrice = mPrice;
        this.mDirectDialingCodeWith = mDirectDialingCodeWith;
        this.mDirectDialingCodeWithout = mDirectDialingCodeWithout;
        this.mSmsBody = mSmsBody;
        this.mSMSCode = mSMSCode;
    }

    public String mDirectDialingCodeWith;
    public String mDirectDialingCodeWithout;
    public String mSmsBody;
    public String mSMSCode;
    public String mProcess12;
    public String mDetailsActivateProcess;
    public String mDescription;
    public String mOperatorType;

    public BLModel() {
    }


}
