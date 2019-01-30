package gol.four.ldcc.gol.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;

import gol.four.ldcc.gol.R;
import gol.four.ldcc.gol.model.DMGrantItem;
import gol.four.ldcc.gol.model.UserInfo;
import gol.four.ldcc.gol.network.GolService;
import gol.four.ldcc.gol.util.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DMGrantAdapter extends BaseAdapter {
    ArrayList<DMGrantItem> list;
    ArrayList<UserInfo> emp;
    GrantViewHolder vh;
    Context context;

    public void clear(){
        list = new ArrayList<>();
        emp = new ArrayList<>();
        this.notifyDataSetChanged();
    }

    public DMGrantAdapter(){
        list = new ArrayList<>();
        emp = new ArrayList<>();
    }

    public void addEmpInfo(UserInfo data){
        emp.add(data);
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
        Collections.reverse(list);
        Collections.reverse(emp);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dm_grant_item, viewGroup, false);

            vh = new GrantViewHolder();
            vh.name = (TextView) view.findViewById(R.id.dm_grant_name);
            vh.time = (TextView) view.findViewById(R.id.dm_grant_time);
            vh.ok = (Button) view.findViewById(R.id.dm_grant_ok);
            vh.deny = (Button) view.findViewById(R.id.dm_grant_deny);

            view.setTag(vh);
        }else{
            vh = (GrantViewHolder) view.getTag();
        }

        vh.name.setText(list.get(i).getName());
        vh.time.setText(list.get(i).getTime());

        //권한 취소
        vh.deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfo userInfo = emp.get(i);
                String pk = userInfo.getPk();
                String cid = context.getSharedPreferences("My_File", 0).getString("pk","");
                GolService.instance().getService().changeDoorGrant(cid, pk,i, 1).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.d("DMGA SUC", response.toString());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.d("DMGA FAIL", t.getMessage());
                    }
                });

            }
        });

        //권한 승인
        vh.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserInfo userInfo = emp.get(i);
                String pk = userInfo.getPk();
                String cid = context.getSharedPreferences("My_File", 0).getString("pk","");
                int row = Integer.parseInt(userInfo.getRow());
                GolService.instance().getService().changeDoorGrant(cid, pk,row, 2).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.d("DMGA SUC", response.toString());

                        Intent i = new Intent("list_update");
                        context.sendBroadcast(i);
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.d("DMGA FAIL", t.getMessage());
                    }
                });

            }
        });


        return view;
    }

    public class GrantViewHolder{
        TextView time, name;
        Button ok, deny;
    }
}
