package com.example.cuadrados;

import org.apache.http.conn.scheme.PlainSocketFactory;

import android.app.Activity;
import android.content.pm.LabeledIntent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
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
				ic.setImageResource(R.drawable.cuadradoazul);
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
		
		TextView tv = new TextView(getApplicationContext());
		tv.setBackgroundColor(Color.GREEN);
		tv.setText("J1");
		tv.setTextColor(Color.BLACK);
		tv.setTextSize(heightLine*50/100);
		tv.setGravity(Gravity.CENTER);
		
		LayoutParams lp2 = new LayoutParams(marginLat, heightLine);
		lp2.setMargins(0, 0, 0, 0);
		fp.addView(tv, 0, lp2);
	}
}
