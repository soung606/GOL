package gol.four.ldcc.gol.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import gol.four.ldcc.gol.R;
import gol.four.ldcc.gol.adapter.IMListAdapter;
import gol.four.ldcc.gol.model.IMListItem;
import gol.four.ldcc.gol.network.GolService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IMListFragment extends Fragment {
    ListView lv;
    IMListAdapter adapter;
    public IMListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_im_list, container, false);
        init(v);

        return v;
    }

    private void init(View v){
        lv = v.findViewById(R.id.im_item_lv);
        adapter = new IMListAdapter();
        lv.setAdapter(adapter);

        addData();
    }

    private void addData(){
        GolService golService = GolService.instance();
        Call<ArrayList<JsonObject>> res = golService.getService().getMaterials();
        res.enqueue(new Callback<ArrayList<JsonObject>>() {
            @Override//bnm,.
            public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {
                Log.d("IMLF", response.body().toString());

                ArrayList<JsonObject> data = response.body();
                for(int i = 0; i < data.size(); i++){
                    IMListItem item = new IMListItem();

                    item.setCode(data.get(i).get("code").toString());
                    item.setName(data.get(i).get("name").toString().replaceAll("\"", ""));
                    item.setWeight(data.get(i).get("weight").toString());

                    Log.d("Test", item.getName());

                    adapter.add(item);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<JsonObject>> call, Throwable t) {
                Log.d("IMLF", t.getMessage());
            }
        });
    }


}
