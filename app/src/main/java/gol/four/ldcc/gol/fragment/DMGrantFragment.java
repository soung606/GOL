package gol.four.ldcc.gol.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import gol.four.ldcc.gol.R;


public class DMGrantFragment extends Fragment {
    EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this gol.four.ldcc.gol.fragment
        return inflater.inflate(R.layout.fragment_dm_grant, container, false);
    }



}