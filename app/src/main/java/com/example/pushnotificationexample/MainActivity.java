package com.example.pushnotificationexample;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Main Screen");

        Button btnPushNotifications = (Button) findViewById(R.id.btn_pushNotifications);
        btnPushNotifications.setOnClickListener(this);

        Button btnPushNotifications2 = (Button) findViewById(R.id.btn_pushNotifications_2);
        btnPushNotifications2.setOnClickListener(this);

        Button btnPushNotificationsCustom = (Button) findViewById(R.id.btn_pushNotifications_3);
        btnPushNotificationsCustom.setOnClickListener(this);

        Button btnPushNotificationsOpenActivity = (Button) findViewById(R.id.btn_pushNotificationsOpenActivity);
        btnPushNotificationsOpenActivity.setOnClickListener(this);

        Button btnOpenListProductScreen = (Button) findViewById(R.id.btn_open_list_product_screen);
        btnOpenListProductScreen.setOnClickListener(this);
    }

    ActivityResultLauncher<Intent> mLaunchActivity = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        // Handle the Intent
                    }
                }
            });

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
            case R.id.btn_pushNotifications_3:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    pushNotificationCustom();
                }
                break;
            case R.id.btn_pushNotificationsOpenActivity:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    pushNotificationOpenActivityWithBackStack();
                }
                break;
            case R.id.btn_open_list_product_screen:
                // The launcher with the Intent you want to start
                Intent intent = new Intent(this, ListProductActivity.class);
                mLaunchActivity.launch(intent);
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

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.anonymous_icon);
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

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.anonymous_icon);
        // Sound of file in raw folder
        // Từ Android 8 trở đi, custom sound cần setting vào channel
        Uri soundCustom = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sound);

        NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_2)
                .setSmallIcon(R.drawable.bell_icon)
                .setContentTitle("My notification 2")
                .setContentText("My content 2")
                .setLargeIcon(bitmap)
                .setSound(soundCustom)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(getNotificationId(), builder2.build());
    }

    private void pushNotificationCustom() {
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, DetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // Sử dụng RemoteViews để set custom notifications
        // collapse
        RemoteViews customNotifications = new RemoteViews(getPackageName(), R.layout.custom_notification);
        customNotifications.setTextViewText(R.id.txt_custom_notification_title, "Custom Notifications Title");
        customNotifications.setTextViewText(R.id.txt_custom_notification_info, "Custom Notifications Info");
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.anonymous_icon);
        customNotifications.setImageViewBitmap(R.id.img_Large_Icon, bitmap);

        // expanded
        RemoteViews customNotificationsExpanded = new RemoteViews(getPackageName(), R.layout.custom_notification_expended);
        customNotifications.setTextViewText(R.id.txt_custom_notification_title_expanded, "Custom Notifications Title Expanded");
        customNotifications.setTextViewText(R.id.txt_custom_notification_info_expanded, "Custom Notifications Info Expanded");

        // Sound of file in raw folder
        // Từ Android 8 trở đi, custom sound cần setting vào channel
        Uri soundCustom = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sound);

        NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_2)
                .setSmallIcon(R.drawable.bell_icon)
                .setCustomContentView(customNotifications)
                .setCustomBigContentView(customNotificationsExpanded)
                .setSound(soundCustom)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(getNotificationId(), builder2.build());
    }

    private void pushNotificationOpenActivityWithBackStack() {
        // Create an Intent for the activity you want to start
        Intent resultIntent = new Intent(this, DetailActivity.class);
        // Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        // Get the PendingIntent containing the entire back stack
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.anonymous_icon);
        // Sound of file in raw folder
        // Từ Android 8 trở đi, custom sound cần setting vào channel
        Uri soundCustom = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sound);

        NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_2)
                .setSmallIcon(R.drawable.bell_icon)
                .setContentTitle("My title notification to Start Activity with Back Stack")
                .setContentText("My content notification to Start Activity with Back Stack")
                .setLargeIcon(bitmap)
                .setSound(soundCustom)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(resultPendingIntent)
                .setAutoCancel(true);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(getNotificationId(), builder2.build());
    }

    private int getNotificationId() {
        return 1;
    }
}