package com.example.cuadrados;

import java.io.UnsupportedEncodingException;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class Ayuda extends Activity {

	private final static String[] textos = {
			"Antes de comenzar una partida, es necesario seleccionar el número de jugadores (deben ser al menos dos)."
					+ "\r\n\r\nPara cada jugador se podrá elegir un avatar y nombre personalizado."
					+ "\r\n\r\nAdemás, se podrá definir el tamaño del tablero.",
			"Pulsa una línea para cambiarla de color."
					+ "\r\n\r\nCuando los cuatro lados de un cuadrado estén completos, el cuadrado cambiará al color del jugador que pintó la última línea."
					+ "\r\n\r\nTu puntuación dependerá del número de cuadrados que hayas completado."
					+ "\r\n\r\nCuando no queden más cuadrados por completar, la partida terminará.",
			"César Estebas Gómez" 
					+ "\r\n\r\nMaría Estefanía Casero"
					+ "\r\n\r\nGonzalo Sáenz San Millán" 
					+ "\r\n\r\n\r\ncuadrados4444@gmail.com"};

	private int sel;
	private int width;
	private int height;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.ayuda);

		adaptLayout();

		sel = 0;
		setFondo(sel);
		TextView texto = (TextView) findViewById(123456789);
		setText(texto, textos[sel]);
		setTextLayout(sel, texto);
		
		Button arrowL = (Button) findViewById(R.id.arrowL);
		if (sel == 0) {
			arrowL.setVisibility(View.INVISIBLE);
		}
		arrowL.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				sel--;
				Button arrowL = (Button) findViewById(R.id.arrowL);
				Button arrowR = (Button) findViewById(R.id.arrowR);
				if (sel == -1) {
					sel = textos.length - 1;
					arrowR.setVisibility(View.INVISIBLE);
				}
				setFondo(sel);
				TextView texto = (TextView) findViewById(123456789);
				setText(texto, textos[sel]);
				setTextLayout(sel, texto);

				if (sel == 0) {
					arrowL.setVisibility(View.INVISIBLE);
				} else {
					arrowL.setVisibility(View.VISIBLE);
				}
				if (sel != textos.length - 1) {
					arrowR.setVisibility(View.VISIBLE);
				}
			}
		});

		Button arrowR = (Button) findViewById(R.id.arrowR);
		if (sel == textos.length - 1) {
			arrowR.setVisibility(View.INVISIBLE);
		}
		arrowR.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				sel++;
				Button arrowL = (Button) findViewById(R.id.arrowL);
				Button arrowR = (Button) findViewById(R.id.arrowR);
				if (sel == textos.length) {
					sel = 0;
					arrowL.setVisibility(View.INVISIBLE);
				}
				setFondo(sel);
				TextView texto = (TextView) findViewById(123456789);
				setText(texto, textos[sel]);
				setTextLayout(sel, texto);

				if (sel == textos.length - 1) {
					arrowR.setVisibility(View.INVISIBLE);
				} else {
					arrowR.setVisibility(View.VISIBLE);
				}
				if (sel != 0) {
					arrowL.setVisibility(View.VISIBLE);
				}
			}
		});

		Button btBack = (Button) findViewById(R.id.btAtras);
		btBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				setResult(RESULT_OK);
				finish();
			}
		});
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onResume() {
		Cuadrados application = (Cuadrados) getApplicationContext();

		if (application.soundImg()) {
			application.resumeSound();
		}
		super.onResume();
	}

	@Override
	public void onPause() {
		Cuadrados application = (Cuadrados) getApplicationContext();
		application.stopSound();
		super.onPause();
	}

	@SuppressWarnings("deprecation")
	private void adaptLayout() {

		Display display = getWindowManager().getDefaultDisplay();
		int width = (int) (display.getWidth());
		int height = (int) (display.getHeight());
		int widthBtBack = width * 12 / 100;
		int marginBtArrowTop = (height - widthBtBack) / 2;
		int marginBtBackTop = marginBtArrowTop - widthBtBack;

		Button arrowL = (Button) findViewById(R.id.arrowL);
		arrowL.setWidth(widthBtBack);
		arrowL.setHeight(widthBtBack);
		LayoutParams paramsArrowL = new LayoutParams(widthBtBack * 8 / 10,
				widthBtBack);
		paramsArrowL.setMargins(widthBtBack / 6, marginBtArrowTop,
				widthBtBack / 8, 0);
		arrowL.setLayoutParams(paramsArrowL);

		Button arrowR = (Button) findViewById(R.id.arrowR);
		arrowR.setWidth(widthBtBack);
		arrowR.setHeight(widthBtBack);
		LayoutParams paramsArrowR = new LayoutParams(widthBtBack * 8 / 10,
				widthBtBack);
		paramsArrowR.setMargins(widthBtBack / 8, marginBtArrowTop,
				widthBtBack / 6, 0);
		arrowR.setLayoutParams(paramsArrowR);

		Button btBack = (Button) findViewById(R.id.btAtras);
		btBack.setWidth(widthBtBack);
		btBack.setHeight(widthBtBack);
		LayoutParams paramsBtBack = new LayoutParams(widthBtBack * 8 / 10,
				widthBtBack);
		paramsBtBack.setMargins(widthBtBack / 8, marginBtBackTop,
				widthBtBack / 6, 0);
		btBack.setLayoutParams(paramsBtBack);

		this.width = width - widthBtBack - widthBtBack - widthBtBack / 8
				- widthBtBack / 6 - widthBtBack / 8 - widthBtBack / 6;
		this.height = height - height * 17 / 100;
		LinearLayout textoAyuda = (LinearLayout) findViewById(R.id.textoAyuda);

		TextView texto = new TextView(getApplicationContext());
		texto.setId(123456789);
		texto.setTextColor(Color.BLACK);
		texto.setTypeface(null, Typeface.BOLD);
		textoAyuda.addView(texto);
	}

	private void setFondo(int n) {
		LinearLayout fondo = (LinearLayout) findViewById(R.id.fondoAyuda);
		if (n == 0) {
			fondo.setBackgroundResource(R.drawable.fondoaayudaconfig);
		}
		if (n == 1) {
			fondo.setBackgroundResource(R.drawable.fondoaayudacomojugarpng);
		}
		if (n == 2) {
			fondo.setBackgroundResource(R.drawable.fondoaayudacreditos);
		}
	}

	@SuppressWarnings("deprecation")
	private void setTextLayout(int n, TextView tv) {
		Display display = getWindowManager().getDefaultDisplay();
		int height = (int) (display.getHeight());
		LayoutParams tit2L = null;
		if (n != 2) {
			tit2L = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);
			tit2L.setMargins(0, height * 17 / 100, 0, 0);
		} else {
			tit2L = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);
			tit2L.setMargins(height * 25 / 100, height * 25 / 100, 0, 0);
			tv.setTextSize(height * 4 / 100);
		}
		tv.setLayoutParams(tit2L);
	}

	private void setText(TextView tv, String text) {
		try {
			tv.setText(new String(text.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Paint paint = new Paint();
		Rect bounds = new Rect();

		int text_height = 0;
		int text_width = 0;

		paint.setTypeface(Typeface.DEFAULT);// your preference here

		int text_check_w = this.width * 4;
		int text_check_h = this.height;

		int text_size = 0;
		boolean found_desired_size = false;

		while (!found_desired_size) {
			text_size++;
			paint.setTextSize(text_size);// have this the same as your text size

			paint.getTextBounds(text, 0, text.length(), bounds);

			text_height = bounds.height();
			text_width = bounds.width();

			System.out.println("H:  " + text_height + " - " + text_check_h);
			System.out.println("W:  " + text_width + " - " + text_check_w);

			if (text_height > text_check_h || text_width > text_check_w) {
				found_desired_size = true;
			}
		}
		tv.setTextSize(text_size);
	}
}
