package com.walton.internetdataplan.nvai;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.walton.internetdataplan.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by DELL on 12/5/2016.
 */

public class NetUsageListViewAdapter extends ArrayAdapter<NetUsageAppModel> {

     ArrayList<NetUsageAppModel> netUsageAppModelsArrayList;
    Context mContext;

    public NetUsageListViewAdapter(ArrayList<NetUsageAppModel> data, Context context) {
        super(context, R.layout.nvai_net_usage_listview_row_item, data);
        this.netUsageAppModelsArrayList = data;
        this.mContext=context;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        NetUsageAppModel netUsageAppModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        //  final View rowView;
        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.nvai_net_usage_listview_row_item, parent, false);
            viewHolder.appIcon= (ImageView) convertView.findViewById(R.id.ivNetUsageListAppIcon);
            viewHolder.appName = (TextView) convertView.findViewById(R.id.tvNetUsageListAppName);
            viewHolder.appTx = (TextView) convertView.findViewById(R.id.tvTX);
            viewHolder.appRx = (TextView) convertView.findViewById(R.id.tvRx);
            viewHolder.appRTx = (TextView) convertView.findViewById(R.id.tvRTx);


            // rowView=convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            //  rowView=convertView;
        }


        //
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(1);
//        df.format(mParchentage);
        //




        viewHolder.appName.setText(""+netUsageAppModel.getAppName());
        if(Float.parseFloat(netUsageAppModel.getTxData())>=1024)
        {
            float abc=Float.parseFloat(netUsageAppModel.getTxData())/1024;
            if(abc>=1024)
            {
                float abcd=abc/1024;
                viewHolder.appTx.setText("" + df.format(abcd) + " GB");
            }
            else {
                viewHolder.appTx.setText("" + df.format(abc) + " MB");
            }
        }
        else
        {
            viewHolder.appTx.setText(""+netUsageAppModel.getTxData()+ " KB");
        }

        if(Float.parseFloat(netUsageAppModel.getRxData())>=1024)
        {
            float abc=Float.parseFloat(netUsageAppModel.getRxData())/1024;
            if(abc>=1024)
            {
                float abcd=abc/1024;
                viewHolder.appRx.setText("" + df.format(abcd) + " GB");
            }
            else {
                viewHolder.appRx.setText("" + df.format(abc) + " MB");
            }
        }
        else
        {
            viewHolder.appRx.setText(""+netUsageAppModel.getRxData()+ " KB");
        }

        if(Float.parseFloat(netUsageAppModel.getRtxData())>=1024)
        {
            float abc=Float.parseFloat(netUsageAppModel.getRtxData())/1024;
            if(abc>=1024)
            {
                float abcd=abc/1024;
                viewHolder.appRTx.setText("" + df.format(abcd) + " GB");
            }
            else {
                viewHolder.appRTx.setText("" + df.format(abc) + " MB");
            }
        }
        else
        {
            viewHolder.appRTx.setText(""+netUsageAppModel.getRtxData()+ " KB");
        }



        viewHolder.appIcon.setImageDrawable(netUsageAppModel.getAppIcon());


        return convertView;
    }





    private static class ViewHolder {
        ImageView appIcon;
        TextView appName;
        TextView appTx;
        TextView appRx;
        TextView appRTx;

    }




}
