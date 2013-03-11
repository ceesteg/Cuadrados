package com.example.cuadrados;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PantallaPartida extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.partida);
		
		adaptLayout();
		
		Cuadrados application = (Cuadrados)getApplicationContext();
		
		
		if(application.soundImg()){
			application.resumeSound();
		}
		
		Button btSalirPartida = (Button)findViewById(R.id.botonsalirpartida);	
		btSalirPartida.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setResult(RESULT_OK);
				finish();
			}
		});
	}
	
	@Override
	public void onStop(){
		super.onStop();
	}
	
	@Override
	public void onResume(){
		Cuadrados application = (Cuadrados)getApplicationContext();
		
		if(application.soundImg()){
			application.resumeSound();
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
	private void adaptLayout() {

		Display display = getWindowManager().getDefaultDisplay();
		int width = (int) (display.getWidth());
		int height = (int) (display.getHeight());
		
		int num = 5;
		
		int heightTot = height*70/100;
		int heightLine = heightTot/(20/100*(num+1) + num);
		int widthLine = heightLine*20/100;
		int sizeLayout = widthLine*(num+1)+heightLine*num;
		int marginLat = (width-sizeLayout)/2;
		int marginT = (height-sizeLayout)/2;
		int marginLeft = widthLine+marginLat;
		int marginTop = marginT;
		
		RelativeLayout fp = (RelativeLayout) findViewById(R.id.fondopartida);

				
		LinearLayout fl = new LinearLayout(getApplicationContext());
		fl.setOrientation(LinearLayout.HORIZONTAL);
		fl.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1));
		
		ImageView iv3 = new ImageView(getApplicationContext());
		iv3.setImageResource(R.drawable.lineabase);
		LayoutParams lp3 = new LayoutParams(heightLine, widthLine);
		lp3.setMargins(marginLeft, marginTop, 0, 0);
		fl.addView(iv3, lp3);
		marginLeft = widthLine;
		
		for (int i=1; i<num; i++){
			ImageView iv = new ImageView(getApplicationContext());
			iv.setImageResource(R.drawable.lineabase);
			LayoutParams lp = new LayoutParams(heightLine, widthLine);
			lp.setMargins(marginLeft, marginTop, 0, 0);
			fl.addView(iv, lp);
		}
		fp.addView(fl);
		
		for (int i=0; i<num; i++){
			marginTop = widthLine*(i+1)+heightLine*i+marginT;
			marginLeft = marginLat;
			
			LinearLayout fl1 = new LinearLayout(getApplicationContext());
			fl1.setOrientation(LinearLayout.HORIZONTAL);
			fl1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1));
			
			ImageView iv1 = new ImageView(getApplicationContext());
			iv1.setImageResource(R.drawable.lineabasev);
			LayoutParams lp1 = new LayoutParams(widthLine, heightLine);
			lp1.setMargins(marginLeft, marginTop, 0, 0);
			fl1.addView(iv1, lp1);
			
			for (int n=1; n<num+1; n++){
				ImageView ic = new ImageView(getApplicationContext());
				ic.setImageResource(R.drawable.cuadradobase);
				LayoutParams lpC = new LayoutParams(heightLine, heightLine);
				lpC.setMargins(0, marginTop, 0, 0);
				fl1.addView(ic, lpC);
				
				ImageView iv = new ImageView(getApplicationContext());
				iv.setImageResource(R.drawable.lineabasev);
				LayoutParams lp = new LayoutParams(widthLine, heightLine);
				lp.setMargins(0, marginTop, 0, 0);
				fl1.addView(iv, lp);
			}
			fp.addView(fl1);
			
			marginTop = heightLine*(i+1)+widthLine*(i+1)+marginT;
			marginLeft = widthLine+marginLat;
			
			LinearLayout fl2 = new LinearLayout(getApplicationContext());
			fl2.setOrientation(LinearLayout.HORIZONTAL);
			fl2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1));
			
			ImageView iv2 = new ImageView(getApplicationContext());
			iv2.setImageResource(R.drawable.lineabase);
			LayoutParams lp2 = new LayoutParams(heightLine, widthLine);
			lp2.setMargins(marginLeft, marginTop, 0, 0);
			fl2.addView(iv2, 0, lp2);
			marginLeft = widthLine;
			
			for (int m=1; m<num; m++){
				ImageView iv = new ImageView(getApplicationContext());
				iv.setImageResource(R.drawable.lineabase);
				LayoutParams lp = new LayoutParams(heightLine, widthLine);
				lp.setMargins(marginLeft, marginTop, 0, 0);
				fl2.addView(iv, lp);
			}
			fp.addView(fl2);
		}
		
		
		// Marcadores
		
		int marginLatMarcador = marginLat*25/100;
		int widthMarcador = marginLat*5/10;
		int heightMarcador = heightLine;
		int marginBetMarcs = (sizeLayout-heightMarcador*4)/3;
		int marginRightMarcs = marginLat+sizeLayout+marginLatMarcador;
		
		LinearLayout fl2 = new LinearLayout(getApplicationContext());
		fl2.setOrientation(LinearLayout.VERTICAL);
		fl2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1));
		
		TextView tv = new TextView(getApplicationContext());
		tv.setText("11");
		tv.setBackgroundResource(R.drawable.lineaverde);
		tv.setTextColor(Color.LTGRAY);
		tv.setTextSize(heightLine*50/100);
		tv.setGravity(Gravity.CENTER);
		
		LayoutParams lpMar = new LayoutParams(widthMarcador, heightMarcador);
		lpMar.setMargins(marginLatMarcador, marginT, marginLatMarcador, 0);
		tv.setLayoutParams(lpMar);
		fl2.addView(tv);
		
		TextView tv1 = new TextView(getApplicationContext());
		tv1.setText("5");
		tv1.setBackgroundResource(R.drawable.linearojo);
		tv1.setTextColor(Color.LTGRAY);
		tv1.setTextSize(heightLine*50/100);
		tv1.setGravity(Gravity.CENTER);
		
		LayoutParams lpMar1 = new LayoutParams(widthMarcador, heightMarcador);
		lpMar1.setMargins(marginLatMarcador, marginBetMarcs, marginLatMarcador, 0);
		tv1.setLayoutParams(lpMar1);
		fl2.addView(tv1);
		
		
		fp.addView(fl2);
		
		LinearLayout fl3 = new LinearLayout(getApplicationContext());
		fl3.setOrientation(LinearLayout.VERTICAL);
		fl3.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1));
		
		TextView tv2 = new TextView(getApplicationContext());
		tv2.setText("2");
		tv2.setBackgroundResource(R.drawable.lineaazul);
		tv2.setTextColor(Color.LTGRAY);
		tv2.setTextSize(heightLine*50/100);
		tv2.setGravity(Gravity.CENTER);
		
		LayoutParams lpMar2 = new LayoutParams(widthMarcador, heightMarcador);
		lpMar2.setMargins(marginRightMarcs, marginT, marginLatMarcador, 0);
		tv2.setLayoutParams(lpMar2);
		fl3.addView(tv2);
		
		TextView tv3 = new TextView(getApplicationContext());
		tv3.setText("8");
		tv3.setBackgroundResource(R.drawable.lineaamarillo);
		tv3.setTextColor(Color.LTGRAY);
		tv3.setTextSize(heightLine*50/100);
		tv3.setGravity(Gravity.CENTER);
		
		LayoutParams lpMar3 = new LayoutParams(widthMarcador, heightMarcador);
		lpMar3.setMargins(marginRightMarcs, marginBetMarcs, marginLatMarcador, marginT);
		tv3.setLayoutParams(lpMar3);
		fl3.addView(tv3);
		
		fp.addView(fl3);
		
		
		// Botón salir partida
		
		int widthBtExit = width*135/1000;
		
		Button btSalirPartida = (Button)findViewById(R.id.botonsalirpartida);	
		btSalirPartida.setWidth(widthBtExit);
		btSalirPartida.setHeight(widthBtExit);
	}
}
