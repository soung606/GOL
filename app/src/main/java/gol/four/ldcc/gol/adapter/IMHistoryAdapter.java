package gol.four.ldcc.gol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import gol.four.ldcc.gol.R;
import gol.four.ldcc.gol.model.IMHistoryItem;

public class IMHistoryAdapter extends BaseAdapter {
    ArrayList<IMHistoryItem> list;

    public IMHistoryAdapter(){
        list = new ArrayList<>();
    }

    public void add(IMHistoryItem i){
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
        IMHistoryViewHolder vh;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.im_history_item, viewGroup, false);

            vh = new IMHistoryViewHolder();
            vh.name = (TextView) view.findViewById(R.id.im_history_name);
            vh.matName = (TextView) view.findViewById(R.id.im_history_mat_name);
            vh.num = (TextView) view.findViewById(R.id.im_history_num);

            view.setTag(vh);
        }else{
            vh = (IMHistoryViewHolder) view.getTag();
        }

        vh.name.setText(list.get(i).getName());
        vh.matName.setText(list.get(i).getMatName());
        vh.num.setText(list.get(i).getNum());

        return view;
    }

    public class IMHistoryViewHolder{
        TextView name, matName, num;
    }
}
