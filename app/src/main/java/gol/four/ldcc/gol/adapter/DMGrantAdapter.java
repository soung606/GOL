package gol.four.ldcc.gol.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import gol.four.ldcc.gol.R;
import gol.four.ldcc.gol.model.DMGrantItem;
import gol.four.ldcc.gol.network.GolService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DMGrantAdapter extends BaseAdapter {
    ArrayList<DMGrantItem> list;
    GrantViewHolder vh;
    Context context;

    public void clear(){
        list = new ArrayList<>();
        this.notifyDataSetChanged();
    }

    public DMGrantAdapter(){
        list = new ArrayList<>();

    }

    public void add(DMGrantItem i){
        list.add(i);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        context = viewGroup.getContext();
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dm_grant_item, viewGroup, false);

            vh = new GrantViewHolder();
            vh.name = (TextView) view.findViewById(R.id.dm_grant_name);
            vh.time = (TextView) view.findViewById(R.id.dm_grant_time);
            vh.grant = (Button) view.findViewById(R.id.dm_grant_isgrant);

            view.setTag(vh);
        }else{
            vh = (GrantViewHolder) view.getTag();
        }

        vh.name.setText(list.get(i).getName());
        vh.time.setText(list.get(i).getTime());
        if(list.get(i).getIsGrant().equals("0"))
            vh.grant.setText("권한 부여");
        else
            vh.grant.setText("권한 해제");

        vh.grant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String auth = list.get(i).getIsGrant();
                Button b = (Button)view;

                if(auth.equals("0")){
                    b.setText("권한 해제");
                    auth = "1";
                    list.get(i).setIsGrant("1");
                }else{
                    b.setText("권한 부여");
                    auth="0";
                    list.get(i).setIsGrant("0");
                }


                //set UserInfo
/*
                GolService.instance().getService().changeState().enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.d("DMGA SUC", response.toString());
                        JsonObject result = response.body();
                        Log.d("DMGA SUC", result.toString()+" temp");
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.d("DMGA FAIL", t.getMessage());
                    }
                });*/

            }
        });


        return view;
    }

    public class GrantViewHolder{
        TextView time, name;
        Button grant;
    }
}
