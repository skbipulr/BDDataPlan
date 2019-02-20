/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.walton.internetdataplan.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.walton.internetdataplan.AppManager;
import com.walton.internetdataplan.R;
import com.walton.internetdataplan.adapters.AirtelPrepaidAdapter;
import com.walton.internetdataplan.utitls.KeyBoard;

public class AirtelPrepaidFragment extends Fragment {
    public static RecyclerView recyclerView;
    public Context mContext;
    AirtelPrepaidAdapter airtelAdapter;
    public Activity mActivity;
    public TextView txtLoading2;
    public ProgressBar splash_progress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getContext();
        mActivity = getActivity();
        KeyBoard.hideKeyBoard(mActivity);
        //   AirtelActivity.mSearchAction.setVisibility(View.VISIBLE);
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.airtel_recycler_layout, container, false);
        splash_progress=(ProgressBar) view.findViewById(R.id.splash_progress);
        splash_progress.setVisibility(View.VISIBLE);
        txtLoading2=(TextView) view.findViewById(R.id.txtLoading2);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        AirtelPrepaidFragment.MyAsyncTask myAsyncTask = new AirtelPrepaidFragment.MyAsyncTask();
        myAsyncTask.execute();


        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mContext=getContext();
        super.onCreate(savedInstanceState);
    }

    public class MyAsyncTask extends AsyncTask {
        public MyAsyncTask()
        {

        }
        @Override
        protected Object doInBackground(Object[] objects) {
            if (AppManager.mAirtelPrepaidDataPackList.size() == 18) {

            }
            else
            {
                AppManager.getInstance(mContext).retrieveAirtelPrepaidDataPackList(mContext, "");
            }

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Object o) {
            airtelAdapter = new AirtelPrepaidAdapter(mContext, AppManager.mAirtelPrepaidDataPackList, false);
            recyclerView.setAdapter(airtelAdapter);
            splash_progress.setVisibility(View.GONE);
            txtLoading2.setVisibility(View.GONE);
            recyclerView.invalidate();
            // super.onPostExecute(o);
        }
    }

}
