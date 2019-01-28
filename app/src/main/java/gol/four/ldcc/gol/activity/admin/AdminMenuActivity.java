package gol.four.ldcc.gol.activity.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import gol.four.ldcc.gol.R;

public class AdminMenuActivity extends AppCompatActivity {

    private LinearLayout itemClick, doorClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        init();
    }

    private void init(){
        doorClick = (LinearLayout) findViewById(R.id.door_man_click);
        itemClick = (LinearLayout) findViewById(R.id.item_man_click);

        doorClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DoorManageActivity.class));
            }
        });
    }
}
