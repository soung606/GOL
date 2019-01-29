package gol.four.ldcc.gol.activity.worker;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import gol.four.ldcc.gol.R;
import gol.four.ldcc.gol.activity.LoginActivity;
import gol.four.ldcc.gol.activity.admin.AdminMenuActivity;

public class WorkerMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_menu);

        Button door_button = findViewById(R.id.door_menu_button);

        Button logout = findViewById(R.id.logout);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("My_File", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();

                Intent intent=new Intent(WorkerMenuActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        door_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WorkerMenuActivity.this,DoorActivity.class);
                //intent.putExtra("text",String.valueOf(editText.getText()));
                startActivity(intent);
            }
        });

    }
}
