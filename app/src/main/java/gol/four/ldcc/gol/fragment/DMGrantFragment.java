package gol.four.ldcc.gol.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import gol.four.ldcc.gol.R;
import gol.four.ldcc.gol.adapter.DMGrantAdapter;
import gol.four.ldcc.gol.model.DMGrantItem;
import gol.four.ldcc.gol.network.GolService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DMGrantFragment extends Fragment {
    EditText editText;
    Button searchBtn;
    ListView lv;
    DMGrantAdapter adapter;
    GolService golService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this gol.four.ldcc.gol.fragment
        View v = inflater.inflate(R.layout.fragment_dm_grant, container, false);
        init(v);

        return v;
    }

    public void init(View v){
        golService = GolService.instance();

        editText = (EditText) v.findViewById(R.id.dm_grant_edit);
        searchBtn = (Button) v.findViewById(R.id.btn_grant);
        lv = (ListView) v.findViewById(R.id.grant_lv);
        adapter = new DMGrantAdapter();
        lv.setAdapter(adapter);

        initValueAdd();


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val = editText.getText().toString();
                if(!val.equals("")) {
                    adapter.clear();
                    Call<ArrayList<JsonObject>> res = golService.getService().getNameApplies(val);
                    res.enqueue(new Callback<ArrayList<JsonObject>>() {
                        @Override
                        public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {
                            ArrayList<JsonObject> data = response.body();

                            if(data != null){
                                for(int i = 0; i < data.size(); i++) {
                                    JsonObject temp = data.get(i);
                                    JsonObject empInfo = temp.getAsJsonObject("employee_idx");
                                    DMGrantItem item = new DMGrantItem();
                                    item.setTime(temp.get("register_date").getAsString().split("T")[0]);
                                    item.setName(empInfo.get("name").getAsString().replaceAll("\"", ""));

                                    int status = temp.get("apply_state").getAsInt();

                                    if (status == 0 || status == 1)
                                        item.setIsGrant("권한 부여");
                                    else
                                        item.setIsGrant("권한 해제");

                                    adapter.add(item);
                                }
                                adapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<JsonObject>> call, Throwable t) {
                            Log.d("DMGR", t.getMessage());
                        }
                    });
                }
            }
        });
    }

    public void initValueAdd(){
        Call<ArrayList<JsonObject>> res = golService.getService().getApplies();
        res.enqueue(new Callback<ArrayList<JsonObject>>() {
            @Override
            public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {
                ArrayList<JsonObject> data = response.body();

                if(data != null){
                    for(int i = 0; i < data.size(); i++) {
                        JsonObject temp = data.get(i);
                        JsonObject empInfo = temp.getAsJsonObject("employee_idx");
                        DMGrantItem item = new DMGrantItem();
                        item.setTime(temp.get("register_date").getAsString().split("T")[0]);
                        item.setName(empInfo.get("name").getAsString().replaceAll("\"", ""));

                        int status = temp.get("apply_state").getAsInt();

                        if (status == 0 || status == 1)
                            item.setIsGrant("권한 부여");
                        else
                            item.setIsGrant("권한 해제");

                        adapter.add(item);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<JsonObject>> call, Throwable t) {
                Log.d("DMGF", t.getMessage());
            }
        });
    }



}
