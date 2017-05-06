package com.projeman.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.projeman.library.R;

public class MainActivity extends Activity {

	private final int _SplashTime = 5000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		
		/*Removing system bar from screen*/        
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	@Override
	protected void onStart() {	
		super.onStart();
		
		new Handler().postDelayed(new Thread() {
			@Override
			public void run() {				
				super.run();
				
				Intent intent = new Intent(MainActivity.this, MainMenu.class);
				startActivity(intent);
				MainActivity.this.finish();
				overridePendingTransition(R.drawable.fade_in, R.drawable.fade_out);
			}
		}, _SplashTime);
	}

}
