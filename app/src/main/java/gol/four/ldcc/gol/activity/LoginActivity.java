package gol.four.ldcc.gol.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import gol.four.ldcc.gol.R;
import gol.four.ldcc.gol.activity.worker.WorkerMenuActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button login_button = findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,WorkerMenuActivity.class);
                //intent.putExtra("text",String.valueOf(editText.getText()));
                startActivity(intent);
                finish();
            }
        });


    }
}
