package com.example.cuadrados;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
		WindowManager.LayoutParams.FLAG_FULLSCREEN); 
		setContentView(R.layout.activity_main);
		
		Cuadrados application = (Cuadrados)getApplicationContext();
		Button btSound = (Button)findViewById(R.id.button3);
		if(application.soundImg()){
			application.startSound(R.raw.sonidofondo);
			btSound.setBackgroundResource(R.drawable.consonido);
		}else{
			btSound.setBackgroundResource(R.drawable.sinsonido);
		}
		
		Button btExit = (Button)findViewById(R.id.button2);
		btExit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setResult(RESULT_OK);
				finish();
			}
		});
		btSound.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		        // TODO Auto-generated method stub
		    	Cuadrados application = (Cuadrados)getApplicationContext();
		        Button bt = (Button)findViewById(R.id.button3);
				if(application.soundImg()){
					application.pauseSound();
					bt.setBackgroundResource(R.drawable.sinsonido);
					application.setSoundImg(false);
				} else{
					application.resumeSound();
				    bt.setBackgroundResource(R.drawable.consonido);
				    application.setSoundImg(true);
				}
		    }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public void onStop(){
		Cuadrados application = (Cuadrados)getApplicationContext();
		application.pauseSound();
		super.onStop();
	}

}
