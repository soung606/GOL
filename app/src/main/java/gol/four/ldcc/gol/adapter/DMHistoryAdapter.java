package gol.four.ldcc.gol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import gol.four.ldcc.gol.R;
import gol.four.ldcc.gol.model.DMHistoryItem;

public class DMHistoryAdapter extends BaseAdapter {
    ArrayList<DMHistoryItem> list;

    public DMHistoryAdapter(){
        list = new ArrayList<>();
    }

    public void add(DMHistoryItem i){
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
        HistoryViewHolder vh;
        Context context = viewGroup.getContext();

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dm_history_item, viewGroup, false);

            vh = new HistoryViewHolder();
            vh.time = (TextView) view.findViewById(R.id.dm_his_time);
            vh.admin = (TextView) view.findViewById(R.id.dm_his_admin);
            vh.user = (TextView) view.findViewById(R.id.dm_his_user);

        } else{
            vh = (HistoryViewHolder) view.getTag();
        }

        vh.time.setText(list.get(i).getTime());
        vh.user.setText(list.get(i).getUser());
        vh.admin.setText(list.get(i).getAdmin());

        return view;
    }

    public class HistoryViewHolder{
        public TextView time, user, admin;
    }
}
