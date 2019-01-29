package gol.four.ldcc.gol.activity.admin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


import gol.four.ldcc.gol.R;
import gol.four.ldcc.gol.activity.LoginActivity;
import gol.four.ldcc.gol.activity.worker.WorkerMenuActivity;

public class AdminMenuActivity extends AppCompatActivity {

    private LinearLayout itemClick, doorClick;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        init();
    }

    private void init(){
        doorClick = (LinearLayout) findViewById(R.id.door_man_click);
        itemClick = (LinearLayout) findViewById(R.id.item_man_click);
        btnLogout = (Button) findViewById(R.id.btn_admin_logout);

        //컨테이너 출입 관리
        doorClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DoorManageActivity.class));
            }
        });
        //자재관리
        itemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ItemManageActivity.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("My_File", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();

                Intent intent=new Intent(AdminMenuActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
