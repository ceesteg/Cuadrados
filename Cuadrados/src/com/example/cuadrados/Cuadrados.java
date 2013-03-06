package com.example.cuadrados;

import android.app.Application;
import android.content.Intent;
import android.media.MediaPlayer;

public class Cuadrados extends Application {

	private MediaPlayer player;
	private boolean sound = true;

	public MediaPlayer getPlayer() {
		return player;
	}

	public void setPlayer(int resId) {
		player = MediaPlayer.create(getApplicationContext(), resId);
	}

	public boolean soundImg() {
		return sound;
	}

	public void setSoundImg(boolean s) {
		sound = s;
	}
	
	public void startSound(int resId){
		setPlayer(resId);
		startService(new Intent(getApplicationContext(), ServicioMusica.class));
	}
	
	public void resumeSound(){
		startService(new Intent(getApplicationContext(), ServicioMusica.class));
	}
	
	public void stopSound(){
		stopService(new Intent(getApplicationContext(), ServicioMusica.class));
	}
}
