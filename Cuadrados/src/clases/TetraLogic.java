package clases;

public class TetraLogic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Jugador j1 = new Jugador(1, "rojo");
		Jugador j2 = new Jugador(2, "azul");
		Jugador[] j = {j1, j2};
		
		Partida p = new Partida(j, 2, 1);
		
		p.iniciarPartida();
		
//		Jugador j = new Jugador(1, "rojo");
//		Tablero t = new Tablero(6, 6);
//		
//		j.ponerLinea(t);
//		
//		System.out.println(t.getLinea(1, 1).estaCompleta());
//		System.out.println(t.getLinea(2, 0).estaCompleta());
		
		
////		Pruebas linea
//		Linea l = new Linea();
//		System.out.println(l.estaCompleta());
//		System.out.println(l.completar(2));
//		System.out.println(l.completaPor());
//		System.out.println(l.completar(1));
//		System.out.println(l.completaPor());
//		System.out.println(l.estaCompleta());
//		l.resetear();
//		System.out.println(l.estaCompleta());
//		System.out.println(l.completaPor());
		
//		Pruebas contador
//		// TODO Auto-generated method stub
//		int jugadores = 2;
//		Contador c = new Contador(jugadores);
//		
//		c.anotar(1);
//		c.anotar(0);
//		c.anotar(1);
//		
//		System.out.println(c.puntuacionJugador(1));
//		
//		c.resetear();
//
//		System.out.println(c.puntuacionJugador(1));
	}

}
