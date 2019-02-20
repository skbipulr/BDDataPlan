package com.walton.internetdataplan.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Button;

import com.walton.internetdataplan.R;
import com.walton.internetdataplan.utitls.AppsSettings;

import java.util.Locale;

/**
 * Created by Faruq on 1/4/2017.
 */

public class SplashActivity extends Activity {
    Context mContext;
    public Button mOK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
        {
            createAlert();
        }
        else if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("bn"))
        {
            createAlert_bn();
        }
        else
        {
            if (Locale.getDefault().getDisplayLanguage().contains("Ban") || Locale.getDefault().getDisplayLanguage().contains("Bn") || Locale.getDefault().getDisplayLanguage().contains("bn") || Locale.getDefault().getDisplayLanguage().contains("বাংলা")) {
                AppsSettings.getAppsSettings(mContext).setLanguage("bn");
                createAlert_bn();
            } else {
                AppsSettings.getAppsSettings(mContext).setLanguage("eng");
                createAlert();
            }
        }



    }

    private void createAlert() {
            AlertDialog.Builder adb = new AlertDialog.Builder(mContext);
            adb.setIcon(R.drawable.attention_100);
            adb.setTitle(""+getResources().getString(R.string.app_name));
            adb.setMessage(getResources().getString(R.string.first_time_alert));
            adb.setCancelable(false);
            adb.setPositiveButton(""+getResources().getString(R.string.btn_ok), new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    dialogInterface.dismiss();
                    finish();
                }
            });
            adb.create();
            adb.show();

    }
    private void createAlert_bn()
    {
        AlertDialog.Builder adb = new AlertDialog.Builder(mContext);
        adb.setIcon(R.drawable.attention_100);
        adb.setTitle(""+getResources().getString(R.string.app_name_bn));
        adb.setMessage(getResources().getString(R.string.first_time_alert_bn));
        adb.setCancelable(false);
        adb.setPositiveButton(""+getResources().getString(R.string.btn_ok_bn), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                dialogInterface.dismiss();
                finish();
            }
        });
        adb.create();
        adb.show();
    }

}

