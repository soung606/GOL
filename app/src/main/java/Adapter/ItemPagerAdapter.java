package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fragment.DMReqFragment;

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
            case 1:
            case 2:
                return (new DMReqFragment());
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
