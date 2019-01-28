package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import Adapter.DMHistoryAdapter;
import gol.four.ldcc.gol.R;
import model.DMHistoryItem;

public class DMHistoryFragment extends Fragment {
    ListView list;
    DMHistoryAdapter adapter;
    public DMHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dm_history, container, false);

        init(v);
        return v;
    }

    private void init(View v){
        list = (ListView) v.findViewById(R.id.io_lv);
        adapter = new DMHistoryAdapter();

        list.setAdapter(adapter);

        DMHistoryItem i = new DMHistoryItem();
        i.setAdmin("b");
        i.setTime("b");
        i.setUser("b");

        adapter.add(i);
    }


}
