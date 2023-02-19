package com.example.pushnotificationexample;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

public class MyApplication extends Application {

    public static final String CHANNEL_ID = "CHANNEL_ID_1";
    public static final String CHANNEL_ID_2 = "CHANNEL_ID_2";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Từ Android 8 trở đi, custom sound cần setting vào channel
            // Sound of system
            Uri soundDefault = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            // Sound of file in raw folder
            Uri soundCustom = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sound);
            AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build();

            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(description);
            channel.setSound(soundDefault, audioAttributes);

            CharSequence name2 = getString(R.string.channel_name_2);
            String description2 = getString(R.string.channel_description_2);
            NotificationChannel channel2 = new NotificationChannel(CHANNEL_ID_2, name2, NotificationManager.IMPORTANCE_DEFAULT);
            channel2.setDescription(description2);
            channel.setSound(soundCustom, audioAttributes);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (null != notificationManager) {
                notificationManager.createNotificationChannel(channel);
                notificationManager.createNotificationChannel(channel2);
            }
        }
    }
}
