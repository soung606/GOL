package gol.four.ldcc.gol.activity.worker;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import gol.four.ldcc.gol.R;
import gol.four.ldcc.gol.activity.LoginActivity;
import gol.four.ldcc.gol.fcm.FCMService;
import gol.four.ldcc.gol.network.GolService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoorActivity extends AppCompatActivity {

    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    private NdefMessage ndefMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door);

        SharedPreferences sf = getSharedPreferences("My_File", Activity.MODE_PRIVATE);

        final ImageButton request_open = findViewById(R.id.open_request);

        final String pk = sf.getString("pk","");

        request_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseBody> result = GolService.instance().getService().postApplies(0,0,null, pk,0);

                result.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (request_open.isClickable()) {
                            request_open.setClickable(false);
                        }
                        request_open.setImageResource(R.drawable.request_wait);
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"요청이 전송되지 않았습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT);
                    }
                });

               //서버 신호가 true 면
                Log.d("MESSSSSSSSSSSSSAGE",FCMService.isOpenNotOpen()+"");
                while(FCMService.isReceiveMessage()){
                    if(FCMService.isOpenNotOpen()){
                      request_open.setImageResource(R.drawable.request_ok);
                    }
                    //서버 신호가 아니면
                    else{
                        request_open.setImageResource(R.drawable.request_no);
                    }
                }

            }



        });


    }
    @Override
    protected void onPause() {
        if(nfcAdapter != null){
            nfcAdapter.disableForegroundDispatch(DoorActivity.this);
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(nfcAdapter != null){
            nfcAdapter.setNdefPushMessage( ndefMessage,DoorActivity.this);
        }
    }

    public static NdefRecord createNewTextRecord(String text, Locale locale, boolean encodelnUtf8){
        byte[] langBytes = locale.getLanguage().getBytes(Charset.forName("US-ASCII"));
        Charset utfEncoding = encodelnUtf8 ? Charset.forName("UTF-8"):Charset.forName("UTF-16");
        byte[] textBytes = text.getBytes(utfEncoding);
        int utfBit = encodelnUtf8 ? 0:(1<<7);
        char status = (char)(utfBit + langBytes.length);
        byte[] data = new byte[1 + langBytes.length + textBytes.length];
        data[0] = (byte)status;
        System.arraycopy(langBytes, 0, data, 1, langBytes.length);
        System.arraycopy(textBytes, 0, data, 1 + langBytes.length, textBytes.length);

        return new NdefRecord(NdefRecord.TNF_WELL_KNOWN,NdefRecord.RTD_TEXT, new byte[0], data);

    }
}
