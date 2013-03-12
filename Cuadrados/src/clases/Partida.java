package clases;

public class Partida {

	Jugador[] jugadores;
	Tablero tablero;
	Contador contador;
	int numJugadores;
	int jugadorActual;
	boolean pausa;
	
	public Partida(Jugador[] jugadores, int numFilas, int numColumnas){
		this.jugadores = jugadores;
		this.numJugadores = jugadores.length;
		this.tablero = new Tablero(numFilas, numColumnas);

		pausa = false;
	}
	
	protected void iniciarPartida() {
		jugadorActual = 0;
		contador = new Contador(numJugadores);
		
		while (!partidaAcabada()){
			System.out.println("turno jugador " + jugadorActual);
			turnoJugador();
		}

		mostrarPuntuaciones(true);
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
