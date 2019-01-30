package gol.four.ldcc.gol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import gol.four.ldcc.gol.R;
import gol.four.ldcc.gol.model.DMReqItem;
/*
DMReq 페이지에 커스텀을 띄어주기 위함
* */
public class DMReqAdapter extends BaseAdapter {
    private ArrayList<DMReqItem> list;

    public DMReqAdapter(){
        list = new ArrayList<>();
    }

    public void add(DMReqItem i){
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
        DMReqViewHolder vh;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dm_req_item, viewGroup, false);

            vh = new DMReqViewHolder();
            vh.name = (TextView) view.findViewById(R.id.dm_req_name);
            vh.time = (TextView) view.findViewById(R.id.dm_req_time);
            vh.grant = (TextView) view.findViewById(R.id.dm_req_grant);

            view.setTag(vh);
        }else{
            vh = (DMReqViewHolder) view.getTag();
        }

        vh.name.setText(list.get(i).getName());
        vh.time.setText(list.get(i).getTime());
        vh.grant.setText("대기");

        return view;
    }

    public class DMReqViewHolder{
        public TextView time, name, grant;
    }
}
