package gol.four.ldcc.gol.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import gol.four.ldcc.gol.R;
import gol.four.ldcc.gol.activity.LoginActivity;
import gol.four.ldcc.gol.activity.worker.DoorInitActivity;
import gol.four.ldcc.gol.activity.worker.DoorResultActivity;
import gol.four.ldcc.gol.activity.worker.DoorWaitActivity;

/**
 * push message를 받아 처리하는 클래스
 * */
public class FCMService extends FirebaseMessagingService {
    private static boolean openNotOpen;
    private static boolean receiveMessage;
    public FCMService() {
        openNotOpen = true;
        receiveMessage = false;
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        String auth = remoteMessage.getData().get("auth");
        if(auth.equals("0")){
            sendNotificationAdmin(data);
        }
        else{
            receiveMessage = true;
            String status = remoteMessage.getData().get("status");

            if(status.equals("2")){//open door
                openNotOpen =  true;
            }
            else if(status.equals("1")){
                openNotOpen= false;
            }
            else{
                openNotOpen = false;
            }

            sendNotificationWorker(data);
        }

    }

    private void sendNotificationAdmin(Map<String, String> data){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent content = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(data.get("title"))
                .setContentText(data.get("body"))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setVibrate(new long[]{1000, 1000})
                .setLights(Color.WHITE,1500,1500)
                .setContentIntent(content);

        NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(0 /* ID of notification */, nBuilder.build());

    }

    private void sendNotificationWorker(Map<String, String> data){

        Intent intent = new Intent(getApplicationContext(), DoorResultActivity.class);
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

    public static boolean isOpenNotOpen(){
        return openNotOpen;
    }
    public static boolean isReceiveMessage(){
        return receiveMessage;
    }
    public static void setReceiveMessage(boolean value){
      receiveMessage = value;
    }
}
