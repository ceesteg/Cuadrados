package com.example.cuadrados;

import java.util.ArrayList;

import clases.Contador;
import clases.Jugador;
import clases.Tablero;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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
	ArrayList<Jugador> jugadores;
	Contador contador;
	int numJugadores;
	int jugadorActual;
	int tamanio;
	boolean pausa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.partida);

		this.tamanio = getIntent().getExtras()
				.getInt("tamanio");
		this.tablero = new Tablero(this.tamanio, this.tamanio);

		ArrayList<Jugador> jugadores = getIntent().getExtras()
				.getParcelableArrayList("jugadores");

		this.jugadores = jugadores;
		this.numJugadores = this.jugadores.size();

		adaptLayout(tamanio);

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

		int heightLine = 0;
		int widthLine = 0;
		if (num % 2 == 1) {
			heightLine = height / (((5 * num + 5) / 10) + num);
			widthLine = heightLine * 5 / 10;
		} else {
			heightLine = height / (((6 * num + 6) / 10) + num);
			widthLine = heightLine * 5 / 10;
		}
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

		int marginLatMarcador = marginLat * 5 / 100;
		int widthMarcador = marginLat * 9 / 10;
		int heightMarcador = widthMarcador * 8 / 10;
		int marginBetMarcs = heightMarcador * 2 / 10;
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
			LinearLayout marc = new LinearLayout(getApplicationContext());
			marc.setId((i+1)*11111);
			marc.setOrientation(LinearLayout.HORIZONTAL);
			marc.setLayoutParams(new LayoutParams(widthMarcador,
					heightMarcador, 1));
			marc.setBackgroundResource(R.drawable.cuadradobase);
			LinearLayout nameCont = new LinearLayout(getApplicationContext());
			nameCont.setOrientation(LinearLayout.VERTICAL);
			nameCont.setLayoutParams(new LayoutParams(
					widthMarcador/2, heightMarcador, 1));

			TextView cont = new TextView(getApplicationContext());
			cont.setLayoutParams(new LayoutParams(widthMarcador/2, heightMarcador/2, 1));
			cont.setId(this.jugadores.get(i).getId() * 1111);
			cont.setTextSize(2 * heightMarcador / 10);
			cont.setText("0");
			cont.setTextColor(Color.BLACK);
			cont.setGravity(Gravity.CENTER);
			nameCont.addView(cont);

			TextView name = new TextView(getApplicationContext());
			name.setLayoutParams(new LayoutParams(widthMarcador/2, heightMarcador/2, 1));
			name.setText(this.jugadores.get(i).getNombre());
			name.setTextSize(13 * heightMarcador / 100);
			name.setTextColor(Color.BLACK);
			name.setGravity(Gravity.CENTER);
			nameCont.addView(name);

			ImageView avt = new ImageView(getApplicationContext());
			avt.setLayoutParams(new LayoutParams(widthMarcador / 2,
					heightMarcador));
			avt.setBackgroundResource(this.jugadores.get(i).getAvatar());

			LayoutParams lpMar = new LayoutParams(widthMarcador, heightMarcador);
			if (i % 2 == 0) {
				lpMar.setMargins(marginLatMarcador, marginBetMarcs,
						marginLatMarcador, 0);
				marc.setLayoutParams(lpMar);

				marc.addView(nameCont);
				marc.addView(avt);
				fl2.addView(marc);
			} else {
				lpMar.setMargins(marginRightMarcs, marginBetMarcs,
						marginLatMarcador, 0);
				marc.setLayoutParams(lpMar);

				marc.addView(avt);
				marc.addView(nameCont);
				fl3.addView(marc);
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
		RelativeLayout.LayoutParams lpBtExit = new RelativeLayout.LayoutParams(
				widthBtExit, widthBtExit);
		lpBtExit.setMargins(marginLeftButExit, marginTopButExit, marginButExit,
				marginButExit);
		btSalirPartida.setLayoutParams(lpBtExit);

		// Botón sonido partida

		int widthBtSonido = 7 * widthBtExit / 10;
		int marginButSonido = width * 2 / 100;
		int marginTopButSonido = marginTopButExit;

		Cuadrados application = (Cuadrados) getApplicationContext();

		Button btSonidoPartida = (Button) findViewById(R.id.botonsonidopartida);
		RelativeLayout.LayoutParams lpBtSonido = new RelativeLayout.LayoutParams(
				widthBtSonido, widthBtSonido);
		lpBtSonido.setMargins(marginButSonido, marginTopButSonido,
				marginButSonido, marginButSonido);
		btSonidoPartida.setLayoutParams(lpBtSonido);

		if (application.soundImg()) {
			if (application.getPlayer() == null) {
				application.startSound(R.raw.sonidofondo);
			} else {
				application.resumeSound();
			}
			btSonidoPartida.setBackgroundResource(R.drawable.consonido);
		} else {
			btSonidoPartida.setBackgroundResource(R.drawable.sinsonido);
		}

		btSonidoPartida.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Cuadrados application = (Cuadrados) getApplicationContext();
				Button bt = (Button) findViewById(R.id.botonsonidopartida);
				if (application.soundImg()) {
					application.stopSound();
					bt.setBackgroundResource(R.drawable.sinsonido);
					application.setSoundImg(false);
				} else {
					application.resumeSound();
					bt.setBackgroundResource(R.drawable.consonido);
					application.setSoundImg(true);
				}
			}
		});
	}

	private void ponerLinea(ImageView iv, final int tipo) {
		iv.setOnClickListener(new View.OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				boolean conseguidoPunto = false;
				ImageView iv = (ImageView) findViewById(v.getId());
				int li = v.getId();

				if (tipo == 1) {
					li = li - tamanio * (tamanio + 1);
				}

				if (!tablero.getLinea(li, tipo).estaCompleta()) {
					String color = jugadores.get(jugadorActual).getColor();

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
							TextView marc = (TextView) findViewById(jugadores
									.get(jugadorActual).getId() * 1111);
							sumarUnoMarcador(marc);
							contador.anotar(jugadorActual);
							conseguidoPunto = true;
						}
					}
					if (!partidaAcabada()) {
						if (!conseguidoPunto)
							pasaTurno();
					} else {
						
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

						RelativeLayout fp = (RelativeLayout) findViewById(R.id.fondopartida);
						Display display = getWindowManager().getDefaultDisplay();
						int width = (int) (display.getWidth());
						int height = (int) (display.getHeight());
						
						int btS = height/5;
						int btMl2 = width*333/1000;
						int btMt = height*2/3;
						int btMl1 = width-btS-btS-btMl2-btMl2;
						
						LinearLayout winBack = new LinearLayout(getApplicationContext());
						winBack.setOrientation(LinearLayout.VERTICAL);
						winBack.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
								LayoutParams.MATCH_PARENT));
						winBack.setBackgroundResource(R.drawable.fondofin);
						
						LinearLayout txts = new LinearLayout(getApplicationContext());
						txts.setOrientation(LinearLayout.VERTICAL);
						txts.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, btMt));

						TextView tit = new TextView(getApplicationContext());
						if (empate){
							tit.setText(getString(R.string.empate1)+" "+puntMax+" "+getString(R.string.puntos));
						}else{
							tit.setText(getString(R.string.ganador1)+" "+jugadores.get(jugMax).getNombre()+" "+
									getString(R.string.ganador2)+" "+puntMax+" "+getString(R.string.puntos));
						}
						tit.setTextSize(height * 5 / 100);
						tit.setTextColor(Color.BLACK);
						tit.setGravity(Gravity.CENTER);
						tit.setTypeface(null, Typeface.BOLD);
						LayoutParams titL = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
						titL.setMargins(7*width/100, 1*height/10, 7*width/100, 0);
						txts.addView(tit, titL);
						
						TextView tit2 = new TextView(getApplicationContext());
						tit2.setText(R.string.jugardenuevo);
						tit2.setTextSize(height * 5 / 100);
						tit2.setTextColor(Color.BLACK);
						tit2.setGravity(Gravity.CENTER);
						tit2.setTypeface(null, Typeface.BOLD);
						LayoutParams tit2L = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
						tit2L.setMargins(7*width/100, 15*height/100, 7*width/100, 0);
						txts.addView(tit2, tit2L);
						
						winBack.addView(txts);
						
						LinearLayout buts = new LinearLayout(getApplicationContext());
						buts.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, height-btMt));
						
						Button btSi = new Button(getApplicationContext());
						btSi.setBackgroundResource(R.drawable.accepted);
						LayoutParams lpbS = new LayoutParams(btS, btS);
						lpbS.setMargins(btMl2, 0, 0, 0);
						buts.addView(btSi, lpbS);
						
						btSi.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								Intent inten = new Intent(getApplicationContext(),
										PantallaPartida.class);
								inten.putParcelableArrayListExtra("jugadores", jugadores);
								inten.putExtra("tamanio", tamanio);
								startActivity(inten);
								finish();
							}
						});						
							
						Button btNo = new Button(getApplicationContext());
						btNo.setBackgroundResource(R.drawable.cancel);
						LayoutParams lpbN = new LayoutParams(btS, btS);
						lpbN.setMargins(btMl1, 0, btS, 0);
						buts.addView(btNo, lpbN);
						
						btNo.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								setResult(RESULT_OK);
								finish();
							}
						});
						
						winBack.addView(buts);
						fp.addView(winBack);
						
						Button btSalirPartida = (Button) findViewById(R.id.botonsalirpartida);
						btSalirPartida.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
							}
						});
						
						Button btSonidoPartida = (Button) findViewById(R.id.botonsonidopartida);
						btSonidoPartida.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
							}
						});
					}
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

	private void fondoActivo(LinearLayout ll, String color) {
		if (color.equals("rojo")) {
			ll.setBackgroundResource(R.drawable.cuadradorojoactivo);
		} else if (color.equals("azul")) {
			ll.setBackgroundResource(R.drawable.cuadradoazulactivo);
		} else if (color.equals("verde")) {
			ll.setBackgroundResource(R.drawable.cuadradoverdeactivo);
		} else {
			ll.setBackgroundResource(R.drawable.cuadradoamarilloactivo);
		}
	}

	private void sumarUnoMarcador(TextView tv) {
		int aux = Integer.parseInt(tv.getText().toString()) + 1;
		System.out.println(aux);
		tv.setText(String.valueOf(aux));
	}

	protected void iniciarPartida() {
		jugadorActual = 0;
		LinearLayout marc = (LinearLayout) findViewById((jugadorActual+1)*11111);
		fondoActivo(marc, jugadores.get(jugadorActual).getColor());
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
		
		int jugAnterior = jugadorActual-1;
		
		if(jugadorActual==0){
			jugAnterior = numJugadores-1;
		}
		LinearLayout marc = (LinearLayout) findViewById((jugadorActual+1)*11111);
		fondoActivo(marc, jugadores.get(jugadorActual).getColor());
		
		LinearLayout ant = (LinearLayout) findViewById((jugAnterior+1)*11111);
		ant.setBackgroundResource(R.drawable.cuadradobase);
		
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

		int[] l = jugadores.get(jugadorActual).ponerLinea(tablero);

		while (tablero.getLinea(l[0], l[1]).estaCompleta()) {
			l = jugadores.get(jugadorActual).ponerLinea(tablero);
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
