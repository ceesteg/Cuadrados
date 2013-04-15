package com.example.cuadrados;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;

public class Principal extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); 
		setContentView(R.layout.principal);
		
		adaptLayout();		
		
		Cuadrados application = (Cuadrados)getApplicationContext();
		
		Button btSound = (Button)findViewById(R.id.botonsonido);
		
		if(application.soundImg()){
			if(application.getPlayer()==null){
				application.startSound(R.raw.sonidofondo);
			}else{
				application.resumeSound();
			}
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
		    	Cuadrados application = (Cuadrados)getApplicationContext();
		        Button bt = (Button)findViewById(R.id.botonsonido);
				if(application.soundImg()){
					application.stopSound();
					bt.setBackgroundResource(R.drawable.sinsonido);
					application.setSoundImg(false);
				} else{
					application.resumeSound();
				    bt.setBackgroundResource(R.drawable.consonido);
				    application.setSoundImg(true);
				}
		    }
		});
		
		Button btPlay = (Button)findViewById(R.id.botonjugar);	
		btPlay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent inten = new Intent(getApplicationContext(), SeleccionJugadores.class);
				startActivity(inten);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}
	
	@Override
	public void onStop(){
		super.onStop();
	}
	
	@Override
	public void onResume(){
		Cuadrados application = (Cuadrados)getApplicationContext();
		
		Button btSound = (Button)findViewById(R.id.botonsonido);
		
		if(application.soundImg()){
			application.resumeSound();
			btSound.setBackgroundResource(R.drawable.consonido);
		}else{
			btSound.setBackgroundResource(R.drawable.sinsonido);
		}
		super.onResume();
	}
	
	@Override
	public void onPause(){
		Cuadrados application = (Cuadrados)getApplicationContext();
		application.stopSound();
		super.onPause();
	}

	@SuppressWarnings("deprecation")
	private void adaptLayout(){
		
		Display display = getWindowManager().getDefaultDisplay();
		int width = (int) (display.getWidth());
		int height = (int) (display.getHeight());
		int widthBtExit = width*135/1000;
		int sizeBtPlay = width*22/100;
		int marginBtPlay = height*39/100;
		int marginBtPlayLeft = ((width-sizeBtPlay)/2)-widthBtExit;
		int widthBtLeft = width*8/100;
		int widthHelp = width*5/100;
		int heightBtConf = widthBtLeft*875/1000;
		int heightBtSound = widthBtLeft*787/1000;
		int heightBtHelp = heightBtSound;
		int marginBut = height*4/100;
		int marginButLeft = height*8/100;
		int marginLeft = height*325/10000;
		int marginHelpLeft = height*475/10000;

		
		Button btPlay = (Button)findViewById(R.id.botonjugar);
		btPlay.setWidth(sizeBtPlay);
		btPlay.setHeight(sizeBtPlay);
		LayoutParams paramsBtPlay = new LayoutParams(sizeBtPlay, sizeBtPlay);
		paramsBtPlay.setMargins(marginBtPlayLeft, marginBtPlay, 0, 0);
		btPlay.setLayoutParams(paramsBtPlay);
		
		Button btSound = (Button)findViewById(R.id.botonsonido);
		LayoutParams paramsBtSound = new LayoutParams(widthBtLeft, heightBtSound);
		paramsBtSound.setMargins(marginLeft, 0, 0, marginButLeft);
		btSound.setLayoutParams(paramsBtSound);
		
		Button btConf = (Button)findViewById(R.id.botonconfiguracion);
		LayoutParams paramsBtConf = new LayoutParams(widthBtLeft, heightBtConf);
		paramsBtConf.setMargins(marginLeft, 0, 0, marginButLeft);
		btConf.setLayoutParams(paramsBtConf);
		
		Button btHelp = (Button)findViewById(R.id.botonayuda);
		LayoutParams paramsBtHelp = new LayoutParams(widthHelp, heightBtHelp);
		paramsBtHelp.setMargins(marginHelpLeft, 0, 0, marginBut);
		btHelp.setLayoutParams(paramsBtHelp);
		
		Button btExit = (Button)findViewById(R.id.botonsalir);	
		btExit.setWidth(widthBtExit);
		btExit.setHeight(widthBtExit);
	}
}
