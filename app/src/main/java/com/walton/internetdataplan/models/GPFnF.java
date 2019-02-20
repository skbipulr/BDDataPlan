package com.walton.internetdataplan.models;

/**
 * Created by Faruq on 12/10/2016.
 */

public class GPFnF {
    String mTitle;
    String mTitleForBtn;
    String mDialCode;

    public GPFnF(String mTitle, String mTitleForBtn, String mDialCode) {
        this.mTitle = mTitle;
        this.mTitleForBtn = mTitleForBtn;
        this.mDialCode = mDialCode;
    }

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

    public String getmDialCode() {
        return mDialCode;
    }

    public void setmDialCode(String mDialCode) {
        this.mDialCode = mDialCode;
    }
}
