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
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.walton.internetdataplan.AppManager;
import com.walton.internetdataplan.R;
import com.walton.internetdataplan.adapters.RobiMigrationAdapter;
import com.walton.internetdataplan.utitls.AppConstants;
import com.walton.internetdataplan.utitls.AppsSettings;
import com.walton.internetdataplan.utitls.KeyBoard;

public class RobiMigrationFragment extends Fragment {
    public static RecyclerView recyclerView;
    public Context mContext;
    public Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mActivity = getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        KeyBoard.hideKeyBoard(mActivity);
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.recycler_view_layout, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        if(AppsSettings.getAppsSettings(mContext).getLanguage().equals("eng"))
        {
            AppManager.getInstance(mContext).retrieveMigrationList(mContext, AppConstants.MIGRATION_ROBI, "");
        }
        else
        {
            AppManager.getInstance(mContext).retrieveMigrationList(mContext, AppConstants.MIGRATION_ROBI_BN, "");
        }

        RobiMigrationAdapter mAdapter = new RobiMigrationAdapter(mContext, AppManager.mMigrationList, false);
        recyclerView.setAdapter(mAdapter);


        return view;
    }
}
