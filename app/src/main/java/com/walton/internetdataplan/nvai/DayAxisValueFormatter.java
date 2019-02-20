package com.walton.internetdataplan.nvai;

import android.util.Log;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.Calendar;

/**
 * Created by DELL on 12/6/2016.
 */

public class DayAxisValueFormatter implements IAxisValueFormatter
{

    protected String[] mMonths = new String[]{
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };

    private BarLineChartBase<?> chart;

    public DayAxisValueFormatter(BarLineChartBase<?> chart) {
        this.chart = chart;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        Log.d("abcdefg",value+" is value ");

        String xAxisString="";

        switch ((int)value){
            case 0:
                Calendar today = Calendar.getInstance();
                xAxisString= String.valueOf(today.get(Calendar.DAY_OF_MONTH))+" "+mMonths[today.get(Calendar.MONTH)];
                break;
            default:
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_MONTH, -((int)value));
                xAxisString= String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))+" "+mMonths[calendar.get(Calendar.MONTH)];

//            case 1:
//                Calendar calendar = Calendar.getInstance();
//                calendar.add(Calendar.DAY_OF_MONTH, -1);
//                xAxisString= String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))+" "+mMonths[calendar.get(Calendar.MONTH)];
//                break;
//            case 2:
//                Calendar calendar2 = Calendar.getInstance();
//                calendar2.add(Calendar.DAY_OF_MONTH, -2);
//                xAxisString= String.valueOf(calendar2.get(Calendar.DAY_OF_MONTH))+" "+mMonths[calendar2.get(Calendar.MONTH)];
//            break;
//            case 3:
//                Calendar calendar3 = Calendar.getInstance();
//                calendar3.add(Calendar.DAY_OF_MONTH, -3);
//                xAxisString= String.valueOf(calendar3.get(Calendar.DAY_OF_MONTH))+" "+mMonths[calendar3.get(Calendar.MONTH)];
//            break;
//            case 4:
//                Calendar calendar4 = Calendar.getInstance();
//                calendar4.add(Calendar.DAY_OF_MONTH, -4);
//                xAxisString= String.valueOf(calendar4.get(Calendar.DAY_OF_MONTH))+" "+mMonths[calendar4.get(Calendar.MONTH)];
//            break;
//            case 5:
//                Calendar calendar5 = Calendar.getInstance();
//                calendar5.add(Calendar.DAY_OF_MONTH, -5);
//                xAxisString= String.valueOf(calendar5.get(Calendar.DAY_OF_MONTH))+" "+mMonths[calendar5.get(Calendar.MONTH)];
//            break;
//            case 6:
//                Calendar calendar6 = Calendar.getInstance();
//                calendar6.add(Calendar.DAY_OF_MONTH, -6);
//                xAxisString= String.valueOf(calendar6.get(Calendar.DAY_OF_MONTH))+" "+mMonths[calendar6.get(Calendar.MONTH)];
//            break;

        }
        return xAxisString;





//        int days = (int) value;
//
//        int year = determineYear(days);
//
//        int month = determineMonth(days);
//        String monthName = mMonths[month % mMonths.length];
//        String yearName = String.valueOf(year);
//
//        if (chart.getVisibleXRange() > 30 * 6) {
//
//            return monthName + " " + yearName;
//        } else {
//
//            int dayOfMonth = determineDayOfMonth(days, month + 12 * (year - 2016));
//
//            String appendix = "th";
//
//            switch (dayOfMonth) {
//                case 1:
//                    appendix = "st";
//                    break;
//                case 2:
//                    appendix = "nd";
//                    break;
//                case 3:
//                    appendix = "rd";
//                    break;
//                case 21:
//                    appendix = "st";
//                    break;
//                case 22:
//                    appendix = "nd";
//                    break;
//                case 23:
//                    appendix = "rd";
//                    break;
//                case 31:
//                    appendix = "st";
//                    break;
//            }
//
//            if (value==4){
//                return "kjklll";
//            }
//            return "abcd";
//
////            return dayOfMonth == 0 ? "" : dayOfMonth + appendix + " " + monthName;
//        }
    }

    private int getDaysForMonth(int month, int year) {

        // month is 0-based

        if (month == 1) {
            int x400 = month % 400;
            if (x400 < 0)
            {
                x400 = -x400;
            }
            boolean is29 = (month % 4) == 0 && x400 != 100 && x400 != 200 && x400 != 300;

            return is29 ? 29 : 28;
        }

        if (month == 3 || month == 5 || month == 8 || month == 10)
            return 30;
        else
            return 31;
    }

    private int determineMonth(int dayOfYear) {

        int month = -1;
        int days = 0;

        while (days < dayOfYear) {
            month = month + 1;

            if (month >= 12)
                month = 0;

            int year = determineYear(days);
            days += getDaysForMonth(month, year);
        }

        return Math.max(month, 0);
    }

    private int determineDayOfMonth(int days, int month) {

        int count = 0;
        int daysForMonths = 0;

        while (count < month) {

            int year = determineYear(daysForMonths);
            daysForMonths += getDaysForMonth(count % 12, year);
            count++;
        }

        return days - daysForMonths;
    }

    private int determineYear(int days) {

        if (days <= 366)
            return 2016;
        else if (days <= 730)
            return 2017;
        else if (days <= 1094)
            return 2018;
        else if (days <= 1458)
            return 2019;
        else
            return 2020;

    }
}