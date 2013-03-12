package clases;

public class Contador {
	
	int [] puntuaciones;
	
	public Contador(int jugadores){
		this.puntuaciones = new int[jugadores];
	}
	
	public void anotar (int jugador){
		puntuaciones[jugador]++;
	}
	
	public int puntuacionJugador(int jugador){
		return puntuaciones[jugador];
	}
	
	public int[] devolverPuntuaciones(){
		return puntuaciones;
	}
	
	public void resetear(){
		for (int i=0; i<puntuaciones.length; i++)
			puntuaciones[i] = 0;
	}
}
