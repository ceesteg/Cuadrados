package com.example.cuadrados;

import clases.Contador;
import clases.Jugador;
import clases.Tablero;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

	Tablero tablero;
	Jugador[] jugadores;
	Contador contador;
	int numJugadores;
	int jugadorActual;
	boolean pausa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.partida);

		int num = 5;
		this.tablero = new Tablero(num, num);
		
		Jugador j1 = new Jugador(1, "rojo");
		Jugador j2 = new Jugador(2, "azul");
		Jugador j3 = new Jugador(3, "verde");
		Jugador j4 = new Jugador(4, "amarillo");
		Jugador[] j = {j1, j2, j3, j4};
		this.jugadores = j;
		this.numJugadores = this.jugadores.length;
		
		adaptLayout(num);

		Cuadrados application = (Cuadrados) getApplicationContext();

		if (application.soundImg()) {
			application.resumeSound();
		}

		Button btSalirPartida = (Button) findViewById(R.id.botonsalirpartida);
		btSalirPartida.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				setResult(RESULT_OK);
				finish();
			}
		});
		
		iniciarPartida();
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
	private void adaptLayout(int num) {

		Display display = getWindowManager().getDefaultDisplay();
		int width = (int) (display.getWidth());
		int height = (int) (display.getHeight());

		int lineasHorizontales = 0;
		int lineasVerticales = 0;
		int cuadrados = 0;

		int heightTot = height * 70 / 100;
		int heightLine = heightTot / (20 / 100 * (num + 1) + num);
		int widthLine = heightLine * 30 / 100;
		int sizeLayout = widthLine * (num + 1) + heightLine * num;
		int marginLat = (width - sizeLayout) / 2;
		int marginT = (height - sizeLayout) / 2;
		int marginLeft = widthLine + marginLat;
		int marginTop = marginT;

		RelativeLayout fp = (RelativeLayout) findViewById(R.id.fondopartida);

		LinearLayout fl = new LinearLayout(getApplicationContext());
		fl.setOrientation(LinearLayout.HORIZONTAL);
		fl.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 1));

		ImageView iv3 = new ImageView(getApplicationContext());
		iv3.setImageResource(R.drawable.lineabase);
		iv3.setId(this.tablero.getLinea(lineasHorizontales, 0).getId());
		lineasHorizontales++;
		ponerLinea(iv3, 0);

		LayoutParams lp3 = new LayoutParams(heightLine, widthLine);
		lp3.setMargins(marginLeft, marginTop, 0, 0);
		fl.addView(iv3, lp3);
		marginLeft = widthLine;

		for (int i = 1; i < num; i++) {
			ImageView iv = new ImageView(getApplicationContext());
			iv.setImageResource(R.drawable.lineabase);
			iv.setId(this.tablero.getLinea(lineasHorizontales, 0).getId());
			lineasHorizontales++;
			ponerLinea(iv, 0);

			LayoutParams lp = new LayoutParams(heightLine, widthLine);
			lp.setMargins(marginLeft, marginTop, 0, 0);
			fl.addView(iv, lp);
		}
		fp.addView(fl);

		for (int i = 0; i < num; i++) {
			marginTop = widthLine * (i + 1) + heightLine * i + marginT;
			marginLeft = marginLat;

			LinearLayout fl1 = new LinearLayout(getApplicationContext());
			fl1.setOrientation(LinearLayout.HORIZONTAL);
			fl1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 1));

			ImageView iv1 = new ImageView(getApplicationContext());
			iv1.setImageResource(R.drawable.lineabasev);
			iv3.setId(this.tablero.getLinea(lineasVerticales, 1).getId());
			lineasVerticales++;
			ponerLinea(iv3, 1);

			LayoutParams lp1 = new LayoutParams(widthLine, heightLine);
			lp1.setMargins(marginLeft, marginTop, 0, 0);
			fl1.addView(iv1, lp1);

			for (int n = 1; n < num + 1; n++) {
				ImageView ic = new ImageView(getApplicationContext());
				ic.setImageResource(R.drawable.cuadradobase);
				ic.setId(cuadrados);
				cuadrados++;
				LayoutParams lpC = new LayoutParams(heightLine, heightLine);
				lpC.setMargins(0, marginTop, 0, 0);
				fl1.addView(ic, lpC);

				ImageView iv = new ImageView(getApplicationContext());
				iv.setImageResource(R.drawable.lineabasev);
				iv.setId(this.tablero.getLinea(lineasVerticales, 1).getId());
				lineasVerticales++;
				ponerLinea(iv, 1);

				LayoutParams lp = new LayoutParams(widthLine, heightLine);
				lp.setMargins(0, marginTop, 0, 0);
				fl1.addView(iv, lp);
			}
			fp.addView(fl1);

			marginTop = heightLine * (i + 1) + widthLine * (i + 1) + marginT;
			marginLeft = widthLine + marginLat;

			LinearLayout fl2 = new LinearLayout(getApplicationContext());
			fl2.setOrientation(LinearLayout.HORIZONTAL);
			fl2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 1));

			ImageView iv2 = new ImageView(getApplicationContext());
			iv2.setImageResource(R.drawable.lineabase);
			iv2.setId(this.tablero.getLinea(lineasHorizontales, 0).getId());
			lineasHorizontales++;
			ponerLinea(iv2, 0);

			LayoutParams lp2 = new LayoutParams(heightLine, widthLine);
			lp2.setMargins(marginLeft, marginTop, 0, 0);
			fl2.addView(iv2, 0, lp2);
			marginLeft = widthLine;

			for (int m = 1; m < num; m++) {
				ImageView iv = new ImageView(getApplicationContext());
				iv.setImageResource(R.drawable.lineabase);
				iv.setId(this.tablero.getLinea(lineasHorizontales, 0).getId());
				lineasHorizontales++;
				ponerLinea(iv, 0);

				LayoutParams lp = new LayoutParams(heightLine, widthLine);
				lp.setMargins(marginLeft, marginTop, 0, 0);
				fl2.addView(iv, lp);
			}
			fp.addView(fl2);
		}

		// Marcadores

		int marginLatMarcador = marginLat * 25 / 100;
		int widthMarcador = marginLat * 5 / 10;
		int heightMarcador = heightLine;
		int marginBetMarcs = (sizeLayout - heightMarcador * 4) / 3;
		int marginRightMarcs = marginLat + sizeLayout + marginLatMarcador;

		LinearLayout fl2 = new LinearLayout(getApplicationContext());
		fl2.setOrientation(LinearLayout.VERTICAL);
		fl2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 1));

		TextView tv = new TextView(getApplicationContext());
		tv.setText("11");
		tv.setBackgroundResource(R.drawable.lineaverde);
		tv.setTextColor(Color.LTGRAY);
		tv.setTextSize(heightLine * 50 / 100);
		tv.setGravity(Gravity.CENTER);

		LayoutParams lpMar = new LayoutParams(widthMarcador, heightMarcador);
		lpMar.setMargins(marginLatMarcador, marginT, marginLatMarcador, 0);
		tv.setLayoutParams(lpMar);
		fl2.addView(tv);

		TextView tv1 = new TextView(getApplicationContext());
		tv1.setText("5");
		tv1.setBackgroundResource(R.drawable.linearojo);
		tv1.setTextColor(Color.LTGRAY);
		tv1.setTextSize(heightLine * 50 / 100);
		tv1.setGravity(Gravity.CENTER);

		LayoutParams lpMar1 = new LayoutParams(widthMarcador, heightMarcador);
		lpMar1.setMargins(marginLatMarcador, marginBetMarcs, marginLatMarcador,
				0);
		tv1.setLayoutParams(lpMar1);
		fl2.addView(tv1);

		fp.addView(fl2);

		LinearLayout fl3 = new LinearLayout(getApplicationContext());
		fl3.setOrientation(LinearLayout.VERTICAL);
		fl3.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 1));

		TextView tv2 = new TextView(getApplicationContext());
		tv2.setText("2");
		tv2.setBackgroundResource(R.drawable.lineaazul);
		tv2.setTextColor(Color.LTGRAY);
		tv2.setTextSize(heightLine * 50 / 100);
		tv2.setGravity(Gravity.CENTER);

		LayoutParams lpMar2 = new LayoutParams(widthMarcador, heightMarcador);
		lpMar2.setMargins(marginRightMarcs, marginT, marginLatMarcador, 0);
		tv2.setLayoutParams(lpMar2);
		fl3.addView(tv2);

		TextView tv3 = new TextView(getApplicationContext());
		tv3.setText("8");
		tv3.setBackgroundResource(R.drawable.lineaamarillo);
		tv3.setTextColor(Color.LTGRAY);
		tv3.setTextSize(heightLine * 50 / 100);
		tv3.setGravity(Gravity.CENTER);

		LayoutParams lpMar3 = new LayoutParams(widthMarcador, heightMarcador);
		lpMar3.setMargins(marginRightMarcs, marginBetMarcs, marginLatMarcador,
				marginT);
		tv3.setLayoutParams(lpMar3);
		fl3.addView(tv3);

		fp.addView(fl3);

		// Botón salir partida

		int widthBtExit = width * 135 / 1000;

		Button btSalirPartida = (Button) findViewById(R.id.botonsalirpartida);
		btSalirPartida.setWidth(widthBtExit);
		btSalirPartida.setHeight(widthBtExit);
	}

	private void ponerLinea(ImageView iv, int tipo) {
		if (tipo == 0) {
			iv.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					boolean conseguidoPunto = false;
					ImageView iv = (ImageView) findViewById(v.getId());

					Log.e("Id linea", String.valueOf(v.getId()));
					
					if(!tablero.getLinea(v.getId(), 0).estaCompleta()){
						String color = jugadores[jugadorActual].getColor();
						
						if(color.equals("rojo")){
							iv.setImageResource(R.drawable.linearojo);
						} else if(color.equals("azul")){
							iv.setImageResource(R.drawable.lineaazul);
						} else if(color.equals("verde")){
							iv.setImageResource(R.drawable.lineaverde);
						} else{
							iv.setImageResource(R.drawable.lineaamarillo);
						}
						
						tablero.getLinea(v.getId(), 0).completar(jugadorActual);
						
						int[] cuadradosAfectados = tablero.cuadradosAfectadosPorLinea(v.getId(),  0);
						
						for (int i = 0; i < cuadradosAfectados.length; i++){
							if (tablero.getCuadrado(cuadradosAfectados[i]).estaCompleto()){
								tablero.getCuadrado(cuadradosAfectados[i]).completoPor(jugadorActual);
								contador.anotar(jugadorActual);
								conseguidoPunto = true;
							}
						}
						
						if (!conseguidoPunto)
							pasaTurno();
					}
				}
			});
		} 
		if (tipo == 1) {
			iv.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					boolean conseguidoPunto = false;
					ImageView iv = (ImageView) findViewById(v.getId());
					
					if(!tablero.getLinea(v.getId(), 1).estaCompleta()){
						String color = jugadores[jugadorActual].getColor();
						
						if(color.equals("rojo")){
							iv.setImageResource(R.drawable.linearojov);
						} else if(color.equals("azul")){
							iv.setImageResource(R.drawable.lineaazulv);
						} else if(color.equals("verde")){
							iv.setImageResource(R.drawable.lineaverdev);
						} else{
							iv.setImageResource(R.drawable.lineaamarillov);
						}
						
						tablero.getLinea(v.getId(), 1).completar(jugadorActual);
						
						int[] cuadradosAfectados = tablero.cuadradosAfectadosPorLinea(v.getId(),  1);
						
						for (int i = 0; i < cuadradosAfectados.length; i++){
							if (tablero.getCuadrado(cuadradosAfectados[i]).estaCompleto()){
								tablero.getCuadrado(cuadradosAfectados[i]).completoPor(jugadorActual);
								contador.anotar(jugadorActual);
								conseguidoPunto = true;
							}
						}
						
						if (!conseguidoPunto)
							pasaTurno();
					}
				}
			});
		}
	}
	
	
	protected void iniciarPartida() {
		jugadorActual = 0;
		contador = new Contador(numJugadores);
		
//		while (!partidaAcabada()){
//			System.out.println("turno jugador " + jugadorActual);
//			turnoJugador();
//		}
//
//		mostrarPuntuaciones(true);
	}
	
	protected void pausar() {
		pausa = true;
	}
	
	protected void reanudar() {
		pausa = false;
	}
	
	protected int pasaTurno() {
		jugadorActual++;
		if (jugadorActual == numJugadores)
			jugadorActual = 0;
		return jugadorActual;
	}
	
	protected boolean partidaAcabada () {
		for (int i = 0; i < tablero.getNumCuadrados(); i++){
			if (!tablero.getCuadrado(i).estaCompleto())
				return false;
		}
		return true;
	}
	
	protected void turnoJugador() {
		boolean conseguidoPunto = false;
		
		int[] l = jugadores[jugadorActual].ponerLinea(tablero);
		
		while (tablero.getLinea(l[0],  l[1]).estaCompleta()){
			l = jugadores[jugadorActual].ponerLinea(tablero);
		}
		
		tablero.getLinea(l[0],  l[1]).completar(jugadorActual);
		
		//Aquí hay que mirar si la línea completa algún cuadrado, sólo hay que mirar si hay algún
		//	cuadrado que toque completo, no podía estar completo antes, ya que le faltaría esta línea.
		
		int[] cuadradosAfectados = tablero.cuadradosAfectadosPorLinea(l[0],  l[1]);
		
		for (int i = 0; i < cuadradosAfectados.length; i++){
			if (tablero.getCuadrado(cuadradosAfectados[i]).estaCompleto()){
				tablero.getCuadrado(cuadradosAfectados[i]).completoPor(jugadorActual);
				contador.anotar(jugadorActual);
				conseguidoPunto = true;
			}
		}
		
		if (!conseguidoPunto)
			pasaTurno();
	}
	
	public void mostrarPuntuaciones(boolean acabada) {
		for (int i = 0; i < numJugadores; i++)
			System.out.println("El jugador " + i + " lleva " + contador.puntuacionJugador(i) + " puntos.");
		
		if (acabada) {
			int jugMax = 0;
			int puntMax = contador.puntuacionJugador(0);
			boolean empate = false;
			
			for (int i = 1; i < numJugadores; i++) {
				if (contador.puntuacionJugador(i) > puntMax) {
					puntMax = contador.puntuacionJugador(i);
					jugMax = i;
					empate = false;
				} else if (contador.puntuacionJugador(i) == puntMax) {
					empate = true;
				}
			}
			
			if (empate)
				System.out.println("La partida ha acabado en empate.");
			else 
				System.out.println("El jugador " + jugMax + " es el ganador con " + puntMax + " puntos.");
		}
	}
}
