package com.walton.internetdataplan.models;

/**
 * Created by Faruq on 12/10/2016.
 */

public class GPMusic {
    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDialCode() {
        return mDialCode;
    }

    public void setmDialCode(String mDialCode) {
        this.mDialCode = mDialCode;
    }

    public String getmDialForStart() {
        return mDialForStart;
    }

    public void setmDialForStart(String mDialForStart) {
        this.mDialForStart = mDialForStart;
    }

    public String getmDialForStop() {
        return mDialForStop;
    }

    public void setmDialForStop(String mDialForStop) {
        this.mDialForStop = mDialForStop;
    }

    public String getmSentCode() {
        return mSentCode;
    }

    public void setmSentCode(String mSentCode) {
        this.mSentCode = mSentCode;
    }

    public String getmCharge() {
        return mCharge;
    }

    public void setmCharge(String mCharge) {
        this.mCharge = mCharge;
    }

    public GPMusic(String mTitle, String mDialCode, String mDialForStart, String mDialForStop, String mSentCode, String mCharge) {
        this.mTitle = mTitle;
        this.mDialCode = mDialCode;
        this.mDialForStart = mDialForStart;
        this.mDialForStop = mDialForStop;
        this.mSentCode = mSentCode;
        this.mCharge = mCharge;
    }

    public String mTitle;
    public String mDialCode;
    public String mDialForStart;
    public String mDialForStop;
    public String mSentCode;
    public String mCharge;
}
