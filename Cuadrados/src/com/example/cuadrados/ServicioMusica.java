package com.example.cuadrados;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ServicioMusica extends Service {
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onCreate() {
		Cuadrados app = (Cuadrados) getApplicationContext();
		app.getPlayer().setLooping(true); 
	}

	@Override
	public void onDestroy() {
		Cuadrados app = (Cuadrados) getApplicationContext();
		app.getPlayer().pause();
	}
	
	@Override
	public void onStart(Intent intent, int startid) {
		Cuadrados app = (Cuadrados) getApplicationContext();
		app.getPlayer().start();
	}
}
