package com.itcs;

import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Main extends FragmentActivity {

	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;
	PagerTitleStrip pagerTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);		
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		//pagerTitle = (PagerTitleStrip) findViewById(R.id.pager_title_strip);
		//pagerTitle.setBackgroundColor(Color.argb(120, 200, 200, 200));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
        case R.id.action_setip:
        	this.finish();
            return true;
        case R.id.action_exit:
            this.finish();
            return true;
        default:
            return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			position = position % 3;
			Fragment fragment;
            switch (position) {
                case 0:
                    fragment = new Red();
                    break;
                case 1:
                    fragment = new Green();
                    break;
                case 2:
                    fragment = new Blue();
                    break;
                default:
                    fragment  = null;
                    break;
            }
            return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 99;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			position = position % 3;
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.RED).toUpperCase(l);
			case 1:
				return getString(R.string.GREEN).toUpperCase(l);
			case 2:
				return getString(R.string.BLUE).toUpperCase(l);
			}
			return null;
		}
	}

}
