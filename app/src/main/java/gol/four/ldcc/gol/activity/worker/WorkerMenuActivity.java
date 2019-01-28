package gol.four.ldcc.gol.activity.worker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import gol.four.ldcc.gol.R;

public class WorkerMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_menu);


        Button door_button = findViewById(R.id.door_menu_button);

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
