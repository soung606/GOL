package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fragment.DMGrantFragment;
import fragment.DMHistoryFragment;
import fragment.DMReqFragment;

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
