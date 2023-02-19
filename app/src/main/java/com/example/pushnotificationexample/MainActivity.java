package com.example.pushnotificationexample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPushNotifications = (Button) findViewById(R.id.btn_pushNotifications);
        btnPushNotifications.setOnClickListener(this);

        Button btnPushNotifications2 = (Button) findViewById(R.id.btn_pushNotifications_2);
        btnPushNotifications2.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pushNotifications:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    pushNotification();
                }
                break;
            case R.id.btn_pushNotifications_2:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    pushNotification2();
                }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void pushNotification() {
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setContentTitle("Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title")
//                .setContentText("Content Content Content Content Content Content Content Content Content Content Content Content Content")
//                .setStyle(new NotificationCompat.BigTextStyle()
//                        .bigText("Much longer text that cannot fit one line..."))
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, DetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.anonymus_icon);
        // Sound of system
        // Từ Android 8 trở đi, custom sound cần setting vào channel
        Uri soundDefault = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setSmallIcon(R.drawable.bell_icon)
                .setContentTitle("My notification 1")
                .setContentText("My content 1")
                .setLargeIcon(bitmap)
                .setSound(soundDefault)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(getNotificationId(), builder2.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void pushNotification2() {
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setContentTitle("Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title")
//                .setContentText("Content Content Content Content Content Content Content Content Content Content Content Content Content")
//                .setStyle(new NotificationCompat.BigTextStyle()
//                        .bigText("Much longer text that cannot fit one line..."))
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, DetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.anonymus_icon);
        // Sound of file in raw folder
        // Từ Android 8 trở đi, custom sound cần setting vào channel
        Uri soundCustom = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sound);

        NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_2)
                .setSmallIcon(R.drawable.bell_icon)
                .setContentTitle("My notification 2")
                .setContentText("My content 1")
                .setLargeIcon(bitmap)
                .setSound(soundCustom)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(getNotificationId(), builder2.build());
    }

    private int getNotificationId() {
        return 1;
    }
}