package gol.four.ldcc.gol.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import gol.four.ldcc.gol.R;
import gol.four.ldcc.gol.adapter.IMHistoryAdapter;
import gol.four.ldcc.gol.model.IMHistoryItem;
import gol.four.ldcc.gol.network.GolService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IMHistoryFragment extends Fragment {
    ListView lv;
    IMHistoryAdapter adapter;
    public IMHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_im_history, container, false);
        init(v);
        return v;
    }

    private void init(View v){
        lv = (ListView) v.findViewById(R.id.im_history_lv);
        adapter = new IMHistoryAdapter();
        lv.setAdapter(adapter);

        addData();
    }

    private void addData(){
        GolService service = GolService.instance();
        Call<ArrayList<JsonObject>> res = service.getService().getMaterialHistory();
        res.enqueue(new Callback<ArrayList<JsonObject>>() {
            @Override
            public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {
                ArrayList<JsonObject> data = response.body();

                for(int i = 0; i < data.size(); i++){
                    IMHistoryItem item = new IMHistoryItem();
                    item.setName(data.get(i).get("employee_idx").toString());
                    item.setMatName(data.get(i).get("material_code").toString());
                    item.setNum(data.get(i).get("material_num").toString());

                    Log.d("IMHF", item.getName());

                    adapter.add(item);
                }

                adapter.notifyDataSetChanged();//바뀐 데이터 업데이트
            }

            @Override
            public void onFailure(Call<ArrayList<JsonObject>> call, Throwable t) {
                Log.d("IMHF", t.getMessage());
            }
        });
    }

}
