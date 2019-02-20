package com.walton.internetdataplan.models;

/**
 * Created by Faruq on 10/24/2016.
 */

public class TeletalkModel {


    public int mID;
    public String mPackageName;
    public String mVolume;
    public String mPrice;
    public String mValidity;
    public String mDirectDialingCode;
    public String mSmsBodyForPrepaid;
    public String mSmsBodyForPost;
    public String mSMSCode;
    public String mProcess12;
    public String mDetailsActivateProcess;
    public String mDescription;

    public TeletalkModel() {
    }

    public TeletalkModel(int mID, String mDescription, String mPackageName, String mVolume, String mPrice, String mValidity, String mDirectDialingCode, String mSmsBodyForPrepaid, String mSmsBodyForPost, String mSMSCode, String mProcess12, String mDetailsActivateProcess) {
        this.mID = mID;
        this.mDescription = mDescription;
        this.mPackageName = mPackageName;
        this.mVolume = mVolume;
        this.mPrice = mPrice;
        this.mValidity = mValidity;
        this.mDirectDialingCode = mDirectDialingCode;
        this.mSmsBodyForPrepaid = mSmsBodyForPrepaid;
        this.mSmsBodyForPost = mSmsBodyForPost;
        this.mSMSCode = mSMSCode;
        this.mProcess12 = mProcess12;
        this.mDetailsActivateProcess = mDetailsActivateProcess;
    }

    public TeletalkModel(String mPackageName, String mVolume, String mPrice, String mValidity, String mDirectDialingCode, String mSmsBodyForPrepaid, String mSmsBodyForPost, String mSMSCode, String mProcess12, String mDetailsActivateProcess, String mDescription) {
        this.mPackageName = mPackageName;
        this.mVolume = mVolume;
        this.mPrice = mPrice;
        this.mValidity = mValidity;
        this.mDirectDialingCode = mDirectDialingCode;
        this.mSmsBodyForPrepaid = mSmsBodyForPrepaid;
        this.mSmsBodyForPost = mSmsBodyForPost;
        this.mSMSCode = mSMSCode;
        this.mProcess12 = mProcess12;
        this.mDetailsActivateProcess = mDetailsActivateProcess;
        this.mDescription = mDescription;
    }

    public TeletalkModel(int mID, String mPackageName, String mVolume, String mPrice, String mValidity, String mDirectDialingCode, String mSmsBodyForPrepaid, String mSmsBodyForPost, String mSMSCode) {
        this.mID = mID;
        this.mPackageName = mPackageName;
        this.mVolume = mVolume;
        this.mPrice = mPrice;
        this.mValidity = mValidity;
        this.mDirectDialingCode = mDirectDialingCode;
        this.mSmsBodyForPrepaid = mSmsBodyForPrepaid;
        this.mSmsBodyForPost = mSmsBodyForPost;
        this.mSMSCode = mSMSCode;
    }

    public TeletalkModel(String mPackageName, String mVolume, String mPrice, String mValidity, String mDirectDialingCode, String mSmsBodyForPrepaid, String mSmsBodyForPost, String mSMSCode) {
        this.mPackageName = mPackageName;
        this.mVolume = mVolume;
        this.mPrice = mPrice;
        this.mValidity = mValidity;
        this.mDirectDialingCode = mDirectDialingCode;
        this.mSmsBodyForPrepaid = mSmsBodyForPrepaid;
        this.mSmsBodyForPost = mSmsBodyForPost;
        this.mSMSCode = mSMSCode;
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

    public String getmVolume() {
        return mVolume;
    }

    public void setmVolume(String mVolume) {
        this.mVolume = mVolume;
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

    public String getmSmsBodyForPrepaid() {
        return mSmsBodyForPrepaid;
    }

    public void setmSmsBodyForPrepaid(String mSmsBodyForPrepaid) {
        this.mSmsBodyForPrepaid = mSmsBodyForPrepaid;
    }

    public String getmSmsBodyForPost() {
        return mSmsBodyForPost;
    }

    public void setmSmsBodyForPost(String mSmsBodyForPost) {
        this.mSmsBodyForPost = mSmsBodyForPost;
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
}
