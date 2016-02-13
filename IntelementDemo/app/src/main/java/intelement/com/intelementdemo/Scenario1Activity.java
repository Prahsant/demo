package intelement.com.intelementdemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class Scenario1Activity extends FragmentActivity {

    private static final int NUM_PAGES = 4;

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senario1);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("Item 1").setIndicator("Item 1", null),
                FragmentTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("Item 2").setIndicator("Item 2", null),
                FragmentTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("Item 3").setIndicator("Item 3", null),
                FragmentTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("Item 4").setIndicator("Item 4", null),
                FragmentTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("Item 5").setIndicator("Item 5", null),
                FragmentTab.class, null);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        //mPager.setOnPageChangeListener(this);
    }

    public void onColorButtonClick(View view){
        RelativeLayout colorLayout = (RelativeLayout)findViewById(R.id.colorLayout);
        int viewId = view.getId();
        if(viewId == R.id.btnRed) {
            colorLayout.setBackgroundColor(Color.RED);
        }else if(viewId == R.id.btnBlue){
            colorLayout.setBackgroundColor(Color.BLUE);
        }else if(viewId == R.id.btnGreen){
            colorLayout.setBackgroundColor(Color.GREEN);
        }
    }
/*
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Toast.makeText(this, "Screen " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scenario1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_scenario2) {
            startActivity(new Intent(this,Scenario2Activity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new ScreenSlidePageFragment();
            Bundle b = new Bundle();
            b.putInt("id", position);
            fragment.setArguments(b);
            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
