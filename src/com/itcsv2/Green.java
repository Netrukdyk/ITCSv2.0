package com.itcsv2;

import com.itcsv2.R;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Green extends Fragment implements OnClickListener {
	
	Button Button_switches;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.green, container, false);
        root.setBackgroundColor(Color.argb(50, 42, 255, 42));
        
        Button_switches = (Button) root.findViewById(R.id.button_switches);
		Button_switches.setOnClickListener(this);
		
        return root;
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
        case R.id.button_switches:
        	Intent open = new Intent(v.getContext(), Switches.class);
            startActivity(open);
        break;
       }
		
	}
}