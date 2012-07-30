package com.example.helios;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {
	private static final String TAG = "BootReceiver";

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub

		Log.e(TAG, "============> BootReceiver.onReceive");
		// ¿ª»úÆô¶¯service
		Intent mBootIntent = new Intent(arg0, TestService.class);
		mBootIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		arg0.startService(mBootIntent);
	}

}
