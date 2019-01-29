package gol.four.ldcc.gol.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import gol.four.ldcc.gol.Network.GolService;
import gol.four.ldcc.gol.R;
import gol.four.ldcc.gol.activity.admin.AdminMenuActivity;
import gol.four.ldcc.gol.activity.worker.WorkerMenuActivity;
import gol.four.ldcc.gol.databinding.ActivityLoginBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,WorkerMenuActivity.class);
                //intent.putExtra("text",String.valueOf(editText.getText()));
                //startActivity(intent);
                startActivity(new Intent(getApplicationContext(), AdminMenuActivity.class));
                finish();

                String id = binding.idField.getText().toString();
                String password = binding.passwordField.getText().toString();

                Call<ArrayList<JsonObject>> result = GolService.instance().getService().login(id, password);
                result.enqueue(new Callback<ArrayList<JsonObject>>() {
                    @Override
                    public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {
                        Log.d("TEST", response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<ArrayList<JsonObject>> call, Throwable t) {
                        Log.d("TEST", "FAIL");
                    }
                });

            }
        });
    }
}
