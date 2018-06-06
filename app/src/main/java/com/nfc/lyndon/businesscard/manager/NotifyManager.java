package com.nfc.lyndon.businesscard.manager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.nfc.lyndon.businesscard.R;

/**
 * 通知管理
 */
public class NotifyManager extends ContextWrapper {

    private NotificationManager mNotificationManager;

    public static final String CHANNEL_ID = "default";
    private static final String CHANNEL_DESCRIPTION = "Thank you for using!";

    public NotifyManager(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,
                    base.getResources().getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(CHANNEL_DESCRIPTION);
            notificationChannel.enableLights(true);
            getNotificationManager().createNotificationChannel(notificationChannel);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotificationChannel(){
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "default_name", NotificationManager.IMPORTANCE_HIGH);
        getNotificationManager().createNotificationChannel(channel);
    }

    public NotificationCompat.Builder getNotification(String title, String content, PendingIntent intent) {
        NotificationCompat.Builder builder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();
            builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        } else {
            builder = new NotificationCompat.Builder(this, null);
            builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        }
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setContentIntent(intent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        //点击自动删除通知
        builder.setAutoCancel(true);
        return builder;
    }

    public NotificationCompat.Builder getNotification(int progress, PendingIntent intent) {
        NotificationCompat.Builder builder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();
            builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        } else {
            builder = new NotificationCompat.Builder(this, null);
        }

        builder.setDefaults(NotificationCompat.FLAG_ONLY_ALERT_ONCE);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(intent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setOngoing(true);
        //点击自动删除通知
        builder.setAutoCancel(true);
        builder.setOnlyAlertOnce(true);
        return builder;
    }

    public void notify(int id, NotificationCompat.Builder builder) {
        if (getNotificationManager() != null) {
            getNotificationManager().notify(id, builder.build());
        }
    }

    public NotificationManager getNotificationManager() {
        if (mNotificationManager == null)
            mNotificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        return mNotificationManager;
    }
}
