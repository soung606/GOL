package gol.four.ldcc.gol.activity.admin;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Adapter.DoorPagerAdapter;

import gol.four.ldcc.gol.R;

public class DoorManageActivity extends AppCompatActivity {
    private TabLayout tl;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_manage);
        init();
    }

    private void init(){
        tl = (TabLayout) findViewById(R.id.door_tab_layout);
        tl.addTab(tl.newTab().setText("신청 내역"));
        tl.addTab(tl.newTab().setText("출입 기록"));
        tl.addTab(tl.newTab().setText("권한 부여"));

        tl.setTabGravity(TabLayout.GRAVITY_FILL);

        vp = (ViewPager)findViewById(R.id.door_pager);

        DoorPagerAdapter doorPagerAdapter = new DoorPagerAdapter(getSupportFragmentManager(), tl.getTabCount());
        vp.setAdapter(doorPagerAdapter);
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tl));
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
