package clases;

public class Cuadrado {
	
	int jugador;
	Linea l1, l2, l3, l4;
	int id;
	
	public Cuadrado(int id, Linea l1, Linea l2, Linea l3, Linea l4){
		this.id = id;
		jugador = -1;
		this.l1 = l1;
		this.l2 = l2;
		this.l3 = l3;
		this.l4 = l4;
	}
	
	protected void resetear(){
		jugador = -1;
	}
	
	public boolean estaCompleto(){
		return l1.estaCompleta() && l2.estaCompleta() && l3.estaCompleta() && l4.estaCompleta();
	}
	
	public int lineasCompletas(){
		int numCompletas = 0;
		if (l1.estaCompleta())
			numCompletas++;
		if (l2.estaCompleta())
			numCompletas++;
		if (l3.estaCompleta())
			numCompletas++;
		if (l4.estaCompleta())
			numCompletas++;
		
		return numCompletas;
	}
	
	public boolean completoPor (int jugador){
		if (!estaCompleto()){
			this.jugador = jugador;
			return true;
		}
		else
			return false;
	}
}
