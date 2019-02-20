package com.walton.internetdataplan.models;

/**
 * Created by Faruq on 10/24/2016.
 */

public class RobiModel {
    public int mID;
    public String mPackageName;
    public String mPrice;
    public String mValidity;
    public String mAutoRenew;
    public String mDirectDialingCode;
    public String mProcess12;
    public String mDetailsActivateProcess;
    public String mDescription;
    public String mOperatorType;


    /**
     * End of  TABLE TELETALK (Prepaid & PostPaid) SCHEMA
     */

    public RobiModel(int mID, String mPackageName, String mPrice, String mValidity, String mAutoRenew, String mDirectDialingCode, String mProcess12, String mDetailsActivateProcess, String mDescription, String mOperatorType) {
        this.mID = mID;
        this.mPackageName = mPackageName;
        this.mPrice = mPrice;
        this.mValidity = mValidity;
        this.mAutoRenew = mAutoRenew;
        this.mDirectDialingCode = mDirectDialingCode;
        this.mProcess12 = mProcess12;
        this.mDetailsActivateProcess = mDetailsActivateProcess;
        this.mDescription = mDescription;
        this.mOperatorType = mOperatorType;
    }

    public RobiModel(String mPackageName, String mPrice, String mValidity, String mAutoRenew, String mDirectDialingCode, String mProcess12, String mDetailsActivateProcess, String mDescription, String mOperatorType) {
        this.mPackageName = mPackageName;
        this.mPrice = mPrice;
        this.mValidity = mValidity;
        this.mAutoRenew = mAutoRenew;
        this.mDirectDialingCode = mDirectDialingCode;
        this.mProcess12 = mProcess12;
        this.mDetailsActivateProcess = mDetailsActivateProcess;
        this.mDescription = mDescription;
        this.mOperatorType = mOperatorType;
    }

    public RobiModel(int mID, String mPackageName, String mPrice, String mValidity, String mAutoRenew, String mDirectDialingCode) {
        this.mID = mID;
        this.mPackageName = mPackageName;
        this.mPrice = mPrice;
        this.mValidity = mValidity;
        this.mAutoRenew = mAutoRenew;
        this.mDirectDialingCode = mDirectDialingCode;
    }

    public RobiModel(String mPackageName, String mPrice, String mValidity, String mAutoRenew, String mDirectDialingCode) {
        this.mPackageName = mPackageName;
        this.mPrice = mPrice;
        this.mValidity = mValidity;
        this.mAutoRenew = mAutoRenew;
        this.mDirectDialingCode = mDirectDialingCode;
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

    public String getmAutoRenew() {
        return mAutoRenew;
    }

    public void setmAutoRenew(String mAutoRenew) {
        this.mAutoRenew = mAutoRenew;
    }

    public String getmDirectDialingCode() {
        return mDirectDialingCode;
    }

    public void setmDirectDialingCode(String mDirectDialingCode) {
        this.mDirectDialingCode = mDirectDialingCode;
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
