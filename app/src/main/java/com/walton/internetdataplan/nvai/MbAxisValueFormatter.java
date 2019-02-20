package com.walton.internetdataplan.nvai;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

/**
 * Created by DELL on 12/6/2016.
 */

public class MbAxisValueFormatter implements IAxisValueFormatter {



    private DecimalFormat mFormat;

    public MbAxisValueFormatter() {
        mFormat = new DecimalFormat("###,###,###,##0.0");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormat.format(value) + " Mb";
    }



}
