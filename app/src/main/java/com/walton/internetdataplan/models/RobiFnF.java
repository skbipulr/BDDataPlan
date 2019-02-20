package com.walton.internetdataplan.models;

/**
 * Created by Faruq on 12/10/2016.
 */

public class RobiFnF {
    String mTitle;
    String mTitleForBtn;
    String mCode;
    String mBody;

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmTitleForBtn() {
        return mTitleForBtn;
    }

    public void setmTitleForBtn(String mTitleForBtn) {
        this.mTitleForBtn = mTitleForBtn;
    }

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public String getmBody() {
        return mBody;
    }

    public void setmBody(String mBody) {
        this.mBody = mBody;
    }

    public RobiFnF(String mTitle, String mTitleForBtn, String mCode, String mBody) {
        this.mTitle = mTitle;
        this.mTitleForBtn = mTitleForBtn;
        this.mCode = mCode;
        this.mBody = mBody;
    }
}
