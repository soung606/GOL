package gol.four.ldcc.gol.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import gol.four.ldcc.gol.network.GolService;
import gol.four.ldcc.gol.R;
import gol.four.ldcc.gol.activity.admin.AdminMenuActivity;
import gol.four.ldcc.gol.activity.worker.WorkerMenuActivity;
import gol.four.ldcc.gol.databinding.ActivityLoginBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    String sfName = "My_File";
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        getFCMToken();
        final CheckBox auto_login = findViewById(R.id.auto_login);
        SharedPreferences sf = getSharedPreferences(sfName, Activity.MODE_PRIVATE);
        if(sf.getBoolean("isFirst",false) == false){

            binding.loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String id = binding.idField.getText().toString();
                    final String password = binding.passwordField.getText().toString();

                    Call<ArrayList<JsonObject>> result = GolService.instance().getService().login(id, password);
                    result.enqueue(new Callback<ArrayList<JsonObject>>() {
                        @Override
                        public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {
                            //Log.d("TEST!!", response.body().toString());
                            //[{"model":"GoL_Server.employee","pk":2,"fields":{"login_id":"qw","password":"12","name":"test","dt_register":"2019-01-29T00:52:48.998Z","authority":0,"auth":0}}]

                            JsonObject user_fields = (JsonObject) response.body().get(0).get("fields");
                            String auth = user_fields.get("authority").toString();
                            if(auth.equals("1")){
                                Intent intent=new Intent(LoginActivity.this,AdminMenuActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Intent intent=new Intent(LoginActivity.this,WorkerMenuActivity.class);
                                startActivity(intent);
                            }
                            if(auto_login.isChecked() == true){
                                SharedPreferences sf = getSharedPreferences(sfName, 0);
                                SharedPreferences.Editor editor = sf.edit();//저장하려면 editor가 필요
                                editor.putString("id", id); // 아이디 입력
                                editor.putString("password", password);// 비번입력
                                editor.putString("auth", auth);//권한저장
                                editor.putBoolean("isFirst", true);//자동로그인 저장
                                editor.commit(); // 파일에 최종 반영함

                            }

                            finish();
                        }
                        @Override
                        public void onFailure(Call<ArrayList<JsonObject>> call, Throwable t) {
                            Log.d("TEST", t.getMessage());
                        }
                    });
                }
            });

        }
        else{
            if(sf.getString("auth","").equals("1")){
                Intent intent=new Intent(LoginActivity.this,AdminMenuActivity.class);
                startActivity(intent);
            }
            else{
                Intent intent=new Intent(LoginActivity.this,WorkerMenuActivity.class);
                startActivity(intent);
            }
            finish();
        }
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

                                // Log and toast

                                Log.d("FCM Login", token);
                                Toast.makeText(LoginActivity.this, token, Toast.LENGTH_SHORT).show();
                            }
                        });
                // [END retrieve_current_token]
    }
}
