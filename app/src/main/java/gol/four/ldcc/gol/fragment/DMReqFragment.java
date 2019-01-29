package gol.four.ldcc.gol.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import gol.four.ldcc.gol.adapter.DMReqAdapter;
import gol.four.ldcc.gol.R;
import gol.four.ldcc.gol.model.DMReqItem;


public class DMReqFragment extends Fragment {
    private ListView list;
    private DMReqAdapter adapter;

    public DMReqFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this gol.four.ldcc.gol.fragment
        View v = inflater.inflate(R.layout.fragment_dm_req, container, false);
        init(v);
        return v;
    }

    private void init(View v){
        list = (ListView) v.findViewById(R.id.req_lv);
        adapter = new DMReqAdapter();
        list.setAdapter(adapter);

        DMReqItem temp = new DMReqItem();
        temp.setGrant("a");
        temp.setName("a");
        temp.setTime("a");

        adapter.add(temp);
    }

}
