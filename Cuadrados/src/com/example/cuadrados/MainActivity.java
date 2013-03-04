package com.example.cuadrados;

import android.os.Bundle;
import android.app.Activity;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
		WindowManager.LayoutParams.FLAG_FULLSCREEN); 
		setContentView(R.layout.activity_main);
		
		adaptLayout();		
		
		Cuadrados application = (Cuadrados)getApplicationContext();
		
		Button btSound = (Button)findViewById(R.id.botonsonido);
		
		if(application.soundImg()){
			application.startSound(R.raw.sonidofondo);
			btSound.setBackgroundResource(R.drawable.consonido);
		}else{
			btSound.setBackgroundResource(R.drawable.sinsonido);
		}

		Button btExit = (Button)findViewById(R.id.botonsalir);	
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
		        Button bt = (Button)findViewById(R.id.botonsonido);
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

	@SuppressWarnings("deprecation")
	private void adaptLayout(){
		
		Display display = getWindowManager().getDefaultDisplay();
		int width = (int) (display.getWidth());
		int heigh = (int) (display.getHeight());
		int sizeBtGeneral = width*15/100;
		int sizeBtPlay = width*25/100;
		int headerWidth = width*70/100;
		int headerHeigh = headerWidth*14/100;
		int marginHeaderTop = heigh*10/100;
		int marginHeaderLeft = ((width-headerWidth)/2)-sizeBtGeneral;
		int marginBtPlayTop = heigh*375/1000;
		int marginBtPlay = marginBtPlayTop-marginHeaderTop-headerHeigh;
		int marginBtPlayLeft = ((width-sizeBtPlay)/2)-sizeBtGeneral;
		
		
		ImageView header = (ImageView) findViewById(R.id.cabeceracuadrados);
		LayoutParams paramsHeader = new LayoutParams(headerWidth, headerHeigh);
		paramsHeader.setMargins(marginHeaderLeft, marginHeaderTop, 0, 0);
		header.setLayoutParams(paramsHeader);
		
		Button btPlay = (Button)findViewById(R.id.botonjugar);
		btPlay.setWidth(sizeBtPlay);
		btPlay.setHeight(sizeBtPlay);
		LayoutParams paramsBtPlay = new LayoutParams(sizeBtPlay, sizeBtPlay);
		paramsBtPlay.setMargins(marginBtPlayLeft, marginBtPlay, 0, 0);
		btPlay.setLayoutParams(paramsBtPlay);
		
		Button btSound = (Button)findViewById(R.id.botonsonido);
		btSound.setWidth(sizeBtGeneral);
		btSound.setHeight(sizeBtGeneral);
		
		Button btConf = (Button)findViewById(R.id.botonconfiguracion);
		btConf.setWidth(sizeBtGeneral);
		btConf.setHeight(sizeBtGeneral);
		
		Button btHelp = (Button)findViewById(R.id.botonayuda);
		btHelp.setWidth(sizeBtGeneral);
		btHelp.setHeight(sizeBtGeneral);
		
		Button btExit = (Button)findViewById(R.id.botonsalir);	
		btExit.setWidth(sizeBtGeneral);
		btExit.setHeight(sizeBtGeneral);
	}
}
