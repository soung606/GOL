package gol.four.ldcc.gol.activity.worker;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.nio.charset.Charset;
import java.util.Locale;

import gol.four.ldcc.gol.R;
import gol.four.ldcc.gol.fcm.FCMService;
import gol.four.ldcc.gol.network.GolService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoorResultActivity extends AppCompatActivity {

    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    private NdefMessage ndefMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_result);

        final ImageButton open_result = findViewById(R.id.open_result);

        open_result.setClickable(false);

        Log.d("AreYouInHere",FCMService.isReceiveMessage()+"");

        if(FCMService.isOpenNotOpen()){
            open_result.setImageResource(R.drawable.request_ok);
        }
        else{
            open_result.setImageResource(R.drawable.request_no);
        }

    }

}
