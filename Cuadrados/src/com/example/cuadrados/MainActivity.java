package com.example.cuadrados;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
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
				if(application.sound()){
					bt.setBackgroundResource(R.drawable.sinsonido);
					application.setSound(false);
				} else{
				    bt.setBackgroundResource(R.drawable.consonido);
				    application.setSound(true);
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
	

}
