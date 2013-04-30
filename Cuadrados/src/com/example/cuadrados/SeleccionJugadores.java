package com.example.cuadrados;

import java.util.ArrayList;

import clases.Jugador;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

public class SeleccionJugadores extends Activity {

	private final static int[] avatares = { R.drawable.avatar00,
			R.drawable.avatar01, R.drawable.avatar02, R.drawable.avatar03,
			R.drawable.avatar04, R.drawable.avatar05, R.drawable.avatar06,
			R.drawable.avatar07, R.drawable.avatar08, R.drawable.avatardr,
			R.drawable.avatarsp };

	private int avatar1;
	private int avatar2;
	private int avatar3;
	private int avatar4;
	private int activos;
	private int rButSel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		avatar1 = 1;
		avatar2 = 2;
		avatar3 = 0;
		avatar4 = 0;
		
		activos = 2;
		rButSel = 4;

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.seleccion);

		ImageView ivJug1 = (ImageView) findViewById(R.id.ImageViewjug1);
		setImage(ivJug1, avatar1);
		ImageView ivJug2 = (ImageView) findViewById(R.id.ImageViewjug2);
		setImage(ivJug2, avatar2);
		ImageView ivJug3 = (ImageView) findViewById(R.id.ImageViewjug3);
		setImage(ivJug3, avatar3);
		ImageView ivJug4 = (ImageView) findViewById(R.id.imageViewjug4);
		setImage(ivJug4, avatar4);
		EditText et3 = (EditText) findViewById(R.id.EditTextjug3);
		et3.setEnabled(false);
		et3.setBackgroundResource(R.drawable.cuadradobase);
		EditText et4 = (EditText) findViewById(R.id.EditTextjug4);
		et4.setEnabled(false);
		et4.setBackgroundResource(R.drawable.cuadradobase);
		
		Cuadrados application = (Cuadrados) getApplicationContext();

		if (application.soundImg()) {
			if (application.getPlayer() == null) {
				application.startSound(R.raw.sonidofondo);
			} else {
				application.resumeSound();
			}
		}
		
		Button btJug1l = (Button) findViewById(R.id.buttonjug1l);
		btJug1l.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				avatar1--;
				EditText et1 = (EditText) findViewById(R.id.EditTextjug1);
				if(activos>2){
					if(avatar1==0){
						et1.setEnabled(false);
						et1.setBackgroundResource(R.drawable.cuadradobase);
						activos--;
					}
				} else{
					if(avatar1==0){
						avatar1 = avatares.length-1;
					}
				}
				if(avatar1==-1){
					avatar1=avatares.length-1;
					et1.setEnabled(true);
					et1.setBackgroundColor(getResources().getColor(R.color.WHITE));
					activos++;
				}
				ImageView ivJug1 = (ImageView) findViewById(R.id.ImageViewjug1);
				setImage(ivJug1, avatar1);
				System.out.println(activos);
			}
		});
		
		Button btJug2l = (Button) findViewById(R.id.buttonjug2l);
		btJug2l.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				avatar2--;
				EditText et2 = (EditText) findViewById(R.id.EditTextjug2);
				if(activos>2){
					if(avatar2==0){
						et2.setEnabled(false);
						et2.setBackgroundResource(R.drawable.cuadradobase);
						activos--;
					}
				} else{
					if(avatar2==0){
						avatar2 = avatares.length-1;
					}
				}
				if(avatar2==-1){
					avatar2=avatares.length-1;
					et2.setEnabled(true);
					et2.setBackgroundColor(getResources().getColor(R.color.WHITE));
					activos++;
				}
				ImageView ivJug2 = (ImageView) findViewById(R.id.ImageViewjug2);
				setImage(ivJug2, avatar2);
				System.out.println(activos);
			}
		});
		
		Button btJug3l = (Button) findViewById(R.id.buttonjug3l);
		btJug3l.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				avatar3--;
				EditText et3 = (EditText) findViewById(R.id.EditTextjug3);
				if(activos>2){
					if(avatar3==0){
						et3.setEnabled(false);
						et3.setBackgroundResource(R.drawable.cuadradobase);
						activos--;
					}
				} else{
					if(avatar3==0){
						avatar3 = avatares.length-1;
					}
				}
				if(avatar3==-1){
					avatar3=avatares.length-1;
					et3.setEnabled(true);
					et3.setBackgroundColor(getResources().getColor(R.color.WHITE));
					activos++;
				}
				ImageView ivJug3 = (ImageView) findViewById(R.id.ImageViewjug3);
				setImage(ivJug3, avatar3);
				System.out.println(activos);
			}
		});
		
		Button btJug4l = (Button) findViewById(R.id.buttonjug4l);
		btJug4l.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				avatar4--;
				EditText et4 = (EditText) findViewById(R.id.EditTextjug4);
				if(activos>2){
					if(avatar4==0){
						et4.setEnabled(false);
						et4.setBackgroundResource(R.drawable.cuadradobase);
						activos--;
					}
				} else{
					if(avatar4==0){
						avatar4 = avatares.length-1;
					}
				}
				if(avatar4==-1){
					avatar4=avatares.length-1;
					et4.setEnabled(true);
					et4.setBackgroundColor(getResources().getColor(R.color.WHITE));
					activos++;
				}
				ImageView ivJug4 = (ImageView) findViewById(R.id.imageViewjug4);
				setImage(ivJug4, avatar4);
				System.out.println(activos);
			}
		});
		
		Button btJug1r = (Button) findViewById(R.id.buttonjug1r);
		btJug1r.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				avatar1++;
				boolean newA = true;
				if(avatar1==avatares.length){
					avatar1=0;
				}
				EditText et1 = (EditText) findViewById(R.id.EditTextjug1);
				if(activos>2){
					if(avatar1==0){
						et1.setEnabled(false);
						et1.setBackgroundResource(R.drawable.cuadradobase);
						activos--;
					}
				} else{
					if(avatar1==0){
						avatar1 = 1;
						newA = false;
					}
				}
				if(avatar1==1){
					et1.setEnabled(true);
					et1.setBackgroundColor(getResources().getColor(R.color.WHITE));
					if(newA){
						activos++;
					}
				}
				ImageView ivJug1 = (ImageView) findViewById(R.id.ImageViewjug1);
				setImage(ivJug1, avatar1);
				System.out.println(activos);
			}
		});
		
		Button btJug2r = (Button) findViewById(R.id.buttonjug2r);
		btJug2r.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				avatar2++;
				boolean newA = true;
				if(avatar2==avatares.length){
					avatar2=0;
				}
				EditText et2 = (EditText) findViewById(R.id.EditTextjug2);
				if(activos>2){
					if(avatar2==0){
						et2.setEnabled(false);
						et2.setBackgroundResource(R.drawable.cuadradobase);
						activos--;
					}
				} else{
					if(avatar2==0){
						avatar2 = 1;
						newA = false;
					}
				}
				if(avatar2==1){
					et2.setEnabled(true);
					et2.setBackgroundColor(getResources().getColor(R.color.WHITE));
					if(newA){
						activos++;
					}
				}
				ImageView ivJug2 = (ImageView) findViewById(R.id.ImageViewjug2);
				setImage(ivJug2, avatar2);
				System.out.println(activos);
			}
		});
		
		Button btJug3r = (Button) findViewById(R.id.buttonjug3r);
		btJug3r.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				avatar3++;
				boolean newA = true;
				if(avatar3==avatares.length){
					avatar3=0;
				}
				EditText et3 = (EditText) findViewById(R.id.EditTextjug3);
				if(activos>2){
					if(avatar3==0){
						et3.setEnabled(false);
						et3.setBackgroundResource(R.drawable.cuadradobase);
						activos--;
					}
				} else{
					if(avatar3==0){
						avatar3 = 1;
						newA = false;
					}
				}
				if(avatar3==1){
					et3.setEnabled(true);
					et3.setBackgroundColor(getResources().getColor(R.color.WHITE));
					if(newA){
						activos++;
					}
				}
				ImageView ivJug3 = (ImageView) findViewById(R.id.ImageViewjug3);
				setImage(ivJug3, avatar3);
				System.out.println(activos);
			}
		});
		
		Button btJug4r = (Button) findViewById(R.id.buttonjug4r);
		btJug4r.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				avatar4++;
				boolean newA = true;
				if(avatar4==avatares.length){
					avatar4=0;
				}
				EditText et4 = (EditText) findViewById(R.id.EditTextjug4);
				if(activos>2){
					if(avatar4==0){
						et4.setEnabled(false);
						et4.setBackgroundResource(R.drawable.cuadradobase);
						activos--;
					}
				} else{
					if(avatar4==0){
						avatar4 = 1;
						newA = false;
					}
				}
				if(avatar4==1){
					et4.setEnabled(true);
					et4.setBackgroundColor(getResources().getColor(R.color.WHITE));
					if(newA){
						activos++;
					}
				}
				ImageView ivJug4 = (ImageView) findViewById(R.id.imageViewjug4);
				setImage(ivJug4, avatar4);
				System.out.println(activos);
			}
		});
		
		RadioButton rb4 = (RadioButton) findViewById(R.id.radioButton4x4);
		rb4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				rButSel = 4;
				setTamanioElegido();
			}
		});
		RadioButton rb5 = (RadioButton) findViewById(R.id.radioButton5x5);
		rb5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				rButSel = 5;
				setTamanioElegido();
			}
		});
		RadioButton rb6 = (RadioButton) findViewById(R.id.radioButton6x6);
		rb6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				rButSel = 6;
				setTamanioElegido();
			}
		});
		
		
		Button btPlay = (Button) findViewById(R.id.botonJugarPartida);
		btPlay.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent inten = new Intent(getApplicationContext(),
						PantallaPartida.class);
				ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
				
				int i = 1;
				Jugador j = null;
				String text = "";
				
				EditText et1 = (EditText) findViewById(R.id.EditTextjug1);
				if(et1.isEnabled()){
					if(et1.getText().toString().trim().equals("")){
						text = "PJ"+i;
					}else{
						text = et1.getText().toString();
					}
					j = new Jugador(i, "azul", text, avatares[avatar1]);
					jugadores.add(j);
					i++;
				}
				EditText et2 = (EditText) findViewById(R.id.EditTextjug2);
				if(et2.isEnabled()){
					if(et2.getText().toString().trim().equals("")){
						text = "PJ"+i;
					}else{
						text = et2.getText().toString();
					}
					j = new Jugador(i, "rojo", text, avatares[avatar2]);
					jugadores.add(j);
					i++;
				}
				EditText et3 = (EditText) findViewById(R.id.EditTextjug3);
				if(et3.isEnabled()){
					if(et3.getText().toString().trim().equals("")){
						text = "PJ"+i;
					}else{
						text = et3.getText().toString();
					}
					j = new Jugador(i, "verde", text, avatares[avatar3]);
					jugadores.add(j);
					i++;
				}
				EditText et4 = (EditText) findViewById(R.id.EditTextjug4);
				if(et4.isEnabled()){
					if(et4.getText().toString().trim().equals("")){
						text = "PJ"+i;
					}else{
						text = et4.getText().toString();
					}
					j = new Jugador(i, "amarillo", text, avatares[avatar4]);
					jugadores.add(j);
					i++;
				}
				
				inten.putParcelableArrayListExtra("jugadores", jugadores);
				inten.putExtra("tamanio", rButSel);
				startActivity(inten);
				finish();
			}
		});
		
		Button btAtras = (Button) findViewById(R.id.botonAtras);
		btAtras.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setResult(RESULT_OK);
				finish();
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
	
	protected void setImage(ImageView iv, int pos){
		iv.setBackgroundResource(avatares[pos]);
	}
	
	protected void setTamanioElegido(){
		RadioButton rb4 = (RadioButton) findViewById(R.id.radioButton4x4);
		RadioButton rb5 = (RadioButton) findViewById(R.id.radioButton5x5);
		RadioButton rb6 = (RadioButton) findViewById(R.id.radioButton6x6);
		if(rButSel==4){
			rb4.setChecked(true);
			rb5.setChecked(false);
			rb6.setChecked(false);
		}
		if(rButSel==5){
			rb4.setChecked(false);
			rb5.setChecked(true);
			rb6.setChecked(false);
		}
		if(rButSel==6){
			rb4.setChecked(false);
			rb5.setChecked(false);
			rb6.setChecked(true);
		}
	}
}
