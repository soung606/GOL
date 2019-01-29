package gol.four.ldcc.gol.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import gol.four.ldcc.gol.fragment.DMGrantFragment;
import gol.four.ldcc.gol.fragment.DMHistoryFragment;
import gol.four.ldcc.gol.fragment.DMReqFragment;

public class DoorPagerAdapter extends FragmentStatePagerAdapter {
    private int tabCount;

    public DoorPagerAdapter(FragmentManager fm, int tc) {
        super(fm);
        this.tabCount = tc;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0 :
                DMReqFragment req = new DMReqFragment();
                return req;
            case 1:
                DMHistoryFragment io = new DMHistoryFragment();
                return io;
            case 2:
                DMGrantFragment grant = new DMGrantFragment();
                return grant;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
