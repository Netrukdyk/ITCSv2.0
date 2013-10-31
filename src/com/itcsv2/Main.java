package com.itcsv2;

import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import com.itcsv2.Duomenys;
import com.itcsv2.Main;
import com.itcsv2.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Main extends FragmentActivity {

	Fragment red_fragment, green_fragment, blue_fragment;
	
	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;
	PagerTitleStrip pagerTitle;
	JSONObject jData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);		
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		// Page title background color
		//pagerTitle = (PagerTitleStrip) findViewById(R.id.pager_title_strip);
		//pagerTitle.setBackgroundColor(Color.argb(120, 200, 200, 200));
		
		try {
			JSONObject traukiniai = (JSONObject) jData.get("traukiniai");
			int traukinys = traukiniai.getInt("speed");
			Log.v("DATA", traukinys+"");
			
		} catch (NullPointerException e) {
			Log.v("DATA", "null");
			e.printStackTrace();
		}
		catch (JSONException e) {
			Log.v("DATA", "no data");
			e.printStackTrace();
		}
		
		// Get Global json data
		//Global g = (Global)getApplication();
		//JSONObject data=g.getData();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Duomenys.atnaujintiJson();				
				} catch (Exception e) {
					Log.v("JSON", e.toString());
				}
			}
		}).start();
		
		if(Duomenys.all_data != null){mViewPager.setVisibility(View.VISIBLE);}
		else{mViewPager.setVisibility(View.GONE);}
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
        	nustatytiIP();
            return true;
        case R.id.action_exit:
            this.finish();
            return true;
        default:
            return super.onOptionsItemSelected(item);
		}
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			position = position % 3;
            switch (position) {
                case 0:
                	return red_fragment = new Red();
                case 1:
                	return green_fragment = new Green();
                case 2:
                	return blue_fragment = new Blue();
                default:
                	return null;
            }
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
	public void nustatytiIP() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Nurodykite IP");
		final EditText input = new EditText(this);
		input.setText(Duomenys.getIP());
		input.setInputType(InputType.TYPE_CLASS_PHONE);
		builder.setView(input);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String ivestas_ip = input.getText().toString();
				SharedPreferences settings = getApplicationContext().getSharedPreferences("main", 0);
				SharedPreferences.Editor editor = settings.edit();
				editor.putString("IPaddr", ivestas_ip);
				Duomenys.setIP(ivestas_ip);
				//Log.v("SetIP", "Nustatytas i: "+Pagrindinis.IPaddr);
				editor.commit();
				Thread a = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Duomenys.atnaujintiJson();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							Log.v("JSON", e.toString());
						}				
					}
				});
				a.start();
				
			}
		});
		builder.setNegativeButton("Uþdaryti", new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        dialog.cancel();
		    }
		});

		builder.show();
		
		
	}

}
