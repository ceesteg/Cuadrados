package clases;
import java.util.Scanner;


public class Jugador {

	String nombre;
	String rutaFoto;
	String color;
	int id;
	
	public Jugador (int id, String color){
		this.id = id;
		this.color = color;
	}
	
	public Jugador (int id, String color, String nombre){
		this.id = id;
		this.color = color;
		this.nombre = nombre;
	}
	
	public Jugador (int id, String color, String nombre, String rutaFoto){
		this.id = id;
		this.color = color;
		this.nombre = nombre;
		this.rutaFoto = rutaFoto;
	}
	
	public void setColor(String color){
		this.color = color;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	public void setFoto(String rutaFoto){
		this.rutaFoto = rutaFoto;
	}
	
	public int getId(){
		return id;
	}
	
	public String getColor(){
		return color;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public String getFoto(){
		return rutaFoto;
	}
	
	public int[] ponerLinea(Tablero t){
		Scanner sc = new Scanner(System.in);
		boolean movimientoRealizado = false;
		Linea l = null;
		int x = -1, y = -1;
		
		while (!movimientoRealizado){
			System.out.println("Selecciona el número de línea.");	
			x = sc.nextInt();
			System.out.println("La línea es vertical u horizontal; 0 = horizontal; 1 = vertical");
			y = sc.nextInt();
			
			l = t.getLinea(x, y);
			if (!l.estaCompleta()){
				//Prefiero no completar la línea aquí, ya que un usuario malintencionado
				// podría trucar la aplicación y poner donde no debe...
				
				movimientoRealizado = true;
			}
		}
		
		int[] a = {x, y};
		return a;
	}
}
