package com.walton.internetdataplan.utitls;

import android.content.Context;

import com.walton.internetdataplan.AppManager;


public class AddInfo {
    public static Context mContext;

    public AddInfo(Context mContext) {
        this.mContext = mContext;
    }


    // insert Miscellaneous info
    public static void addMiscellanceousInfo() {
        // Context _mContext, String mOperator, String mTitle, String mDialingCode, String mSMSBody, String mSMSCode, String mDetails

        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Missed Call Alert START ", "", "START MCA", "6222", "Tariff:\n" +
                "\n" +
                "The service is available for All Grameenphone Subscribers.\n" +
                "Monthly Fee is BDT 10.0 (+ SD +VAT+SC -Excluding Business Solutions Postpaid).\n" +
                "SMS charges is free for subscribe/unsubscribe.\n" +
                "\n" +
                "* The service will not work if Voice Mail Service is activated.");

        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Missed Call Alert STOP ", "", "STOP MCA", "6222", "Tariff:\n" +
                "\n" +
                "The service is available for All Grameenphone Subscribers.\n" +
                "Monthly Fee is BDT 10.0 (+ SD +VAT+SC -Excluding Business Solutions Postpaid).\n" +
                "SMS charges is free for subscribe/unsubscribe.\n" +
                "\n" +
                "* The service will not work if Voice Mail Service is activated.");
        // added
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, "GP", "Call Block Service START ", " *121*6*4*1#", "Start CB", "5678", "Any GP subscriber can subscribe to the Call Block service.");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Call Block Service STOP ", "*121*6*4*4#", "Stop CB", "5678", "Any GP subscriber can subscribe to the Call Block service.");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Emergency Balance Check", "*1010*1#", "", "", "To avail the Emergency Balance(Free of Charge)");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Own Number Check ", "*2#", "", "", "Check your own number)");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Balance Inquiry", "*556#", "", "", "Check your Balance)");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Remaining internet balance", "*500*60#", "", "", "Check your Remaining internet balance)");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Internet balance (for *PAYG)", "*500*61#", "", "", "Check your Internet balance (for *PAYG))");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Internet balance (Except *PAYG)", "*500*60#", "", "", "Check your Internet balance (Except *PAYG)");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "FnF List", "*121*1*5*5#", "FF", "2888", "Check your FNF Number list");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Add FnF", "*121*1*5*1#", "", "", "Add your FNF number");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Add Super FnF", "*121*1*5*2#", "", "", "Add your Super FnF");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Add Super FnF", "*121*1*5*2#", "", "", "Add your Super FnF");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Delete FnF/Super FnF", "*121*1*5*3#", "", "", "Delete FnF/Super FnF");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Delete FnF/Super FnF", "*121*1*5*3#", "", "", "Delete FnF/Super FnF");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "FnF Service Menu", "121#", "", "", "Customers can dial *121# to add, delete, view and Change the FnF and Super FnF number. This is applicable for those price plans that have FnF or Super FnF like: Bondhu, Amontron, Xplore, djuice, Business Solution and Ekota (1& 3)");

        //added
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Trial Plan (30 Days) Start", "*7728*1*1#", "START T", "7728", "* Grameenphone Standard Data Charge is applicable with all the Plans. All subscription Plans are including additional SD, VAT and SC. ");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Trial Plan (30 Days) Stop", "", "STOP T", "7728", "* Grameenphone Standard Data Charge is applicable with all the Plans. All subscription Plans are including additional SD, VAT and SC. ");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Daily Plan (1 Day) at BDT 2.39 Start", "*7728*2*1#", "START D", "7728", "* Grameenphone Standard Data Charge is applicable with all the Plans. All subscription Plans are including additional SD, VAT and SC. ");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Daily Plan (1 Day) at BDT 2.39 Stop", "", "STOP D", "7728", "* Grameenphone Standard Data Charge is applicable with all the Plans. All subscription Plans are including additional SD, VAT and SC. ");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Weekly Plan (7 Days) at BDT 14.61 Start", "dial *7728*3*1#", "START W", "7728", "* Grameenphone Standard Data Charge is applicable with all the Plans. All subscription Plans are including additional SD, VAT and SC. ");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Weekly Plan (7 Days) at BDT 14.61 Stop", "", "STOP W", "7728", "* Grameenphone Standard Data Charge is applicable with all the Plans. All subscription Plans are including additional SD, VAT and SC. ");

        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Bi-weekly Plan (15 Days) at BDT 24.35 Start", "dial *7728*4*1#", "START F", "7728", "* Grameenphone Standard Data Charge is applicable with all the Plans. All subscription Plans are including additional SD, VAT and SC. ");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Bi-weekly Plan (15 Days) at BDT 24.35 Stop", "", "STOP F", "7728", "* Grameenphone Standard Data Charge is applicable with all the Plans. All subscription Plans are including additional SD, VAT and SC. ");


        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Monthly Plan (30 Days) at BDT 42.61 Start", "dial *7728*5*1#", "START M", "7728", "* Grameenphone Standard Data Charge is applicable with all the Plans. All subscription Plans are including additional SD, VAT and SC. ");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Monthly Plan (30 Days) at BDT 42.61 Stop", "", "STOP M", "7728", "* Grameenphone Standard Data Charge is applicable with all the Plans. All subscription Plans are including additional SD, VAT and SC. ");

        // common service
       /* AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Check IMEI ", "*#06#", "", "", "Check your IMEI)");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_Airtel, "Check IMEI ", "*#06#", "", "", "Check your IMEI)");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_ROBI, "Check IMEI ", "*#06#", "", "", "Check your IMEI)");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_TELETALK, "Check IMEI ", "*#06#", "", "", "Check your IMEI)");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_BanglalinkPrepaid, "Check IMEI ", "*#06#", "", "", "Check your IMEI)");

*/
        //added
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_GP, "Check IMEI ", "*#06#", "", "", "Check your IMEI)");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_Airtel, "Check IMEI ", "*#06#", "", "", "Check your IMEI)");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_ROBI, "Check IMEI ", "*#06#", "", "", "Check your IMEI)");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_TELETALK, "Check IMEI ", "*#06#", "", "", "Check your IMEI)");
        AppManager.getInstance(mContext).addMiscellaneousInfo(mContext, AppConstants.TABLE_BanglalinkPrepaid, "Check IMEI ", "*#06#", "", "", "Check your IMEI)");

    }

    // insert gp info
    //	becouse  prepaid and postpaid are save data
    public static void addGPDataPackInfo() {

// 15 packages
        // Context _mContext, String mPackage, String mPrice, String mValidity, String mDialingCode, String mAutosmsBody,String mAutosmsCode,String mProcess12,String mActivateProcess,String mDescription,String mOperatorType
       /* AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "Pay As You Go", "0.97", "Per MB", "*121*3000#", "PAYG", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "2MB", "1.5", "2", "*121*3001#", "2mb on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "4MB", "2.5", "2", "*121*3002#", "4mb on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "35MB", "18", "7", "*121*3003#", "35mb on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "60MB", "22", "3", "*121*3029#", "60mb on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "75MB", "37", "7", "*121*3004#", "75mb on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "100MB", "56", "30", "*121*3005#", "100mb on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "250MB", "119", "28", "*121*3006#", "250mb on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "500MB", "149", "28", "*121*3007#", "500mb on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "1.5GB", "337", "28", "*121*3009#", "1536MB on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "2.5GB", "427", "28", "*121*3010#", "2560MB on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "4GB", "609", "28", "*121*3012#", "4gb on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "8GB", "1157", "28", "*121*3013#", "u on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "12GB", "1522", "28", "*121*3014#", "12gb on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "20GB", "2436", "30", "*121*3015#", "20gb on", "5000", "", "", "", "both");

    }*/

        //added
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "Pay As You Go", "0.97", "Per MB", "*121*3000#", "PAYG", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "2MB", "1.5", "2", "*121*3001#", "2mb on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "4MB", "2.5", "2", "*121*3002#", "4mb on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "35MB", "18", "7", "*121*3003#", "35mb on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "60MB", "22", "3", "*121*3029#", "60mb on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "75MB", "37", "7", "*121*3004#", "75mb on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "100MB", "56", "30", "*121*3005#", "100mb on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "250MB", "119", "28", "*121*3006#", "250mb on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "500MB", "149", "28", "*121*3007#", "500mb on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "1.5GB", "337", "28", "*121*3009#", "1536MB on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "2.5GB", "427", "28", "*121*3010#", "2560MB on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "4GB", "609", "28", "*121*3012#", "4gb on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "8GB", "1157", "28", "*121*3013#", "u on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "12GB", "1522", "28", "*121*3014#", "12gb on", "5000", "", "", "", "both");
        AppManager.getInstance(mContext).addGPDataFullPackInfo(mContext, "20GB", "2436", "30", "*121*3015#", "20gb on", "5000", "", "", "", "both");

    }
    // insert Airtel info

    public static void addAirtelDataPackInfo() {

// 15 packages
        // Context _mContext, String mPackage,  String mValidity, String mDialingCode, String mPrice,String mAutosmsBody,String mAutosmsCode,String mProcess12,String mActivateProcess,String mDescription,String mOperatorType
      /*  AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "30MB", "2", "*121*781#", "10", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "45MB", "1", "*121*782#", "10", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "100MB", "2", "*121*640#", "15", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "60MB", "3", "*121*5001#", "15", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "100MB", "7", "*121*771#", "20", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "200MB", "3", "*121*250#", "25", "", "", "", "", "", "both");


        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "30MB", "7", "*121*5002#", "30", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "250MB", "7", "*121*5003#", "50", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "300MB", "5", "*121*350#", "35", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "1GB", "5", "*121*981#", "82", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "515MB", "7", "*121*5011#", "100", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "1GB", "15", "*121*151#", "127", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "1GB", "28", "*121*5014#", "199", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "1.5GB", "28", "*121*731#", "275", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "2.5GB", "28", "*121*5020#", "350", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "3GB", "28", "*121*5025#", "450", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "5GB", "28", "*121*711#", "650", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "8GB", "28", "*121*5040#", "950", "", "", "", "", "", "both");
*/
        //added
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "30MB", "2", "*121*781#", "10", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "45MB", "1", "*121*782#", "10", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "100MB", "2", "*121*640#", "15", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "60MB", "3", "*121*5001#", "15", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "100MB", "7", "*121*771#", "20", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "200MB", "3", "*121*250#", "25", "", "", "", "", "", "both");


        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "30MB", "7", "*121*5002#", "30", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "250MB", "7", "*121*5003#", "50", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "300MB", "5", "*121*350#", "35", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "1GB", "5", "*121*981#", "82", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "515MB", "7", "*121*5011#", "100", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "1GB", "15", "*121*151#", "127", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "1GB", "28", "*121*5014#", "199", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "1.5GB", "28", "*121*731#", "275", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "2.5GB", "28", "*121*5020#", "350", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "3GB", "28", "*121*5025#", "450", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "5GB", "28", "*121*711#", "650", "", "", "", "", "", "both");
        AppManager.getInstance(mContext).addAirtelDataFullPackInfo(mContext, "8GB", "28", "*121*5040#", "950", "", "", "", "", "", "both");

    }

    // insert BL Prepaid info
    public static void addBLPrepaidDataPackInfo() {

// 22 packages
        //Context _mContext, String mPackage,  String mValidity,String mPrice, String mDialingCodeWith,String mDialingCodeWithout,
        // String mAutosmsBody,String mAutosmsCode,String mProcess12,String mActivateProcess,String mDescription,String mOperatorType
        // Standard Volume Packs
        // http://www.banglalink.com.bd/en/personal/internet?shs_term_node_tid_depth=146&packagevaliditytype=8&price%255Bmin%255D=0&price%255Bmax%255D=2000&validity%255Bmin%255D=0&validity%255Bmax%255D=30
       /* AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "25GB", "30", "1,799", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "5GB", "30", "499", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "2GB+1GB Night", "30", "349", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "1GB", "30", "209", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "300MB+200MB Social", "30", "119", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "175MB", "7", "36", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "100MB", "7", "26", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "50MB", "1", "13", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "9MB", "1", "3", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
*/
        //added
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "25GB", "30", "1,799", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "5GB", "30", "499", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "3GB", "30", "399", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "1536MB", "30", "209", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "750MB", "30", "119", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "250MB", "30", "47", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "250MB", "7", "36", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "150MB", "7", "26", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "50MB", "1", "13", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "9MB", "1", "3", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");

        // http://www.banglalink.com.bd/en/personal/internet?shs_term_node_tid_depth=153&packagevaliditytype=8&price%255Bmin%255D=0&price%255Bmax%255D=2000&validity%255Bmin%255D=0&validity%255Bmax%255D=30


       /* AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "25GB", "30", "1,799", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "5GB", "30", "499", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "3GB", "30", "399", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "1536MB", "30", "209", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "750MB", "30", "119", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "250MB", "30", "47", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "250MB", "7", "36", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "150MB", "7", "26", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "50MB", "1", "13", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "9MB", "1", "3", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Standard Volume Packs", "Prepaid");
        */

        //added
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "25GB", "30", "1799", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Recharge Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "5GB", "30", "499", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Recharge Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "1536MB", "30", "209", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Recharge Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "750MB", "30", "119", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Recharge Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "250MB", "30", "47", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Recharge Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "250MB", "7", "36", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Recharge Packs", "Prepaid");
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "150MB", "7", "26", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Recharge Packs", "Prepaid");
        // http://www.banglalink.com.bd/en/personal/internet?shs_term_node_tid_depth=148&packagevaliditytype=8&price%255Bmin%255D=0&price%255Bmax%255D=2000&validity%255Bmin%255D=0&validity%255Bmax%255D=30
        AppManager.getInstance(mContext).addBLPrePaidDataFullPackInfo(mContext, "100MB", "7", "7", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "Social Media Packs", "Prepaid");


    }

    // insert BL Prepaid info
    public static void addBLPostpaidDataPackInfo() {

// 22 packages
        //Context _mContext, String mPackage,  String mValidity,String mPrice, String mDialingCodeWith,String mDialingCodeWithout, String mAutosmsBody,String mAutosmsCode,String mProcess12,String mActivateProcess,String mDescription,String mOperatorType
//        http:
//www.banglalink.com.bd/en/personal/internet?shs_term_node_tid_depth=150&packagevaliditytype=8&price%255Bmin%255D=0&price%255Bmax%255D=2000&validity%255Bmin%255D=0&validity%255Bmax%255D=30
       /* AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "15GB", "30", "1,500", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "bill cycle based Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "8GB", "30", "900", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "bill cycle based Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "4GB", "30", "500", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "bill cycle based Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "2GB", "30", "350", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "bill cycle based Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "1.5GB", "30", "275", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "bill cycle based Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "1GB", "30", "210", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "bill cycle based Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "600 MB", "30", "150", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "bill cycle based Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "300 MB", "30", "99", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "bill cycle based Packs", "Postpaid");
*/
        //added
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "15GB", "30", "1,500", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "bill cycle based Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "8GB", "30", "900", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "bill cycle based Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "4GB", "30", "500", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "bill cycle based Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "2GB", "30", "350", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "bill cycle based Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "1.5GB", "30", "275", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "bill cycle based Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "1GB", "30", "210", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "bill cycle based Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "600 MB", "30", "150", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "bill cycle based Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "300 MB", "30", "99", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "bill cycle based Packs", "Postpaid");

        // http://www.banglalink.com.bd/en/personal/internet?shs_term_node_tid_depth=151&packagevaliditytype=8&price%255Bmin%255D=0&price%255Bmax%255D=2000&validity%255Bmin%255D=0&validity%255Bmax%255D=30
        /*AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "15GB", "30", "1,500", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "add-ons Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "8GB", "30", "900", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "add-ons Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "4GB", "30", "500", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "add-ons Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "2GB", "30", "350", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "add-ons Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "1.5GB", "30", "275", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "add-ons Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "600 MB", "30", "150", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "add-ons Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "300 MB", "30", "99", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "add-ons Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "160 MB", "7", "30", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "add-ons Packs", "Postpaid");
*/
        //added
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "25GB", "30", "1,799", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "add-ons Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "5GB", "30", "499", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "add-ons Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "3GB", "30", "399", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "add-ons Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "1.5GB", "30", "209", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "add-ons Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "350MB", "30", "119", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "add-ons Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "175MB", "7", "36", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "add-ons Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "50 MB", "1", "13", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "add-ons Packs", "Postpaid");
        AppManager.getInstance(mContext).addBLPostPaidDataFullPackInfo(mContext, "9 MB", "1", "3", "*5000*566#", "*5000*566# ", "", "*5000#", "", "", "add-ons Packs", "Postpaid");

    }

    // add Robi info
    public static void addRobiDataPackInfo() {
        // Context _mContext, String mPackage, String mPrice, String mValidity, String mAutoRenew,
        // String mDialingCode, String mProcess12, String mActivateProcess, String mDescription, String mOperatorType
       /* AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "7 MB", "2.44", "1", "Yes", "*8444*4#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "20 MB", "2.44", "1", "Yes", "*8444*202#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "20 MB", "2.44", "1", "Yes", "*8444*203#", "", "", "Social FB IMO", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "20 MB", "2.44", "1", "Yes", "*8444*0102#", "", "", "FB & WA Social Pack", "both");


        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "300 MB", "91.31", "28", "Yes", "*8444*250#", "", "", "", "both");


        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "3 GB", "459", "28", "Yes", "*8444*92#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "45 MB", "13.39", "1", "No", "*140*25*1#", "", "", "Combo Bundle", "prepaid");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "1 GB", "284.9", "28", "No", "*123*3*4*3#", "", "", "Combo Bundle", "prepaid");


        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "80 MB", "6.09", "7", "Yes", "*8444*080#", "", "", "Facebook, Facebook Messenger, Whatsapp Messenger", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "200 MB", "9", "1", "Yes", "*123*200#", "", "", "You Tube Pack", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "250 MB", "12.18", "28", "Yes", "*123*56#", "", "", "IMO", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "350 MB", "90.1", "7", "No", "*123*3*4*2#", "", "", "Combo Bundle", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "500 MB", "25", "3", "Yes", "*123*500#", "", "", "You Tube Pack", "both");


        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "25 MB", "1.22", "1", "Yes", "*8444*0101#", "", "", "Viber IMU WhatsApp", "both");


        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "40 MB", "4.87", "1", "Yes", "*8444*04#", "", "", "All Facebook", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "40 MB", "4.87", "1", "Yes", "*8444*06#", "", "", "Whatapp Messenger", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "40 MB", "4.87", "1", "Yes", "*8444*140#", "", "", "Video Pack", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "100 MB", "23.13", "10", "Yes", "*8444*10019#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "100 MB", "12.18", "1", "Yes", "*8444*110#", "", "", "Video Pack", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "120 MB", "46.87", "28", "Yes", "*8444*100#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "150 MB", "29.22", "7", "Yes", "*8444*2007#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "175 MB", "40.18", "10", "Yes", "*8444*0175#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "250 MB", "12.18", "28", "Yes", "*8444*0250#", "", "", "Social Pack", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "350 MB", "120.53", "Unlimited", "Yes", "*123*99#", "", "", "Unlimited Validity", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "600 MB", "170.45", "28", "Yes", "*8444*0600#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "750 MB", "149", "10", "No", "*123*149#", "", "", "", "prepaid");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "1 GB", "213.06", "28", "Yes", "*8444*500#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "1 GB", "23.13", "3", "Yes", "*8444*31990#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "1 GB", "242.28", "Unlimited", "No", " *123*199#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "1 GB", "91.48", "7", "No", "*123*3*2*2#", "", "", "", "both");


        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "2 GB", "182.63", "30", "Yes", "*8444*2150#", "", "", "Video Monthly Pack", "both");


        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "2 GB", "35.31", "3", "Yes", "*123*0239#", "", "", "2GB Night", "both");


        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "2 GB", "322.15", "28", "Yes", "*8444*85#", "", "", "", "prepaid");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "2 GB", "364.03", "Unlimited", "No", "*123*299#", "", "", "", "prepaid");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "2 GB", "181.41", "14", "No", "*123*3*2*3#", "", "", "", "prepaid");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "2 GB", "120.53", "7", "No", "*123*0099#", "", "", "", "prepaid");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "4.5 GB", "607.53", "28", "Yes", "*8444*4000#", "", "", "", "both");
        */

        //added
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "7 MB", "2.44", "1", "Yes", "*8444*4#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "20 MB(Facebook+Viber)", "2.44", "1", "Yes", "*8444*202#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "20 MB(Social FB IMO)", "2.44", "1", "Yes", "*8444*203#", "", "", "Social FB IMO", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "20 MB(FB & WA Social Pack)", "2.44", "1", "Yes", "*8444*0102#", "", "", "FB & WA Social Pack", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "25 MB", "1.22", "1", "Yes", "*8444*0101#", "", "", "Viber IMU WhatsApp", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "40 MB(All Facebook)", "4.87", "1", "Yes", "*8444*04#", "", "", "All Facebook", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "40 MB(WhatsApp Pack)", "4.87", "1", "Yes", "*8444*06#", "", "", "Whatapp Messenger", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "40 MB(Video Pack)", "4.87", "1", "Yes", "*8444*140#", "", "", "Video Pack", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "45 MB", "12.18", "1", "Yes", "*8444*21#", "", "", "", "both");

        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "80 MB", "6.09", "7", "Yes", "*8444*080#", "", "", "Facebook, Facebook Messenger, Whatsapp Messenger", "both");

        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "100 MB", "23.13", "10", "Yes", "*8444*10019#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "100 MB(Video Pack)", "12.18", "1", "Yes", "*8444*110#", "", "", "Video Pack", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "120 MB", "46.87", "28", "Yes", "*8444*100#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "150 MB", "29.22", "7", "Yes", "*8444*2007#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "175 MB", "40.18", "10", "Yes", "*8444*0175#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "200 MB(You Tube Pack)", "9", "1", "Yes", "*123*200#", "", "", "You Tube Pack", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "200 MB", "20", "1", "Yes", "*123*0020#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "250 MB(Social pack)", "12.18", "28", "Yes", "*8444*0250#", "", "", "Social Pack", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "250 MB(IMO)", "12.18", "28", "Yes", "*123*56#", "", "", "IMO", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "350 MB", "120.53", "Unlimited", "Yes", "*123*99#", "", "", "Unlimited Validity", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "500 MB(You Tube Pack)", "20", "3", "Yes", "*123*500#", "", "", "You Tube pack", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "500 MB", "49", "7", "Yes", "*123*049#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "500 MB", "36.53", "2", "Yes", "*123*50030#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "500 MB", "37.74", "3", "Yes", "*123*789*2#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "500 MB", "149", "28", "Yes", " *123*0149#", "", "", "", "prepaid");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "750 MB(Combo Bundle)", "149", "10", "No", "*123*149#", "", "", "", "prepaid");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "1 GB", "213.06", "28", "Yes", "*8444*500#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "1 GB", "242.28", "Unlimited", "No", " *123*199#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "1 GB", "89", "7", "Yes", " *123*089#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "1 GB", "284.9", "28", "No", "*123*3*4*3#", "", "", "Combo Bundle", "prepaid");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "1 GB", "49", "7", "Yes", "*123*1024#", "", "", "You Tube Pack", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "1 GB", "64.53", "7", "No", "*123*789*1#", "", "", "Streaming Pack", "both");

        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "1536MB", "101", "7", "Yes", "*123*101#", "", "", "Youtube+ Facebook", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "1536MB", "228", "28", "Yes", "*123*228#", "", "", "Youtube+ Facebook", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "2GB", "322.15", "28", "Yes", "*8444*85#", "", "", "", " Prepaid");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "2GB", "364.03", "Unlimited", "", "*123*299#", "", "", "Unlimited Validity", "Prepaid");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "2GB", "129", "7", "", "*123*02#", "", "", "", "Prepaid");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "2560 MB", "337", "28", "Yes", "*123*2048#", "", "", "", "both");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "3GB", "459", "28", "Yes", "*8444*92#", "", "", "", "prepaid");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "3GB", "249", "7", "Yes", "*123*0249#", "", "", "Iflix, Facebook, WhatsApp, viber, imo , Instagram, snapchat, Robi TV, Yonder", "prepaid");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "3GB", "489", "30", "Yes", "*123*0489#", "", "", "Iflix, Facebook, WhatsApp, viber, imo , Instagram, snapchat, RobiTV,Yonder", "prepaid");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "3584 MB", "427", "28", "Yes", "*123*2560#", "", "", "", "prepaid");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "4608 MB", "607.53", "28", "Yes", "*8444*4000#", "", "", "", "prepaid");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "7GB", "649", "28", "No", "*123*7168#", "", "", "", "prepaid");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "7GB+3GB content", "889", "30", "Yes", "*123*0889#", "", "", "Iflix, Facebook, WhatsApp, viber, imo , Instagram, snapchat, Robi TV, Yonder", "prepaid");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "10GB", "1489", "30", "Yes", " *123*01489#", "", "", "Iflix, Facebook, WhatsApp, viber, imo , Instagram, snapchat, Robi TV, Yonder", "prepaid");
        AppManager.getInstance(mContext).addRobiDataFullPackInfo(mContext, "30GB", "2989", "30", "Yes", "*123*02989#", "", "", "Iflix, Facebook, WhatsApp, viber, imo , Instagram, snapchat, Robi TV, Yonder", "prepaid");


    }

    // add migration list info
    public static void addMigrationInfo() {
        // for gp
        //done
        // Context _mContext,String operator,String opPacakge,String processType,String dialCode,String smsBody,String smsCode,String detailsOpertorToOperator,String detailsOperatorToOthers,detailsFnF,detailsPulse,detailsSMS
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_GRAMEENPHONE, "Nishchinto", "sms", "", "N", "4444", "21 poisha/10 second", "21 poisha/10 second", " N/A ", "10 second", "50 poisha/SMS", "Prepaid");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_GRAMEENPHONE, "Bondhu", "sms", "", "B", "4444", "27.5 poisha/10 second", "27.5  poisha/10 second", "1 Super FnF (5.5 poisha/10 second) & 17 FnF(11.5 poisha/10 second)", "10 second", "50 poisha/SMS", "Prepaid");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_GRAMEENPHONE, "Djuice", "sms", "", "D", "4444", "27.5 poisha/10 second, Community: 11.5 poisha/10 second", "27.5 poisha/10 second", "10 FnF (11.5 poisha/10 second)", "10 second", "50 poisha/SMS", "Prepaid");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_GRAMEENPHONE, "Smile", "sms", "", "SM", "4444", "28 poisha/10 second", "28 poisha/10 second", "3 FnF (11.5 paisa/10 second)", "10 second", "50 poisha/SMS", "Prepaid");

        //done
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_GRAMEENPHONE_BN, "নিশ্চিন্ত", "sms", "", "N", "4444", "২১ পয়সা / ১০ সেকেন্ড", "২১ পয়সা / ১০ সেকেন্ড", " প্রযোজ্য নয় ", " ১০ সেকেন্ড", "৫০ পয়সা / এস এম এস", "প্রিপেইড");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_GRAMEENPHONE_BN, "বন্ধু", "sms", "", "B", "4444", "২৭.৫ পয়সা/ ১০ সেকেন্ড", "২৭.৫ পয়সা/ ১০ সেকেন্ড", "১ সুপার এফএন্ডএফ (৫.৫ পয়সা/ ১০ সেকেন্ড) এবং ১৭ এফএন্ডএফ (১১.৫ পয়সা/ ১০ সেকেন্ড)", "১০ সেকেন্ড", " ৫০ পয়সা / এসএমএস", "প্রিপেইড");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_GRAMEENPHONE_BN, "ডিজুস", "sms", "", "D", "4444", "২৭.৫ পয়সা / ১০ সেকেন্ড, কমিউনিটি: ১১.৫ পয়সা / ১০ সেকেন্ড", "২৭.৫ পয়সা/ ১০ সেকেন্ড", "১০ এফএন্ডএফ (১১.৫ পয়সা/ ১০ সেকেন্ড)", "১০ সেকেন্ড", " ৫০ পয়সা / এসএমএস", "প্রিপেইড");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_GRAMEENPHONE_BN, "স্মাইল", "sms", "", "SM", "4444", "২৮ পয়সা / ১০ সেকেন্ড", "২৮ পয়সা/ ১০ সেকেন্ড", "৩ এফএন্ডএফ (১১.৫ পয়সা/ ১০ সেকেন্ড)", " ১০ সেকেন্ড", "৫০ পয়সা / এসএমএস", "প্রিপেইড");


        // for Robi
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_ROBI, "The MEGA  FnF", "dial", "*8999*90#", "", "", "FnF(4 PM to 12 AM): 1.1 Paisa/sec and FnF (12 AM to 4 PM): 0.8 Paisa/sec", "FnF (24 hours): 1.1 Paisa/sec", "Subscribers are eligible to subscribe for MEGA  FnF numbers. There is lifetime validity for the FnF numbers.", "1 second", "Subscribers of the MEGA FnF package will now be able to enjoy an MEGA SMS pack for only Tk. 5!", "Prepaid");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_ROBI, "Hoot Hut Chomok 32", "dial", "*8999*32#", "", "", "11p/10 sec", "21p/10sec", "13p/10sec to 3 Robi FnF numbers & 2 other local operator(s) FnF numbers.", "10 second", "Not applicable", "Prepaid");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_ROBI, "Robi Club 34", "dial", "*8999*34#", "", "", "(4 PM to 12 AM): 12 paisa/10 sec and (12 AM to 4 PM): 23 paisa/10 sec", "(24 hours): 23 paisa/10 sec", "Not applicable", "10 second", "Not applicable", "Prepaid");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_ROBI, "Goti 36", "dial", "*8999*36#", "", "", "(24 hours): 21 Paisa/10sec", "(24 hours): 21 Paisa/10sec", "Not applicable", "10 second", "Not applicable", "Prepaid");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_ROBI, "Nobanno 37", "dial", "*8999*37#", "", "", "(10 PM to 8 AM): 8 Paisa/10sec and (8 AM to 10 PM): 21 Paisa/10sec", "(24 hours): 21 Paisa/10sec", "Not applicable", "10 second", "Not applicable", "Prepaid");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_ROBI, "Shorol 39", "dial", "*8999*39#", "", "", "22 Paisa/10sec", "22 Paisa/10sec", "Not applicable", "10 second", "Not applicable", "Prepaid");

        // for Robi bangla
        //done
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_ROBI_BN, "মেগা এফএনএফ", "dial", "*8999*90#", "", "", "রবি-রবি/এয়ারটেল এফএনএফ = ১.১পয়সা/ সেকেন্ড (বিকাল ৪টা থেকে রাত ১২টা) | রবি-রবি/এয়ারটেল এফএনএফ = ০.৮ পয়সা/ সেকেন্ড (রাত ১২টা থেকে বিকাল ৪টা)", "রবি- অন্য এফএনএফ অপারেটরে = ১.১ পয়সা/ সেকেন্ড (দিনরাত ২৪ ঘন্টা)|", "এফএনএফ নম্বরে আজীবন মেয়াদ প্রযোজ্য |", "১ সেকেন্ড", "মূল্য (৫% সম্পূরক শুল্ক + সম্পূরক শুল্কসহ ট্যারিফের উপর ১৫% ভ্যাট ব্যাতীত) ৫ টাকা, মেয়াদ ২ দিন ", "প্রিপেইড");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_ROBI_BN, "হুটহাট চমক ৩২", "dial", "*8999*32#", "", "", "১১ পয়সা/১০ সেকেন্ড", "২১ পয়সা/১০ সেকেন্ড", "রবি/এয়ারটেল এফএনএফ(৩) ১৩ পয়সা/১০ সেকেন্ড ফ্লাট, অন্যান্য এফএনএফ(২),১৩ পয়সা/১০ সেকেন্ড ফ্লাট | ", "১০ সেকেন্ড", "প্রযোজ্য নহে", "প্রিপেইড");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_ROBI_BN, "রবি ক্লাব ৩৪", "dial", "*8999*34#", "", "", "রবি-রবি/এয়ারটেল (বিকাল ৪ টা - রাত ১২ টা): ১২ পয়সা / ১০ সেকেন্ড, রবি-রবি/এয়ারটেল (রাত ১২ টা - বিকাল ৪ টা): ২৩ পয়সা / ১০ সেকেন্ড", "রবি-অন্যান্য (২৪ ঘন্টা): ২৩ পয়সা / ১০ সেকেন্ড", "প্রযোজ্য নহে", "১০ সেকেন্ড", "প্রযোজ্য নহে", "প্রিপেইড");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_ROBI_BN, "গতি ৩৬", "dial", "*8999*36#", "", "", "রবি-রবি/এয়ারটেল ( ২৪ ঘন্টা ): ২১ পয়সা / ১০ সেকেন্ড", "রবি-অন্যান্য (২৪ ঘন্টা): ২১ পয়সা / ১০ সেকেন্ড", "প্রযোজ্য নহে", "১০ সেকেন্ড", "প্রযোজ্য নহে", "প্রিপেইড");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_ROBI_BN, "নবান্ন ৩৭", "dial", "*8999*37#", "", "", "রবি-রবি/এয়ারটেল (রাত ১০ টা - সকাল ৮ টা): ৮ পয়সা / ১০সেকেন্ড, রবি-রবি/এয়ারটেল (সকাল ৮ টা - রাত ১০ টা): ২১ পয়সা / ১০সেকেন্ড", "রবি-অন্যান্য (২৪ ঘন্টা): ২১ পয়সা / ১০সেকেন্ড", "প্রযোজ্য নহে", "১০ সেকেন্ড", "প্রযোজ্য নহে", "প্রিপেইড");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_ROBI_BN, "সরল ৩৯", "dial", "*8999*39#", "", "", "২২ পায়সা/১০সেকেন্ডস", "২২ পায়সা/১০সেকেন্ডস", "প্রযোজ্য নহে", "১০ সেকেন্ড", "প্রযোজ্য নহে", "প্রিপেইড");

        // for Banglalink

        //done
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_BANGLALINK, "desh", "dial", "*999*1*143#", "", "", "26 poisha/10 second during 10 pm to 8 am and 26 poisha/10 second during 8 am to 10 pm", "29 poisha/10 second during 10 pm to 8 am and 29 poisha/10 second during 8 am to 10 pm", "get 3 fnf to any operator. 10 second pulse applicable", "10 second", "50 poisha/SMS", "Prepaid");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_BANGLALINK, "play", "sms", "", "p", "9999", "22 poisha/10 second during  12am-4pm and 27 poisha/10 second during  4pm-12am", "22 poisha/10 second during 12am-4pm and 27 poisha/10 second during  4pm-12am", "Special fnf rate should be 0.55 poisha/ses instead of 0.5 poisha/ses", "10 second", "29 poisha/SMS", "Prepaid");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_BANGLALINK, "desh hello package", "sms", "", "h", "9999", "23 poisha/10 second during 24 hours.", "Bl to others:12 poisha/10 second during 24 hours.", "1 special fnf at only 5.5 paisa/10 second", "10 second", "50 poisha/SMS", "Prepaid");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_BANGLALINK, "desh ek rate darun", "dial", "*999*1*146#", "", "", "20.83 poisha/10 second during 24 hours", "20.83 poisha/10 second during 24 hours", "1 special super  fnf ,30p/min (12am-4Pm). Please if you want more about FnF dial *166*#", "10 second", "50 poisha/SMS", "Prepaid");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_BANGLALINK, "desh 10 fnf", "dial", "*999*1*112#", "", "", "27 poisha/10 second during 24 hours", "28.67 poisha/10 second during 24 hours", "1 special super  fnf ,6p/10 sec (12am-4Pm)and 7p/10 sec (4Pm-12am). enjoy 9 fnf to any operator with call rates as low as 11 paisa/10 second", "10 second", "50 poisha/SMS", "Prepaid");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_BANGLALINK, "1 second pulse", "dial", "*999*1*111#", "", "", "2.2 poisha/1 second during 24 hours", "2.2 poisha/1 second during 24 hours", "there is no fnf for this package", "1 second", "50 poisha/SMS", "Prepaid");
        //AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_BANGLALINK, "banglalink inspire", "dial", "*121#", "", "", "only 45 paisa/minute for 24 hours within the same professional group", "only 42 paisa/minute on 2 supplementary numbers", "7 fnf numbers to any mobile operator: 60 paisa/min to banglalink fnf numbers and 84 paisa/min to other operators fnf numbers. all fnf and cug rates have 10 second pulse.", "10 second", "every new connection comes with 300 sms/month to any operator, 500 mms/month");


        // for Banglalink bangla

        //done
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_BANGLALINK_BN, "বাংলালিংক দেশ", "dial", "*999*1*143#", "", "", "রাত ১০টা থেকে সকাল ৮টা ২৬ পয়সা/ ১০ সেকেন্ড, সকাল ৮টা থেকে রাত ১০টা ২৬ পয়সা/ ১০ সেকেন্ড", "রাত ১০টা থেকে সকাল ৮টা ২৯ পয়সা/ ১০ সেকেন্ড, সকাল ৮টা থেকে রাত ১০টা ২৯ পয়সা/ ১০ সেকেন্ড", "যে কোন নাম্বারে ৩টি এফএনএফ, শুধু বাংলালিংক এফএনএফ নাম্বারে সর্বনিম্ন ১০ পয়সা/১০ সেকেন্ড", "১০ সেকেন্ড পাল্\u200Cস", "৫০ পয়সা/এসএমএস", "প্রিপেইড");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_BANGLALINK_BN, "বাংলালিংক প্লে", "sms", "", "p", "9999", "রাত ১২টা থেকে বিকাল ৪টা ২২ পয়সা/ ১০ সেকেন্ড, বিকাল ৪টা থেকে রাত ১২টা ২৭ পয়সা/ ১০ সেকেন্ড  ", "রাত ১২টা থেকে বিকাল ৪টা ২২ পয়সা/ ১০ সেকেন্ড, বিকাল ৪টা থেকে রাত ১২টা ২৭ পয়সা/ ১০ সেকেন্ড  ", "সবচেয়ে কম রেটে ১৮ এফএনএফ (যে কোন অপারেটরে)। একটি স্পেশাল এফএনএফ নাম্বারে থাকছে ০.৫৫ পয়সা/সেকন্ড রেট, এফএনএফ ও স্পেশাল এফএনএফ নাম্বারে এসএমএস রেট ২৯ পয়সা/ এসএমএস এবং সকল বাংলালিংক নাম্বারেও এমএমএস পাঠান ২৯ পয়সা/এমএমএস রেটে", "১০ সেকেন্ড পাল্\u200Cস", "২৯ পয়সা/এসএমএস", "প্রিপেইড");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_BANGLALINK_BN, "দেশ হ্যালো প্যাকেজ", "sms", "", "h", "9999", "২৪ ঘন্টা সময় ২৩ পয়সা / ১০ সেকেন্ড", "২৪ ঘন্টা সময় ১২ পয়সা / ১০ সেকেন্ড", "শুধুমাত্র  ৫.৫ পায়সা/১০ সেকেন্ড ১ বিশেষ এফএনএফ এর ক্ষেত্রে", "১০ সেকেন্ড পাল্\u200Cস", "৫০ পয়সা/এসএমএস", "প্রিপেইড");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_BANGLALINK_BN, "দেশ এক রেট দারুণ", "dial", "*999*1*146#", "", "", "২৪ ঘন্টা সময় ২০.৮৩ পয়সা / ১০ সেকেন্ড", "২৪ ঘন্টা সময় ২০.৮৩ পয়সা / ১০ সেকেন্ড", "আপনি যদি প্রিপেইড গ্রাহক (ই-ভাউচার ব্যতীত) হয়ে থাকেন, তাহলে *৯৯৯*১*১৪৬# ডায়াল করে এই প্যাকেজটি ফ্রি মাইগ্রেট করতে পারবেন।", "১০ সেকেন্ড পাল্\u200Cস", "৫০ পয়সা/এসএমএস", "প্রিপেইড");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_BANGLALINK_BN, "দেশ ১০ এফএনএফ", "dial", "*999*1*112#", "", "", "২৪ ঘন্টা সময় ২৭ পয়সা / ১০ সেকেন্ড", "২৪ ঘন্টা সময় ২৮.৬৭ পয়সা / ১০ সেকেন্ড", " ৬ পায়সা/১০ সেকেন্ড   (রাত ১২টা থেকে বিকেল৪ টা )  এবং ৭ পায়সা/১০ সেকেন্ড  ( বিকেল  ৪টা থেকে  রাত ১২টা)  ১  বিশেষ এফএনএফ এর ক্ষেত্রে,  যেকোনো অপারেটরে ৯টি এফএনএফ, যেকোনো অপারেটরের এফএনএফ নাম্বারে ১১ পয়সা/১০ সেকেন্ড", "১০ সেকেন্ড পাল্\u200Cস", "৫০ পয়সা/এসএমএস", "প্রিপেইড");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_BANGLALINK_BN, "এক সেকেন্ড পাল্\u200Cস", "dial", "*999*1*111#", "", "", "২৪ ঘন্টা সময় ২.২ পয়সা / ১ সেকেন্ড", "২৪ ঘন্টা সময় ২.২ পয়সা / ১ সেকেন্ড", "এই প্যাকেজের জন্য কোন এফএনএফ প্রযোজ্য  নহে", "১ সেকেন্ড পাল্\u200Cস", "৫০ পয়সা/এসএমএস", "প্রিপেইড");

        //AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_BANGLALINK, "banglalink inspire", "dial", "*121#", "", "", "only 45 paisa/minute for 24 hours within the same professional group", "only 42 paisa/minute on 2 supplementary numbers", "7 fnf numbers to any mobile operator: 60 paisa/min to banglalink fnf numbers and 84 paisa/min to other operators fnf numbers. all fnf and cug rates have 10 second pulse.", "10 second", "every new connection comes with 300 sms/month to any operator, 500 mms/month");


        //Teletalk
        //Prepaid
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_TELETALK, "Projonmo", "Prepaid", "1 FnF number (any operator)", "10 Paisa/Pulse (8am - 12am), 5 Paisa/Pulse (12am - 8am)", "10 sec", "Onnet: Tk. 0.35/sms and Offnet: Tk. 0.45/sms", "Migration procedure: To Migrate to Ekush, Write \"21\" and send SMS to 555. To Migrate to Bijoy, Write \"bij\" and send SMS to 555. To Migrate to Shadheen, Write \"sha\" and send SMS to 555.", "", "", "", "");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_TELETALK_BN, "প্রজন্ম", "প্রিপেইড", "১টি (যে কোনো অপারেটরে)", "১০ পয়সা/পালস্ (পিক (সকাল ৮টা থেকে রাত ১২টা)), ৫ পয়সা/পালস্ (অফ পিক (রাত ১২টা থেকে সকাল ৮টা))", "১০ সেকেন্ড", "অন নেট ৩৫ পয়সা ও অফ নেট ৪৫ পয়সা", "প্যাকেজ পরিবর্তন করতেঃ কাঙ্ক্ষিত প্যাকেজ কোডলিখে এসএমএস করুন ৫৫৫ নম্বরে- একুশ ২১, বিজয় bij, স্বাধীন sha", "", "", "", "");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_TELETALK, "Youth", "Prepaid", "3 FnF (any operator).", "on-net:1 Paisa/Sec (8am - 12am), 0.5 Paisa/Sec (12am - 8am), off-net: 1.5 Paisa/Sec (24 hours)", "1 Sec", "On-net:30 paisa/sms, Off-net: 40 paisa/sms.", "Any prepaid subscriber (Excluding PCO and Telecharge) can migrate to this package. To migrate, subscriber will type Y3G and send to 555 (Charge free).", "", "", "", "");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_TELETALK_BN, "ইয়ুথ", "প্রিপেইড", "৩ টা এফএনএফ (যেকোন অপারেটরে)", "১ পয়সা/সেকেন্ড (সকাল ৮টা থেকে রাত ১২টা) ০.৫ পয়সা/সেকেন্ড (রাত ১২টা থেকে সকাল ৮টা) ১.৫ পয়সা/সেকেন্ড (অন্য অপারেটরে ২৪ ঘ", "৫ সেকেন্ড", "অন নেট-৩০ পয়সা, অফ নেট-৪০ পয়সা", "কোন প্রিপেইড গ্রাহক (পিসিও ও Telecharge বহির্ভূত) এই প্যাকেজে মাইগ্রেট করতে পারবেন | মাইগ্রেট করতে গ্রাহককে Y3G টাইপ করুন এবং ৫৫৫ (চার্জ ফ্রি) পাঠাতে হবে|", "", "", "", "");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_TELETALK, "Bornomala", "Prepaid", "FnF not applicable", "30 Paisa/min (On-net: 24 Hours), 60 Paisa/min (Off-net:24 Hours)", "10 sec", "On net- Tk. 0.30/SMS, Off net- Tk. 0.40/SMS", " Not Defined", "", "", "", "");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_TELETALK_BN, "বর্ণমালা", "প্রিপেইড", "এফএনএফ প্রযোজ্য নয়", "৩০ পয়সা/মিনিট (অন নেটঃ ২৪ ঘন্টা), ৬০ পয়সা/মিনিট (অফ নেটঃ ২৪ ঘন্টা)", "১০ সেকেন্ড", "অন নেট - ৩০ পয়সা, অফ নেট - ৪০ পয়সা", "প্রযোজ্য নয়", "", "", "", "");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_TELETALK, "Mayer Hasi", "Prepaid", "10 FnF (5 On-net, 5 Off-Net)", "90 Paisa/Minute (Off-Net) 60 Paisa/Minute (On-Net 8 am-12 am) 30 Paisa/Minute (On-Net 12 am-8 am)", "1 Second Pulse", "30 Paisa/SMS (On-Net) 40 Paisa/SMS (Off-Net)", "Existing Teletalk subscribers who are eligible for stipend can migrate to this package. For migration subscriber needs to type MH <space> stipend account number and send SMS to 556. (Charge free). Migration from Mayer Hasi to other package is NOT allowed.", "", "", "", "");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_TELETALK_BN, "মায়ের হাসি", "প্রিপেইড", "১০ টি এফএনএফ (৫টি অননেট-৫ট অফনেট)", "৯০ পয়সা/মিনিট অফনেটে ৬০ পয়সা/মিনিট অননেটে(সকাল ৮টা-রাত ১২টা) ৩০ পয়সা/মিনিট অননেটে(রাতে ১২টা-সকাল ৮টা)", "১ সেকেন্ড পালস", "৩০ পয়সা/এসএমএস (অননেট) ৪০ পয়সা/এসএমএস (অফনেট)", "বৃত্তির জন্য যোগ্য টেলিটকের গ্রাহকরা এই প্যাকেজে মাইগ্রেট করতে পারবেন | মাইগ্রেশন করতে গ্রাহক কে এমএইচ <স্পেস> বৃত্তি অ্যাকাউন্ট নম্বর টাইপ করে ৫৫৬ নম্বরে এসএমএস পাঠাতে হবে | অন্যান্য প্যাকেজ মায়ের হাসি থেকে মাইগ্রেশন যোগ্য নয় |", "", "", "", "");

        //New added
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_TELETALK, "Oporajita", "Prepaid", "99 FnF number", "Only for 29 & 99 Tk recaharge:30 Paisa/min (On Net:24 Hours)and 60 Paisa/min (On Net:24  Hours). Regular rate:60 Paisa/min (On Net:24 Hours) and 90 Paisa/min (Off Net:24 Hours) ", "1 second", "Tk. 40 paisa/sms( Onnet & Offnet)", " Not Defined", "", "", "", "");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_TELETALK_BN, "অপরাজিতা", "প্রিপেইড", "৯৯টি এফএনএফ ", "শুধুমাত্র ২৯ ও ৯৯টাকা রিচার্জের জন্য :৩০ পয়সা/মিনিট(অন নেটঃ২ ৪ ঘন্টা ), ৬০ পয়সা/মিনিট (অন নেটঃ২ ৪ ঘন্টা)", "১০ সেকেন্ড", "নিয়মিত হার: ৬০ পয়সা/মিনিট(অন নেটঃ২ ৪ ঘন্টা ), ৯৯ পয়সা/মিনিট (অন নেটঃ২ ৪ ঘন্টা)", "প্রযোজ্য নয়", "", "", "", "");

        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_TELETALK, "Shadheen", "Prepaid", "9 FnF number (any operator)", "60 Paisa/min (On Net:8am-12am), 30 Paisa/min (On Net: 12am-8am)", "10 second", "Onnet: Tk. 0.35/sms and Offnet: Tk. 0.45/sms", " Not Defined", "", "", "", "");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_TELETALK_BN, "স্বাধীন", "প্রিপেইড", "৯টি এফএনএফ (যে কোনো অপারেটরে)", "৬০ পয়সা/মিনিট(অন নেটঃ সকাল ৮টা-রাত ১২টা ), ৩০ পয়সা/মিনিট (অন নেটঃরাত ১২টা-সকাল ৮টা)", "১০ সেকেন্ড", "অন নেট ৩৫ পয়সা ও অফ নেট ৪৫ পয়সা", "প্রযোজ্য নয়", "", "", "", "");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_TELETALK, "Agami", "Prepaid", "FnF Not Applicable", "4.17 Paisa/Pulse (24 Hours)", "10 Seconds", "On net - 25 Paisa, Off net - 35 Paisa", "Migration procedure: To Migrate to Ekush, Write \"21\" and send SMS to 555. To Migrate to Bijoy, Write \"bij\" and send SMS to 555. To Migrate to Shadheen, Write \"sha\" and send SMS to 555. ", "", "", "", "");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_TELETALK_BN, "আগামী", "প্রিপেইড", "এফএনএফ প্রযোজ্য নয়", "৪.১৭ পয়সা/পালস্ (২৪ ঘণ্টা এক রেট)", "১০ সেকেন্ড", "অন নেট - ২৫ পয়সা, অফ নেট - ৩৫ পয়সা", "প্যাকেজ পরিবর্তন করতেঃ কাঙ্ক্ষিত প্যাকেজ কোডলিখে এসএমএস করুন ৫৫৫ নম্বরে- একুশ ২১, বিজয় bij, স্বাধীন sha, স্ট্যান্ডার্ড nor", "", "", "", "");

        //Pospaid
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_TELETALK, "Shapla ", "Postpaid", "Dial 1515 and follow the next direction or write Reg in message option and send it to 363.", "80 Paisa/min (On-net: 24 Hours), Tk. 1.00/min (Off-net: 24 Hours)", "1 Sec", "Tk. 0.50 [Nationwide] / Tk. 2.00 [International]", "Migration procedure:  Migration to postpaid from prepaid, to prepaid from postpaid or to corporate from any package please contact at your nearest Teletalk Customer Care Center.", "", "", "", "");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_TELETALK_BN, "শাপলা ", "পোস্টপেইড", "সেবাটি চালু করতে আপনার টেলিটক নম্বর থেকে ডায়াল করুন ১৫১৫ নম্বরে এবং পরবর্তী নির্দেশনা অনুসরণ করুন, অথবা, Reg লিখে এসএমএস পাঠান ৩৬৩ নম্বরে।", "৮০ পয়সা/মিনিট (অন ণেটঃ ২৪ ঘন্টা), ১.০০ টাকা/মিনিট (অফ ণেটঃ ২৪ ঘন্টা)", "১ সেকেন্ড", "০.৫০ টাকা দেশে ও ২টাকা দেশের বাইরে", "প্যাকেজ পরিবর্তন করতেঃ  (পোস্টপেইড থেকে প্রিপেইড, প্রিপেইড থেকে পোস্টপেইড কিংবা কর্পোরেট প্যাকেজে মাইগ্রেশন করতে যোগাযোগ করুন টেলিটক কাস্টমার কেয়ারে।) ", "", "", "", "");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_TELETALK, "Rajanigandha", "Postpaid", "FnF not applicable", "80p/min (peak:8am - 05 pm), 80p/min (off-peak:5 pm -12am), 25p/min(super off-peak:12am - 8am)", "1 sec pulse", "Tk. 0.50 [Nationwide] / Tk. 2.00 [International]", "Migration procedure: Migration to postpaid from prepaid, to prepaid from postpaid or to corporate from any package please contact at your nearest Teletalk Customer Care Center.", "", "", "", "");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_TELETALK_BN, "রজনীগন্ধা", "পোস্টপেইড", "এফএনএফ প্রযোজ্য নয়", "৮০ পয়সা/মিনিট (পিকঃ সকাল ৮টা থেকে বিকাল ৫টা)), ৮০ পয়সা/মিনিট (অফ পিকঃ বিকাল ৫টা থেকে রাত ১২টা), ২৫ পয়সা/মিনিট (সুপার অ", "এক সেকেন্ড পালস্।", "৫০ পয়সা ও ২টাকা (আন্তর্জাতিক)", "প্যাকেজ পরিবর্তন করতেঃ  (পোস্টপেইড থেকে প্রিপেইড, প্রিপেইড থেকে পোস্টপেইড কিংবা কর্পোরেট প্যাকেজে মাইগ্রেশন করতে যোগাযোগ করুন টেলিটক কাস্টমার কেয়ারে।)", "", "", "", "");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_TELETALK, "Gravity ", "Postpaid", "10 FnF number (any operator)", "1p/sec (peak: 8am - 5pm), 1p/sec (off-peak: 5pm - 12am) 0.5p/sec(super off-peak: 12am-8am)", "1 sec", "35p/SMS", "Migration procedure: Migration to postpaid from prepaid, to prepaid from postpaid or to corporate from any package please contact at your nearest Teletalk Customer Care Center. ", "", "", "", "");
        AppManager.getInstance(mContext).addMigrationInfo(mContext, AppConstants.MIGRATION_TELETALK_BN, "গ্রাভিটি ", "পোস্টপেইড", "১০টি এফএনএফ (যে কোনো অপারেটরে)", "১ পয়সা/পালস্ (পিকঃ সকাল ৮টা থেকে বিকাল ৫টা), ১পয়সা/পালস্ (অফ পিকঃ বিকাল ৫টা থেকে রাত ১২টা), ০.৫পয়সা/পালস্ (সুপার অফ পিকঃ", "১ সেকেন্ড", "এসএমএস ৩৫ পয়সা", "প্যাকেজ পরিবর্তন করতেঃ (পোস্টপেইড থেকে প্রিপেইড, প্রিপেইড থেকে পোস্টপেইড কিংবা কর্পোরেট প্যাকেজে মাইগ্রেশন করতে যোগাযোগ করুন টেলিটক কাস্টমার কেয়ারে।)", "", "", "", "");


    }
    // add Teletalk info
    public static void addTeletalkDataPackInfo() {
        // Context _mContext, String mPackage, String mVolume,String mPrice,
        // String mValidity, String mDialingCode, String mAutosmsBodyForPrepaid,
        // String mAutosmsBodyForPostpaid,String mAutosmsCode,
        // String mProcess12,String mActivateProcess,String mDescription



        //done
        AppManager.getInstance(mContext).addTeletalkDataFullPackInfo(mContext, "Mini Packs", "45 MB", "8", "2", "*111*501#", "D51", "F51", "111", "", "", "both");
        AppManager.getInstance(mContext).addTeletalkDataFullPackInfo(mContext, "Mini Packs", "100 MB", "15", "3", "*111*502#", "D37", "F37", "111", "", "", "both");
        AppManager.getInstance(mContext).addTeletalkDataFullPackInfo(mContext, "Mini Packs", "250 MB", "25", "3", "*111*503#", "D82", "F82", "111", "", "", "both");
        AppManager.getInstance(mContext).addTeletalkDataFullPackInfo(mContext, "Weekly Packs", "150 MB", "20", "7", "*111*512#", "D83", "F83", "111", "", "", "both");
        AppManager.getInstance(mContext).addTeletalkDataFullPackInfo(mContext, "Weekly Packs", "300 MB", "35", "10", "*111*513#", "D52", "F52", "111", "", "", "both");
        AppManager.getInstance(mContext).addTeletalkDataFullPackInfo(mContext, "Weekly Packs", "1 GB", "70", "10", "*111*511#", "D58", "F58", "111", "", "", "both");
        AppManager.getInstance(mContext).addTeletalkDataFullPackInfo(mContext, "Fortnightly Packs", "1 GB", "100", "15", "*111*521#", "D19", "F19", "111", "", "", "both");
        AppManager.getInstance(mContext).addTeletalkDataFullPackInfo(mContext, "Fortnightly Packs", "2 GB", "150", "15", "*111*522#", "D85", "F85", "111", "", "", "both");
        AppManager.getInstance(mContext).addTeletalkDataFullPackInfo(mContext, "Monthly Packs", "1 GB", "180", "30", "*111*531#", "D31", "F21", "111", "", "", "both");
        AppManager.getInstance(mContext).addTeletalkDataFullPackInfo(mContext, "Monthly Packs", "2 GB", "270", "30", "*111*532#", "D20", "F20", "111", "", "", "both");
        AppManager.getInstance(mContext).addTeletalkDataFullPackInfo(mContext, "Monthly Packs", "4 GB", "350", "30", "*111*533#", "D25", "F4", "111", "", "", "both");
        AppManager.getInstance(mContext).addTeletalkDataFullPackInfo(mContext, "Off-Peak Pack", "1 GB", "99", "30", "*111*530#", "D60", "F25", "111", "", "", "both");
        AppManager.getInstance(mContext).addTeletalkDataFullPackInfo(mContext, "Day-Night Pack", "(1 + 1) GB", "150", "30", "*111*534#", "D86", "F86", "111", "", "", "both");
        AppManager.getInstance(mContext).addTeletalkDataFullPackInfo(mContext, "Value Packs", "5 GB", "400", "30", "*111*550#", "D21", "F22", "111", "", "", "both");
        AppManager.getInstance(mContext).addTeletalkDataFullPackInfo(mContext, "Value Packs", "10 GB", "750", "30", "*111*551#", "D22", "F9", "111", "", "", "both");
        AppManager.getInstance(mContext).addTeletalkDataFullPackInfo(mContext, "Value Packs", "15 GB", "900", "30", "*111*552#", "D26", "F28", "111", "", "", "both");
        AppManager.getInstance(mContext).addTeletalkDataFullPackInfo(mContext, "Value Packs", "25 GB", "1050", "30", "*111*553#", "D32", "F31", "111", "", "", "both");
        AppManager.getInstance(mContext).addTeletalkDataFullPackInfo(mContext, "Value Packs", "40 GB", "1500", "30", "*111*554#", "D33", "F11", "111", "", "", "both");
        AppManager.getInstance(mContext).addTeletalkDataFullPackInfo(mContext, "Value Packs", "60 GB", "2000", "30", "*111*555#", "D62", "F26", "111", "", "", "both");



        // http://www.teletalk.com.bd/packages/mixPackageDetails.jsp?packageID=24006,17006&menuItem=108002
        //done
        AppManager.getInstance(mContext).addTeletalkDataFullPackInfo(mContext, "Bornomala package (512 Kbps)", "500 MB", "70", "30", "", "D71", "", "111", "", "", "prepaid");
        AppManager.getInstance(mContext).addTeletalkDataFullPackInfo(mContext, "Bornomala package (512 Kbps)", "1 GB", "130", "30", "", "D72", "", "111", "", "", "prepaid");
        AppManager.getInstance(mContext).addTeletalkDataFullPackInfo(mContext, "Bornomala package (512 Kbps)", "6 GB", "400", "30", "", "D73", "", "111", "", "", "prepaid");
        // http://www.teletalk.com.bd/packages/dataPackageDetails.jsp?packageID=23005


    }


}
