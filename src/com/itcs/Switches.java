package com.itcs;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Switches extends Activity implements OnClickListener{
	Button btn;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.switches);
		
		btn = (Button) findViewById(R.id.button_trains);
		btn.setOnClickListener(this);
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
        	finish();
            return true;
        case R.id.action_exit:
            finish();
            return true;
        default:
            return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
        case R.id.button_trains:
        	finish();
        break;
       }		
	}

}
