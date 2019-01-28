package gol.four.ldcc.gol.activity.worker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import gol.four.ldcc.gol.R;

public class DoorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door);

        final ImageButton request_open = findViewById(R.id.open_request);

        request_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //응답대기중

               request_open.setImageResource(R.drawable.request_wait);

               request_open.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       //서버 신호가 true 면
                       if(true){
                           request_open.setImageResource(R.drawable.request_ok);
                       }
                       //서버 신호가 아니면
                       else{
                           request_open.setImageResource(R.drawable.request_no);
                       }
                   }
               });




            }
        });
    }
}
