package com.example.android.background.utilities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.VibrationEffect;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.example.android.background.MainActivity;
import com.example.android.background.R;

/**
 * Utility class for creating hydration notifications
 */
public class NotificationUtils {

    private static final String CHANNEL_ID = "reminder_notification_channel";
    private static final int NOTIFICATION_ID = 342;

    public static void remindUserBecauseCharging(Context context) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.main_notification_channel_name);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);

            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context,CHANNEL_ID)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_drink_notification)
                .setLargeIcon(LargeIcon(context))
                .setContentTitle(context.getString(R.string.charging_reminder_notification_title))
                .setContentText(context.getString(R.string.charging_reminder_notification_body))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(context.getString(R.string.charging_reminder_notification_body)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(true);

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }

        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());

    }

    private static final int PENDING_INTENT_ID = 243;

    private static PendingIntent contentIntent(Context context) {

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, PENDING_INTENT_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        return pendingIntent;
    }

    private static Bitmap LargeIcon(Context context) {
        Resources resources = context.getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_cancel_black_24px);
        return bitmap;
    }


}
