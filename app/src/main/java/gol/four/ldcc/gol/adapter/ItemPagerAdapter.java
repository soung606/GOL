package gol.four.ldcc.gol.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import gol.four.ldcc.gol.fragment.DMReqFragment;
import gol.four.ldcc.gol.fragment.IMHistoryFragment;
import gol.four.ldcc.gol.fragment.IMListFragment;

public class ItemPagerAdapter extends FragmentStatePagerAdapter {
    private int tabCount;//tab의 갯수
    public ItemPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return (new IMListFragment());
            case 1:
                return (new IMHistoryFragment());
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
