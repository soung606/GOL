package gol.four.ldcc.gol.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import gol.four.ldcc.gol.R;
import gol.four.ldcc.gol.model.DMGrantItem;

public class DMGrantAdapter extends BaseAdapter {
    ArrayList<DMGrantItem> list;

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
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        GrantViewHolder vh;
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


        return view;
    }

    public class GrantViewHolder{
        TextView time, name;
        Button grant;
    }
}
