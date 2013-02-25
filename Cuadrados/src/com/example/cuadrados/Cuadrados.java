package com.example.cuadrados;

import android.app.Application;
import android.media.MediaPlayer;

public class Cuadrados extends Application {

	private MediaPlayer mp;
	private boolean sound = true;

	public boolean soundImg() {
		return sound;
	}

	public void setSoundImg(boolean s) {
		sound = s;
	}
	
	public void startSound(int resId){
		mp = MediaPlayer.create((Cuadrados) getApplicationContext(), resId);
		mp.start();
	}
	
	public void resumeSound(){
		mp.start();
	} 
	
	public void pauseSound(){
		mp.pause();
	}
	
	public void stopSound(){
		mp.stop();
	}
}
