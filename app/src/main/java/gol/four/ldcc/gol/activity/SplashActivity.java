package gol.four.ldcc.gol.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import gol.four.ldcc.gol.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseApp.initializeApp(getApplicationContext());
                getFCMToken();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        }, 1500);
    }

    private void getFCMToken(){
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("FCM Login", "getInstanceId failed", task.getException());
                            return;
                        }
                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        //add the token to sharedPreference
                        SharedPreferences sharedPreferences = getSharedPreferences("My_File", 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString("token", token);
                        Log.d("SPLASH TOKEN", token);
                        editor.commit();

                        //Log.d("FCM Login", token);
                    }
                });
        // [END retrieve_current_token]
    }
}
