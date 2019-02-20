package com.walton.internetdataplan.utitls;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Call3rdPartyApps {

	public static void openBrowser(Context mContext,String url) {

		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);

	}

}
