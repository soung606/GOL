package gol.four.ldcc.gol.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import gol.four.ldcc.gol.R;
import gol.four.ldcc.gol.activity.LoginActivity;

/**
 * push message를 받아 처리하는 클래스
 * */
public class FCMService extends FirebaseMessagingService {
    public FCMService() { }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        sendNotification(data);
    }

    private void sendNotification(Map<String, String> data){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent content = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(data.get("title"))
                .setContentText(data.get("msg"))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setVibrate(new long[]{1000, 1000})
                .setLights(Color.WHITE,1500,1500)
                .setContentIntent(content);

        NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(0 /* ID of notification */, nBuilder.build());
    }

    @Override
    public void onNewToken(String s) {
        Log.d("FCM TOKEN", s);

        sendToken(s);
    }

    private void sendToken(String s){

    }
}
