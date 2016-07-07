package android.fancy.reader.activity;

import android.content.Intent;
import android.fancy.reader.R;
import android.fancy.reader.Tool.Constants;
import android.fancy.reader.fragment.BookListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends BaseActivity {


    private static final int PAGE_COUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layoutResID = R.layout.activity_main;
        super.onCreate(savedInstanceState);

        TabLayout tabs = (TabLayout) findViewById(R.id.main_pager_tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.main_pager);
        assert tabs != null;
        assert viewPager != null;
        viewPager.setOffscreenPageLimit(PAGE_COUNT);

        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_pick_date);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
//          todo       return prepareIntent(PrefsActivity.class);
            case R.id.action_go_to_search:
//          todo       return prepareIntent(SearchActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean prepareIntent(Class clazz) {
        startActivity(new Intent(MainActivity.this, clazz));
        return true;
    }

    private class MainPagerAdapter extends FragmentStatePagerAdapter{
        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            Fragment newFragment = new BookListFragment();

            bundle.putBoolean(Constants.BundleKeys.IS_NATIVE, position==0);
            newFragment.setArguments(bundle);
            return newFragment;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return position==0?"本地书架":"书城";
        }
    }
}
