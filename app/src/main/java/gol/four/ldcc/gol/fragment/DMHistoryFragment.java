package gol.four.ldcc.gol.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import gol.four.ldcc.gol.adapter.DMHistoryAdapter;
import gol.four.ldcc.gol.R;
import gol.four.ldcc.gol.model.DMHistoryItem;
import gol.four.ldcc.gol.network.GolService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DMHistoryFragment extends Fragment {
    ListView list;
    DMHistoryAdapter adapter;

    @Override
    public void onResume() {
        super.onResume();
    }

    public DMHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this gol.four.ldcc.gol.fragment
        View v = inflater.inflate(R.layout.fragment_dm_history, container, false);

        init(v);
        return v;
    }

    private void init(View v){
        list = (ListView) v.findViewById(R.id.io_lv);
        adapter = new DMHistoryAdapter();

        list.setAdapter(adapter);

        GolService.instance().getService().getApplies(2).enqueue(new Callback<ArrayList<JsonObject>>() {
            @Override
            public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {
                ArrayList<JsonObject> data = response.body();

                if (data != null) {
                    for (int i = 0; i < data.size(); i++) {
                        JsonObject temp = data.get(i);

                        JsonObject confirm = temp.getAsJsonObject("confirm_idx");
                        JsonObject empInfo = temp.getAsJsonObject("employee_idx");

                        DMHistoryItem item = new DMHistoryItem();
                        item.setTime(temp.get("confirm_date").getAsString().split("T")[0]);
                        item.setAdmin(confirm.get("login_id").getAsString());
                        item.setUser(empInfo.get("login_id").getAsString());

                        adapter.add(item);
                    }

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<JsonObject>> call, Throwable t) {
                Log.d("DMHF", t.getMessage());
            }
        });
    }


}
