package android.fancy.reader.activity;

import android.fancy.reader.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TableLayout;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layoutResID = R.layout.activity_main;
        super.onCreate(savedInstanceState);

        TableLayout tabs = (TableLayout) findViewById(R.id.main_pager_tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.main_pager);
        assert viewPager != null;
        viewPager.setOffscreenPageLimit(2);


    }

    private class MainPagerAdapter extends FragmentStatePagerAdapter{
        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            Fragment newFragment ;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }
}
