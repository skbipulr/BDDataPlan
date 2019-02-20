package com.walton.internetdataplan.utitls;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.walton.internetdataplan.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// File Name: AppSettingSingleton.java
public class WHelper {

    private Context mContext = null;
    private static WHelper appSettingSingleton = null;
    private SharedPreferences prefs = null;

    /*
     * A private Constructor prevents any other class from instantiating.
     */
    private WHelper(Context _mContext) {
        this.mContext = _mContext;
        prefs = PreferenceManager.getDefaultSharedPreferences(this.mContext);
    }

    /* Static 'instance' method */
    public static WHelper getInstance(Context _mContext) {
        return (appSettingSingleton == null ? appSettingSingleton = new WHelper(
                _mContext) : appSettingSingleton);
    }

    public void DialingCode(String callingNo) {
        String encodedHash = Uri.encode("#");
        callingNo = callingNo.replaceAll("#", encodedHash);

        Intent dial = new Intent();
        dial.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        dial.setAction("android.intent.action.DIAL");
        dial.setData(Uri.parse("tel:" + callingNo));
        mContext.startActivity(dial);
    }


		/*
     * constants
	 */

    /*
     * SMS based purchasing alert dialog
     */
    public static void showMsgDialogForGP(final Context context, String mTitle, final String mSMSBody, final String mSMSCode) {
        // custom dialog
        final Dialog dialog = new Dialog(context);

        // use the following line before setContentView() to remove the
        // title of dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.gp_confirm_dialog);




        // dynamically changes title text of confirmation dialog
        TextView tv_title = (TextView) dialog.findViewById(R.id.tv_title);

            tv_title.setText("" + mTitle);

        // Initialising edittext
        EditText et_phone_numner = (EditText) dialog
                .findViewById(R.id.et_phone_numner);
        EditText editTextSMS = (EditText) dialog.findViewById(R.id.editTextSMS);

        editTextSMS.setText("" + mSMSBody);

        Button btn_send_sms = (Button) dialog.findViewById(R.id.btn_send2);
        if(AppsSettings.getAppsSettings(context).getLanguage().equals("eng"))
        {
            btn_send_sms.setText(""+context.getResources().getString(R.string.sent_message));
        }
        else
        {
            btn_send_sms.setText(""+context.getResources().getString(R.string.sent_message_bn));
        }

        // setting values to edittext
        et_phone_numner.setText("" + mSMSCode);

        // if button is clicked, close the custom dialog
        btn_send_sms.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"
                        + mSMSCode));
                intent.putExtra("sms_body", mSMSBody);
                context.startActivity(intent);

                dialog.dismiss();
            }
        });
        dialog.show();
    }









    /*
     * constants
	 */

    /*
     * SMS based purchasing alert dialog
     */
    public static void showMsgDialogForTeletalk(final Context context, String mTitle, final String mSMSBody, final String mSMSCode) {
        // custom dialog
        final Dialog dialog = new Dialog(context);

        // use the following line before setContentView() to remove the
        // title of dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.teletalk_confirm_dialog);




        // dynamically changes title text of confirmation dialog
        TextView tv_title = (TextView) dialog.findViewById(R.id.tv_title);

        tv_title.setText("" + mTitle);

        // Initialising edittext
        EditText et_phone_numner = (EditText) dialog
                .findViewById(R.id.et_phone_numner);
        EditText editTextSMS = (EditText) dialog.findViewById(R.id.editTextSMS);

        editTextSMS.setText("" + mSMSBody);

        Button btn_send_sms = (Button) dialog.findViewById(R.id.btn_send2);
        if(AppsSettings.getAppsSettings(context).getLanguage().equals("eng"))
        {
            btn_send_sms.setText(""+context.getResources().getString(R.string.sent_message));
        }
        else
        {
            btn_send_sms.setText(""+context.getResources().getString(R.string.sent_message_bn));
        }

        // setting values to edittext
        et_phone_numner.setText("" + mSMSCode);

        // if button is clicked, close the custom dialog
        btn_send_sms.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"
                        + mSMSCode));
                intent.putExtra("sms_body", mSMSBody);
                context.startActivity(intent);

                dialog.dismiss();
            }
        });
        dialog.show();
    }




















	/*
     * constants
	 */

    /*
     * SMS based purchasing alert dialog
     */
    public static void showMsgDialogForAirtel(final Context context, String mTitle, final String mSMSBody, final String mSMSCode) {
        // custom dialog
        final Dialog dialog = new Dialog(context);

        // use the following line before setContentView() to remove the
        // title of dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.airtel_confirm_dialog);




        // dynamically changes title text of confirmation dialog
        TextView tv_title = (TextView) dialog.findViewById(R.id.tv_title);

        tv_title.setText("" + mTitle);

        // Initialising edittext
        EditText et_phone_numner = (EditText) dialog
                .findViewById(R.id.et_phone_numner);
        EditText editTextSMS = (EditText) dialog.findViewById(R.id.editTextSMS);

        editTextSMS.setText("" + mSMSBody);

        Button btn_send_sms = (Button) dialog.findViewById(R.id.btn_send2);
        if(AppsSettings.getAppsSettings(context).getLanguage().equals("eng"))
        {
            btn_send_sms.setText(""+context.getResources().getString(R.string.sent_message));
        }
        else
        {
            btn_send_sms.setText(""+context.getResources().getString(R.string.sent_message_bn));
        }

        // setting values to edittext
        et_phone_numner.setText("" + mSMSCode);

        // if button is clicked, close the custom dialog
        btn_send_sms.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"
                        + mSMSCode));
                intent.putExtra("sms_body", mSMSBody);
                context.startActivity(intent);

                dialog.dismiss();
            }
        });
        dialog.show();
    }

    	/*
     * constants
	 */

    /*
     * SMS based purchasing alert dialog
     */
    public static void showMsgDialogForBL(final Context context, String mTitle, final String mSMSBody, final String mSMSCode) {
        // custom dialog
        final Dialog dialog = new Dialog(context);

        // use the following line before setContentView() to remove the
        // title of dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bl_confirm_dialog);




        // dynamically changes title text of confirmation dialog
        TextView tv_title = (TextView) dialog.findViewById(R.id.tv_title);

        tv_title.setText("" + mTitle);

        // Initialising edittext
        EditText et_phone_numner = (EditText) dialog
                .findViewById(R.id.et_phone_numner);
        EditText editTextSMS = (EditText) dialog.findViewById(R.id.editTextSMS);

        editTextSMS.setText("" + mSMSBody);

        Button btn_send_sms = (Button) dialog.findViewById(R.id.btn_send2);
        if(AppsSettings.getAppsSettings(context).getLanguage().equals("eng"))
        {
            btn_send_sms.setText(""+context.getResources().getString(R.string.sent_message));
        }
        else
        {
            btn_send_sms.setText(""+context.getResources().getString(R.string.sent_message_bn));
        }

        // setting values to edittext
        et_phone_numner.setText("" + mSMSCode);

        // if button is clicked, close the custom dialog
        btn_send_sms.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"
                        + mSMSCode));
                intent.putExtra("sms_body", mSMSBody);
                context.startActivity(intent);

                dialog.dismiss();
            }
        });
        dialog.show();
    }

	/*
     * constants
	 */

    /*
     * only show alert with one button
     */
    public static void onlyShowAlertWithOneBtn(final Context context, String mOperatorName, String mPackage, final String mSMSBody) {
        // custom dialog
        final Dialog dialog = new Dialog(context);

         AlertDialog.Builder adb=new AlertDialog.Builder(context);
        adb.setTitle(mOperatorName+" : "+mPackage);
        adb.setMessage(""+mSMSBody);
        adb.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        adb.create();
        adb.show();
    }






    	/*
     * constants
	 */

    /*
     * SMS based purchasing alert dialog
     */
    public static void showMsgDialogForRobiExtra(final Context context, String mTitle, final String mSMSBody, final String mSMSCode) {
        // custom dialog
        final Dialog dialog = new Dialog(context);

        // use the following line before setContentView() to remove the
        // title of dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.robi_extra_confirm_dialog);



        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = LayoutParams.MATCH_PARENT;

        dialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);








        // dynamically changes title text of confirmation dialog
        TextView tv_title = (TextView) dialog.findViewById(R.id.tv_title);

        tv_title.setText("" + mTitle);

        // Initialising edittext
        EditText et_phone_numner = (EditText) dialog
                .findViewById(R.id.et_phone_numner);
        EditText editTextSMS = (EditText) dialog.findViewById(R.id.editTextSMS);

        editTextSMS.setText("" + mSMSBody);

        Button btn_send_sms = (Button) dialog.findViewById(R.id.btn_send2);

        // setting values to edittext
        et_phone_numner.setText("" + mSMSCode);

        // if button is clicked, close the custom dialog
        btn_send_sms.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"
                        + mSMSCode));
                intent.putExtra("sms_body", mSMSBody);
                context.startActivity(intent);

                dialog.dismiss();
            }
        });
        dialog.show();
    }











































    	/*
     * constants
	 */

    /*
     * SMS based purchasing alert dialog
     */
    public static void showMsgDialogForRobi(final Context context, String mTitle, final String mSMSBody, final String mSMSCode) {
        // custom dialog
        final Dialog dialog = new Dialog(context);

        // use the following line before setContentView() to remove the
        // title of dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.robi_confirm_dialog);




        // dynamically changes title text of confirmation dialog
        TextView tv_title = (TextView) dialog.findViewById(R.id.tv_title);

        tv_title.setText("" + mTitle);

        // Initialising edittext
        EditText et_phone_numner = (EditText) dialog
                .findViewById(R.id.et_phone_numner);
        EditText editTextSMS = (EditText) dialog.findViewById(R.id.editTextSMS);

        editTextSMS.setText("" + mSMSBody);

        Button btn_send_sms = (Button) dialog.findViewById(R.id.btn_send2);
        if(AppsSettings.getAppsSettings(context).getLanguage().equals("eng"))
        {
            btn_send_sms.setText(""+context.getResources().getString(R.string.sent_message));
        }
        else
        {
            btn_send_sms.setText(""+context.getResources().getString(R.string.sent_message_bn));
        }

        // setting values to edittext
        et_phone_numner.setText("" + mSMSCode);

        // if button is clicked, close the custom dialog
        btn_send_sms.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"
                        + mSMSCode));
                intent.putExtra("sms_body", mSMSBody);
                context.startActivity(intent);

                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void createAlertin3(String mTitle, String mSMSBody, String mSMSCode) {
        String smsFormate;
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);
        // set view
        View customView = LayoutInflater.from(mContext).inflate(R.layout.custom_alert, null);

        TextView packg = (TextView) customView.findViewById(R.id.packg);
        packg.setText("Package: " + mTitle);
        TextView smsforAutoRenew = (TextView) customView.findViewById(R.id.smsforAutoRenew);
        smsFormate = "SMS Keyword to " + mSMSCode + " with " + "\"" + mSMSBody + "\"";
        smsforAutoRenew.setText(smsFormate);
        alertDialogBuilder.setView(customView);

        // create alert dialog
        final AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
        Button button = (Button) customView.findViewById(R.id.btnAction);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });
    }

    public void createAlertin2(String mTitle, String mDetails) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);
        // set view
        View customView = LayoutInflater.from(mContext).inflate(R.layout.custom_alert, null);

        TextView packg = (TextView) customView.findViewById(R.id.packg);
        packg.setText(mTitle);
        TextView smsforAutoRenew = (TextView) customView.findViewById(R.id.smsforAutoRenew);
        smsforAutoRenew.setText(mDetails);
        alertDialogBuilder.setView(customView);

        // create alert dialog
        final AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
        Button button = (Button) customView.findViewById(R.id.btnAction);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });
    }
    // return date true or false
    public boolean compare2Dates(String date1, String date2)
    {
        boolean mYes=false;
        try {
            Date start = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                    .parse(date1);
            Date end = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                    .parse(date2);

            System.out.println(start);
            System.out.println(end);

            if (start.compareTo(end) <= 0) {
             mYes=true;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mYes;
    }

    public void share()
    {
        try {
            String shareBody = "https://play.google.com/store/apps/details?id=com.walton.internetdataplan";

            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "BD DATA PLAN (Open it in Google Play Store to Download the Application)");

            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            sharingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }
        catch (Exception e)
        {

        }


    }

}
