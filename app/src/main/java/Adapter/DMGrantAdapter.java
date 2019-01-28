package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import gol.four.ldcc.gol.R;
import model.DMGrantItem;

public class DMGrantAdapter extends BaseAdapter {
    ArrayList<DMGrantItem> list;

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
            vh.company = (TextView) view.findViewById(R.id.dm_grant_company);
            vh.grant = (TextView) view.findViewById(R.id.dm_grant_isgrant);
        }else{
            vh = (GrantViewHolder) view.getTag();
        }

        vh.name.setText(list.get(i).getName());
        vh.company.setText(list.get(i).getCompany());
        vh.grant.setText(list.get(i).getIsGrant());

        return view;
    }

    public class GrantViewHolder{
        TextView company, name, grant;
    }
}
