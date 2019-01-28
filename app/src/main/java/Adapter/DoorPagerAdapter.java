package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fragment.DMIoFragment;
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
            case 2:
                DMIoFragment io = new DMIoFragment();
                return io;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
