package com.example.cuadrados;

import android.app.Application;

public class Cuadrados extends Application {

	private boolean sound = true;

	public boolean sound() {
		return sound;
	}

	public void setSound(boolean s) {
		sound = s;
	}
}
