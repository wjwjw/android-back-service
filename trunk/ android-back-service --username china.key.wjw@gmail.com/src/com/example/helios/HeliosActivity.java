package com.example.helios;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RemoteViews;

public class HeliosActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //booting or not
        final CheckBox bootCheck = (CheckBox) findViewById(R.id.boot_complete_checkbox);
        final ComponentName cm = new ComponentName("com.example.helios", "com.example.helios.BootReceiver");
        final PackageManager pm = getPackageManager();
        int state = pm.getComponentEnabledSetting(cm);
        if (state != PackageManager.COMPONENT_ENABLED_STATE_DISABLED
//                && state != PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER
                ) {
            bootCheck.setChecked(true);
        }
        bootCheck
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                            boolean isChecked) {
                        int newState = bootCheck.isChecked() ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                                : PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
                        pm.setComponentEnabledSetting(cm, newState,
                                PackageManager.DONT_KILL_APP);
                    }
                });
         
        //sevice start
        Intent intent = new Intent(HeliosActivity.this,TestService.class);
        startService(intent); 
   }
    
     
	// display a notifications
	public void showNotification() {
		NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		Notification notification = new Notification(R.drawable.ic_launcher,
				"new message!", System.currentTimeMillis());

		RemoteViews remoteViews = new RemoteViews(getPackageName(),
				R.layout.notification);
		remoteViews.setImageViewResource(R.drawable.ic_launcher,
				R.drawable.user);
		remoteViews.setTextViewText(R.id.text, "go to the Activity");
		notification.contentView = remoteViews;

		notification.contentIntent = PendingIntent.getActivity(HeliosActivity.this, 0,
				new Intent(HeliosActivity.this, HeliosActivity.class), 0);

		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.defaults |= Notification.DEFAULT_SOUND;
		manager.notify(1, notification);
	}

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
