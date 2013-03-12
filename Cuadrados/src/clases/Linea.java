package clases;

public class Linea {

	int jugador;
	int id;
	
	public Linea (int id) {
		jugador = -1;
		this.id = id;
	}
	
	public boolean completar (int jugador) {
		if (!estaCompleta()){
			this.jugador = jugador;
			return true;
		}
		else 
			return false;
	}
	
	public boolean estaCompleta () {
		return jugador != -1;
	}
	
	public int completaPor(){
		return jugador;
	}
	
	public void resetear(){
		jugador = -1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
