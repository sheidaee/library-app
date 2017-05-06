package com.projeman.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.projeman.library.R;

public class MainMenu extends Activity implements OnClickListener {
	
	public String fonts="tahoma.ttf";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		setFace();
		
		// Menu button should be invisible in this screen
		Button btnMenu = (Button) findViewById(R.id.btn_menu_compound);
		btnMenu.setVisibility(View.INVISIBLE);							
	}
	
	protected void setFace() {
		
		View btnBookList = findViewById(R.id.btnBookList);
		btnBookList.setOnClickListener(this);
		
		View btnSetting = findViewById(R.id.btnSetting);
		btnSetting.setOnClickListener(this);
		
		View btnExit = findViewById(R.id.btnExit);
		btnExit.setOnClickListener(this);
		
						
		Typeface face = Typeface.createFromAsset(getAssets(), "font/"+fonts+"");
		
		((Button)btnBookList).setTypeface(face);
		((Button)btnSetting).setTypeface(face);
		((Button)btnExit).setTypeface(face);		
	}
	//---------------------------------------------------------------

	@Override
	public void onClick(View v) {		
		switch(v.getId()) {			
			case R.id.btnBookList :
				Intent i = new Intent(MainMenu.this, BooksList.class);
								
				Bundle bundle = new Bundle();
				bundle.putString("font", fonts);
				
				i.putExtras(bundle);
				startActivity(i);
				break;
							
			case R.id.btnSetting:
				Intent intent = new Intent(MainMenu.this,Font.class);														
				startActivity(intent);							
				break;		
				
			case R.id.btnExit:
				//snippet exit app
				Intent in = new Intent(Intent.ACTION_MAIN);
				in.addCategory(Intent.CATEGORY_HOME);
				in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(in);
			break;
		}				
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		Font mainmenu =new Font();
		fonts = mainmenu.getFont();
		setFace();
	}
}