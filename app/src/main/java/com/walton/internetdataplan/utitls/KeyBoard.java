package com.walton.internetdataplan.utitls;

import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Faruq on 12/15/2016.
 */

public class KeyBoard {
    public static void showKeyBoard(Activity mActivity)
    {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
    public static void hideKeyBoard(Activity mActivity)
    {
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = mActivity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(mActivity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
