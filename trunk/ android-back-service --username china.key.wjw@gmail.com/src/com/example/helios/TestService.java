package com.example.helios;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class TestService extends Service {
	private final int NOTIFICATION_ID = 1;

	@Override
	public void onStart(Intent intent, int startId) {
		// Log.e(TAG, "============> TestService.onBind");
		// userful
			new Thread(new Runnable() {
			public void run() {
				// TODO
				showNotification();
			}
		}).start();
	}

	// display a notifications
	public void showNotification() {
		NotificationManager nManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		Notification n = new Notification(R.drawable.ic_launcher,
				"new message!", System.currentTimeMillis());
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, HeliosActivity.class), 0);
		n.setLatestEventInfo(this, "NO", "Order", contentIntent);
		nManager.notify(NOTIFICATION_ID, n); // ÈÎÎñÀ¸Æô¶¯
	}

	// call the Activity
	public void callActivity() {
		Intent intent = new Intent(TestService.this, HeliosActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	// test log
	private static final String TAG = "TestService";

	@Override
	public void onCreate() {
		Log.e(TAG, "============> TestService.onCreate");
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.e(TAG, "============> TestService.onUnbind");
		return null;
	}

	@Override
	public boolean onUnbind(Intent i) {
		Log.e(TAG, "============> TestService.onUnbind");
		return false;
	}

	@Override
	public void onRebind(Intent i) {
		Log.e(TAG, "============> TestService.onRebind");
	}

	@Override
	public void onDestroy() {
		Log.e(TAG, "============> TestService.onDestroy");
	}

}
