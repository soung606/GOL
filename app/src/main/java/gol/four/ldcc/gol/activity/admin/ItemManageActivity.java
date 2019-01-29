package gol.four.ldcc.gol.activity.admin;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import gol.four.ldcc.gol.adapter.ItemPagerAdapter;
import gol.four.ldcc.gol.R;

public class ItemManageActivity extends AppCompatActivity {
    private TabLayout tl;
    private ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_manage);

        init();
    }

    private void init(){
        //set tab
        tl = (TabLayout) findViewById(R.id.item_tab_layout);
        tl.addTab(tl.newTab().setText("자재 목록"));
        tl.addTab(tl.newTab().setText("자재 히스토리"));
        tl.setTabGravity(TabLayout.GRAVITY_FILL);

        //set view pager
        vp = (ViewPager) findViewById(R.id.item_pager);

        ItemPagerAdapter itemPagerAdapter = new ItemPagerAdapter(getSupportFragmentManager(), tl.getTabCount());
        vp.setAdapter(itemPagerAdapter);
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
