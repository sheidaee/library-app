package com.projeman.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.projeman.library.R;


public class Font extends Activity implements RadioGroup.OnCheckedChangeListener{
	
	 public static String font="tahoma.ttf";
	 RadioGroup rg;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.font);
		rg=(RadioGroup)findViewById(R.id.radioGroup);
		rg.setOnCheckedChangeListener(this);
	
		//-----------------------------
	}
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {

		switch(checkedId) {
		
			case R.id.radio1:
				font="tahoma.ttf";	
				finish();
				break;
			case R.id.radio2:
				font="BZar.ttf";
				finish();
				break;		
		}							
	}
	
	protected String getFont() {
		return font;
	}
}