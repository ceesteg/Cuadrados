package com.example.cuadrados;

import clases.Contador;
import clases.Jugador;
import clases.Tablero;
import android.app.Activity;
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
		Jugador[] j = { j1, j2, j3, j4 };
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

		ImageView lh0 = new ImageView(getApplicationContext());
		lh0.setImageResource(R.drawable.lineabase);
		lh0.setId(this.tablero.getLinea(lineasHorizontales, 0).getId());
		lineasHorizontales++;
		ponerLinea(lh0, 0);

		LayoutParams lplh0 = new LayoutParams(heightLine, widthLine);
		lplh0.setMargins(marginLeft, marginTop, 0, 0);
		fl.addView(lh0, lplh0);
		marginLeft = widthLine;

		for (int i = 1; i < num; i++) {
			ImageView lh = new ImageView(getApplicationContext());
			lh.setImageResource(R.drawable.lineabase);
			lh.setId(this.tablero.getLinea(lineasHorizontales, 0).getId());
			lineasHorizontales++;
			ponerLinea(lh, 0);

			LayoutParams lplh = new LayoutParams(heightLine, widthLine);
			lplh.setMargins(marginLeft, marginTop, 0, 0);
			fl.addView(lh, lplh);
		}
		fp.addView(fl);

		for (int i = 0; i < num; i++) {
			marginTop = widthLine * (i + 1) + heightLine * i + marginT;
			marginLeft = marginLat;

			LinearLayout fl1 = new LinearLayout(getApplicationContext());
			fl1.setOrientation(LinearLayout.HORIZONTAL);
			fl1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 1));

			ImageView lv1 = new ImageView(getApplicationContext());
			lv1.setImageResource(R.drawable.lineabasev);
			lv1.setId(this.tablero.getLinea(lineasVerticales, 1).getId());
			lineasVerticales++;
			ponerLinea(lv1, 1);

			LayoutParams lplv1 = new LayoutParams(widthLine, heightLine);
			lplv1.setMargins(marginLeft, marginTop, 0, 0);
			fl1.addView(lv1, lplv1);

			for (int n = 1; n < num + 1; n++) {
				ImageView ic = new ImageView(getApplicationContext());
				ic.setImageResource(R.drawable.cuadradobase);
				ic.setId(tablero.getCuadrado(cuadrados).getId());
				cuadrados++;
				LayoutParams lpC = new LayoutParams(heightLine, heightLine);
				lpC.setMargins(0, marginTop, 0, 0);
				fl1.addView(ic, lpC);

				ImageView lv = new ImageView(getApplicationContext());
				lv.setImageResource(R.drawable.lineabasev);
				lv.setId(this.tablero.getLinea(lineasVerticales, 1).getId());
				lineasVerticales++;
				ponerLinea(lv, 1);

				LayoutParams lplv = new LayoutParams(widthLine, heightLine);
				lplv.setMargins(0, marginTop, 0, 0);
				fl1.addView(lv, lplv);
			}
			fp.addView(fl1);

			marginTop = heightLine * (i + 1) + widthLine * (i + 1) + marginT;
			marginLeft = widthLine + marginLat;

			LinearLayout fl2 = new LinearLayout(getApplicationContext());
			fl2.setOrientation(LinearLayout.HORIZONTAL);
			fl2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 1));

			ImageView lh1 = new ImageView(getApplicationContext());
			lh1.setImageResource(R.drawable.lineabase);
			lh1.setId(this.tablero.getLinea(lineasHorizontales, 0).getId());
			lineasHorizontales++;
			ponerLinea(lh1, 0);

			LayoutParams lplh1 = new LayoutParams(heightLine, widthLine);
			lplh1.setMargins(marginLeft, marginTop, 0, 0);
			fl2.addView(lh1, 0, lplh1);
			marginLeft = widthLine;

			for (int m = 1; m < num; m++) {
				ImageView lh = new ImageView(getApplicationContext());
				lh.setImageResource(R.drawable.lineabase);
				lh.setId(this.tablero.getLinea(lineasHorizontales, 0).getId());
				lineasHorizontales++;
				ponerLinea(lh, 0);

				LayoutParams lplh = new LayoutParams(heightLine, widthLine);
				lplh.setMargins(marginLeft, marginTop, 0, 0);
				fl2.addView(lh, lplh);
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
		LinearLayout fl3 = new LinearLayout(getApplicationContext());
		fl3.setOrientation(LinearLayout.VERTICAL);
		fl3.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 1));

		for (int i = 0; i < this.numJugadores; i++) {
			TextView tv = new TextView(getApplicationContext());
			tv.setId(this.jugadores[i].getId() * 1111);
			tv.setText("0");
			imagenMarcador(tv, this.jugadores[i].getColor());
			tv.setTextColor(Color.LTGRAY);
			tv.setTextSize(heightLine * 50 / 100);
			tv.setGravity(Gravity.CENTER);
			LayoutParams lpMar = new LayoutParams(widthMarcador, heightMarcador);
			if (i % 2 == 0) {
				lpMar.setMargins(marginLatMarcador, marginBetMarcs,
						marginLatMarcador, 0);
				tv.setLayoutParams(lpMar);
				fl2.addView(tv);
			} else {
				lpMar.setMargins(marginRightMarcs, marginBetMarcs,
						marginLatMarcador, 0);
				tv.setLayoutParams(lpMar);
				fl3.addView(tv);
			}
		}
		fp.addView(fl2);
		fp.addView(fl3);

		
		// Botón salir partida

		int widthBtExit = width / 10;
		int marginButExit = width * 2 / 100;
		int marginTopButExit = height - widthBtExit - marginButExit;
		int marginLeftButExit = width - widthBtExit - marginButExit;

		Button btSalirPartida = (Button) findViewById(R.id.botonsalirpartida);
		RelativeLayout.LayoutParams lpBtExit = new RelativeLayout.LayoutParams(widthBtExit, widthBtExit);
		lpBtExit.setMargins(marginLeftButExit, marginTopButExit, marginButExit, marginButExit);
		btSalirPartida.setLayoutParams(lpBtExit);
	}

	private void ponerLinea(ImageView iv, final int tipo) {
		iv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				boolean conseguidoPunto = false;
				ImageView iv = (ImageView) findViewById(v.getId());
				int li = v.getId();

				if (tipo == 1) {
					li = li - 30;
				}

				if (!tablero.getLinea(li, tipo).estaCompleta()) {
					String color = jugadores[jugadorActual].getColor();

					cambiarColor(iv, color, tipo);

					tablero.getLinea(li, tipo).completar(jugadorActual);

					int[] cuadradosAfectados = tablero
							.cuadradosAfectadosPorLinea(li, tipo);

					for (int i = 0; i < cuadradosAfectados.length; i++) {
						if (tablero.getCuadrado(cuadradosAfectados[i])
								.estaCompleto()) {
							tablero.getCuadrado(cuadradosAfectados[i])
									.completoPor(jugadorActual);
							ImageView cuad = (ImageView) findViewById(tablero
									.getCuadrado(cuadradosAfectados[i]).getId());
							rellenarCuadrado(cuad, color);
							TextView marc = (TextView) findViewById(jugadores[jugadorActual]
									.getId() * 1111);
							sumarUnoMarcador(marc);
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

	private void cambiarColor(ImageView iv, String color, int tipo) {
		if (tipo == 0) {
			if (color.equals("rojo")) {
				iv.setImageResource(R.drawable.linearojo);
			} else if (color.equals("azul")) {
				iv.setImageResource(R.drawable.lineaazul);
			} else if (color.equals("verde")) {
				iv.setImageResource(R.drawable.lineaverde);
			} else {
				iv.setImageResource(R.drawable.lineaamarillo);
			}
		} else {
			if (color.equals("rojo")) {
				iv.setImageResource(R.drawable.linearojov);
			} else if (color.equals("azul")) {
				iv.setImageResource(R.drawable.lineaazulv);
			} else if (color.equals("verde")) {
				iv.setImageResource(R.drawable.lineaverdev);
			} else {
				iv.setImageResource(R.drawable.lineaamarillov);
			}
		}
	}

	private void rellenarCuadrado(ImageView iv, String color) {
		if (color.equals("rojo")) {
			iv.setImageResource(R.drawable.cuadradorojo);
		} else if (color.equals("azul")) {
			iv.setImageResource(R.drawable.cuadradoazul);
		} else if (color.equals("verde")) {
			iv.setImageResource(R.drawable.cuadradoverde);
		} else {
			iv.setImageResource(R.drawable.cuadradoamarillo);
		}
	}

	private void imagenMarcador(TextView tv, String color) {
		if (color.equals("rojo")) {
			tv.setBackgroundResource(R.drawable.linearojo);
		} else if (color.equals("azul")) {
			tv.setBackgroundResource(R.drawable.lineaazul);
		} else if (color.equals("verde")) {
			tv.setBackgroundResource(R.drawable.lineaverde);
		} else {
			tv.setBackgroundResource(R.drawable.lineaamarillo);
		}
	}

	private void sumarUnoMarcador(TextView tv) {
		int aux = Integer.parseInt(tv.getText().toString()) + 1;
		System.out.println(aux);
		tv.setText(String.valueOf(aux));
	}

	protected void iniciarPartida() {
		jugadorActual = 0;
		contador = new Contador(numJugadores);

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

	protected boolean partidaAcabada() {
		for (int i = 0; i < tablero.getNumCuadrados(); i++) {
			if (!tablero.getCuadrado(i).estaCompleto())
				return false;
		}
		return true;
	}

	protected void turnoJugador() {
		boolean conseguidoPunto = false;

		int[] l = jugadores[jugadorActual].ponerLinea(tablero);

		while (tablero.getLinea(l[0], l[1]).estaCompleta()) {
			l = jugadores[jugadorActual].ponerLinea(tablero);
		}

		tablero.getLinea(l[0], l[1]).completar(jugadorActual);

		// Aquí hay que mirar si la línea completa algún cuadrado, sólo hay que
		// mirar si hay algún
		// cuadrado que toque completo, no podía estar completo antes, ya que le
		// faltaría esta línea.

		int[] cuadradosAfectados = tablero.cuadradosAfectadosPorLinea(l[0],
				l[1]);

		for (int i = 0; i < cuadradosAfectados.length; i++) {
			if (tablero.getCuadrado(cuadradosAfectados[i]).estaCompleto()) {
				tablero.getCuadrado(cuadradosAfectados[i]).completoPor(
						jugadorActual);
				contador.anotar(jugadorActual);
				conseguidoPunto = true;
			}
		}

		if (!conseguidoPunto)
			pasaTurno();
	}

	public void mostrarPuntuaciones(boolean acabada) {
		for (int i = 0; i < numJugadores; i++)
			System.out.println("El jugador " + i + " lleva "
					+ contador.puntuacionJugador(i) + " puntos.");

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
				System.out.println("El jugador " + jugMax
						+ " es el ganador con " + puntMax + " puntos.");
		}
	}
}
