package com.example.cuadrados;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Cuadrados application = (Cuadrados)getApplicationContext();
		application.startSound(R.raw.sonidofondo);
		
		Button btExit = (Button)findViewById(R.id.button2);
		btExit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setResult(RESULT_OK);
				finish();
			}
		});
		Button btSound = (Button)findViewById(R.id.button3);
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
		application.stopSound();
		super.onStop();
	}

}
