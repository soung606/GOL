package gol.four.ldcc.gol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import gol.four.ldcc.gol.R;
import gol.four.ldcc.gol.model.IMListItem;

public class IMListAdapter extends BaseAdapter {
    ArrayList<IMListItem> list;

    public IMListAdapter(){
        list = new ArrayList<>();
    }

    public void add(IMListItem i){
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
        IMListViewHolder vh;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.im_list_item, viewGroup, false);

            vh = new IMListViewHolder();
            vh.name = (TextView) view.findViewById(R.id.im_list_name);
            vh.code = (TextView) view.findViewById(R.id.im_list_code);
            vh.weight = (TextView) view.findViewById(R.id.im_list_weight);

            view.setTag(vh);
        }else{
            vh = (IMListViewHolder) view.getTag();
        }

        vh.name.setText(list.get(i).getName());
        vh.code.setText(list.get(i).getCode());
        vh.weight.setText(list.get(i).getWeight());

        return view;
    }

    public class IMListViewHolder{
        TextView code, name, weight;
    }
}
