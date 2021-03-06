package gol.four.ldcc.gol.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import gol.four.ldcc.gol.adapter.DMReqAdapter;
import gol.four.ldcc.gol.R;
import gol.four.ldcc.gol.model.DMReqItem;
import gol.four.ldcc.gol.network.GolService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DMReqFragment extends Fragment {
    private ListView list;
    private DMReqAdapter adapter;

    @Override
    public void onResume() {
        super.onResume();
    }

    public DMReqFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this gol.four.ldcc.gol.fragment
        View v = inflater.inflate(R.layout.fragment_dm_req, container, false);
        init(v);
        return v;
    }

    private void init(View v){
        list = (ListView) v.findViewById(R.id.req_lv);
        adapter = new DMReqAdapter();
        list.setAdapter(adapter);

        GolService service = GolService.instance();
        //applystate=0, 대기 상태의 직원들의 정보를 보내줌
        Call<ArrayList<JsonObject>> res = service.getService().getApplies(0);
        res.enqueue(new Callback<ArrayList<JsonObject>>() {
            @Override
            public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {
                ArrayList<JsonObject> data = response.body();

                if(data != null) {
                    for (int i = 0; i < data.size(); i++) {
                        JsonObject temp = data.get(i);

                        JsonObject empInfo = temp.getAsJsonObject("employee_idx");
                        String state = temp.get("apply_state").getAsString();

                        DMReqItem item = new DMReqItem();
                        item.setName(empInfo.get("login_id").getAsString().replaceAll("\"", ""));
                        item.setTime(temp.get("register_date").getAsString().split("T")[0]);

                        adapter.add(item);
                    }

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<JsonObject>> call, Throwable t) {
                Log.d("DMRF", t.getMessage());
            }
        });
    }

}
